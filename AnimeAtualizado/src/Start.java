/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class Start  extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel backMain = new JLabel(new ImageIcon(getClass().getResource("src/giphy.gif")));
	JButton chama = new JButton("Chama");
	public Start(){
		Container main = getContentPane();
		main.setLayout(null);
		
		chama.setBounds(10,10,100,20);
		add(chama);
		add(backMain);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		List<JCar> cars = new ArrayList<JCar>();
		
		/*int teste = 3; 
		
		for (int i = 0; i < teste; i++) {
			cars.add(new JCar("src/sava.png", Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do processo nº"+ (i+1) )), 8));
		}
		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Digite o quantum de tempo"));
*/		cars.add(new JCar("src/sava.png", 3, 8));
		cars.add(new JCar("src/sava.png", 4, 8));
		cars.add(new JCar("src/sava.png", 5, 8));
		int quantum = 2;

		
		chama.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JTunnel(cars, quantum);
				dispose();
				
			}
		});	

}
	public static void main(String[] args) {
		new Start();
	}
}
