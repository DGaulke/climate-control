/**
* The HeaterOnListener interface requires implementing objects to be able
* to process a HeaterOnEvent object
*/
public interface HeaterOnListener extends ClimateControlListener {
	public void processEvent(HeaterOnEvent event);
}
