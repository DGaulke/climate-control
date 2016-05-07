/**
* The AirConditionerIdleState represents the state in a Climate Control system
* in which the air conditioner is idle and the inside temperature is influenced
* by the outside temperature
*/
public class AirConditionerIdleState extends ClimateControlState implements 
		FanOnListener, OffListener, HeaterOnListener, AirConditionerOnListener,
		OutsideTimerRanOutListener {

	private int temperatureThreshold;
	private static AirConditionerIdleState instance;
	/**
	* Constuctor is hidden to maintain singleton property
	*/
	private AirConditionerIdleState(){}
	/**
	* Return the single instance of AirConditionerIdleState if exists
	* else it is created and returned
	*/
	public static AirConditionerIdleState instance(){
		if (instance == null)
			instance = new AirConditionerIdleState();
		return instance;
	}
	/**
	* Set the temperature threshold in which the Air Conditioner turns
	* back on
	*/
	public void setThreshold(int threshold){
		this.temperatureThreshold = threshold;
	}
	/** 
	* Processes a FanOnEvent which causes the context to change
	* the current state where the fan is on
	* @param OffEvent event The event which causes a state change
	*/
	public void processEvent(FanOnEvent event){
		this.context.changeCurrentState(FanState.instance());
	}
	/** 
	* Processes an OffEvent which causes the context to change the 
	* current state so that all appliances are off
	* @param FanOnEvent event The event which causes a state change
	*/
	public void processEvent(OffEvent event){
		this.context.changeCurrentState(OffState.instance());
	}
	/** 
	* Processes a HeaterOnEvent which causes the context to change the
	* current state where the heater is on
	* @param HeaterOnEvent event The event which causes a state change
	*/
	public void processEvent(HeaterOnEvent event){
		this.context.changeCurrentState(HeaterOnState.instance());
	}
	/** 
	* Processes an AirConditionerOnEvent which causes the context to change
	* the current state where the air conditioner is on
	* @param AirConditionerOnEvent event The event which causes a state change
	*/
	public void processEvent(AirConditionerOnEvent event){
		this.context.changeCurrentState(AirConditionerOnState.instance());
	}
	/**
	* Processes an OutsideTimerRanOutEvent which notifies the Outside class
	* and checks if the temperature is still within the threshold.  If not,
	* the Air Conditioner is turned back on.
	* @param OutsideTimerRanOutEvent event The event that occurs when the
	* inside temperature has been changed by the outside temperature
	*/
	public void processEvent(OutsideTimerRanOutEvent event){
		Outside.instance().processEvent(event);
		if (outOfTemperatureThreshold())
			this.context.handleEvent(new AirConditionerOnEvent(this));
	}
	/** 
	* Set the outside class active and update display to reflect the 
	* current state 
	*/
	public void run(){
		Outside.instance().setActive(true);
		this.display.setAirConditionerIdle();
	}
	//Helper method to check if the temperature is within the threshold
	private boolean outOfTemperatureThreshold(){
		return this.context.getTemperatureDifference() >= 
				this.temperatureThreshold;
	}
}
