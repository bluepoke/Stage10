package de.peterkossek.s10;

public class Player {

	private String	name;
	private Phase	phase;
	private PlayerPanel	panel;
	private int points = 0;
	
	public Player(String name) {
		this.name = name;
		this.phase = Phase.getFirstPhase();
		this.panel = new PlayerPanel(this);
	}
	
	public String getName() {
		return name;
	}

	public Phase getPhase() {
		return phase;
	}
	
	public PlayerPanel getPanel() {
		return panel;
	}

	public void nextPhase() {
		phase = Phase.getNextPhase(phase);
		panel.updatePlayerInfo();
	}

	public void addPoints(int points) {
		this.points += points;
		panel.updatePlayerInfo();
	}
	
	public int getPoints() {
		return points;
	}
}
