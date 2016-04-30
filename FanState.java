/**
* The FanState represents the state in a Climate Control system in which
* a fan is on for a given period, then off for a given period, then repeats.
* The inside temperature is affected by the outside temperature
*/
public class FanState extends ClimateControlState implements OffListener, 
		AirConditionerOnListener, HeaterOnListener, FanTimerRanOutListener,
		OutsideTimerRanOutListener {

	private int onPeriod;
	private int offPeriod;
	private boolean fanState;
	private static FanState instance;
	/**
	* Constructor is hidden to maintain singleton property
	*/
	private FanState(){}
	/**
	* Return the single instance of FanState if exists
	* else it is created and returned
	*/
	public static FanState instance(){
		if (instance == null)
			instance = new FanState();
		return instance;
	}
	/** 
	* Set the period in hours that the fan is on before idling
	* @param int period The number of hours the fan is on
	*/
	public void setOnPeriod(int period){
		this.onPeriod = period;
	}
	/** 
	* Set the period in hours that the fan is off before turning back on
	* @param int preiod The number of hours that the fan idles
	*/
	public void setOffPeriod(int period){
		this.offPeriod = period;
	}
	/**
	* Processes an OffEvent which causes the context to change the current
	* state so that all appliances are off
	* @param OffEvent event The event which causes a state change
	*/
	public void processEvent(OffEvent event){
		this.context.changeCurrentState(OffState.instance());
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
	* Processes a HeaterOnEvent which causes the context to change the
	* current state where the heater is on
	* @param HeaterOnEvent event The event which causes a state change
	*/
	public void processEvent(HeaterOnEvent event){
		this.context.changeCurrentState(HeaterOnState.instance());
	}
	/**
	* Processes a FanTimerRanOutEvent which occurs when the fan needs to
	* toggle from on to off and vice versa.
	* @param FanTimerRanOut event The event that causes the fan to change modes
	*/
	public void processEvent(FanTimerRanOutEvent event){
		this.toggleFanState();
		this.displayFanState();
		new Timer(fanState ? onPeriod : offPeriod, Timer.event.FAN);
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
	* Set the outside state active and update display to reflect fan state 
	* and sets timer
	*/
	public void run(){
		Outside.instance().setActive(true);
		this.fanState = true;
		this.display.setFanOn();
		new Timer(onPeriod, Timer.event.FAN);
	}
	//Helper method to display the appropriate fan state
	private void displayFanState(){
		if (this.fanState)
			this.display.setFanOn();
		else
			this.display.setFanIdle();
	}
	//Helper method to toggle the fan from off to on, or on to off
	private void toggleFanState(){
		this.fanState = this.fanState ? false : true;
	}
}
