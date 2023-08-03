package pets_amok;
public class OrganicDog extends DogWalk implements OrganicPet {

	// attributes
	private int hungerLevel;
	private int thirstLevel;
	private int activityLevel;
	private int excrementAmount;
	private boolean needsToPee;
	private boolean needsToPoop;
	private boolean madeAMess;

	public OrganicDog(String name, String desc) {
		super(name, desc);
		this.hungerLevel = 5;
		this.thirstLevel = 5;
		this.activityLevel = 75;
		this.excrementAmount = 1;
		this.needsToPee = false;
		this.needsToPoop = false;
		this.madeAMess = false;
	}

	public OrganicDog(String name, String desc, int happinessLevel, int healthLevel, int hungerLevel, int thirstLevel,
			int excrementAmount) {
		super(name, desc, happinessLevel, healthLevel);
		this.hungerLevel = hungerLevel;
		this.thirstLevel = thirstLevel;
		this.excrementAmount = excrementAmount;
		this.activityLevel = 75;
		this.needsToPee = false;
		this.needsToPoop = false;
		this.madeAMess = false;
	}

	public OrganicDog(String name, String desc, int happinessLevel, int healthLevel, int hungerLevel, int thirstLevel,
			int exrementAmount, int activityLevel, boolean needsToPee, boolean needsToPoop, boolean madeAMess) {
		super(name, desc, happinessLevel, healthLevel);
		this.hungerLevel = hungerLevel;
		this.thirstLevel = thirstLevel;
		this.excrementAmount = excrementAmount;
		this.activityLevel = activityLevel;
		this.needsToPee = needsToPee;
		this.needsToPoop = needsToPoop;
		this.madeAMess = madeAMess;
	}

	// getters
	public int getHungerLevel() {
		return hungerLevel;
	}

	public int getThirstLevel() {
		return thirstLevel;
	}

	public int getActivityLevel() {
		return activityLevel;
	}

	public boolean getNeedsToPee() {
		return needsToPee;
	}

	public boolean getNeedsToPoop() {
		return needsToPoop;
	}

	public boolean getMadeAMess() {
		return madeAMess;
	}

	@Override
	public int tick() {
		hungerLevel += 5;
		thirstLevel += 5;
		if (!(activityLevel == 0)) {
			activityLevel -= 5;
		}
		if (activityLevel < 33 && needsToPee && needsToPoop) {
			madeAMess = true;
		}
		determineHealthAndHappinessLevels();
		return generateWaste();
	}

	@Override
	public void eat() {
		hungerLevel = 0;
		needsToPoop = true;
	}

	@Override
	public void drink() {
		thirstLevel = 0;
		needsToPee = true;
	}

	@Override
	public void walk() {
		if (activityLevel < 75) {
			activityLevel = 75;
		} else {
			activityLevel += 5;
		}
		needsToPee = false;
		needsToPoop = false;
	}

	@Override
	public void play() {
		if (activityLevel < 75) {
			activityLevel = 75;
		} else {
			activityLevel += 5;
		}
	}

	// We need to reset needsToPee and needsToPoop here to false because otherwise
	// the cage status will rarely be set to clean since the tick method determines
	// clean or dirty based on needsToPee and needsToPoop and activity level.
	// Assume the pet goes out while the cage is being cleaned.
	@Override
	public void haveWasteCleanedUp() {
		madeAMess = false;
		needsToPee = false;
		needsToPoop = false;
	}

	// The dog only makes a mess in its cage under the worst of circumstances,
	// therefore, return 0 if they did not have an "accident", otherwise return
	// the waste amount. The calling program does not currently use this for
	// dogs, but it may in the future.
	@Override
	public int generateWaste() {
		if (madeAMess) {
			return excrementAmount;
		} else {
			return 0;
		}
	}

	// The requirements are quoted below:
	// "all pets lose health if their happiness drops too low"
	// "a variable representing overall health that is updated as a result
	// of other attributes moving in a negative or positive direction, influencing
	// happiness"
	private void determineHealthAndHappinessLevels() {

		healthLevel = 0;
		happinessLevel = 0;
		// First, determine health based on hunger, thirst, activity level and litter
		// box state.
		// Note that hunger Level is 0 after being fed, 50 indicates hungry, 75
		// extremely hungry.
		// Thirst is 0 after drinking, 50 indicates thirsty, 75 extremely thirsty.
		// Activity level is initialized by default to 75, and is reset to 75 after
		// playing.
		// Activity level of less than 25 is an issue.
		if (hungerLevel <= 75) {
			healthLevel += 25;
		}
		if (thirstLevel <= 75) {
			healthLevel += 25;
		}
		if (activityLevel >= 10) {
			healthLevel += 25;
		}
		if (!madeAMess) {
			healthLevel += 25;
		}

		// Now consider the happiness level based on 20% health, 20% hunger, 20% thirst,
		// 20% activity, and 20% litter box state
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
		if (!madeAMess) {
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

	@Override
	public String toString() {
		String returnString = name + ";" + description + ";" + hungerLevel + ";" + thirstLevel + ";" + activityLevel
				+ ";" + needsToPee + ";" + needsToPoop + ";" + madeAMess;
		return returnString;
	}

}