package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import client.server.Arcondicionado;
import client.server.Irrigador;
import client.server.Luz;
import client.server.Porta;
import client.server.Tv;

public class TelaGerenciadorComp{
	
	//private static final String IMAGES_DIR = "images\\";
	
	private Arcondicionado arcond;
	private Irrigador irrigador;
	private Luz luzes;
	private Porta portas;
	private Tv tele;
	
	public static TelaComponente arcondTela;
	public static TelaComponente irrigadorTela;
	public static TelaComponente luzesTela;
	public static TelaComponente portasTela;
	public static TelaComponente teleTela;
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciadorComp window = new TelaGerenciadorComp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaGerenciadorComp() {
		arcond = new Arcondicionado();
		arcondTela = new TelaComponente("Ar-Condicionado", "arcondicionado on.png", "arcondicionado off.png", false);
		tele = new Tv();
		teleTela = new TelaComponente("Televisão", "televisão on.png", "televisão off.png", false);
		luzes = new Luz();
		luzesTela = new TelaComponente("Luzes", "luzes on.png", "luzes off.png", false);
		portas = new Porta();
		portasTela = new TelaComponente("Portas", "porta on.png", "porta off.png", false);
		irrigador = new Irrigador();
		irrigadorTela = new TelaComponente("Irrigadores", "irrigacao on.png", "irrigacao off.png", false);
		
		initialize();
		new Thread(new Runnable() {
						
			
			@SuppressWarnings("static-access")
			@Override
			public void run() {
					while(true) {
					arcondTela.setStatus(arcond.getStatus());
					teleTela.setStatus(tele.getStatus());
					luzesTela.setStatus(luzes.getStatus());
					portasTela.setStatus(portas.getStatus());
					irrigadorTela.setStatus(irrigador.getStatus());
					try {
						frame.repaint();
					}catch(Exception e) {
						//TODO
					}
				}
			}
		}).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Componentes");
		frame.setResizable(false);
		frame.setBounds(600, 100, 650, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel cardComp = new JPanel();
		scrollPane.setViewportView(cardComp);
		
		JPanel panel = new JPanel();
		cardComp.add(panel);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		panel.add(topPanel);
		topPanel.setLayout(new GridLayout(1, 3, 0, 1));
		
		JPanel subPanel = new JPanel();
		panel.add(subPanel);
		subPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		
		JPanel panelAr = arcondTela.getPanel();
		topPanel.add(panelAr);
		
		JPanel panelTv = teleTela.getPanel();
		topPanel.add(panelTv);
		
		JPanel panelLuz = luzesTela.getPanel();
		topPanel.add(panelLuz);
		
		JPanel panelPorta = portasTela.getPanel();
		subPanel.add(panelPorta);
		
		JPanel panelIrrig = irrigadorTela.getPanel();
		subPanel.add(panelIrrig);
		
		
		
				//THREAD AR
				new Thread(new Runnable() {
					@SuppressWarnings("static-access")
					@Override
					public void run() {
						arcond.main(null);
						}
				}).start();
				//THREAD TV
				new Thread(new Runnable() {
					@SuppressWarnings("static-access")
					@Override
					public void run() {
						tele.main(null);
						}
				}).start();
				//THREAD LUZ
				new Thread(new Runnable() {
					@SuppressWarnings("static-access")
					@Override
					public void run() {
						luzes.main(null);
						}
				}).start();
				//THREAD PORTA
				new Thread(new Runnable() {
					@SuppressWarnings("static-access")
					@Override
					public void run() {
						portas.main(null);
						}
				}).start();
				//THREAD IRRIGADOR
				new Thread(new Runnable() {
					@SuppressWarnings("static-access")
					@Override
					public void run() {
						irrigador.main(null);
						}
				}).start();
		
	}
}
