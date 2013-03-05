package de.peterkossek.s10;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class ApplicationFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CMD_FINISH_ROUND = "FINISH_ROUND";
	private JPanel contentPane;
	private JPanel pnlPlayerDisplay;
	private Player[] players;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {		}
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
		players = new Player[6];
		players[0] = new Player("P1");
		players[1] = new Player("P2");
		players[2] = new Player("P3");
		players[3] = new Player("P4");
		players[4] = new Player("P5");
		players[5] = new Player("P6");
		for (Player p : players)
			pnlPlayerDisplay.add(p.getPanel());
	}

	/**
	 * Create the frame.
	 */
	public ApplicationFrame() {
		setTitle("Stage 10");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pnlPlayerDisplay = new JPanel();
		pnlPlayerDisplay.setLayout(new GridLayout(2, 3, 5, 5));
		contentPane.add(pnlPlayerDisplay, BorderLayout.CENTER);
		
		JPanel pnlLowerButtons = new JPanel();
		contentPane.add(pnlLowerButtons, BorderLayout.SOUTH);
		GridBagLayout gbl_pnlLowerButtons = new GridBagLayout();
		gbl_pnlLowerButtons.columnWidths = new int[]{0, 0};
		gbl_pnlLowerButtons.rowHeights = new int[]{0, 0};
		gbl_pnlLowerButtons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnlLowerButtons.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		pnlLowerButtons.setLayout(gbl_pnlLowerButtons);
		
		JButton btnFinishRound = new JButton("Durchgang beenden");
		btnFinishRound.setActionCommand(CMD_FINISH_ROUND);
		btnFinishRound.addActionListener(this);
		GridBagConstraints gbc_btnFinishRound = new GridBagConstraints();
		gbc_btnFinishRound.gridx = 0;
		gbc_btnFinishRound.gridy = 0;
		pnlLowerButtons.add(btnFinishRound, gbc_btnFinishRound);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(CMD_FINISH_ROUND)) {
			FinishRoundDialog frd = new FinishRoundDialog(this, players);
			RoundResult roundResult = frd.getResult();
			ArrayList<Player> lastPhaseFinishedPlayers = new ArrayList<Player>();
			if (roundResult != null) {
				for (Player player : roundResult.getPlayers()) {
					PlayerResult playerResult = roundResult.getPlayerResult(player);
					if (playerResult.isPhaseCompleted()) {
						if (Phase.isLastPhase(player.getPhase())) {
							lastPhaseFinishedPlayers.add(player);
						} else {
							player.nextPhase();
						}
					}
					player.addPoints(playerResult.getPoints());
				}
				if (!lastPhaseFinishedPlayers.isEmpty()) {
					new WinnerDialog(this, lastPhaseFinishedPlayers);
				}
			}
		}
	}
}
