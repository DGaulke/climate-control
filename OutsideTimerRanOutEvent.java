import java.util.*;

public class OutsideTimerRanOutEvent extends ClimateControlEvent {
	
	public OutsideTimerRanOutEvent(Object source){
		super(source);
	}
	
	public void connectToListener(ClimateControlListener listener){
		try {
			((OutsideTimerRanOutListener)listener).processEvent(this);
		} catch (ClassCastException cce){
		}
	}
}
