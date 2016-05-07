/**
* The FanTimerRanOutListener interface requires implementing objects
* to be able to process an FanOnEvent object
*/
public interface FanTimerRanOutListener extends ClimateControlListener {
	public void processEvent(FanTimerRanOutEvent event);
}
