/**
* The AirConditionerOnListener interface requires implementing objects
* to be able to process an AirConditionerOnEvent object
*/
public interface AirConditionerOnListener extends ClimateControlListener {
	public void processEvent(AirConditionerOnEvent event);
}
