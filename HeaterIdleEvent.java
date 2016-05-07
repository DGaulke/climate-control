/**
* The HeaterIdleEvent represents the event in which the Heater becomes idle
* because it has reached a threshold between the inside temperature and the
* desired temperature
*/
public class HeaterIdleEvent extends ClimateControlEvent {
	/** Create a new HeaterIdleEvent
	* @param Object source The source object that created the event
	*/
	public HeaterIdleEvent (Object source){
		super(source);
	}
	/**
	* Connect the event to the current state object which implements the
	* listener interface associated with the event
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListner which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try{
			((HeaterIdleListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
