/**
* The Fan button class sends an OffEvent to a Climate Control system
*/
public class FanButton extends GUIButton {
	/**
	* Construct an FanButton captioned "Fan"
	*/
	public FanButton(){
		super("Fan");
	}
	/**
	* When button is pressed, inform the Climate Control context
	* @param CliamteControlContext context The Context which is
	* informed of the event 
	* @param ClimateControlDisplay source The interface which received
	* the button press
	* @return boolean Returns true when the event has been sent
	*/
	public boolean onPress(ClimateControlContext context, 
			ClimateControlDisplay source){
		context.handleEvent(new FanOnEvent(source));
		return true;
	}
}
