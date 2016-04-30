/**
* The AirConditionerIdleListener interface requires implementing objects
* to be able to process an AirConditionerIdleEvent object
*/
public interface AirConditionerIdleListener extends ClimateControlListener {
	public void processEvent(AirConditionerIdleEvent event);
}
