package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import mqtt.sub.equipment.ArCondicionadoManager;
import mqtt.sub.equipment.IrrigadorManager;
import mqtt.sub.equipment.LuzesManager;
import mqtt.sub.equipment.PortasManager;
import mqtt.sub.equipment.TVManager;

public class TelaGerenciadorComp{
	
	//private static final String IMAGES_DIR = "images\\";
	
	private ArCondicionadoManager arcond;
	private IrrigadorManager irrigador;
	private LuzesManager luzes;
	private PortasManager portas;
	private TVManager tele;
	
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
		arcond = new ArCondicionadoManager();
		arcondTela = new TelaComponente("Ar-Condicionado", "arcondicionado on.png", "arcondicionado off.png", false);
		tele = new TVManager();
		teleTela = new TelaComponente("Televisão", "televisão on.png", "televisão off.png", false);
		luzes = new LuzesManager();
		luzesTela = new TelaComponente("Luzes", "luzes on.png", "luzes off.png", false);
		portas = new PortasManager();
		portasTela = new TelaComponente("Portas", "porta on.png", "porta off.png", false);
		irrigador = new IrrigadorManager();
		irrigadorTela = new TelaComponente("Irrigadores", "irrigacao on.png", "irrigacao off.png", false);
		
		initialize();
		new Thread(new Runnable() {
			
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
		
	}
}
