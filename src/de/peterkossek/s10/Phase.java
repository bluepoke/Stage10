package de.peterkossek.s10;

import java.util.ArrayList;
import java.util.LinkedList;

public class Phase {
	
	private String description;
	private int number;
	private static ArrayList<Phase> phases = new ArrayList<Phase>();
	
	public Phase(String description, int number) {
		this.description = description;
		this.number = number;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ArrayList<Phase> getAllPhases() {
		if (phases == null || phases.isEmpty()) {
			initPhases();
		}
		return phases;
	}
	
	public int getPhaseNumber() {
		return number;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Phase) {
			Phase p = (Phase) o;
			return p.getDescription().equals(this.getDescription());
		} else
			return false;
	}

	private static void initPhases() {
		phases.add(new Phase("Zwei Drillinge", 1));
		phases.add(new Phase("Drilling und Viererfolge", 2));
		phases.add(new Phase("Vierling und Viererfolge", 3));
		phases.add(new Phase("Siebenerfolge", 4));
		phases.add(new Phase("Achterfolge", 5));
		phases.add(new Phase("Neunerfolge", 6));
		phases.add(new Phase("Zwei Vierlinge", 7));
		phases.add(new Phase("7 Karten einer Farbe", 8));
		phases.add(new Phase("Fünfling und Zwilling", 9));
		phases.add(new Phase("Fünfling und Drilling", 10));
	}

	public static Phase getFirstPhase() {
		return getAllPhases().get(0);
	}
	
	public static boolean isLastPhase(Phase phase) {
		return getAllPhases().indexOf(phase)==getAllPhases().size()-1;
	}

	public static Phase getNextPhase(Phase phase) {
		int oldIndex = getAllPhases().indexOf(phase);
		if (oldIndex >= getAllPhases().size())
			return null;
		else
			return getAllPhases().get(oldIndex+1);
		
	}
}
