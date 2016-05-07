/**
* The AirConditionerIdleEvent represents the event in which the Air Conditioner becomes
* idle because it has reached a threshold between the inside temperature and the
* desired temperature
*/
public class AirConditionerIdleEvent extends ClimateControlEvent {
	/**
	* Create a new AirConditionerIdleEvent
	* @param Object source The source object that created the event
	*/
	public AirConditionerIdleEvent(Object source){
		super(source);
	}
	/**
	* Connect the event to the current state object which implements the
	* listener interface associated with the event
	* @param ClimateControlListener listener An object that implements
	* CliamteControlListener which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try {
			((AirConditionerIdleListener) listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
