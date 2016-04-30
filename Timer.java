import java.util.*;
/**
* The Timer class counts clock ticks and notifies the Climate Control context
* of the corresponding event
*/
public class Timer implements Observer {
	public enum event {FAN, OUTSIDE, HEATER, AIR_CONDITIONER} 
	private int timeValue;
	private event timerType;
	private int id;
	private static int idCount = 0;
	/**
	* Construct a timer with the specified time limit
	* @param int value The amount of time until the timer notifies the context
	* @param event timerType The type of timer which determines the type 
	* of event
	*/
	public Timer(int value, event timerType){
		this.timeValue = value;
		this.timerType = timerType;
		this.id = ++idCount;
		Clock.instance().addObserver(this);
	}
	/**
	* Track clock ticks and notify the context when the timer reaches zero
	* @param Observable clock The clock object that generates clicks
	* @param Object event Data or event returned by the Observable object
	*/
	@Override
	public void update(Observable clock, Object event){
		if (--timeValue != 0)
			return;
	
		Clock.instance().deleteObserver(this);
		switch (timerType){
			case FAN: 
				ClimateControlContext.instance().handleEvent(
						new FanTimerRanOutEvent(this));
				break;
			case OUTSIDE:  
				ClimateControlContext.instance().handleEvent(
						new OutsideTimerRanOutEvent(this));
				break;
			case HEATER: 
				ClimateControlContext.instance().handleEvent(
						new HeaterTimerRanOutEvent(this));
				break;
			case AIR_CONDITIONER: 
				ClimateControlContext.instance().handleEvent(
						new AirConditionerTimerRanOutEvent(this));
				break;
			default: 
				break;
		}
	}
}
