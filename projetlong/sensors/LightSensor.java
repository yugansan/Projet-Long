package sensors;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;
import color.ColorMemory;

public class LightSensor {
	
	private ColorHTSensor colorSensor;// color sensor du robot 
	private ColorMemory colorMemory; // memoire des couleur
	private int NUMBER_OF_SCAN_BEFORE_SAVING=1; // nombre de scans
	
	
	
	
	/**
	 * 
	 * @param port : port sur lequel est branche le lightSensor
	 */
	public LightSensor(SensorPort port){
		this.colorSensor= new ColorHTSensor(port);
		this.colorMemory = new ColorMemory();
	}
	
	/**
	 * 
	 * @param port : port sur lequel est branche le lightSensor
	 * @param number : nombre de scan a effectuer
	 */
	public LightSensor(SensorPort port,int number){
		this.colorSensor= new ColorHTSensor(port);
		this.colorMemory = new ColorMemory();
		this.NUMBER_OF_SCAN_BEFORE_SAVING =number;
	}
	
	
	/**
	 * lance le scan avec le nombre de scan predefinit lors de la creation
	 * @return : retourne le tableau correspondant au scan
	 */
	public int [][]scan(){
		int [][] colorsRGB = new int[3][1];
		lejos.robotics.Color color;
			color = colorSensor.getColor();
			colorsRGB[0][0]=color.getRed();
			colorsRGB[1][0]=color.getGreen();
			colorsRGB[2][0]=color.getBlue();
		return colorsRGB;
	}
	
	
	/**
	 * lance le scan avec le nombre de scan predefinit lors de la creation
	 * @return : retourne le tableau correspondant au scan
	 */
	public int [][]firstScan(){
		int [][] colorsRGB = new int[3][NUMBER_OF_SCAN_BEFORE_SAVING];
		lejos.robotics.Color color;
		
		for(int i=0;i<this.NUMBER_OF_SCAN_BEFORE_SAVING;i++){
			System.out.println("ENTER on color");
			Button.waitForAnyPress();
			if(Button.ENTER.isDown()){
				color = colorSensor.getColor();
				colorsRGB[0][i]=color.getRed();
				colorsRGB[1][i]=color.getGreen();
				colorsRGB[2][i]=color.getBlue();
			}
		}
		return colorsRGB;
	}
	
	
	
	/**
	 * 
	 * @param colorsRGB : le tableau a stocker dans le memoryColor
	 * @return true si le stockage a etait bien effectuer false sinon
	 */
	public boolean stocker(int [][] colorsRGB){
		return this.colorMemory.addColor(colorsRGB);
	}
	
	public boolean getPos0andVerifie(){
		if(this.colorMemory.isCouleurExistant(scan())){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param position : position a laquelle se trouve la couleur scanner
	 * @return vrai si correspond bien a la position donne false sinon
	 */
	public boolean getPosAndVerifie(int position){
		if(this.colorMemory.getColorPosition(scan())==position){
			return true;
		}
		return false;
	}
	
}
