package action;

import mouvement.Movement;
import sensors.LightSensor;

public abstract class Action {
	LightSensor lightSensor;
	Movement movement;
	
	public abstract boolean launchAction();
	

}
