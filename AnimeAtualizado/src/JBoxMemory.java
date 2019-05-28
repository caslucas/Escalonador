import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JBoxMemory extends JLabel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean hasAnyThreadRunning = false;
	int tamProcess = 0;
	int velocity = 12;
	
	public int getTamProcess() {
		return tamProcess;
	}
	
	public JBoxMemory(String iconPath, int tamProcess, int velocity) {
		this.tamProcess = tamProcess;
		this.velocity = velocity;
		setIcon(new ImageIcon(iconPath));
		setBounds(3, 50, 110, 50);	
	}
	
	public void setCoordinateXY(int boxCoordinateX, int boxCoordinateY) {
		setBounds(boxCoordinateX, boxCoordinateY, 110, 50);
	}
	
	public void run(int limit, int posY) {
		moveY(posY);
		moveX(limit);
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
				setBounds(getX(), getY() + velocity, getWidth(), getHeight());
			}
		}else{
			while(getY()>limit) {
				try{Thread.sleep(20);} catch(Exception erro) {}
				setBounds(getX(), getY() - velocity, getWidth(), getHeight());
			}
		}
		setBounds(getX(), limit, getWidth(), getHeight());
	}
	
	public Thread runAsThread(int limit, int posY) {
		return new Thread() {
			public void run() {
				JBoxMemory.this.run(limit, posY);
			}
		};
	}
	

	
	public void ifTamProcessBiggerTamMemory() {
		hasAnyThreadRunning = true;
		new Thread() {
			public void run() {
				JBoxMemory.this.run(300, 56);
				moveY(217);
				moveX(370);	
				hasAnyThreadRunning = false;
				}
			}.start();
		}
	
	public void ifMemoryBBiggerMemoryA() {
		hasAnyThreadRunning = true;
		new Thread() {
			public void run() {
				JBoxMemory.this.run(300, 56);
				moveY(180);
				//moveX(340);	
				hasAnyThreadRunning = false;
				}
			}.start();
		}
	}

