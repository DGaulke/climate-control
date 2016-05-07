/**
* The FanOnListener interface requires implementing objects
* to be able to process an FanOnEvent object
*/
public interface FanOnListener extends ClimateControlListener {
	public void processEvent(FanOnEvent event);
}
