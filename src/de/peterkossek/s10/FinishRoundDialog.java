package de.peterkossek.s10;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FinishRoundDialog extends JDialog implements ActionListener {

	private static final String OK = "OK";
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<ResultPanel> resultPanels = new ArrayList<ResultPanel>();
	private RoundResult roundResult = null;

	/**
	 * Create the dialog.
	 */
	public FinishRoundDialog(Component parent, ArrayList<Player> players) {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 3, 5, 5));
		ButtonGroup buttonGroup = new ButtonGroup();
		for (int i = 0; i < players.size(); i++) {
			ResultPanel panel = new ResultPanel(players.get(i));
			panel.setWinnerButtonGroup(buttonGroup);
			contentPanel.add(panel);
			resultPanels.add(panel);
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
		setLocationRelativeTo(parent);
		setTitle("Durchgang beendet");
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
