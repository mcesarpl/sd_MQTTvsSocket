package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TelaComponente {

	private static final String IMAGES_DIR = "images\\";
	
	private String nome;
	private String iconeon;
	private String iconeoff;
	private String status;
	private JFrame frame;
	private JButton btn_status;
	private JLabel lb_icone;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaComponente window = new TelaComponente("Ar-Condicionado", "arcondicionado on.png", "arcondicionado off.png", true);
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
	public TelaComponente(String nome, String iconeon, String iconeoff, boolean status) {
		this.nome = nome;
		this.iconeon = IMAGES_DIR + iconeon;
		this.iconeoff =IMAGES_DIR +  iconeoff;
		if(status) this.status = "ON";
		else this.status = "OFF";
		initialize();
	}
	
	
	public TelaComponente(String nome, String iconeon, boolean status) {
		this.nome = nome;
		this.iconeon = iconeon;
		this.iconeoff = iconeon;
		if(status) this.status = "ON";
		else this.status = "OFF";
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame(this.nome);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel pn_imagem = new JPanel();
		panel.add(pn_imagem);
		
		lb_icone = new JLabel();
		lb_icone.setHorizontalAlignment(SwingConstants.CENTER);
		if(this.status=="ON") lb_icone.setIcon(new ImageIcon(this.iconeon));
		else lb_icone.setIcon(new ImageIcon(this.iconeoff));
		lb_icone.setVisible(true);
		pn_imagem.setLayout(new BorderLayout(0, 0));
		pn_imagem.add(lb_icone, BorderLayout.CENTER);
		
		JLabel lb_nome = new JLabel(this.nome.toUpperCase());
		lb_nome.setForeground(new Color(0, 191, 255));
		lb_nome.setFont(new Font("Verdana", Font.BOLD, 14));
		lb_nome.setHorizontalAlignment(SwingConstants.CENTER);
		pn_imagem.add(lb_nome, BorderLayout.SOUTH);
		
		JPanel pn_sub = new JPanel();
		panel.add(pn_sub);
		pn_sub.setLayout(new BoxLayout(pn_sub, BoxLayout.X_AXIS));
		
		JLabel lb_status = new JLabel("Estado:  ");
		lb_status.setHorizontalAlignment(SwingConstants.LEFT);
		pn_sub.add(lb_status);
		
		btn_status = new JButton(this.status);
		btn_status.setForeground(Color.WHITE);
		if(this.status=="ON")btn_status.setBackground(new Color(0, 255, 127));
		else btn_status.setBackground(new Color(178, 34, 34));;
		btn_status.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_status.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pn_sub.add(btn_status);
		
		JLabel lbBottom = new JLabel(" ");
		lbBottom.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lbBottom, BorderLayout.SOUTH);
		frame.validate();
		
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(boolean b) {
		if(b)
		{
			this.status = "ON";
			btn_status.setBackground(new Color(0, 255, 127));
			btn_status.setText("ON");
			lb_icone.setIcon(new ImageIcon(getIconOn()));
		}
		else
		{
			btn_status.setBackground(new Color(178, 34, 34));
			btn_status.setText("OFF");
			lb_icone.setIcon(new ImageIcon(getIconOff()));
		}
	}
	
	
	public String getIconOn()
	{
		return this.iconeon;
	}
	
	public String getIconOff()
	{
		return this.iconeoff;
	}
	
	public JPanel getPanel()
	{
		return this.panel;
	}
	
	

}
