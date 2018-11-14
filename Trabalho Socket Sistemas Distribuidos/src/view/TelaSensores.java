package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.server.SensorHum;
import client.server.SensorPresence;
import client.server.SensorTemp;
import client.server.Server;


public class TelaSensores implements ActionListener, ChangeListener{

	private static final String IMAGES_DIR = "images\\";
	private Server servidor;
	private SensorTemp temperatura;
	private SensorHum umidade;
	private SensorPresence presenca;
	
	private JFrame frmSimuladorDeSensores;
	private JTextField txtSlider;
	private JTextField txtUmidade;
	private JComboBox<String> cboxHora;
	private JComboBox<String> cboxMinutos;
	private JSlider sliderUmi;
	private JSlider sliderTemp;
	private JButton btnEnter;
	private JButton btnPresenca;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSensores window = new TelaSensores();
					window.frmSimuladorDeSensores.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaSensores() {
		servidor = new Server();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimuladorDeSensores = new JFrame();
		frmSimuladorDeSensores.setTitle("Simulador de Sensores");
		frmSimuladorDeSensores.setResizable(false);
		frmSimuladorDeSensores.setBounds(100, 100, 550, 400);
		frmSimuladorDeSensores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimuladorDeSensores.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel clockPanel = new JPanel();
		frmSimuladorDeSensores.getContentPane().add(clockPanel);
		clockPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTempo = new JLabel("Rel\u00F3gio");
		lbTempo.setHorizontalAlignment(SwingConstants.CENTER);
		clockPanel.add(lbTempo, BorderLayout.NORTH);
		
		JPanel clockSubPanel = new JPanel();
		clockPanel.add(clockSubPanel, BorderLayout.CENTER);
		clockSubPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel clockintPanel = new JPanel();
		clockSubPanel.add(clockintPanel, BorderLayout.SOUTH);
		
		cboxHora = new JComboBox<String>();
		cboxHora.setMaximumRowCount(10);
		cboxHora.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		clockintPanel.add(cboxHora);
		
		JLabel label = new JLabel(":");
		clockintPanel.add(label);
		
		cboxMinutos = new JComboBox<String>();
		cboxMinutos.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cboxMinutos.setMaximumRowCount(10);
		clockintPanel.add(cboxMinutos);
		
		btnEnter = new JButton("Enter");
		clockintPanel.add(btnEnter);
		btnEnter.addActionListener(this);
		
		JLabel label_1 = new JLabel("");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setIcon(new ImageIcon(IMAGES_DIR + "clock.png"));
		clockSubPanel.add(label_1, BorderLayout.CENTER);
		
		JSeparator separator_1 = new JSeparator();
		clockPanel.add(separator_1, BorderLayout.SOUTH);
		
		JPanel tempPanel = new JPanel();
		frmSimuladorDeSensores.getContentPane().add(tempPanel);
		tempPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTemp = new JLabel("Temperatura");
		lbTemp.setHorizontalAlignment(SwingConstants.CENTER);
		tempPanel.add(lbTemp, BorderLayout.NORTH);
		
		JPanel tempSubPanel = new JPanel();
		tempPanel.add(tempSubPanel, BorderLayout.CENTER);
		tempSubPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(IMAGES_DIR +"term.png"));
		tempSubPanel.add(label_2, BorderLayout.CENTER);
		
		JPanel tempSliderPanel = new JPanel();
		tempSubPanel.add(tempSliderPanel, BorderLayout.SOUTH);
		tempSliderPanel.setLayout(new BorderLayout(0, 0));
		
		sliderTemp = new JSlider();
		sliderTemp.setPaintTicks(true);
		sliderTemp.setMinorTickSpacing(5);
		sliderTemp.setMajorTickSpacing(10);
		sliderTemp.setMaximum(60);
		sliderTemp.setValue(24);
		sliderTemp.setForeground(Color.GRAY);
		tempSliderPanel.add(sliderTemp, BorderLayout.CENTER);
		sliderTemp.addChangeListener(this);
		
		txtSlider = new JTextField();
		txtSlider.setEditable(false);
		tempSliderPanel.add(txtSlider, BorderLayout.EAST);
		txtSlider.setColumns(5);
		txtSlider.setText(""+sliderTemp.getValue()+" °C");
		
		JSeparator separator = new JSeparator();
		tempPanel.add(separator, BorderLayout.SOUTH);
		
		JPanel presPanel = new JPanel();
		frmSimuladorDeSensores.getContentPane().add(presPanel);
		presPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbPresenca = new JLabel("Sensor de Presen\u00E7a");
		lbPresenca.setHorizontalAlignment(SwingConstants.CENTER);
		presPanel.add(lbPresenca, BorderLayout.NORTH);
		
		JPanel pressSubPanel = new JPanel();
		presPanel.add(pressSubPanel, BorderLayout.CENTER);
		pressSubPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setIcon(new ImageIcon(IMAGES_DIR + "baseline_transfer_within_a_station_black_48dp.png"));
		pressSubPanel.add(label_3, BorderLayout.CENTER);
		
		btnPresenca = new JButton("OFF");
		pressSubPanel.add(btnPresenca, BorderLayout.SOUTH);
		btnPresenca.setBackground(new Color(178, 34, 34));
		btnPresenca.addActionListener(this);
		
		JPanel umidPanel = new JPanel();
		frmSimuladorDeSensores.getContentPane().add(umidPanel);
		umidPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lbUmidade = new JLabel("Sensor de Umidade");
		lbUmidade.setHorizontalAlignment(SwingConstants.CENTER);
		umidPanel.add(lbUmidade, BorderLayout.NORTH);
		
		JPanel umidSubPanel = new JPanel();
		umidPanel.add(umidSubPanel, BorderLayout.CENTER);
		umidSubPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setIcon(new ImageIcon(IMAGES_DIR + "baseline_cloud_black_48dp.png"));
		umidSubPanel.add(label_4, BorderLayout.CENTER);
		
		JPanel umidSliderPanel = new JPanel();
		umidSubPanel.add(umidSliderPanel, BorderLayout.SOUTH);
		umidSliderPanel.setLayout(new BorderLayout(0, 0));
		
		sliderUmi = new JSlider();
		sliderUmi.setValue(60);
		sliderUmi.setMinorTickSpacing(5);
		sliderUmi.setMajorTickSpacing(10);
		sliderUmi.setPaintTicks(true);
		umidSliderPanel.add(sliderUmi, BorderLayout.CENTER);
		sliderUmi.addChangeListener(this);
		
		txtUmidade = new JTextField();
		txtUmidade.setEditable(false);
		umidSliderPanel.add(txtUmidade, BorderLayout.EAST);
		txtUmidade.setColumns(5);
		txtUmidade.setText(""+sliderUmi.getValue()+" %");
	
		
		//THREAD SERVIDOR
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				servidor.main(null);
				}
		}).start();
		//THREAD SENSOR TEMPERATURA
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				temperatura.main(null);
				}
		}).start();
		//THREAD SENSOR UMIDADE
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				umidade.main(null);
				}
		}).start();
		//THREAD SENSOR PRESENCA
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			@Override
			public void run() {
				presenca.main(null);
				}
		}).start();
		
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void stateChanged(ChangeEvent ce) {
		if(ce.getSource() == sliderTemp)
		{
			txtSlider.setText(""+sliderTemp.getValue()+" °C");
			System.out.println("Beleza no Action PERFORMED, Temp = " + sliderTemp.getValue());
			//servidor.changeTemp(sliderTemp.getValue());
			temperatura.changeTemp(sliderTemp.getValue());
		}
		else
		{
			txtUmidade.setText(""+sliderUmi.getValue()+" %");
			System.out.println("Beleza no Action PERFORMED, Umidade = " + sliderUmi.getValue());
			//servidor.changeHumidade(sliderUmi.getValue());
			umidade.changeHumidade(sliderUmi.getValue());
		}
		
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnEnter)
		{
			int hora = Integer.parseInt(cboxHora.getSelectedItem().toString());
			int min = Integer.parseInt(cboxMinutos.getSelectedItem().toString());
			double time = hora + ((double)min)/60;
			System.out.println("Beleza no Action PERFORMED, TIME = " + time);
			servidor.changeTime(time);
		}
		else {
			if(ae.getActionCommand() == "ON") {
				btnPresenca.setBackground(new Color(178, 34, 34));
				btnPresenca.setText("OFF");
				System.out.println("Beleza no Action PERFORMED, Presença = False");
				presenca.changePresence(false);
			}
			else
			{
				btnPresenca.setBackground(new Color(0, 255, 127));
				btnPresenca.setText("ON");
				System.out.println("Beleza no Action PERFORMED, Presença = True");
				presenca.changePresence(true);
			}
		}
		
		
	}

}
