import java.util.*;
/**
* The AirConditionerTimerRanOutEvent represents the event in which an Fan's
* timer runs out
*/
public class FanTimerRanOutEvent extends ClimateControlEvent {
	/**
	* Construct a FanTimerRanOutEvent
	* @param Object source The source of the event
	*/	
	public FanTimerRanOutEvent(Object source){
		super(source);
	}
	/**
	* Connect the event to the state objects that are listening
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListener which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try {
			((FanTimerRanOutListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
