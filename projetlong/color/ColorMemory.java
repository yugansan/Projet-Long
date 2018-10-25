package color;

import java.util.ArrayList;


public class ColorMemory {
	private int interEps =10;
	private ArrayList<ColorRGB>colorMemory;
	
	public ColorMemory(){
		this.colorMemory=new ArrayList<>();
	}
	
	/**
	 * 
	 * @param position la position ou se trouve la couleur
	 * @return la couleur si elle existe null sinon
	 */
	public ColorRGB getColorAtPosition(int position){
		if(position>=0 && position< colorMemory.size()){
			return colorMemory.get(position);
		}else{
			return null;
		}
	}
	
	
	/**
	 *	MEME CODE A VOIR SIMPLIFICATION  isCouleurExistant
	 * @param position la position ou se trouve la couleur
	 * @return la couleur si elle existe null sinon
	 */
	public int getColorPosition(int [][] colorRGB){
		if(colorRGB!=null){
			int [][] colorsRGBtmp = getMINMAXColor(colorRGB);
			for (int i = 0; i < colorsRGBtmp[0].length; i++) {
				for(int j = 0;j< colorMemory.size();j++){
					if(colorMemory.get(j).isInInterval(colorsRGBtmp[0][i], colorsRGBtmp[1][i], colorsRGBtmp[2][i])){
						return j;
					}
				}
			}
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @param colorRGB couleur RGB a verifier
	 * @return true si elle existe dans la memoire false sinon
	 */
	public boolean isCouleurExistant(int [][] colorRGB){
		if(colorRGB!=null){
			int [][] colorsRGBtmp = getMINMAXColor(colorRGB);
			for (int i = 0; i < colorsRGBtmp[0].length; i++) {
				for(int j = 0;j< colorMemory.size();j++){
					if(colorMemory.get(j).isInInterval(colorsRGBtmp[0][i], colorsRGBtmp[1][i], colorsRGBtmp[2][i])){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param colorRGB : la couleur a ajouter
	 * @return true si la couleur n'existe pas dans la memoire et qu'elle a etait enregistrer false sinon
	 */
	public boolean addColor(int[][] colorRGB){
		if(colorRGB!=null){
			int [][] colorsRGBtmp = getMINMAXColor(colorRGB);
			if(colorsRGBtmp.length==3){
				if(colorMemory.isEmpty()){
					//System.out.println("empty memory");
					colorMemory.add(new ColorRGB(colorsRGBtmp[0][0]-interEps, colorsRGBtmp[0][1]+interEps, colorsRGBtmp[1][0]-interEps,
							colorsRGBtmp[1][1]+interEps, colorsRGBtmp[2][0]-interEps, colorsRGBtmp[2][1]+interEps));
					return true;
				}
				else{
					for (int i = 0; i < colorsRGBtmp[0].length; i++) {
						for(int j = 0;j< colorMemory.size();j++){
							if(colorMemory.get(j).isInInterval(colorsRGBtmp[0][i], colorsRGBtmp[1][i], colorsRGBtmp[2][i])){
								//System.out.println("couleur existant n° "+j);
								return false;
							}
						}
					}
					colorMemory.add(new ColorRGB(colorsRGBtmp[0][0]-interEps, colorsRGBtmp[0][1]+interEps, colorsRGBtmp[1][0]-interEps,
							colorsRGBtmp[1][1]+interEps, colorsRGBtmp[2][0]-interEps, colorsRGBtmp[2][1]+interEps));
					return true;	
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param colorRGB : tableau des valeurs corespondant respectivement a rouge, vert, bleu  
	 * @return un tableau de 3lignes corespondant au minimum maximum de chaque couleur RGB
	 */
	private int [][]getMINMAXColor(int[][] colorRGB){
		if(colorRGB.length==3){
			int [][]colorsInOrder=new int[3][2];
			colorsInOrder[0][0]=colorsInOrder[0][1]=colorRGB[0][0];
			colorsInOrder[1][0]=colorsInOrder[1][1]=colorRGB[1][0];
			colorsInOrder[2][0]=colorsInOrder[2][1]=colorRGB[2][0];
			for (int i = 0; i < colorRGB[0].length; i++) {
				//0 : RED
				if(colorRGB[0][i]<=colorsInOrder[0][0]){
					colorsInOrder[0][0]=colorRGB[0][i];
				}else if(colorRGB[0][i]>colorsInOrder[0][1]){
					colorsInOrder[0][1]=colorRGB[0][i];
				}
				//1 : GREEN
				if(colorRGB[1][i]<=colorsInOrder[1][0]){
					colorsInOrder[1][0]=colorRGB[1][i];
				}else if(colorRGB[1][i]>colorsInOrder[1][1]){
					colorsInOrder[1][1]=colorRGB[1][i];
				}
				// 2: BLUE
				if(colorRGB[2][i]<=colorsInOrder[2][0]){
					colorsInOrder[2][0]=colorRGB[2][i];
				}else if(colorRGB[2][i]>colorsInOrder[2][1]){
					colorsInOrder[2][1]=colorRGB[2][i];
				}
			}
			return colorsInOrder;
		}
		return null;
	}
}
