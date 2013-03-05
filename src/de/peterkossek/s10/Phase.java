package de.peterkossek.s10;

public class Phase {
	
	private String description;
	private int number;
	private static Phase[] phases = null;
	
	public Phase(String description, int number) {
		this.description = description;
		this.number = number;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static Phase[] getAllPhases() {
		if (phases == null) {
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
		phases = new Phase[10];
		phases[0] = new Phase("Zwei Drillinge", 1);
		phases[1] = new Phase("Drilling und Viererfolge", 2);
		phases[2] = new Phase("Vierling und Viererfolge", 3);
		phases[3] = new Phase("Siebenerfolge", 4);
		phases[4] = new Phase("Achterfolge", 5);
		phases[5] = new Phase("Neunerfolge", 6);
		phases[6] = new Phase("Zwei Vierlinge", 7);
		phases[7] = new Phase("7 Karten einer Farbe", 8);
		phases[8] = new Phase("Fünfling und Zwilling", 9);
		phases[9] = new Phase("Fünfling und Drilling", 10);
	}

	public static Phase getFirstPhase() {
		return getAllPhases()[0];
	}
	
}
