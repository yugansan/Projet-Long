package action;

import mouvement.Movement;

public class DemiTour extends Action{
	
	public DemiTour(Movement movement) {
		this.movement = movement;
	}
	
	
	@Override
	public boolean launchAction() {
		movement.setSpeed();
		for(int i=0; i<3000; i++) {
			movement.moveForward();
		}
		for(int i = 0; i<=1300; i++) {
			movement.turnLeftForward();
		}
		return true;
	}

}
