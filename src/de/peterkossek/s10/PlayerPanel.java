package de.peterkossek.s10;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class PlayerPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private JLabel lblPhaseNumber;
	private JLabel lblPlayerName;
	private JLabel lblPhaseDescription;
	private JLabel lblPlayer;
	private JLabel lblTask;
	
	/**
	 * Create the panel.
	 */
	public PlayerPanel(Player player) {
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.player = player;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 100, 0};
		gridBagLayout.rowHeights = new int[]{14, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblPlayer = new JLabel("Spieler:");
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.anchor = GridBagConstraints.WEST;
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayer.gridx = 0;
		gbc_lblPlayer.gridy = 0;
		add(lblPlayer, gbc_lblPlayer);
		lblPlayerName = new JLabel(player.getName());
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPlayerName.gridx = 1;
		gbc_lblPlayerName.gridy = 0;
		add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblPhase = new JLabel("Phase:");
		GridBagConstraints gbc_lblPhase = new GridBagConstraints();
		gbc_lblPhase.anchor = GridBagConstraints.WEST;
		gbc_lblPhase.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhase.gridx = 0;
		gbc_lblPhase.gridy = 1;
		add(lblPhase, gbc_lblPhase);
		
		lblPhaseNumber = new JLabel(String.valueOf(player.getPhase().getPhaseNumber()));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblPhaseNumber, gbc_lblNewLabel_1);
		
		lblTask = new JLabel("Aufgabe:");
		GridBagConstraints gbc_lblTask = new GridBagConstraints();
		gbc_lblTask.anchor = GridBagConstraints.WEST;
		gbc_lblTask.insets = new Insets(0, 0, 5, 5);
		gbc_lblTask.gridx = 0;
		gbc_lblTask.gridy = 2;
		add(lblTask, gbc_lblTask);
		
		lblPhaseDescription = new JLabel(player.getPhase().getDescription());
		GridBagConstraints gbc_lblPhaseDescription = new GridBagConstraints();
		gbc_lblPhaseDescription.anchor = GridBagConstraints.WEST;
		gbc_lblPhaseDescription.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhaseDescription.gridx = 1;
		gbc_lblPhaseDescription.gridy = 2;
		add(lblPhaseDescription, gbc_lblPhaseDescription);
	}
	
	public void updateInfo() {
		lblPlayerName.setText(player.getName());
		lblPhaseNumber.setText(String.valueOf(player.getPhase().getPhaseNumber()));
		lblPhaseDescription.setText(player.getPhase().getDescription());
	}
}
