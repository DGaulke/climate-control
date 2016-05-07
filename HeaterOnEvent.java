/**
* The HeaterOnEvent represents the event in which the Heater is turned on 
* in a Climate Control System
*/
public class HeaterOnEvent extends ClimateControlEvent {
	/**
	* Create a new HeaterOnEvent
	* @param Object event The source object that created the event
	*/
	public HeaterOnEvent (Object source){
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
			((HeaterOnListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
