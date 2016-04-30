import javax.swing.*;
/**
* The AirConditioner button class prompts for a valid desired temperature
* and sends an AirConditionerOnEvent to a Climate Control system
*/
public class AirConditionerButton extends GUIButton {
	/**
	* Construct an AirConditionerButton captioned "Air Conditioner"
	*/
	public AirConditionerButton(){
		super("Air Conditioner");
	}
	/**
	* When button is pressed, prompt for desired temperature and validate.  When
	* valid, send AirConditioner event to context.  If canceled, return false
	* so GUI can handle.
	* @param CliamteControlContext context The Context which is
	* informed of the event 
	* @param ClimateControlDisplay source The interface which received
	* the button press
	* @return boolean Returns true when the event has been sent
	*/
	public boolean onPress(ClimateControlContext context, 
			ClimateControlDisplay source){
		int temp;
		ClimateControlGUI gui = (ClimateControlGUI)source;
		//Prompt for temperature, must be lower than inside temp.  MIN_VALUE
		//represents a canceled action
		do {
			temp = gui.promptForTemperature("Enter desired temperature");
			if (temp == Integer.MIN_VALUE)
				return false;
			else if (temp >= context.getInsideTemperature())
				JOptionPane.showMessageDialog(null, "Desired temperature must "
						+ "be lower than current inside temperature");
		} while (temp >= context.getInsideTemperature());
		//Inform context
		context.setDesiredTemperature(temp);
		context.handleEvent(new AirConditionerOnEvent(source));
		return true;
	}
}
