package main;

import sensors.LightSensor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;

public class TestDemandeCouleurEtScan {

	public static void main(String[] args) {
		System.out.println("choisisez le nombre de couleur a reconnaitre");
		System.out.println("GAUCHE: - DROIT: + ENTRER: valider");
		int nombreDeCouleur=1;
		boolean isDone=false;
		while(!isDone){
			if(Button.RIGHT.isDown()){
				Button.RIGHT.waitForPressAndRelease();
				nombreDeCouleur++;
				System.out.println("nombre = "+nombreDeCouleur);
			}
			if(Button.LEFT.isDown()){
				Button.LEFT.waitForPressAndRelease();
				if(nombreDeCouleur>1)
					nombreDeCouleur--;
				System.out.println("nombre = "+nombreDeCouleur);
			}
			if(Button.ENTER.isDown()){
				Button.ENTER.waitForPressAndRelease();
				isDone=true;
			}
		}
		System.out.println("Nombre de Couleurs :"+nombreDeCouleur);
		Button.waitForAnyPress();
		
		isDone=false;
		System.out.println("choisisez le nombre de scan de couleur");
		System.out.println("GAUCHE: - DROIT: + ENTRER: valider");
		int nbDeScan=1;
		while(!isDone){
			if(Button.RIGHT.isDown()){
				Button.RIGHT.waitForPressAndRelease();
				nbDeScan++;
				System.out.println("nombre = "+nbDeScan);
			}
			if(Button.LEFT.isDown()){
				Button.LEFT.waitForPressAndRelease();
				if(nbDeScan>1)
					nbDeScan--;
				System.out.println("nombre = "+nbDeScan);
			}
			if(Button.ENTER.isDown()){
				Button.ENTER.waitForPressAndRelease();
				isDone=true;
			}
		}
		System.out.println("Nombre de Scan par couleur :"+nbDeScan);
		
		LightSensor lightSensor = new LightSensor(SensorPort.S1, nbDeScan);
		
		
		while(nombreDeCouleur>0){
			System.out.println("ENTRER");
			Button.waitForAnyPress();
			if(Button.ENTER.isDown()){
				if(lightSensor.stocker(lightSensor.firstScan())){
					System.out.println("ENREGISTRER");
					nombreDeCouleur--;
				}else{
					System.out.println("EXISTANT");
				}
			}
		}
		System.out.println("FIN ENREGISTREMENT");
		Button.waitForAnyPress();
	}

}
