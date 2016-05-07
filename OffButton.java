/**
* The Off button class sends an OffEvent to a Climate Control system
*/
public class OffButton extends GUIButton {
	/**
	* Construct an OffButton captioned "Off"
	*/
	public OffButton(){
		super("All Off");
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
		context.handleEvent(new OffEvent(source));
		return true;
	}
}
