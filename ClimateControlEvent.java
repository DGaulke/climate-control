import java.util.*;
/**
* The ClimateControlEvent is an abtract class extended to concrete event objects
* that occur in a Climate Control system
*/
public abstract class ClimateControlEvent extends EventObject {
	/**
	* Create a new ClimateControlEvent
	* @param Object source The source object that created the event
	*/
	public ClimateControlEvent(Object source){
		super(source);
	}
	/** Connect the event to the current state object which implements
	* the listener interface associated with the event
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListener which can process the event
	*/
	public abstract void connectToListener(ClimateControlListener listener);
}
