package pets_amok;

public abstract class VirtualPet {

	protected String name;
	protected String description;
	protected int happinessLevel;
	protected int healthLevel;

	public VirtualPet(String name, String description, int happinessLevel, int healthLevel) {
		this.name = name;
		this.description = description;
		this.happinessLevel = happinessLevel;
		this.healthLevel = healthLevel;
	}
	public VirtualPet(String name, String description) {
		this.name = name;
		this.description = description;
		this.happinessLevel = 100;
		this.healthLevel = 100;
	}
	public abstract int tick();
	public void play() {
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getHappinessLevel() {
		return happinessLevel;
	}
	public int getHealthLevel() {
		return healthLevel;
	}
	@Override
	public String toString() {
		return "Name: " + name + " " + "Description: " + description + " " + " HappinessLevel: " + happinessLevel
				+ " HealthLevel: " + healthLevel;
	}
}