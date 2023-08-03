package pets_amok;


public abstract class DogWalk extends VirtualPet {

	protected DogWalk(String name, String desc) {
		super(name, desc);
	}

	protected DogWalk(String name, String desc, int happinessLevel, int healthLevel) {
		super(name, desc, happinessLevel, healthLevel);
	}

	abstract void walk();

}