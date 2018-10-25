package manager;

import sensors.LightSensor;
import lejos.nxt.Button;
import lejos.nxt.SensorPort;

public class LightSensorManager {
	
	public boolean firstScan(){
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
		
		LightSensor lightSensor = new LightSensor(SensorPort.S1);
		int scaner=0;
		int [][]couleurScanner = new int[3][nbDeScan];
		int [][]couleur;
		while(nombreDeCouleur>0){
			System.out.println("ENTRER");
			Button.ENTER.waitForPressAndRelease();
			scaner=0;
			if(Button.ENTER.isDown()){
				while (scaner<nbDeScan) {
					if(Button.ENTER.isDown()){
						Button.ENTER.waitForPressAndRelease();
						couleur=lightSensor.scan();
						for (int i = 0; i < couleur.length; i++) {
							couleurScanner[i][scaner]=couleur[0][i];
						}
						scaner++;
					}
				}
				if(lightSensor.stocker(couleurScanner)){
					System.out.println("ENREGISTRER");
					nombreDeCouleur--;
				}else{
					System.out.println("EXISTANT");
				}
			}
		}
		System.out.println("FIN ENREGISTREMENT");
		return true;
	}
	
	
	
}
