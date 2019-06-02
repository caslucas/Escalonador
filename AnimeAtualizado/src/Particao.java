
public class Particao  {
	
	int tamanho = 0;
	int posCheckX = 0;
	int posCheckY = 0;
	int posParadaX = 0;
	int posParadaY = 0;
	boolean lastInserted = false;


	
	public boolean isLastInserted() {
		return lastInserted;
	}


	public void setLastInserted(boolean lastInserted) {
		this.lastInserted = lastInserted;
	}


	public Particao(int tamanho, int posCheckX, int posCheckY, int posParadaX, int posParadaY) {
		super();
		this.tamanho = tamanho;
		this.posCheckX = posCheckX;
		this.posCheckY = posCheckY;
		this.posParadaX = posParadaX;
		this.posParadaY = posParadaY;
	}
	

	public int getTamanho() {
		return tamanho;
	}



	public int getPosCheckX() {
		return posCheckX;
	}



	public int getPosCheckY() {
		return posCheckY;
	}



	public int getPosParadaX() {
		return posParadaX;
	}



	public int getPosParadaY() {
		return posParadaY;
	}
	
	public void proxPosicao() {
		
		this.posParadaX -= 44;
	}
	
	public void proxParticao() {
		
		if( getTamanho() >0) {
			
		}
		
	}
	
	

}
