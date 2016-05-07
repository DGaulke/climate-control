/**
* The FanOnEvent representts the event in which the Fan is turned on
* in a Climate Control System
*/
public class FanOnEvent extends ClimateControlEvent {
	/**
	* Create a new FanOnEvent
	* @param Object source The source object that created the event
	*/
	public FanOnEvent (Object source){
		super(source);
	}
	/**
	* Connect the event to the current state object which implments the
	* listener interface associated with the event
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListner which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try {
		 	((FanOnListener)listener).processEvent(this);
		} catch (ClassCastException cce) {
		}
	}
}
