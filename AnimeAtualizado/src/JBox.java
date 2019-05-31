/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JBox extends JLabel {

	private static final long serialVersionUID = 1L;
	
	//private int boxDefaultCoordinateX = 0;
	//private int boxDefaulCoordinateY = 80;
	private int boxDefaultWidth = 50;
	private int boxDefaultHeight = 330;
	 

	public JBox(int boxDefaultCoordinateX, int boxDefaulCoordinateY) {
		setIcon(new ImageIcon("src/caixa.png"));
		setBounds(boxDefaultCoordinateX, boxDefaulCoordinateY, boxDefaultWidth, boxDefaultHeight);
	}
	
	public JBox(int boxDefaultCoordinateX, int boxDefaulCoordinateY, JBox sideJBox, int addToCoordX) {
		int newCoordinateX = sideJBox.getX() + addToCoordX;
		
		setBounds(newCoordinateX, boxDefaulCoordinateY, 
				boxDefaultWidth, boxDefaultHeight);
	}
}
