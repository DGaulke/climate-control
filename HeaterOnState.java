/**
* The HeaterOnState represents the state in a Climate Control system in
* which the inside temperature is raised by a heater
*/
public class HeaterOnState extends ClimateControlState implements OffListener, 
		FanOnListener, AirConditionerOnListener, HeaterIdleListener, 
		HeaterTimerRanOutListener {

	private int period;
	private static HeaterOnState instance;
	/**
	* Constructor is hidden to maintain singleton property
	*/
	private HeaterOnState(){}
	/**
	* Return the single instance of HeaterOnState if exists
	* else it is created and returned
	*/
	public static HeaterOnState instance(){
		if (instance == null)
			instance = new HeaterOnState();
		return instance;
	}
	/**
	* Set the period in hours that it takes for the heater to increase
	* the room temperature by one degree
	*/
	public void setPeriod(int period){
		this.period = period;
	}
	/**
	* Processes an OffEvent which causes the context to change the current
	* state so that all appliances are off
	* @param OffEvent event The event which causes a state change
	*/
	public void processEvent(OffEvent event){
		this.context.changeCurrentState(OffState.instance());
	}
	/** Processes a FanOnEvent which causes the context to change the current
	* state so that the fan is on
	* @param FanOnEvent event The event which causes a state change
	*/
	public void processEvent(FanOnEvent event){
		this.context.changeCurrentState(FanState.instance());
	}
	/**
	* Processes an AirConditionerOnEvent which causes the context to change the
	* current state so that the air conditioner is on 
	* @param AirConditionerOnEvent event The event which causes a state change
	*/
	public void processEvent(AirConditionerOnEvent event){
		this.context.changeCurrentState(AirConditionerOnState.instance());
	}
	/**
	* Processes a HeaterIdleEvent which causes the context to change the current
	* state so that the heater is idle
	* @param HeaterIdleEvent event The event which causes a state change
	*/
	public void processEvent(HeaterIdleEvent event){
		this.context.changeCurrentState(HeaterIdleState.instance());
	}
	/**
	* Processes a HeaterTimerRanOutEvent which checks if the Heater has reached
	* the desired temperature.  When reached, the heater will go into idle mode.
	* @param HeaterTimerRanOutEvent event The event that occurs when the
	* inside temperature increases
	*/
	public void processEvent(HeaterTimerRanOutEvent event){
		this.display.displayInsideTemperature(
				this.context.incrementInsideTemperature());
		
		if (this.context.desiredTemperatureReached())
			this.context.handleEvent(new HeaterIdleEvent(this));
		else
			new Timer(this.period, Timer.event.HEATER);
	}
	/**
	* Set the outside state inactive, update display to reflect the current 
	* state and set a timer
	*/
	public void run(){
		Outside.instance().setActive(false);
		this.display.setHeaterOn();
		new Timer(this.period, Timer.event.HEATER);
	}
}
