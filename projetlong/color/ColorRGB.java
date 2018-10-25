package color;

public class ColorRGB {
	private int []REDinterval;
	private int []GREENinterval;
	private int []BLUEinterval;
	
	/**
	 * 
	 * @param REDMin
	 * @param REDMax
	 * @param GREENMin
	 * @param GREENMax
	 * @param BLUEMin
	 * @param BLUEMax
	 */
	public ColorRGB(int REDMin,int REDMax,
			int GREENMin,int GREENMax,
			int BLUEMin,int BLUEMax){
		this.REDinterval=new int[2];
		REDinterval[0]=REDMin;
		REDinterval[1]=REDMax;
		this.GREENinterval=new int[2];
		GREENinterval[0]=GREENMin;
		GREENinterval[1]=GREENMax;
		this.BLUEinterval=new int[2];
		BLUEinterval[0]=BLUEMin;
		BLUEinterval[1]=BLUEMax;
	}
	
	
	public boolean isInInterval(int red,int green, int blue){
		if( (red>=this.REDinterval[0] && red<=this.REDinterval[1]) &&
				(green>=this.GREENinterval[0] && green<=this.GREENinterval[1]) &&
				(blue>=this.BLUEinterval[0] && blue<=this.BLUEinterval[1]) ){
			return true;
		}else{
			return false;
		}
		
	}
	
	
	

}
