/**
* The AirConditionerOnEvent represents the event in which the Air Conditioner
* is turned on in a Climate Control system
*/
public class AirConditionerOnEvent extends ClimateControlEvent {
	/**
	* Create a new AirConditionerOnEvent
	* @param Object source The source object that created the event
	*/
	public AirConditionerOnEvent(Object source){
		super(source);
	}
	/**
	* Connect the event to the current state object which implements the
	* listener interface associated with the event
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListener which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try {
			((AirConditionerOnListener) listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
