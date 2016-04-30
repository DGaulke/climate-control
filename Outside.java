/**
* Outside is an abstract class which is subclassesd by concrete state classes
* of a Climate Control system where the inside temperature is influenced by
* the outside temperature
*/
public class Outside implements OutsideTimerRanOutListener {
	
	private boolean active;
	private int period;
	private ClimateControlContext context;
	private ClimateControlDisplay display;
	private static Outside instance;
	/**
	* Constructor is hidden to maintain singleton property
	*/
	private Outside(){}
	/**
	* Return the single instance of Outside if exits else it is created
	* and returned
	*/
	public static Outside instance(){
		if(instance == null)
			instance = new Outside();
		instance.context = ClimateControlContext.instance();
		instance.display = ClimateControlGUI.instance();
		return instance;
	}
	/**
	* Set the period in which the outside influences the inside temperature
	* by one degree
	*/
	public void setPeriod(int period){
		this.period = period;
	}
	/**
	* Set the outside class active so it affects the inside temperature
	*/
	public void setActive(boolean active){
		if (!this.active & (this.active = active))
			this.run();
	}
	/**
	* Processes an OutsideTimerRanOutEvent which occurs when the inside
	* temperature approaches the outside temperature
	* @param OutsideTimerRanOutEvent event The event which drives the
	* temperature change.
	*/
	public void processEvent(ClimateControlEvent event){
		System.out.println("hllo");
		if (!this.active)
			return;

		try {
			event = (OutsideTimerRanOutEvent)event;
		} catch (ClassCastException cce){
			System.out.println("Wrong event passed to Outside");
			return;
		}
		processEvent(event);
	}
	public void processEvent(OutsideTimerRanOutEvent event){
		System.out.println("hllo2");
		this.context.approachOutsideTemperature();
		this.display.displayInsideTemperature(
				this.context.getInsideTemperature());
		this.run();

	}
	/**
	* Create a new timer to regulate the outside temperature affecting the
	* inside temperature
	*/
	public void run(){
		new Timer(this.period, Timer.event.OUTSIDE);
	}
}
