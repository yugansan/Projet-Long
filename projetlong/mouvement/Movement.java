package mouvement;

import lejos.nxt.NXTRegulatedMotor;

public class Movement {
	NXTRegulatedMotor leftMotor;
	NXTRegulatedMotor centerMotor;
	NXTRegulatedMotor rightMotor;


	private int AVGSPEED=500;
	private int MAXSpeed=1000;
	private int MINSpeed=50;

	/**
	 * 
	 * @param a2 : left motor
	 * @param b2 : center motor
	 * @param c2 : right motor
	 */
	public Movement(NXTRegulatedMotor a2,NXTRegulatedMotor b2, NXTRegulatedMotor c2){
		this.leftMotor = a2;
		this.centerMotor = b2;
		this.rightMotor = c2;
	}

	/**
	 * 
	 * @param motors :les moteurs a faire tourner en avant
	 */
	public void moveForward(NXTRegulatedMotor... motors){
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				m.forward();
			}
		}
	}

	/**
	 * avance en s'aidant des moteurs droite et gauche
	 */
	public void moveForward(){
		if(leftMotor.getSpeed()>rightMotor.getSpeed()){
			rightMotor.setSpeed(leftMotor.getSpeed());
		}else if(leftMotor.getSpeed()<rightMotor.getSpeed()){
			leftMotor.setSpeed(rightMotor.getSpeed());
		}
		moveForward(leftMotor,rightMotor);
	}

	/**
	 * recule en s'aidant des moteurs droite et gauche
	 */
	public void moveBackward(){
		moveBackward(leftMotor,rightMotor);
	}

	/**
	 * 
	 * @param motors :les moteurs a faire tourner en arriere
	 */
	public void moveBackward(NXTRegulatedMotor... motors){
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				m.backward();
			}
		}
	}

	/**
	 * 
	 * @param motors :les moteurs a stoper
	 */
	public void stopMotors(NXTRegulatedMotor... motors){
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				m.stop();
			}
		}
	}


	/**
	 * turn right motor mindstrom will turn left
	 */
	public void turnLeftForward(){
		stopMotors(leftMotor,centerMotor);
		moveForward(rightMotor);
	}

	/**
	 * turn left motor mindstrom will turn right
	 */
	public void turnRightForward(){
		stopMotors(rightMotor,centerMotor);
		moveForward(leftMotor);
	}

	/**
	 * turn right motor mindstrom will turn left backward
	 */
	public void turnLeftBackward(){
		stopMotors(leftMotor,centerMotor);
		moveBackward(rightMotor);
	}

	/**
	 * turn left motor mindstrom will turn right backward
	 */
	public void turnRightBackward(){
		stopMotors(rightMotor,centerMotor);
		moveBackward(leftMotor);
	}


	public void turn360deg(){
		moveForward(rightMotor);
		moveBackward(leftMotor);
	}

	public void stop(){
		stopMotors(leftMotor,centerMotor,rightMotor);
	}

	public boolean increaseSpeed(NXTRegulatedMotor... motors){
		boolean isIncreasable=true;
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				if(m.getSpeed()<MAXSpeed){
					m.setSpeed(m.getSpeed()+10);
				}else{
					isIncreasable=false;
				}
			}
		}
		return isIncreasable;
	}

	public boolean increaseSpeed(){
		return increaseSpeed(this.leftMotor,this.rightMotor);
	}

	public boolean decreaseSpeed(NXTRegulatedMotor... motors){
		boolean isIncreasable=true;
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				if(m.getSpeed()>MINSpeed){
					m.setSpeed(m.getSpeed()-10);
					isIncreasable = true;
				}else{
					isIncreasable=false;
				}
			}
		}
		return isIncreasable;
	}

	public boolean decreaseSpeed(){
		return decreaseSpeed(this.leftMotor,this.rightMotor);
	}


	public boolean setSpeed(int speed,NXTRegulatedMotor... motors){
		if(speed>MAXSpeed){
			return false;
		}
		for(NXTRegulatedMotor m :motors){
			if(m!=null){
				m.setSpeed(speed);
			}
		}
		return true;
	}

	public boolean setSpeed(){
		return setSpeed(AVGSPEED,this.leftMotor,this.rightMotor);
	}


	public void turnLeftAngle(int angle){
		if(this.leftMotor!=null && this.rightMotor!=null){
			if(this.centerMotor!=null){
				this.centerMotor.rotate(angle);
			}
			this.leftMotor.rotate(angle);
			while(this.leftMotor.isMoving()){
				moveForward();
			}
			//this.rightMotor.rotate(angle);
		}

	}

	public void turnRightAngle(int angle){
		if(this.leftMotor!=null && this.rightMotor!=null){
			if(this.centerMotor!=null){
				this.centerMotor.rotate(angle);
			}
			//this.leftMotor.rotate(angle);
			this.rightMotor.rotate(angle);
			while(this.rightMotor.isMoving()){
				moveForward();
			}
		}

	}

	public void moveRotationLeft(){
		if(this.leftMotor!=null && this.rightMotor!=null){
			if(rightMotor.getSpeed()<AVGSPEED){
				this.rightMotor.setSpeed(AVGSPEED);
			}
			/*for (int i = 0; i < AVGSPEED/2; i++) {
				decreaseSpeed(leftMotor);
				//increaseSpeed(rightMotor);
			}*/
			this.leftMotor.setSpeed(AVGSPEED/3);
		}
	}

	public void moveRotationRight(){
		if(this.leftMotor!=null && this.rightMotor!=null){
			if(leftMotor.getSpeed()<AVGSPEED){
				this.leftMotor.setSpeed(AVGSPEED);

			}
			this.rightMotor.setSpeed(AVGSPEED/3);
			//for (int i = 0; i < AVGSPEED/2; i++) {
			//decreaseSpeed(rightMotor);
			//increaseSpeed(leftMotor);
			//}
		}
	}

	/**
	 * 	// Demi tour à droite
	 */
	public void turnAroundLeft() {
		setSpeed();
		for(int i = 0; i<=1300; i++) {
			turnLeftForward();
		}
	}

	/**
	 * 	// Demi tour à gauche
	 */
	public void turnAroundRight() {
		setSpeed();
		for(int i = 0; i<=1300; i++) {
			turnRightForward();
		}
	}



	public NXTRegulatedMotor getLeftMotor() {
		return leftMotor;
	}

	public NXTRegulatedMotor getCenterMotor() {
		return centerMotor;
	}

	public NXTRegulatedMotor getRightMotor() {
		return rightMotor;
	}
}
