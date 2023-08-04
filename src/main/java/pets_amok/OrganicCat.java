package pets_amok;
public class OrganicCat extends VirtualPet implements OrganicPet {

	private int hungerLevel;
	private int thirstLevel;
	private int activityLevel;
	private int excrementAmount;
	private boolean environmentClean = true;

	public OrganicCat(String name, String desc) {
		super(name, desc);
		this.hungerLevel = 5;
		this.thirstLevel = 5;
		this.activityLevel = 75;
		this.excrementAmount = 1;
	}

	public OrganicCat(String name, String desc, int happinessLevel, int healthLevel, int hungerLevel, int thirstLevel,
			int exrementAmount) {
		super(name, desc, happinessLevel, healthLevel);
		this.hungerLevel = hungerLevel;
		this.thirstLevel = thirstLevel;
		this.excrementAmount = excrementAmount;
		this.activityLevel = 75;
	}

	public OrganicCat(String name, String desc, int happinessLevel, int healthLevel, int hungerLevel, int thirstLevel,
			int exrementAmount, int activityLevel) {
		super(name, desc, happinessLevel, healthLevel);
		this.hungerLevel = hungerLevel;
		this.thirstLevel = thirstLevel;
		this.excrementAmount = excrementAmount;
		this.activityLevel = activityLevel;
	}

	// This overrides the play() method in the parent class - VirtualPet
	@Override
	public void play() {
		activityLevel = 75;
	}

	// This overrides the tick() method in the parent class - VirtualPet
	@Override
	public int tick() {

		hungerLevel += 5;
		thirstLevel += 5;
		if (!(activityLevel == 0)) {
			activityLevel -= 5;
		}
		determineHealthAndHappinessLevels();
		return generateWaste();
	}

	public void setEnvironmentClean(boolean clean) {
		environmentClean = clean;
	}

	// Assignment requirements
	// All pets lose health if their happiness drops too low"
	// A variable representing overall health that is updated as a result
	// 	of other attributes moving in a negative or positive direction, influencing
	// 	happiness"
	private void determineHealthAndHappinessLevels() {

		healthLevel = 0;
		happinessLevel = 0;
		// First, determine health based on hunger, thirst, activity level and litter box state.
		// Note that hunger Level is 0 after being fed, 50 indicates hungry, 75 extremely hungry.
		// Thirst is 0 after drinking, 50 indicates thirsty, 75 extremely thirsty.
		// Activity level is initialized by default to 75, and is reset to 75 after playing.
		// Activity level of less than 10 is an issue.
		if (hungerLevel <= 75) {
			healthLevel += 25;
		}
		if (thirstLevel <= 75) {
			healthLevel += 25;
		}
		if (activityLevel >= 10) {
			healthLevel += 25;
		}
		if (environmentClean) {
			healthLevel += 25;
		}
		// The happiness level based on 20% health, 20% hunger, 20% thirst, 20% activity, and 20% litter box state
		if (healthLevel >= 50) {
			happinessLevel += 20;
		}
		if (hungerLevel <= 50) {
			happinessLevel += 20;
		}
		if (thirstLevel <= 50) {
			happinessLevel += 20;
		}
		if (activityLevel >= 35) {
			happinessLevel += 20;
		}
		if (environmentClean) {
			happinessLevel += 20;
		}
		// If the pet is really unhappy, then take a little away from the health.
		// But only if healthLevel is not equal to 0, because we do not want it
		// to get to a negative level.
		if (healthLevel != 0) {
			if (happinessLevel <= 25) {
				healthLevel -= 10;
			}
		}
	}
	// The overrides below: eat(), drink(), generateWaste() and haveWasteCleanedUP() override the methods in the OrganicPet interface
	@Override
	public void eat() {
		hungerLevel = 0;
	}
	@Override
	public void drink() {
		thirstLevel = 0;
	}
	@Override
	public int generateWaste() {
		return excrementAmount;
	}
	@Override
	public void haveWasteCleanedUp() {
		environmentClean = true;
	}
	public boolean getLitterBoxStatus() {
		return environmentClean;
	}
	public int getHungerLevel() {
		return hungerLevel;
	}
	public int getThirstLevel() {
		return thirstLevel;
	}
	public int getActivityLevel() {
		return activityLevel;
	}
	@Override
	public String toString() {
		return (super.toString() + " HungerLevel: " + hungerLevel + " ThirstLevel " + thirstLevel + " ExcrementAmt "
				+ excrementAmount);
	}
}