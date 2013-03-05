package de.peterkossek.s10;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class ApplicationFrame extends JFrame implements ActionListener {

	private static final String CMD_SETUP_PLAYERS = "SETUP_PLAYERS";
	private static final String INI_FILE_NAME = "Stage10.ini";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String CMD_FINISH_ROUND = "FINISH_ROUND";
	private static final String KEY_PLAYER = "stage10.player.";
	private JPanel contentPane;
	private JPanel pnlPlayerDisplay;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static Properties settings = new Properties();
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnSettings;
	private JMenuItem mntmSetupPlayers;

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
		try {
			loadSettings();
		} catch (Exception e) {
			settings.put(KEY_PLAYER+"1", "Player 1");
			settings.put(KEY_PLAYER+"2", "Player 2");
		}
		for (int i=1; i<=6; i++) {
			String playerName = settings.getProperty(KEY_PLAYER+i);
			if (playerName != null)
				players.add(new Player(playerName));
		}
		updatePlayerPanels();
	}

	private void updatePlayerPanels() {
		pnlPlayerDisplay.removeAll();
		for (Player p : players)
			pnlPlayerDisplay.add(p.getPanel());
		pnlPlayerDisplay.repaint();
		revalidate();
	}

	private void loadSettings() throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(INI_FILE_NAME);
		settings.load(fis);
		fis.close();
	}
	
	private void storeSettings() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(INI_FILE_NAME);
		for (int i=1; i<=players.size(); i++) {
			settings.put(KEY_PLAYER+i, players.get(i-1).getName());
		}
		settings.store(fos, null);
		fos.close();
	}

	/**
	 * Create the frame.
	 */
	public ApplicationFrame() {
		setTitle("Stage 10");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("Datei");
		menuBar.add(mnFile);
		
		mnSettings = new JMenu("Einstellungen");
		menuBar.add(mnSettings);
		
		mntmSetupPlayers = new JMenuItem("Spieler");
		mntmSetupPlayers.setActionCommand(CMD_SETUP_PLAYERS);
		mntmSetupPlayers.addActionListener(this);
		mnSettings.add(mntmSetupPlayers);
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
		} else if (command.equals(CMD_SETUP_PLAYERS)) {
			ArrayList<Player> editedPlayers = new PlayerNameDialog(this, players).getPlayers();
			if (editedPlayers != null) {
				players = editedPlayers;
				updatePlayerPanels();
				try {
					storeSettings();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
