package pets_amok;

public interface RoboticPet {
	public abstract void addOil();

	public abstract double getCurrentOilLevel();

	public abstract boolean needsOil();
}