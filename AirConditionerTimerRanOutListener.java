/**
* The AirConditionerTimerRanOutListener interface requires implementing objects
* to be able to process an AirConditionerOnEvent object
*/
public interface AirConditionerTimerRanOutListener extends ClimateControlListener {
	public void processEvent(AirConditionerTimerRanOutEvent event);
}
