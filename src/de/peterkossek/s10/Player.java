package de.peterkossek.s10;

public class Player {

	private String	name;
	private Phase	phase;
	private PlayerPanel	panel;
	
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
}
