/**
* The OffEvent represents the event in which all temperature control objects
* have been turned off in a Climate Control system
*/
public class OffEvent extends ClimateControlEvent {
	/**
	* Create a new OffEvent
	* @param Object source The source object that created the event
	*/
	public OffEvent(Object source){
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
			((OffListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
