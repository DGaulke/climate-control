import java.util.*;
/** 
* The Clock class is an observable object that delivers
* clock tick events to any interested objects.  It runs 
* concurrently as its own thread.
*/
public class Clock extends Observable implements Runnable {
	private Thread thread = new Thread(this);
	private static Clock instance;
	public enum Events {CLOCK_TICKED_EVENT};
	/**
	* Starts parallel thread which begins clock ticking.
	* Constructor is hidden to maintain singleton property.
	*/
	private Clock(){
		thread.start();
	}
	/**
	* Return the single instance of Clock if exists
	* else it is created and returned
	*/
	public static Clock instance(){
		if (instance == null)
			instance = new Clock();
		return instance;
	}
	/**
	* Thread behavior: sleep for 1 second, then generate
	* a tick event and notify obvs.  Repeat indefinitely.
	*/
	public void run(){
		try {
			while (true) {
				thread.sleep(1000);
				setChanged();
				notifyObservers(Events.CLOCK_TICKED_EVENT);
			}
		} catch (InterruptedException ie) {
		}
	}
}
