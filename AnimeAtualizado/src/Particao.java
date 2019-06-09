import java.io.File;

import jaco.mp3.player.MP3Player;

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
		mp3player1.play();
		
	}
	

	
	public static final String SONG = "src/music/puloMario.mp3";
	
	MP3Player mp3player1 = new MP3Player(new File(SONG));
	

}
