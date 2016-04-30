import java.util.*;
import java.io.*;
/**
* The ClimateControlContext class is used to facilitate a Climate Control
* System.  It maintains the system's current state, the temperature status
* and desired temperature, and acts as a switchboard for the system's events.
*/
public class ClimateControlContext {
	private int insideTemperature = 70; //Initial inside temp
	private int outsideTemperature = 70; //Initial outside temp
	private int desiredTemperature;
	private ClimateControlState currentState;
	private ClimateControlDisplay display;
	private static File configFile;
	private static ClimateControlContext instance;
	/**
	* Constructs a new ClimateControlContext object and initializes
	* its clock and assigns a GUI - private to maintain singleton property
	*/
	private ClimateControlContext(){
	}
	/**
	* Gets the singleton instance if exists, else creates a new singleton
	* of the context object.  Reads configuration data from disk.
	*/
	public static ClimateControlContext instance() {
		if (instance == null){
			instance = new ClimateControlContext();
		}
		return instance;
	}
	/**
	* Load the configuration data and set up initial state in GUI
	*/
	public void initialize(){
		System.out.println("Context initialized");
		try {
			this.setConfigValues();
		} catch (Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		this.display = ClimateControlGUI.instance();
		this.display.displayOutsideTemperature(this.outsideTemperature);
		this.display.displayInsideTemperature(this.insideTemperature);
		this.changeCurrentState(OffState.instance());
	}
	/**
	* Change the current state of the climate control system to the
	* new state provided
	* @param ClimateControlState nextState The next current state of
	* the system
	*/
	public void changeCurrentState(ClimateControlState nextState){
		this.currentState = nextState;
		this.currentState.run();
	}
	/**
	* Get the temperature inside the room
	* @return int The inside temperature in degrees
	*/
	public int getInsideTemperature(){
		return this.insideTemperature;
	}
	/**
	* Set the outside temperature
	* @param int temperature The outside temperature in degrees
	* @return int The temperature that was set
	*/
	public int setOutsideTemperature(int temperature){
		this.display.displayOutsideTemperature(temperature);
		return this.outsideTemperature = temperature;
	}
	/**
	* Get the temperature outside
	* @return int The outside temperature in degrees
	*/
	public int getOutsideTemperature(){
		return this.outsideTemperature;
	}
	/**
	* Get the absolute value of the temperature difference between the inside
	* temperature and the desired temperature
	* @return int The temperature difference in degrees
	*/
	public int getTemperatureDifference(){
		return this.insideTemperature - this.desiredTemperature;
	}
	/**
	* Check if the inside temperature has reached the desired temperature
	* @return boolean Returns true of the inside temperature equals
	* the desired temperature, false if not
	*/
	public boolean desiredTemperatureReached(){
		return this.getTemperatureDifference() == 0;
	}
	/**
	* Set the desired temperature inside the room
	* @param int temperature The desired temperature in degrees
	* @return int The temeperature that was set
	*/
	public int setDesiredTemperature(int temperature){
		this.display.displayDesiredTemperature(temperature);
		return this.desiredTemperature = temperature;
	}
	/**
	* Get the desired temperature inside
	* @return int The desired temperature in degrees
	*/
	public int getDesiredTemperature(){
		return this.desiredTemperature;
	}
	/**
	* Increase the inside temperature by one degree
	* @return int The new inside temperature in degrees
	*/
	public int incrementInsideTemperature(){
		return ++this.insideTemperature;
	}
	/**
	* Decrease the inside temperature by one degree
	* @return int The new inside temperature in degrees
	*/
	public int decrementInsideTemperature(){
		return --this.insideTemperature;
	}
	/**
	* Make the inside temperature approach the outside temperature by one degree
	*/
	public void approachOutsideTemperature(){
		if (this.outsideTemperature > this.insideTemperature)
			this.incrementInsideTemperature();	
		else if (this.outsideTemperature < this.insideTemperature)
			this.decrementInsideTemperature();
	}
	/**
	* Get the display object that reflects the state of the Climate Control system
	* @return ClimateControlDisplay The display object associated with the context
	*/
	public ClimateControlDisplay getDisplay(){
		return this.display;
	}

	public static void setConfigFile(File file){
		ClimateControlContext.configFile = file;
	}
	/**
	* Receive events from the climate control system and deliver to the current
	* state object
	* @ClimateControlEvent event The event received from the system
	*/
	public void handleEvent(ClimateControlEvent event){
		try {
			event.connectToListener((ClimateControlListener)currentState);
		} catch (ClassCastException cce){
			cce.printStackTrace();
		}
	}
	//Helper method to read config values from disk and set in application
	private void setConfigValues() throws Exception {
		try {
			Scanner scanner = new Scanner(ClimateControlContext.configFile);
			FanState.instance().setOnPeriod(
					Integer.valueOf(scanner.nextLine()));
			FanState.instance().setOffPeriod(
					Integer.valueOf(scanner.nextLine()));
			AirConditionerIdleState.instance().setThreshold(
					Integer.valueOf(scanner.nextLine()));
			HeaterIdleState.instance().setThreshold(
					Integer.valueOf(scanner.nextLine()));
			Outside.instance().setPeriod(
					Integer.valueOf(scanner.nextLine()));
			AirConditionerOnState.instance().setPeriod(
					Integer.valueOf(scanner.nextLine()));
			HeaterOnState.instance().setPeriod(
					Integer.valueOf(scanner.nextLine()));
		} catch (NoSuchElementException nsee) {
			throw new IllegalArgumentException("Invalid configuration file");
		} catch (IOException ioe){
			throw ioe;
		}
	}
}
