import java.util.*;
/**
* The ClimateControlListener is extended to specific event listeners
* in a Climate Control system that must be able to process the
* associated events
*/
public interface ClimateControlListener extends EventListener {
	public void processEvent(ClimateControlEvent event);
}
