package main;
import action.DemiTour;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import mouvement.Movement;

public class TestDemiTour {

	public static void main(String[] args) {
		Movement movement = new Movement(Motor.A, Motor.B, Motor.C);
		DemiTour demitour = new DemiTour(movement);
		demitour.launchAction();
//		System.out.println("fin");
//		Button.waitForAnyPress();
		
	}

}
