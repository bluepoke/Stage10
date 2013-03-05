package de.peterkossek.s10;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class WinnerDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	public WinnerDialog(Component parent, ArrayList<Player> finishedPlayers) {
		Player winner;
		if (finishedPlayers.size() == 1) {
			winner = finishedPlayers.get(0);
		} else {
			winner = finishedPlayers.get(0);
			for (int i=1; i<finishedPlayers.size(); i++) {
				Player nextPlayer = finishedPlayers.get(i);
				if (nextPlayer.getPoints() > winner.getPoints())
					winner = nextPlayer;
			}
		}
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{16, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblIntroduction = new JLabel("Der Gewinner ist:");
		GridBagConstraints gbc_lblIntroduction = new GridBagConstraints();
		gbc_lblIntroduction.insets = new Insets(0, 0, 5, 0);
		gbc_lblIntroduction.anchor = GridBagConstraints.NORTH;
		gbc_lblIntroduction.gridx = 0;
		gbc_lblIntroduction.gridy = 0;
		getContentPane().add(lblIntroduction, gbc_lblIntroduction);
		
		JLabel lblWinnerName = new JLabel(winner.getName());
		lblWinnerName.setFont(new Font("Segoe UI", Font.BOLD, 32));
		GridBagConstraints gbc_lblWinnerName = new GridBagConstraints();
		gbc_lblWinnerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblWinnerName.gridx = 0;
		gbc_lblWinnerName.gridy = 1;
		getContentPane().add(lblWinnerName, gbc_lblWinnerName);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 2;
		getContentPane().add(btnOk, gbc_btnOk);
		setLocationRelativeTo(parent);
		setModal(true);
		pack();
		setResizable(false);
		setTitle("Gewinner!");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		setVisible(false);
	}

}
