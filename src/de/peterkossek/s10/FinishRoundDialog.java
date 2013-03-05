package de.peterkossek.s10;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

public class FinishRoundDialog extends JDialog implements ActionListener {

	private static final String OK = "OK";
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ResultPanel[] resultPanels;
	private RoundResult roundResult = null;

	/**
	 * Create the dialog.
	 */
	public FinishRoundDialog(Player[] players) {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		ButtonGroup buttonGroup = new ButtonGroup();
		resultPanels = new ResultPanel[players.length];
		for (int i = 0; i < players.length; i++) {
			ResultPanel panel = new ResultPanel(players[i]);
			panel.setWinnerButtonGroup(buttonGroup);
			contentPanel.add(panel);
			resultPanels[i] = panel;
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(OK);
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		pack();
	}

	public RoundResult getResult() {
		this.setVisible(true);
		return roundResult;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals(OK)) {
			roundResult = new RoundResult();
			for (ResultPanel panel : resultPanels) {
				Player player = panel.getPlayer();
				PlayerResult result = panel.getPlayerResult();
				roundResult.addPlayerResult(player, result);
			}
		}
		this.setVisible(false);
	}

}
