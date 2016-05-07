import javax.swing.*;
/**
* The GUIButton class is an abstract class that is subclassed
* by concrete radio buttons that perform specific events
*/
public abstract class GUIButton extends JRadioButton{
	public GUIButton(String string){
		super(string);
	}
	/**
	* The action to be performed when the button is pressed
	* @param CliamteControlContext context The Context which is
	* informed of the event 
	* @param ClimateControlDisplay source The interface which received
	* the button press
	* @return boolean Returns true when the event has been sent
	*/
	public abstract boolean onPress(ClimateControlContext context, 
			ClimateControlDisplay display);
}
