package pets_amok;


public class LitterBox {

	private int wasteAmount;
	private static final int MAX_WASTE_FOR_CLEANLINESS = 40;

	public LitterBox() {
		wasteAmount = 0;
	}

	public boolean getStatus() {
		if (wasteAmount <= MAX_WASTE_FOR_CLEANLINESS) {
			return true;
		} else {
			return false;
		}
	}

	public int getWasteAmount() {
		return wasteAmount;
	}

	public void addWaste(int amount) {
		wasteAmount += amount;
	}

	public void clean() {
		wasteAmount = 0;
	}
}