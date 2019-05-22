

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class JMemory extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConcurrentLinkedQueue<Thread>  tQueueFirstFit =
            new ConcurrentLinkedQueue<Thread>();
	
	private ConcurrentLinkedQueue<Thread>  tQueueWorstFit=
            new ConcurrentLinkedQueue<Thread>();
	
	private ConcurrentLinkedQueue<Thread>  tQueueBestFit=
            new ConcurrentLinkedQueue<Thread>();
	
	//JLabel processo = new JLabel(new ImageIcon(getClass().getResource("caixa.png")));
	JLabel memory = new JLabel(new ImageIcon(getClass().getResource("memory.png")));
	JLabel memory2 = new JLabel(new ImageIcon(getClass().getResource("memory.png")));
	JLabel back = new JLabel(new ImageIcon(getClass().getResource("back.gif")));
	JButton firstFit = new JButton("First Fit");
	JButton worstFit = new JButton("Worst Fit");
	JButton bestFit = new JButton("Best Fit");
	
	List<JBoxMemory> boxes = null;
	
	int boxCoordinateY = 80;
	int boxCoordinateX = 3;
	int velocity = 0;
	int tamProcesso;
	
	private static int tamMemA = 8, tamMemB = 8;
	
	public JMemory(List<JBoxMemory> boxes, int  tamProcesso) {
		this.boxes = boxes;
		this.tamProcesso = tamProcesso;
		init();
	}
	
	public void init() {

		Container teste = getContentPane();
		teste.setLayout(null);
		
		bestFit.setBounds	(250, 430, 100, 20);
		worstFit.setBounds	(130, 430, 100, 20);
		firstFit.setBounds	(10,  430, 100, 20);
		memory.setBounds	(240, 10, 270, 200);
		memory2.setBounds	(240, 220, 270, 200);
		back.setBounds		(0, 0, 500, 500);
		add(memory);
		add(memory2);
		add(firstFit);
		add(worstFit);
		add(bestFit);
		
		firstFit.setVisible(true);

		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		boxes.forEach((c) ->{
			
			c.setCoordinateXY(boxCoordinateX, boxCoordinateY);
			boxCoordinateX += 0;
			boxCoordinateY += 60;
			add(c);
		});
		add(memory);
		add(memory2);
		add(back);
	
		
		/*########################### Tudo certo  ###################*/
		boxes.forEach((c) -> {
			tQueueFirstFit.add(new Thread() {
				public void run() {
						c.run(240, 70);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if(c.getTamProcess()<=tamMemA) {
							c.runAsThread(340, 70).start();
							tamMemA = tamMemA-c.getTamProcess();
							
						}else if(c.getTamProcess()<=tamMemB) {
							c.ifTamProcessBiggerTamMemory();
							tamMemB = tamMemB -c.getTamProcess();
						}else {
							
							JOptionPane.showMessageDialog(null, "Não há espaço na memoria" + "\n" +"ou tamanho do processo" + "\n" + "maior que o tamanho da memoria");
						}							
					}
				});
			});
		

		firstFit.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueueFirstFit.forEach(t -> {
							t.start();
							try {
								t.join();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							
						});
					}
				}.start();
			}
		});	
		
		/*########################### Comparar o tamanho da memoria para que o processo sempre vá para a memoria que tem maiortamanho ###################*/
		boxes.forEach((c) -> {
			tQueueWorstFit.add(new Thread() {
				public void run() {
						c.run(240, 70);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if(c.getTamProcess()<tamMemA) {
							c.runAsThread(340, 70).start();
							tamMemA = tamMemA - c.getTamProcess();
						}	
						 if(tamMemA<tamMemB) {
								c.ifTamProcessBiggerTamMemory();
							}
						
						
					}
				});
			});
		

		worstFit.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueueWorstFit.forEach(t -> {
							t.start();
							try {
								t.join();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							
						});
					}
				}.start();
			}
		});
		
		
		/*############################# tudo certo ##########################*/
		boxes.forEach((c) -> {
			tQueueBestFit.add(new Thread() {
				public void run() {
						c.run(240, 70);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if(c.getTamProcess()<=tamMemA) {
							c.runAsThread(340, 70).start();
							tamMemA = tamMemA-c.getTamProcess();
							
						}else if(c.getTamProcess()<=tamMemB) {
							c.ifTamProcessBiggerTamMemory();
							tamMemB = tamMemB -c.getTamProcess();
						}else {
							
							JOptionPane.showMessageDialog(null, "Não há espaço na memoria" + "\n" +"ou tamanho do processo" + "\n" + "maior que o tamanho da memoria");
						}							
					}
				});
			});
		
		
		bestFit.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueueBestFit.forEach(t -> {
							t.start();
							try {
								t.join();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							
						});
					}
				}.start();
			}
		});
		
		/*######################## fazer next #########################*/
	}	
}
