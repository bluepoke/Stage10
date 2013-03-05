package de.peterkossek.s10;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;


public class ApplicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlPlayerDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationFrame frame = new ApplicationFrame();
					frame.setup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void setup() {
		Player player1 = new Player("P1");
		pnlPlayerDisplay.add(player1.getPanel());
		Player player2 = new Player("P2");
		pnlPlayerDisplay.add(player2.getPanel());
	}

	/**
	 * Create the frame.
	 */
	public ApplicationFrame() {
		setTitle("Stage 10");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnlPlayerDisplay = new JPanel();
		pnlPlayerDisplay.setLayout(new GridLayout(1, 0, 5, 5));
		contentPane.add(pnlPlayerDisplay, BorderLayout.CENTER);
	}

}
