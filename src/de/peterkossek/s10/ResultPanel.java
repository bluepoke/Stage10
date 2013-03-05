package de.peterkossek.s10;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ResultPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnWinner;
	private Player player;
	private JSpinner spnPoints;
	private JCheckBox chckbxPhaseCompleted;

	public ResultPanel(Player player) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.player = player;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPlayerName = new JLabel(player.getName());
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.gridwidth = 2;
		gbc_lblPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 0;
		add(lblPlayerName, gbc_lblPlayerName);
		
		rdbtnWinner = new JRadioButton("Gewinner");
		rdbtnWinner.addActionListener(this);
		GridBagConstraints gbc_rdbtnWinner = new GridBagConstraints();
		gbc_rdbtnWinner.gridwidth = 2;
		gbc_rdbtnWinner.anchor = GridBagConstraints.WEST;
		gbc_rdbtnWinner.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnWinner.gridx = 0;
		gbc_rdbtnWinner.gridy = 1;
		add(rdbtnWinner, gbc_rdbtnWinner);
		
		chckbxPhaseCompleted = new JCheckBox("Phase abgelegt");
		GridBagConstraints gbc_chckbxPhaseDone = new GridBagConstraints();
		gbc_chckbxPhaseDone.gridwidth = 2;
		gbc_chckbxPhaseDone.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPhaseDone.anchor = GridBagConstraints.WEST;
		gbc_chckbxPhaseDone.gridx = 0;
		gbc_chckbxPhaseDone.gridy = 2;
		add(chckbxPhaseCompleted, gbc_chckbxPhaseDone);
		
		JLabel lblPoints = new JLabel("Punkte:");
		GridBagConstraints gbc_lblPunkte = new GridBagConstraints();
		gbc_lblPunkte.anchor = GridBagConstraints.WEST;
		gbc_lblPunkte.insets = new Insets(0, 0, 0, 5);
		gbc_lblPunkte.gridx = 0;
		gbc_lblPunkte.gridy = 3;
		add(lblPoints, gbc_lblPunkte);
		
		spnPoints = new JSpinner();
		spnPoints.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(5)));
		GridBagConstraints gbc_spnPoints = new GridBagConstraints();
		gbc_spnPoints.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnPoints.gridx = 1;
		gbc_spnPoints.gridy = 3;
		add(spnPoints, gbc_spnPoints);
	}
	
	public void setWinnerButtonGroup(ButtonGroup group) {
		group.add(rdbtnWinner);
	}
	
	public Player getPlayer() {
		return player;
	}

	public PlayerResult getPlayerResult() {
		PlayerResult result = new PlayerResult(rdbtnWinner.isSelected(), (int) spnPoints.getValue(), chckbxPhaseCompleted.isSelected());
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (rdbtnWinner.isSelected()) {
			chckbxPhaseCompleted.setSelected(true);
			spnPoints.setValue(0);
		}
	}

}
