import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
/**
* The ClimateControlGUI is a concrete implementation of the 
* ClimateControlDisplay interface which is used to drive a 
* ClimateControlContext object
*/
public class ClimateControlGUI extends JFrame implements ActionListener, 
		ClimateControlDisplay {
	//Degree symbol char
	private final char DEGREE = (char)0x00B0;
	private ClimateControlContext context;
	private ButtonModel currentMode;
	private JPanel btnPanel = new JPanel();
	private ButtonGroup group = new ButtonGroup();
	private OffButton btnOff = new OffButton();
	private FanButton btnFan = new FanButton();
	private AirConditionerButton btnAC = new AirConditionerButton();
	private HeaterButton btnHeat = new HeaterButton();
	private JButton btnSetOutsideTemp = new JButton("Set Outside Temp");
	private JPanel statusPanel = new JPanel();
	private JLabel status = new JLabel("All appliances off");
	private JLabel currentTemp = new JLabel();
	private JLabel desiredTemp = new JLabel("Not set");
	private JLabel outsideTemp = new JLabel();
	private static ClimateControlGUI instance;
	/**
	* Constructor is private to maintain singleton property
	*/
	private ClimateControlGUI(){
		super("Climate Control System");
		this.initialize();
	}
	/**
	* Return the single instance of ClimateControlGUI if exists
	* else it is created and returned
	*/
	public static ClimateControlGUI instance(){
		if (instance == null)
			instance = new ClimateControlGUI();
		return instance;
	}
	//Helper method to add GUI objects to JFrame
	private void initialize(){
		//Shutdown on window close
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent event){
				System.exit(0);
			}
		});
		//Group radio buttons together
		group.add(btnOff);
		group.add(btnFan);
		group.add(btnAC);
		group.add(btnHeat);
		//Populate button panel in flow layout and add listeners
		btnPanel.setLayout(new FlowLayout());
		btnOff.addActionListener(this);
		btnPanel.add(btnOff);
		group.setSelected(btnOff.getModel(), true);
		currentMode = btnOff.getModel();
		btnFan.addActionListener(this);
		btnPanel.add(btnFan);
		btnAC.addActionListener(this);
		btnPanel.add(btnAC);
		btnHeat.addActionListener(this);
		btnPanel.add(btnHeat);
		btnPanel.add(btnSetOutsideTemp);
		btnSetOutsideTemp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				int temp = promptForTemperature("Enter outside temperature");
				if (temp == Integer.MIN_VALUE)
					return;
				context.setOutsideTemperature(temp);
			}
		});
		//Add display labels to status panel in a grid layout
		statusPanel.setLayout(new GridLayout(4, 2, 5, 10));
		statusPanel.add(new JLabel("Current Temperature:"));
		statusPanel.add(currentTemp);
		statusPanel.add(new JLabel("Desired Temperature:"));
		statusPanel.add(desiredTemp);
		statusPanel.add(new JLabel("Outside Temperature:"));
		statusPanel.add(outsideTemp);
		statusPanel.add(new JLabel("System Status:"));
		statusPanel.add(status);
		//Add panels to frame in border layout
		getContentPane().setLayout(new BorderLayout(5, 10));
		getContentPane().add(btnPanel, BorderLayout.NORTH);
		getContentPane().add(statusPanel, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
	/**
	* When radio buttons are selected, change context to appropriate state
	* @param ActionEvent event The event that detects a button press
	*/
	public void actionPerformed(ActionEvent event){
		if (((GUIButton)event.getSource()).onPress(
				ClimateControlContext.instance(), this))
			this.currentMode = ((GUIButton)event.getSource()).getModel();
		else
			group.setSelected(currentMode, true);
	}
	/**
	* Set the Climate Control context associated with GUI
	* @param ClimateControlContext context The context object for the 
	* Climate Control system
	*/
	public void displayDesiredTemperature(int temperature){
		desiredTemp.setText(String.valueOf(temperature) + DEGREE);
	}
	/**
	* Associate a Climate Control context object with the GUI
	* @param ClimateControlContext context The context object
	*/
	public void setClimateControlContext(ClimateControlContext context){
		this.context = context;
	}
	/**
	* Turn the fan on from the context object
	*/
	public void setFanOn(){
		status.setText("Fan is on");
	}
	/**
	* Idle the fan from the context object
	*/
	public void setFanIdle(){
		status.setText("Fan is idle");
	}
	/**
	* Turn the air conditioner on from the context object
	*/
	public void setAirConditionerOn(){
		status.setText("Air conditioner is on");
	}
	/**
	* Idle the air conditioner from the context object
	*/
	public void setAirConditionerIdle(){
		status.setText("Air conditioner is idle");
	}
	/**
	* Turn the heater on from the context objejct
	*/
	public void setHeaterOn(){
		status.setText("Heater is on");
	}
	/**
	* Idle the heater from the context object
	*/
	public void setHeaterIdle(){
		status.setText("Heater is idle");
	}
	/**
	* Turn all appliances off from the context object
	*/
	public void setAllOff(){
		status.setText("All appliances off");
	}
	/**
	* Display the inside temperature providerd by the context
	*/
	public void displayInsideTemperature(int temperature){
		currentTemp.setText(String.valueOf(temperature) + DEGREE);
	}
	/**
	* Display the outside temperature from the context object
	*/
	public void displayOutsideTemperature(int temperature){
		outsideTemp.setText(String.valueOf(temperature) + DEGREE);
	}
	//Helper method to prompt user for a temperature
	public int promptForTemperature(String message){
		int temperature;
		String input = JOptionPane.showInputDialog(null, message);
		//if users cancels out of prompt, send MIN_VALUE to calling method
		if (input == null)
			return Integer.MIN_VALUE;
		//Try to convert input string to int
		try {
			temperature = Integer.valueOf(input);
		//If input can't be converted, start method again
		} catch (NumberFormatException nfe){
			JOptionPane.showMessageDialog(null, "Temperature must be numeric");
			return promptForTemperature(message);
		}
		return temperature;	
	}
	/**
	* Get the selected radio button
	* @return ButtonModel The model of the selected button
	*/
	public ButtonModel getSelection(){
		return group.getSelection();
	}
	/**
	* Entry point for GUI to control Climate Control system
	* @param String[] args The config file read by the context
	*/
	public static void main(String[] args){
		ClimateControlGUI gui = ClimateControlGUI.instance();
		gui.context = ClimateControlContext.instance();
		gui.context.setConfigFile(new File(args[0]));
		gui.context.initialize();
	}
}
