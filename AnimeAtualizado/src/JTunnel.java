/****************************
 * @author : Lucas Lima
 * Email: lulima559@gmail.com
 ****************************/
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import javax.swing.border.LineBorder;
import jaco.mp3.player.MP3Player;
import javax.swing.*;

public class JTunnel extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JButton FCFS 		= new JButton("FCFS");
	JButton SJF 		= new JButton("SJF");
	JButton RR  		= new JButton("RR");
	JButton priority	= new JButton("Priority");
	JButton random		= new JButton("Random");
	JButton music 		= new JButton("	NoMusic");
	JLabel back 		= new JLabel(new ImageIcon("src/backkk2.png"));
	JLabel camada 		= new JLabel(new ImageIcon("src/camada.png"));
	JLabel camada2 		= new JLabel(new ImageIcon("src/camada2.png"));
	 
    private static final ImageIcon ICON = new ImageIcon("src/capa.png");
    
	List<JCar> cars = null;
	
	int carCoordinateY = 150;
	int carCoordinateX = 3;
	
	private ConcurrentLinkedQueue<Thread>  tQueue =
            new ConcurrentLinkedQueue<Thread>();
	
	private ConcurrentLinkedQueue<Thread>  tQueueFCFS=
            new ConcurrentLinkedQueue<Thread>();
	
	private ConcurrentLinkedQueue<Thread>  tQueueRR=
            new ConcurrentLinkedQueue<Thread>();
	
	private ConcurrentLinkedQueue<Thread>  tQueuePriority =
            new ConcurrentLinkedQueue<Thread>();
	
	
	private int quantum;
	public JTunnel(List<JCar> cars, int quantum) {
		this.cars = cars;
		this.quantum = quantum;
		init();
	}
	
	private void init() {
		Container pprincipal = getContentPane();
		pprincipal.setLayout(null);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize					(1400, 450);
		setLocationRelativeTo	(null);
		setVisible				(true);
		setLayout				(null);
			
		FCFS.setBounds 		(10, 35, 100, 20);
		SJF.setBounds 		(120, 35, 100, 20);
		RR.setBounds 		(230, 35, 100, 20);
		priority.setBounds 	(340, 35, 100, 20);
		random.setBounds 	(450, 35, 100, 20);
		music.setBounds		(1230, 20, 110, 50);
		back.setBounds		(0, 0, 1400, 450);
		camada.setBounds	(300, 35, 900, 312);
		camada2.setBounds	(50, 35, 900, 312);
		
		SJF.setOpaque(false);
		SJF.setBorder(new LineBorder(Color.black));
		SJF.setBackground(Color.white);
		SJF.setForeground(Color.black);
		
		FCFS.setOpaque(false);
		FCFS.setBorder(new LineBorder(Color.black));
		FCFS.setBackground(Color.white);
		FCFS.setForeground(Color.black);
		
		RR.setOpaque(false);
		RR.setBorder(new LineBorder(Color.black));
		RR.setBackground(Color.white);
		RR.setForeground(Color.black);
		
		priority.setOpaque(false);
		priority.setBorder(new LineBorder(Color.black));
		priority.setBackground(Color.white);
		priority.setForeground(Color.black);
		
		random.setOpaque(false);
		random.setBorder(new LineBorder(Color.black));
		random.setBackground(Color.white);
		random.setForeground(Color.black);
		
		music.setOpaque(false);
		music.setBorder(new LineBorder(Color.black));
		music.setBackground(Color.white);
		music.setForeground(Color.black);
		music.setFocusable(false);
		music.setIcon(new ImageIcon("src/download.png"));
		
		add(FCFS);
		add(SJF);
		add(RR);
		add(priority);
		add(random);
		add(music);
		add(camada);
		
		Collections.reverse(cars);

		cars.forEach((c) ->{
			c.setCoordinateXY(carCoordinateX, carCoordinateY);
			carCoordinateX += 63;
			carCoordinateY -= 50;
			add(c);
		});
		
		Collections.reverse(cars);
		
		cars.forEach((c) -> {
			tQueueFCFS.add(new Thread() {
				public void run() {
					c.run(680, 117);			
					for (int i = c.getBoxes().size(); i > 0 ; i--) {
						String message = i == 1 ? (i+": segundo restante"):(i+": segundos restantes");
						Utils.showMessage(message,"Processando...", 800);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					c.removeAllBoxes();
					c.runAsThread(2000, 117).start();
				}
			});
		});
		
		cars.forEach((c) -> {
			tQueueRR.add(new Thread() {
				public void run() {
					c.run(680, 117);		
					int max = quantum>c.getBoxes().size()? c.getBoxes().size(): quantum;
					for (int i = max; i > 0 ; i--) {
						String message = i == 1 ? (i+": segundo restante"):(i+": segundos restantes");
						Utils.showMessage(message,"Processando...", 800);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					c.removeBoxesQuantum(quantum);
					
					if(c.getBoxes().size()>0) {
						c.ifBoxesBackInit();
					}else
						c.runAsThread(2000, 117).start();	
				}
			});
		});
		
		Collections.sort(cars, new Comparator<JCar>() { 
			@Override public int compare(JCar u1, JCar u2) { 
				return u2.getBoxes().size() - u1.getBoxes().size() ;
			} 
		});

		cars.forEach((c) -> {
			tQueuePriority.add(new Thread() {
				public void run() {
					c.run(680, 117);
					for (int i = c.getBoxes().size(); i > 0 ; i--) {
						String message = i == 1 ? (i+": segundo restante"):(i+": segundos restantes");
						Utils.showMessage(message,"Processando...", 800);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					c.removeAllBoxes();
					c.runAsThread(2000, 117).start();
				}
			});
		});
		
		Collections.sort(cars, new Comparator<JCar>() { 
			@Override public int compare(JCar u1, JCar u2) { 
				return u1.getBoxes().size() - u2.getBoxes().size();
			} 
		});
		
		cars.forEach((c) -> {
			tQueue.add(new Thread() {
				public void run() {
					c.run(680, 117);
					for (int i = c.getBoxes().size(); i > 0 ; i--) {
						String message = i == 1 ? (i+": segundo restante"):(i+": segundos restantes");
						Utils.showMessage(message,"Processando...", 800);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					c.removeAllBoxes();
					c.runAsThread(2000, 117).start();
				}
			});
		});
		
		add(camada2);
		add(back);
			
		SJF.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueue.forEach(t -> {
							t.start();
							try {
								t.join();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						});
					}
				}.start();
				mp3player.play();
			}
		});
		
		FCFS.addActionListener( new ActionListener() {   
				public void actionPerformed(ActionEvent e) {
					new Thread() {
						public void run() {
							tQueueFCFS.forEach(t -> {
								t.start();
								try {
									t.join();
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							});
						}
					}.start();
					mp3player.play();
				}
			});
		
		RR.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						//Collections.reverse(cars);
						
						ConcurrentLinkedQueue<Thread>  tQueueRR = null;
								
						while(true) {
							List<JCar> carsWithBoxes = cars.stream()
									.filter(c -> c.getBoxes().size()>0)
									.sorted((c1, c2 )-> c2.getBoxes().size() - c1.getBoxes().size())
									.collect(Collectors.toList());
								
							if(carsWithBoxes.size() == 0) 
								break;
							
							tQueueRR = new ConcurrentLinkedQueue<Thread>();
							
							for (JCar jCar : carsWithBoxes) {
								if(!jCar.hasAnyThreadRunning()) {
									tQueueRR.add(jCar.RR(quantum));	
								}	
							}
							
							tQueueRR.forEach(t -> {
								t.start();
								try {
									t.join();
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
							});
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
				mp3player.play();
			}
		});
		
		priority.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueuePriority.forEach(t -> {
							t.start();
							try {
								t.join();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						});
					}
				}.start();
				mp3player.play();
			}
		});
		
		random.addActionListener( new ActionListener() {   
			public void actionPerformed(ActionEvent e) {
				Random rando = new Random();
				int randog = rando.nextInt(2);
				
				if(randog == 1) {
					String priority = ("Rodando processo de Prioridade");
					Utils.showMessage(priority, "Prioridade", 1000);
					
					//JOptionPane.showMessageDialog(null, "roda teste1");
			
				new Thread() {
					public void run() {
						tQueuePriority.forEach(t -> {
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
				
				else {
					String rr = ("Rondado o processo Round Robin");
					Utils.showMessage(rr, "Round Robin", 1000);
					//JOptionPane.showMessageDialog(null, "roda teste 2");
					new Thread() {
						public void run() {
							//Collections.reverse(cars);
							ConcurrentLinkedQueue<Thread>  tQueueRR = null;
									
							while(true) {
								List<JCar> carsWithBoxes = cars.stream()
										.filter(c -> c.getBoxes().size()>0)
										.sorted((c1, c2 )-> c2.getBoxes().size() - c1.getBoxes().size())
										.collect(Collectors.toList());
									
								if(carsWithBoxes.size() == 0) 
									break;
								
								tQueueRR = new ConcurrentLinkedQueue<Thread>();
								
								for (JCar jCar : carsWithBoxes) {
									if(!jCar.hasAnyThreadRunning()) {
										tQueueRR.add(jCar.RR(quantum));	
									}	
								}
								
								tQueueRR.forEach(t -> {
									t.start();
									try {
										t.join();
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
								});
								
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				mp3player.play();
			}
			
		});
		
		 setIconImage(getIcon().getImage());
			 
		 music.addActionListener( new ActionListener() {   
				public void actionPerformed(ActionEvent e) {
					       mp3player.pause();
				}
		 });
	}
	 
	public static final String SONG = "src/music/di1.mp3";
	
	MP3Player mp3player = new MP3Player(new File(SONG));

	public static ImageIcon getIcon() {
		return ICON;
	}
}
