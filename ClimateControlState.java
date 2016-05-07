/**
* ClimateControlState is an abstract class which is extended to concrete
* states in a Climate Control system and specifies methods that must be
* implemented
*/
public abstract class ClimateControlState implements ClimateControlListener {
	protected static ClimateControlContext context;
	protected static ClimateControlDisplay display;

	/**
	* Create a new ClimateControlState object and initialize the
	* context object, then get its associated display object
	*/
	protected ClimateControlState(){
		context = ClimateControlContext.instance();
		display = context.getDisplay();
	}

	public abstract void run();
	/**
	* Any events that are not specifically handled by concrete
	* state classes will be passed here
	*/
	public void processEvent(ClimateControlEvent event){
	}
}
