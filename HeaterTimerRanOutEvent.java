import java.util.*;
/**
* The AirConditionerTimerRanOutEvent represents the event in which an Air
* Conditioner's timer runs out
*/
public class HeaterTimerRanOutEvent extends ClimateControlEvent {
	/**
	* Construct a HeaterTimerRanOutEvent
	* @param Object source The source of the event
	*/
	public HeaterTimerRanOutEvent(Object source){
		super(source);
	}
	/**
	* Connect the event to the state objects that are listening
	* @param ClimateControlListener listener An object that implements
	* ClimateControlListener which can process the event
	*/
	public void connectToListener(ClimateControlListener listener){
		try {
			((HeaterTimerRanOutListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
