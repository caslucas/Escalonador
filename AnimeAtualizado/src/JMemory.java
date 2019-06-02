
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

public class JMemory extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ConcurrentLinkedQueue<Thread> tQueueFirstFit = new ConcurrentLinkedQueue<Thread>();

	private ConcurrentLinkedQueue<Thread> tQueueWorstFit = new ConcurrentLinkedQueue<Thread>();

	private ConcurrentLinkedQueue<Thread> tQueueBestFit = new ConcurrentLinkedQueue<Thread>();

	private ConcurrentLinkedQueue<Thread> tQueueNextFit = new ConcurrentLinkedQueue<Thread>();

	JLabel memory = new JLabel(new ImageIcon(getClass().getResource("memory.png")));
	JLabel memory2 = new JLabel(new ImageIcon(getClass().getResource("memory.png")));
	JLabel back = new JLabel(new ImageIcon(getClass().getResource("gif.gif")));
	JLabel ram = new JLabel(new ImageIcon(getClass().getResource("arquivoMorto5.png")));
	JButton firstFit = new JButton("First Fit");
	JButton worstFit = new JButton("Worst Fit");
	JButton bestFit = new JButton("Best Fit");
	JButton nextFit = new JButton("Next Fit");
	JButton voltar = new JButton("Voltar");

	List<JBoxMemory> boxes = null;
	List<Particao> particoes = null;

	int boxCoordinateY = 80;
	int boxCoordinateX = 3;
	int velocity = 0;
	int tamProcesso;

	private static int tamMemA = 8, tamMemB = 8;

	public JMemory(List<JBoxMemory> boxes, int tamProcesso, List<Particao> particoes) {
		this.boxes = boxes;
		this.tamProcesso = tamProcesso;
		this.particoes = particoes;
		init();
	}

	public void init() {

		Container teste = getContentPane();
		teste.setLayout(null);

		voltar.setBounds(10, 10, 110, 50);
		nextFit.setBounds(370, 430, 100, 20);
		bestFit.setBounds(250, 430, 100, 20);
		worstFit.setBounds(130, 430, 100, 20);
		firstFit.setBounds(10, 430, 100, 20);
		ram.setBounds(240, 10, 300, 400);
		back.setBounds(0, 0, 500, 500);

		add(firstFit);
		add(worstFit);
		add(bestFit);
		add(nextFit);
		add(voltar);

		firstFit.setVisible(true);
		firstFit.setOpaque(false);
		firstFit.setBorder(new LineBorder(Color.white));
		firstFit.setBackground(Color.white);
		firstFit.setForeground(Color.white);
		firstFit.setFocusable(false);

		worstFit.setOpaque(false);
		worstFit.setBorder(new LineBorder(Color.white));
		worstFit.setBackground(Color.white);
		worstFit.setForeground(Color.white);
		worstFit.setFocusable(false);

		bestFit.setOpaque(false);
		bestFit.setBorder(new LineBorder(Color.white));
		bestFit.setBackground(Color.white);
		bestFit.setForeground(Color.white);
		bestFit.setFocusable(false);

		nextFit.setOpaque(false);
		nextFit.setBorder(new LineBorder(Color.white));
		nextFit.setBackground(Color.white);
		nextFit.setForeground(Color.white);
		nextFit.setFocusable(false);

		voltar.setOpaque(false);
		voltar.setBorder(null);
		voltar.setBackground(Color.white);
		voltar.setForeground(Color.white);
		voltar.setFocusable(false);
		voltar.setIcon(new ImageIcon("src/voltar2.png"));

		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		boxes.forEach((c) -> {
			c.setCoordinateXY(boxCoordinateX, boxCoordinateY);
			boxCoordinateX += 0;
			boxCoordinateY += 50;
			add(c);
		});

		particoes.forEach((p) -> {

			// add(p);
		});

		add(ram);
		add(back);

		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Start();
				dispose();
			}
		});

		/* ########################### Tudo certo ################### */
		boxes.forEach((c) -> {
			tQueueFirstFit.add(new Thread() {
				public void run() {
					c.run(255, 3);
					for (Particao p : particoes) {
						c.run(255, p.getPosCheckY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (c.getTamProcess() <= p.getTamanho()) {
							p.tamanho = p.getTamanho() - c.getTamProcess();
							c.run(p.getPosParadaX(), c.getY());
							p.proxPosicao();
							break;
						}
					}
				}
			});
		});

		firstFit.addActionListener(new ActionListener() {
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

		/*
		 * ########################### Comparar o tamanho da memoria para que o processo
		 * sempre v� para a memoria que tem maiortamanho ###################
		 */
		boxes.forEach((c) -> {
			tQueueWorstFit.add(new Thread() {
				public void run() {
					c.run(255, 3);

					// List<Particao> particoes = new ArrayList<Particao>();
					Particao max = null;
					// articao u1, Particao u2) -> Integer.compare(u1.getTamanho(), u2.getTamanho())
					max = particoes.stream().max(Comparator.comparing(Particao::getTamanho)).get();

					for (Particao p : particoes) {

						c.run(255, p.getPosCheckY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (p.equals(max)) {
							p.tamanho = p.getTamanho() - c.getTamProcess();
							c.run(p.getPosParadaX(), c.getY());
							p.proxPosicao();
							break;
						}
					}
				}
			});
		});

		worstFit.addActionListener(new ActionListener() {
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

		/* ############################# Arrumar Best ########################## */
		boxes.forEach((c) -> {
			tQueueBestFit.add(new Thread() {
				public void run() {
					c.run(255, 3);

					List<Particao> pp = particoes.stream().filter(p -> p.getTamanho() >= c.getTamProcess())
							.sorted((p1, p2) -> p1.getTamanho() - p2.getTamanho()).collect(Collectors.toList());

					Particao min = null;
					min = pp.stream().min(Comparator.comparing(Particao::getTamanho)).orElse(null);

					for (Particao p : particoes) {

						c.run(255, p.getPosCheckY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (p.equals(min)) {
							p.tamanho = p.getTamanho() - c.getTamProcess();
							c.run(p.getPosParadaX(), c.getY());
							p.proxPosicao();
							break;
						}
					}
				}
			});
		});

		bestFit.addActionListener(new ActionListener() {
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

		/* ######################## fazer next ######################### */
		boxes.forEach((c) -> {
			tQueueNextFit.add(new Thread() {
				public void run() {
					c.run(255, 3);

					for (Particao p : particoes) {
						c.run(255, p.getPosCheckY());
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (c.getTamProcess() <= p.getTamanho()) {

							if (!p.isLastInserted()) {
								
								particoes.forEach((e) ->{
									e.setLastInserted(false);
								});
								
								p.setLastInserted(true);	
											
								p.tamanho = p.getTamanho() - c.getTamProcess();
								c.run(p.getPosParadaX(), c.getY());
								p.proxPosicao();
								break;

							}
						}
					}
				}
			});
		});

		nextFit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						tQueueNextFit.forEach(t -> {
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
	}

}
