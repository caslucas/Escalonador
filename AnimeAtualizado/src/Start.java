/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import jaco.mp3.player.MP3Player;




public class Start  extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final ImageIcon ICON = new ImageIcon("src/images/capa.png");
	JLabel back = new JLabel(new ImageIcon(("src/images/anigif.gif")));
	JLabel label = new JLabel(new ImageIcon(("src/images/tog.png")));
	JButton animationCar = new JButton("Animação Escalonador");
	JButton memoryGen = new JButton("Animação  Memoria");
	JButton pdf = new JButton("Abrir PDF ");
	Desktop desktop = Desktop.getDesktop();  
	
	
	
	public Start(MP3Player mp){
		Container main = getContentPane();
		main.setLayout(null);
		
		animationCar.setBounds		(300,300,300,50);
		memoryGen.setBounds			(800,300,300,50);
		pdf.setBounds				(10, 300, 110, 100);
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
		
		pdf.setOpaque(false);
		pdf.setBorder(null); 
		pdf.setBackground(Color.white);
		pdf.setForeground(Color.black);
		pdf.setFocusable(false);
		pdf.setIcon(new ImageIcon("src/images/iconPDF.png"));
		
		add(memoryGen);
		add(animationCar);
		add(pdf);
		add(label);
		add(back);
		
		setSize(1400, 450);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		List<JCar> cars = new ArrayList<JCar>();
		
		
	/*	int teste = 3; 
		
		for (int i = 0; i < teste; i++) {
			cars.add(new JCar("src/sava.png", Integer.parseInt(JOptionPane.showInputDialog("Digite o tamanho do processo nº"+ (i+1) )), 8,""));
		}
		int quantum = Integer.parseInt(JOptionPane.showInputDialog("Digite o quantum de tempo"));*/
		cars.add(new JCar("src/images/sava.png", 4, 8, "Processo 1"));
		cars.add(new JCar("src/images/sava.png", 3, 8, "Processo 2"));
		cars.add(new JCar("src/images/sava.png", 5, 8, "Processo 3"));
		int quantum = 2;
		int garantiaa = 1/3;

		
		animationCar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JTunnel(cars, quantum, garantiaa);
				dispose();
			
			
				mp.stop();
			}
		});	
		
		
		List<JBoxMemory> boxess = new ArrayList<JBoxMemory>();
		
		boxess.add(new JBoxMemory("src/images/caixa.png",1, 5,"DHCP: 1"));
		boxess.add(new JBoxMemory("src/images/caixa.png",2, 5,"TCP:  2"));
		boxess.add(new JBoxMemory("src/images/caixa.png",2, 5,"HTTP: 2"));
		boxess.add(new JBoxMemory("src/images/caixa.png",4, 5,"FTP:  4"));
		boxess.add(new JBoxMemory("src/images/caixa.png",2, 5,"SSH:  2"));
		boxess.add(new JBoxMemory("src/images/caixa.png",2, 5,"POP3: 2"));	
		boxess.add(new JBoxMemory("src/images/caixa.png",1, 5,"SMTP: 1"));	
		
		int tam = 8;
	
		//new FisrtFit(boxes, tam);	
		
		
		List<Particao> partition = new ArrayList<Particao>();
		
		partition.add(new Particao(5, 190, 72, 438, 56));
		partition.add(new Particao(0, 280, 127, 438, 56));
		partition.add(new Particao(8, 280, 180, 438, 56)); 
		partition.add(new Particao(0, 280, 238, 438, 56));
		partition.add(new Particao(2, 280, 296, 278, 56));


		
		/*System.out.println(partition.stream().min((Particao u1, Particao u2) -> 
			Integer.compare(u1.getTamanho(), u2.getTamanho())).get().getTamanho());
		
		System.exit(0); */
		
		memoryGen.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JMemory(boxess, tam, partition);
				dispose();	
				
				mp.stop();
			}
		});	
		
		
		pdf.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					desktop.open(new File("src/pdf/pesquisa.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
			}
		});	
		
		setIconImage(getIcon().getImage());
		
	}
	


	public static ImageIcon getIcon() {
		return ICON;
	}
	
	public static void main(String[] args) {
		final String SONG = "src/music/tokio.mp3";
		
		MP3Player mp3player1 = new MP3Player(new File(SONG));
		new Start(mp3player1);
		mp3player1.play();

	}
}
