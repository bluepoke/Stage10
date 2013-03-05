package de.peterkossek.s10;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayerNameDialog extends JDialog implements ActionListener {

	private static final String OK = "OK";
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txfPlayer;
	private ArrayList<JTextField> fieldList;
	private ArrayList<Player> players = null;

	/**
	 * Create the dialog.
	 */
	public PlayerNameDialog(Component parent, List<Player> players) {
		setTitle("Spieler");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(6, 2, 0, 0));
		fieldList = new ArrayList<JTextField>();
		for (int i=1; i<=6; i++) {
			JLabel lblPlayer = new JLabel("Spieler "+i+":");
			contentPanel.add(lblPlayer);
			txfPlayer = new JTextField();
			try {
				txfPlayer.setText(players.get(i-1).getName());
			} catch (IndexOutOfBoundsException e) {
			}
			contentPanel.add(txfPlayer);
			txfPlayer.setColumns(10);
			fieldList.add(txfPlayer);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(OK);
				okButton.addActionListener(this);
				okButton.setActionCommand(OK);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
		setLocationRelativeTo(parent);
	}
	
	public ArrayList<Player> getPlayers() {
		setVisible(true);
		return players;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(OK)) {
			players = new ArrayList<Player>();
			for (JTextField field : fieldList) {
				String playerName = field.getText();
				if (!playerName.isEmpty())
					players.add(new Player(playerName));
			}
		}
		setVisible(false);
	}

}
