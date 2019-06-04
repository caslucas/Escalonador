/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JCar extends JLabel {
	public String Name;
	
	private static final long serialVersionUID = 1L;
	//List<JQtdCars> cars = new ArrayList<JQtdCars>();
	private boolean hasAnyThreadRunning = false;
	List<JBox> boxes = new ArrayList<JBox>();
	public List<JBox> getBoxes() {
		return boxes;
	}

	public void setBoxes(List<JBox> boxes) {
		this.boxes = boxes;
	}

	private int capacityOfCarH = 2;
	private int velocity = 12;
	
	int boxDefaultCoordinateX = 0;
	int boxDefaulCoordinateY = -35; 
	
	int qtdBoxes = 0;
	
	String nomeProcess;
	
	
	public String getNomeProcess() {
		return nomeProcess;
	}

	public JCar(String iconPath, int qtdBoxes, int velocity, String nomeProcess) {
		this.velocity = velocity;
		this.qtdBoxes = qtdBoxes;
		setIcon(new ImageIcon(iconPath));
		setText(nomeProcess);
		setHorizontalTextPosition(CENTER);
		//setVerticalTextPosition(10);
		setFont(new Font("SansSerif", Font.BOLD, 19));
		setBounds(3, 100, 200, 330);
		insertBoxesByQty(qtdBoxes);
		
	}
	


	public boolean hasAnyThreadRunning() {
		return hasAnyThreadRunning;
	}

	public void setCoordinateXY(int carCoordinateX, int carCoordinateY) {
		setBounds(carCoordinateX, carCoordinateY, 200, 300);
	}		
	
	
	private void insertBoxesByQty(int qtd) {
		
		int rows = qtd/capacityOfCarH;
		
		int leftoverBoxes = qtd%capacityOfCarH;
		
		JBox lastbox = null;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < capacityOfCarH; j++) {
				JBox box = null;
				
				if(lastbox != null) {
					box = new JBox(lastbox.getX() + 32, boxDefaulCoordinateY);
				}else
				{
					box = new JBox(boxDefaultCoordinateX, boxDefaulCoordinateY);
				}
				
				boxes.add(box);
				add(box);
				lastbox = box;
			}
			
			boxDefaulCoordinateY -= 30;
			boxDefaultCoordinateX = 0;
			lastbox = null;
		}
		
		for (int j = 0; j < leftoverBoxes; j++) {
			JBox box = null;
			
			if(lastbox != null) {
				box = new JBox(lastbox.getX() + 32, boxDefaulCoordinateY);
			}else
			{
				box = new JBox(boxDefaultCoordinateX, boxDefaulCoordinateY);
			}
			
			boxes.add(box);
			add(box);
			lastbox = box;
		}
	}

	public void run(int limit, int posY) {
		moveY(posY);
		moveX(limit);
	}
	
	public Thread runAsThread(int limit, int posY) {
		return new Thread() {
			public void run() {
				JCar.this.run(limit, posY);
			}
		};
	}
	
	public void removeAllBoxes() {
		boxes.forEach(b -> {
			remove(b);
		});
		boxes.clear();
	}
	
	public void removeBoxesQuantum(int quantum) {
		int max = quantum>boxes.size()? boxes.size(): quantum;
		for (int i = 0; i < max; i++) {
			JBox box = boxes.remove(boxes.size()-1);
			remove(box);
		}	
	}
	
	
	public void moveX(int limit) {
		if(getX()<limit) {
			while(getX()<limit) {
				try{Thread.sleep(20);} catch(Exception erro) {}
				setBounds(getX() + velocity, getY(), getWidth(), getHeight());
			}
		}else{
			while(getX()>limit) {
				try{Thread.sleep(20);} catch(Exception erro) {}
				setBounds(getX() - velocity, getY(), getWidth(), getHeight());
			}
		}
		setBounds(limit, getY(), getWidth(), getHeight());
	}
	
	
	public void moveY(int limit) {
		if(getY()<limit) {
			while(getY()<limit) {
				try{Thread.sleep(20);} catch(Exception erro) {}
				setBounds(getX(), getY()+velocity, getWidth(), getHeight());
			}
		}else{
			while(getY()>limit) {
				try{Thread.sleep(20);} catch(Exception erro) {}
				setBounds(getX(), getY() - velocity, getWidth(), getHeight());
			}
		}
		setBounds(getX(), limit, getWidth(), getHeight());
	}
			
	public void ifBoxesBackInit() {
		hasAnyThreadRunning = true;
		new Thread() {
			public void run() {
				JCar.this.run(1159, 117);
				moveY(117+100);
				moveX(3);
				moveY(117);
				
				hasAnyThreadRunning = false;
			}
		}.start();
	}
	
	public Thread RR(int quantum) {
		return new Thread() {
			public void run() {
				JCar.this.run(680, 117);		
				int max = quantum>getBoxes().size()? getBoxes().size(): quantum;
				for (int i = max; i > 0 ; i--) {
					String message = i == 1 ? (i+": segundo restante"):(i+": segundos restantes");
					Utils.showMessage(message,"Processando...", 800);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				removeBoxesQuantum(quantum);
				
				if(getBoxes().size()>0) {
					ifBoxesBackInit();
				}else
					runAsThread(2000, 117).start();	
			}
		};
	}
	
	 public void getRandomElement()  { 
		 List<JCar> cars = new ArrayList<JCar>();
			Collections.shuffle(cars); 
	    } 
}
