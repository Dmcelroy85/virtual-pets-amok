package pets_amok;

public class RoboticDog extends DogWalk implements RoboticPet {

	private double currentOilLevel;
	public static final int BEST_OIL_LEVEL = 50;
	public static final int MAX_HAPPINESS_LEVEL = 100;
	public static final int MIN_OIL_LEVEL = 25;

	public RoboticDog(String name, String desc) {
		super(name, desc);
		currentOilLevel = BEST_OIL_LEVEL;
	}

	// Overrides for the three RoboticPet Interface methods:
	@Override
	public void addOil() {
		currentOilLevel = BEST_OIL_LEVEL;
		happinessLevel = MAX_HAPPINESS_LEVEL;
	}

	@Override
	public double getCurrentOilLevel() {
		return currentOilLevel;
	}

	@Override
	public boolean needsOil() {
		return (currentOilLevel <= MIN_OIL_LEVEL);
	}

	// overrides the VirtualPet method
	@Override
	public int tick() {
		determineHealthAndHappinessLevels();
		return 0;
	}

	// overrides the VirtualPet's method
	@Override
	public void play() {
		happinessLevel = MAX_HAPPINESS_LEVEL;
		if (currentOilLevel != 0) {
			currentOilLevel -= 5;
		}
	}

	// overrides the Dog Class' method
	@Override
	public void walk() {
		happinessLevel = MAX_HAPPINESS_LEVEL;
		if (currentOilLevel != 0) {
			currentOilLevel -= 5;
		}
	}

	// The requirements say "Robotic pets only lose health if not oiled regularly".
	// Determine health based on oil level. The ideal oil level is 50, so
	// calculate a percentage based on current oil level: (current oil level/ideal
	// oil level) * 100
	private void determineHealthAndHappinessLevels() {
		Double health = 0.0;
		health = (currentOilLevel / BEST_OIL_LEVEL) * 100;
		healthLevel = health.intValue();

		// Overall health influences happiness.
		if (healthLevel <= 50) {
			happinessLevel = healthLevel;
		}
	}

}