/**
* The HeaterIdleListener interface requires implementing objects to
* be able to process a HeaterIdleEvent object
*/
public interface HeaterIdleListener extends ClimateControlListener {
	public void processEvent(HeaterIdleEvent event);
}
