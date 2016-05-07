/**
* The ClimateControlDisplay interface specifies the methods to be implemented
* in a Climate Control interface and display object
*/
public interface ClimateControlDisplay {
	void setClimateControlContext(ClimateControlContext context);

	void setFanOn();

	void setFanIdle();
	
	void setAirConditionerOn();

	void setAirConditionerIdle();

	void setHeaterOn();

	void setHeaterIdle();

	void setAllOff();

	void displayInsideTemperature(int temperature);

	void displayOutsideTemperature(int temperature);

	void displayDesiredTemperature(int temperature);
	
}
