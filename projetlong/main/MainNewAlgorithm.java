package main;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import mouvement.Movement;
import sensors.LightSensor;
import action.MoveLeftRightAction;

public class MainNewAlgorithm {

	public static void main(String[] args) {
		Movement movement = new Movement(Motor.A, Motor.B, Motor.C);
		LightSensor lightSensor = new LightSensor(SensorPort.S1,3); //3 = nombre de scan
		int i=2; // pour scanner 2 couleurs
		while(i>0){
			System.out.println("ENTRER");
			Button.waitForAnyPress();
			if(Button.ENTER.isDown()){
				if(lightSensor.stocker(lightSensor.firstScan())){
					System.out.println("ENREGISTRER");
				}else{
					System.out.println("EXISTANT");
				}
				i--;
			}

		}
		System.out.println("ENTRER lancer action");
		Button.waitForAnyPress();
		int[] nbDeCouleur = {0,1};
		//On avait mis 0 avant, et là on avait un tableau non initialisé
		MoveLeftRightAction mlr = new MoveLeftRightAction(lightSensor, movement, nbDeCouleur);
		mlr.launchAction();
		/*SearchColorAction sca = new SearchColorAction(lightSensor, movement, 0);
		sca.launchAction();*/
		System.out.println("fin");
		Button.waitForAnyPress();

	}

}
