/**
* The HeaterIdleState represents the state in a Climate Control system in
* which the heater is idle and the inside temperature is influenced by
* the outside temperature
*/
public class HeaterIdleState extends ClimateControlState implements FanOnListener,
		OffListener, AirConditionerOnListener, HeaterOnListener, 
		OutsideTimerRanOutListener {

	private int temperatureThreshold;
	private static HeaterIdleState instance;
	/**
	* Constructor is hidden to maintain singleton property
	*/
	private HeaterIdleState(){}
	/**
	* Return the single instance of HeaterIdleState if exists
	* else it is created and returned
	*/
	public static HeaterIdleState instance(){
		if (instance == null)
			instance = new HeaterIdleState();
		return instance;
	}
	/**
	* Set the temperature threshold in which the Heater turns back on
	*/
	public void setThreshold(int threshold){
		this.temperatureThreshold = threshold;
	}
	/**
	* Processes a FanOnEvent which causes the context to change the current
	* state where the fan is on
	*/
	public void processEvent(FanOnEvent event){
		this.context.changeCurrentState(FanState.instance());
	}
	/**
	* Processes an OffEvent which causes the context to change the
	* current state so that all appliances are off
	*/
	public void processEvent(OffEvent event){
		this.context.changeCurrentState(OffState.instance());
	}
	/**
	* Processes a HeaterOnEvent which causes the context to change the
	* current state where the heater is on
	*/
	public void processEvent(HeaterOnEvent event){
		this.context.changeCurrentState(HeaterOnState.instance());
	}
	/**
	* Processes an AirConditionerOnEvent which causes the context to
	* change the current state where the air conditioner is on
	*/
	public void processEvent(AirConditionerOnEvent event){
		this.context.changeCurrentState(AirConditionerOnState.instance());
	}
	/**
	* Processes an OutsideTimerRanOutEvent which notifies the Outside class
	* and checks if the temperature is still within the threshold.  If not,
	* the Heater is turned back on.
	* @param OutsideTimerRanOutEvent event The event that occurs when the
	* inside temperature has been changed by the outside temperature
	*/
	public void processEvent(OutsideTimerRanOutEvent event){
		Outside.instance().processEvent(event);
		if(outOfTemperatureThreshold())
			this.context.handleEvent(new HeaterOnEvent(this));
	}
	/** 
	* Set the outside class active and update display to reflect the 
	* current state 
	*/
	public void run(){
		Outside.instance().setActive(true);
		this.display.setHeaterIdle();
	}
	//Helper method to check if the temperature is within the threshold
	private boolean outOfTemperatureThreshold(){
		return -this.context.getTemperatureDifference() >=
				this.temperatureThreshold;
	}
}
