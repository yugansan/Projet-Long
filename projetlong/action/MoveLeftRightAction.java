package action;

import mouvement.Movement;
import sensors.LightSensor;

public class MoveLeftRightAction extends Action{
	private int[] positionCouleur;
	private static int nbTimeDontSee=0;
	private int NBDEDEGRE=90;

	public MoveLeftRightAction(LightSensor lightSensor,Movement movement,int[] positionCouleur) {
		this.lightSensor=lightSensor;
		this.movement=movement;
		this.positionCouleur=positionCouleur;
	}


	@Override
	public boolean launchAction() {
		while(true){
			whileRightSide();
			whileLeftSide();
		}
	}


	private boolean whileLeftSide(){
		boolean isColorCrossed=false;
		boolean colorScaned=false;

		while(!isColorCrossed){
			movement.moveForward();
			//movement.turnLeftForward();
			movement.moveRotationLeft();
			if(lightSensor.getPosAndVerifie(positionCouleur[0])){
				colorScaned=true;
				movement.setSpeed();
			}else if(lightSensor.getPosAndVerifie(positionCouleur[1])){
				movement.turnAroundLeft();
				System.out.println("Apres le demi tour");
			}
			else if(colorScaned){
				isColorCrossed=true;
				movement.decreaseSpeed(movement.getLeftMotor());
			}else{
				movement.increaseSpeed(movement.getRightMotor());
				movement.decreaseSpeed(movement.getLeftMotor());
				nbTimeDontSee++;//ajoute
				if(nbTimeDontSee>NBDEDEGRE){
					searchColor();
				}
			}
			//movement.turnLeftForward();
			movement.moveRotationLeft();
		}
		return true;
	}

	private boolean whileRightSide(){
		boolean isColorCrossed=false;
		boolean colorScaned=false;
		while(!isColorCrossed){
			movement.moveForward();
			//movement.turnRightForward();
			movement.moveRotationRight();
			if(lightSensor.getPosAndVerifie(positionCouleur[0])){
				colorScaned=true;
				movement.setSpeed();
			}
			else if(lightSensor.getPosAndVerifie(positionCouleur[1])){
				movement.turnAroundRight();	
				}
			else if(colorScaned){
				isColorCrossed=true;
				movement.decreaseSpeed(movement.getRightMotor());
			}else{
				movement.increaseSpeed(movement.getLeftMotor());
				movement.decreaseSpeed(movement.getRightMotor());
				nbTimeDontSee++;//ajoute
				if(nbTimeDontSee>NBDEDEGRE){
					searchColor();
				}
			}
			//movement.turnRightForward();
			movement.moveRotationRight();
		}
		return true;
	}


	public boolean searchColor() {
		int tournerDeDegre=50;
		movement.moveForward();
		System.out.println("search color");
		//>TODO: verifier pour deux couleurs
		while(lightSensor.getPosAndVerifie(positionCouleur[0])){ 
			for (int i = 0; i < tournerDeDegre; i++) {
				movement.moveRotationLeft();
			}
			for (int i = 0; i < tournerDeDegre; i++) {
				movement.moveRotationRight();
			}
			for (int i = 0; i < tournerDeDegre; i++) {
				movement.moveForward();
			}
		}
		nbTimeDontSee=0;
		return false;
	}
}
