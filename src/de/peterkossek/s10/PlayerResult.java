package de.peterkossek.s10;

public class PlayerResult {

	private boolean winner;
	private int points;
	private boolean phaseCompleted;
	
	public PlayerResult(boolean winner, int points, boolean phaseCompleted) {
		super();
		this.winner = winner;
		this.points = points;
		this.phaseCompleted = phaseCompleted;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public int getPoints() {
		return points;
	}
	
	public boolean isPhaseCompleted() {
		return phaseCompleted;
	}
}
