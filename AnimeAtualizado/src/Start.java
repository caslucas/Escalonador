/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Start {

	public static void main(String[] args) {
		List<JCar> cars = new ArrayList<JCar>();
		
		int teste = 3; 
		
		for (int i = 0; i < teste; i++) {
			cars.add(new JCar("src/sava.png", Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do processo nº"+ (i+1) )), 8));
		}
		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Digite o quantum de tempo"));

		/*
		cars.add(new JCar("src/sava.png", 3, 8));
		cars.add(new JCar("src/sava.png", 3, 8));
		cars.add(new JCar("src/sava.png", 3, 8));
		int quantum = 2;
		*/
		
		new JTunnel(cars, quantum);
	}
}
