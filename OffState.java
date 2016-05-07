/**
* The OffState represents the state in a Climate Control system in which
* all appliances are turned off and the inside temperature is influenced
* by the outside temperature
*/
public class OffState extends ClimateControlState implements FanOnListener, 
		AirConditionerOnListener, HeaterOnListener, OutsideTimerRanOutListener {
	private static OffState instance;
	/**
	* Constructor is hidden to maintain singleton property
	*/
	private OffState(){}
	/**
	* Return the single instance of OffState if exists
	* else it is created and returned
	*/
	public static OffState instance(){
		if (instance == null)
			instance = new OffState();
		return instance;
	}
	/** 
	* Proceses a FanOnEvent which causes the context to change the
	* current state where the fan is on
	* @param FanOnEvent event The event which causes a state change
	*/
	public void processEvent(FanOnEvent event){
		this.context.changeCurrentState(FanState.instance());
	}
	/** 
	* Processes an AirConditionerOnEvent which causes the context to change
	* the current state so that the air conditioner is on
	* @param AirConditionerOnEvent event The event which causes a state change
	*/
	public void processEvent(AirConditionerOnEvent event){
		this.context.changeCurrentState(AirConditionerOnState.instance());
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
	* Processes an OutsideTimerRanOutEvent which notifies the Outside class
	* @param OutsideTimerRanOutEvent event The event that occurs when the
	* inside temperature has been changed by the outside temperature
	*/
	public void processEvent(OutsideTimerRanOutEvent event){
		Outside.instance().processEvent(event);
	}
	/**
	* Set the outside state active and update display to reflect 
	* the current state
	*/
	public void run(){
		Outside.instance().setActive(true);
		this.display.setAllOff();
	}
}
