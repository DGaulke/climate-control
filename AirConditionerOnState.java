/**
* The AirConditionerOnState represents the state in a Climate Control system in 
* which the inside temperature is lowered by an air conditioner
*/
public class AirConditionerOnState extends ClimateControlState implements 
		OffListener, FanOnListener, HeaterOnListener, 
		AirConditionerIdleListener, AirConditionerTimerRanOutListener {

	private int period;
	private static AirConditionerOnState instance;
	/** 
	* Constructor is hidden to maintain singleton property
	*/
	private AirConditionerOnState(){}
	/**
	* Return the single instance of AirConditionerOnState if exists
	* else it is created and returned
	*/
	public static AirConditionerOnState instance(){
		if (instance == null)
			instance = new AirConditionerOnState();
		return instance;
	}
	/** 
	* Set the period in hours that it takes for the air conditioner
	* to cool the room by one degree
	* @param int period The duration in hours
	*/
	public void setPeriod(int period){
		this.period = period;
	}
	/**
	* Processes an OffEvent which causes the context to change the
	* current state so that all appliances are off
	* @param OffEvent event The event which causes a state change
	*/
	public void processEvent(OffEvent event){
		this.context.changeCurrentState(OffState.instance());
	}
	/**
	* Processes a FanOnEvent which causes the context to change
	* the current state where the fan is on
	* @param FanOnEvent event The event which causes a state change
	*/
	public void processEvent(FanOnEvent event){
		this.context.changeCurrentState(FanState.instance());
	}
	/** 
	* Processes a HeaterOnEvent which causes the context to change
	* the current state where the heater is on
	* @param HeaterOnEvent event The event which causes a state change
	*/
	public void processEvent(HeaterOnEvent event){
		this.context.changeCurrentState(HeaterOnState.instance());
	}
	/**
	* Processes an AirConditionerIdleEvent which causes the context to change
	* the current state where the air conditioner goes into idle mode
	* @param AirConditionerIdleEvent event The event which causes a state
	* change
	*/
	public void processEvent(AirConditionerIdleEvent event){
		this.context.changeCurrentState(AirConditionerIdleState.instance());
	}
	/**
	* Processes an AirConditionerTimerRanOutEvent which checks if the Air
	* Conditioner has reached the desired temperature.  When reached,
	* notify
	* decrement the inside temperature by one degree.  If threshold is
	* reached, the air conditioner will go into idle mode
	* @param AirConditionerTimerRanOutEvent event The event that occurs when 
	* the inside temperature drops
	*/
	public void processEvent(AirConditionerTimerRanOutEvent event){
		;
		this.display.displayInsideTemperature(
				this.context.decrementInsideTemperature());

		if (this.context.desiredTemperatureReached())
			this.context.handleEvent(new AirConditionerIdleEvent(this));
		else
			new Timer(this.period, Timer.event.AIR_CONDITIONER);
	}
	/**
	* Set the outside state inactive, update display to reflect the current 
	* state and set a timer
	*/
	public void run(){
		Outside.instance().setActive(false);
		this.display.setAirConditionerOn();
		new Timer(this.period, Timer.event.AIR_CONDITIONER);
	}
}
