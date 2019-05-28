import java.util.List;


public class Particao {
	
	int tamanho = 1;
	int posCheckX = 1;
	int posCheckY = 0;
	int posParadaX = 1;
	int posParadaY = 1;


	
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
	
	

}
