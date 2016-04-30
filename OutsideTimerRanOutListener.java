/**
* The OutsideTimerRanOutListener interface requires implementing objects
* to be able to process an FanOnEvent object
*/
public interface OutsideTimerRanOutListener extends ClimateControlListener {
	public void processEvent(OutsideTimerRanOutEvent event);
}
