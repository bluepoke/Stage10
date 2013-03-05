package de.peterkossek.s10;

import java.util.HashMap;

public class RoundResult {

	HashMap<Player, PlayerResult> results = new HashMap<Player, PlayerResult>();
	
	public void addPlayerResult(Player player, PlayerResult result) {
		results.put(player, result);
	}
	
	public PlayerResult getPlayerResult(Player player) {
		return results.get(player);
	}
	
	public Player[] getPlayers() {
		return results.keySet().toArray(new Player[results.size()]);
	}
}
