/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;




public class Start  extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ImageIcon ICON = new ImageIcon("src/capa.png");
	JLabel back = new JLabel(new ImageIcon(getClass().getResource("anigif.gif")));
	JLabel label = new JLabel(new ImageIcon(getClass().getResource("tog.png")));
	JButton animationCar = new JButton("Animação Escalonador");
	JButton memoryGen = new JButton("Animação  Memoria");
	
	
	public Start(){
		Container main = getContentPane();
		main.setLayout(null);
		
		animationCar.setBounds		(300,300,300,50);
		memoryGen.setBounds			(800,300,300,50);
		label.setBounds				(200, 0, 957, 110);
		back.setBounds				(0, 0, 1400, 450);
			
		memoryGen.setOpaque(false);
		memoryGen.setBorder(new LineBorder(Color.black));
		memoryGen.setBackground(Color.white);
		memoryGen.setForeground(Color.black);
		memoryGen.setFont(new Font("Dialog", Font.BOLD, 20));
		
		animationCar.setOpaque(false);
		animationCar.setBorder(new LineBorder(Color.black));
		animationCar.setBackground(Color.white);
		animationCar.setForeground(Color.black);
		animationCar.setFont(new Font("Dialog", Font.BOLD, 20));
	
		
		add(memoryGen);
		add(animationCar);
		add(label);
		add(back);
		
		setSize(1400, 450);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		List<JCar> cars = new ArrayList<JCar>();
		
		
		/*int teste = 3; 
		
		for (int i = 0; i < teste; i++) {
			cars.add(new JCar("src/sava.png", Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do processo nº"+ (i+1) )), 8));
		}
		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Digite o quantum de tempo"));
*/		cars.add(new JCar("src/sava.png", 4, 8, "Processo 1"));
		cars.add(new JCar("src/sava.png", 3, 8, "Processo 2"));
		cars.add(new JCar("src/sava.png", 5, 8, "Processo 3"));
		int quantum = 2;
		
		
		animationCar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JTunnel(cars, quantum);
				dispose();
				
			}
		});	
		
		
		List<JBoxMemory> boxess = new ArrayList<JBoxMemory>();
		
		boxess.add(new JBoxMemory("src/caixa.png",1, 5));
		boxess.add(new JBoxMemory("src/caixa.png",2, 5));
		boxess.add(new JBoxMemory("src/caixa.png",2, 5));
		boxess.add(new JBoxMemory("src/caixa.png",4, 5));
		boxess.add(new JBoxMemory("src/caixa.png",3, 5));
		boxess.add(new JBoxMemory("src/caixa.png",1, 5));	
		boxess.add(new JBoxMemory("src/caixa.png",2, 5));	
		
		int tam = 8;
	
		//new FisrtFit(boxes, tam);	
		
		
		List<Particao> partition = new ArrayList<Particao>();
		
		partition.add(new Particao(5, 255, 60, 340, 56));
		partition.add(new Particao(0, 280, 118, 340, 56));
		partition.add(new Particao(8, 280, 177, 340, 56)); 
		partition.add(new Particao(0, 280, 220, 340, 56));
		partition.add(new Particao(2, 280, 301, 300, 100));


		
		//System.out.println(partition.stream().max((Particao u1, Particao u2) -> 
			//Integer.compare(u1.getTamanho(), u2.getTamanho())).get().getTamanho());
		
		
		
		memoryGen.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JMemory(boxess, tam, partition);
				dispose();
				
			}
		});	
		
		setIconImage(getIcon().getImage());
	}
	
	
	
	public static ImageIcon getIcon() {
		return ICON;
	}
	
	public static void main(String[] args) {
		new Start();
	}
}
