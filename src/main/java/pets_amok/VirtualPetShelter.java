package pets_amok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class VirtualPetShelter {

	private Map<String, VirtualPet> pets = new HashMap<String, VirtualPet>();
	private LitterBox shelterLitterBox = new LitterBox();
	private int dogWasteAmount;
	private Map<String, Integer> cageWaste = new HashMap<String, Integer>();

	// This method returns a Collection of Virtual Pet objects
	// It mimics the Pet Shelter's database of Virtual Pets.
	// A collection is necessary to be able to iterate through each entry.
	public Collection<VirtualPet> getAllPets() {
		return pets.values();
	}

	public VirtualPet getPet(String name) {
		return pets.get(name);
	}

	public int getNumberOfPets() {
		return pets.size();
	}

	public int getLitterBoxWasteLevel() {
		return shelterLitterBox.getWasteAmount();
	}

	public int getDogWasteAmount() {
		return dogWasteAmount;
	}

	public Set<Entry<String, Integer>> getCageWasteList() {
		return cageWaste.entrySet();
	}

	public void addPet(VirtualPet newPet) {
		pets.put(newPet.getName(), newPet);
	}

	public VirtualPet releasePet(String name) {
		return pets.remove(name);
	}

	public void feedPets() {
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof OrganicPet) {
				((OrganicPet) aPet).eat();
			}
		}
	}

	public void waterPets() {
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof OrganicPet) {
				((OrganicPet) aPet).drink();
			}
		}
	}

	public void oilRoboticPets() {
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof RoboticPet) {
				((RoboticPet) aPet).addOil();
			}
		}
	}

	public void walkDogs() {
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof Dog) {
				((Dog) aPet).walk();
			}
		}
	}

	public void cleanCages() {
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof OrganicDog) {
				((OrganicDog) aPet).haveWasteCleanedUp();
			}
		}
	}

	// this immediately affects the health and happiness
	// of the cats, so update their state here
	public void cleanLitterBox() {
		shelterLitterBox.clean();
		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof OrganicCat) {
				((OrganicCat) aPet).haveWasteCleanedUp();
			}
		}
	}

	public void tick() {
		int catWasteAmount = 0;
		int singleDogWasteAmount = 0;
		dogWasteAmount = 0;
		cageWaste.clear();

		for (VirtualPet aPet : getAllPets()) {
			if (aPet instanceof OrganicCat) {
				((OrganicCat) aPet).setEnvironmentClean(shelterLitterBox.getStatus());
				catWasteAmount = ((OrganicCat) aPet).tick();
				shelterLitterBox.addWaste(catWasteAmount);
			} else if (aPet instanceof OrganicDog) {
				singleDogWasteAmount = ((OrganicDog) aPet).tick();
				if (singleDogWasteAmount > 0) {
					cageWaste.put(aPet.getName(), singleDogWasteAmount);
					dogWasteAmount += singleDogWasteAmount;
				}
			} else {
				aPet.tick();
			}
		}
	}

	// If a name is supplied that does not exist, the Virtual Pet object "returned"
	// will be null. Just to be safe, check it for not null before using it,
	// otherwise we could potentially crash with a null pointer exception.
	public void playWithAPet(String name) {
		VirtualPet aPet = pets.get(name);
		if (aPet != null) {
			aPet.play();
		}
	}

}