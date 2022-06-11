
public class BorderCell extends Cell  {
	private int killCounter;
	private int birthCounter;
	
	// CONSTRUCTOR
	public BorderCell(boolean alive) {
		super(alive);
		setKillCounter(0);
	}

	public int getKillCounter() {
		return killCounter;
	}

	public void setKillCounter(int killCounter) {
		this.killCounter = killCounter;
	}

	public int getBirthCounter() {
		return birthCounter;
	}

	public void setBirthCounter(int birthCounter) {
		this.birthCounter = birthCounter;
	}
	
	public void incrementKillCounter() {
		killCounter += 1;
	}
	
	public void incrementBirthCounter() {
		birthCounter += 1;
	}

}