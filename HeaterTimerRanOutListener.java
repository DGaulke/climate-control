/**
* The HeaterTimerRanOutListener interface requires implementing objects
* to be able to process an FanOnEvent object
*/
public interface HeaterTimerRanOutListener extends ClimateControlListener {
	public void processEvent(HeaterTimerRanOutEvent event);
}
