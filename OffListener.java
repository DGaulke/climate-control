/**
* The OffListener interface requires implementing objects to be able to
* process an OffEvent
*/
public interface OffListener extends ClimateControlListener {
	public void processEvent(OffEvent event);
}
