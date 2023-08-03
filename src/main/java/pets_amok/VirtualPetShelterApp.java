package pets_amok;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class VirtualPetShelterApp {

	public static void main(String[] args) {

		VirtualPetShelter myShelter = new VirtualPetShelter();
		myShelter.addPet(new OrganicCat("Fluffy", "the very furry cat"));
		myShelter.addPet(new OrganicCat("Buffy", "the vampire cat"));

		myShelter.addPet(new OrganicDog("Fifi", "very large, silly dog", 100, 100, 25, 25, 10));
		myShelter.addPet(new OrganicDog("Fido", "watch out- he bites"));

		myShelter.addPet(new RoboticCat("Henry", "large and creaky"));
		myShelter.addPet(new RoboticCat("Harriet", "small and sneaky"));

		myShelter.addPet(new RoboticDog("Spot", "chases a flashlight"));
		myShelter.addPet(new RoboticDog("Spike", "has some sharp edges"));

		displayWelcomeMessage();
		handleVolunteerActions(myShelter);
	}

	public static void displayWelcomeMessage() {

		System.out.println("Welcome to Hound Dogz Virtual Pet Shelter!");
		System.out.println("Thank you for volunteering! We provide free pizza for helping!\n");
	}

	public static void handleVolunteerActions(VirtualPetShelter myShelter) {

		Scanner input = new Scanner(System.in);
		String userOption = "";

		do {
			if (myShelter.getNumberOfPets() == 0) {
				System.out.println("Good news! All pets have been adopted! Please come back soon.");
				break;
			}
			displayPetStatus(myShelter);
			displayMainMenu();
			userOption = input.nextLine();
			switch (userOption) {
			case "1":
				myShelter.feedPets();
				System.out.println("You fed the pets! Thank you!");
				break;
			case "2":
				myShelter.waterPets();
				System.out.println("You watered the pets! Thank you!");
				break;
			case "3":
				myShelter.walkDogs();
				System.out.println("You took the dogs for a walk!  Thank you!");
				break;
			case "4":
				System.out.println("Fantastic! You'd like to play with a pet. Please choose one:\n");
				handlePetActivity(myShelter, "play");
				break;
			case "5":
				myShelter.cleanCages();
				System.out.println("You cleaned the dog cages!  Thank you!");
				break;
			case "6":
				myShelter.cleanLitterBox();
				System.out.println("You cleaned the litter box!  Extra pizza for you! Thank you!");
				break;
			case "7":
				myShelter.oilRoboticPets();
				System.out.println("You oiled the robotic pets! Thank you!");
				break;
			case "8":
				System.out.println("Terrific!  You'd like to adopt a pet.  Please choose one: \n");
				handlePetAdoption(myShelter);
				break;
			case "9":
				System.out.println("Welcome! You'd like to admit a pet. We can help with that.\n");
				handlePetAdmission(myShelter);
				break;
			case "10":
				System.out.println("You loved our pizza!\n\n");
				break;
			case "11":
				System.out.println("Thanks for playing the Virtual Pet Shelter game!");
				break;
			}
			myShelter.tick();

		} while (!userOption.equals("11"));
		input.close();
	}

	public static void displayPetStatus(VirtualPetShelter myShelter) {

		String litterBoxStatus = "Clean";
		String cageStatus = "Clean";
		String oilLevel = "";
		Set<Entry<String, Integer>> cageWasteList;

		System.out.println("The shelter's litter box waste level is: " + myShelter.getLitterBoxWasteLevel());
		System.out.println("The shelter's level of waste in dog cages is: " + myShelter.getDogWasteAmount());

		cageWasteList = myShelter.getCageWasteList();
		for (Entry<String, Integer> entry : cageWasteList) {
			System.out.println("\t" + entry.getKey() + "'s cage: " + entry.getValue());
		}

		System.out.println("This is the status of your Virtual Pets:\n");
		System.out.println(
				"Name                     |Hunger  |Thirst  |Activity Level |Litter Box/Cage |Happiness |Health |Oil Level");
		System.out.println(
				"-------------------------|--------|--------|---------------|----------------|----------|-------|-------------");

		Collection<VirtualPet> shelterPets = myShelter.getAllPets();

		// It is a little nicer to see these displayed by type (cat, dog, roboticCat,
		// etc..)
		// In the future, there is probably a more efficient way to do this, but we
		// would probably need to create another sort-able map object first and
		// potentially add the type to the object.
		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof OrganicCat) {
				OrganicCat shelterCat = (OrganicCat) (shelterPet);
				if (!shelterCat.getLitterBoxStatus()) {
					litterBoxStatus = "Needs Cleaning";
				} else {
					litterBoxStatus = "Clean";
				}
				displayOrganicCatStatus(shelterCat, litterBoxStatus);
			}
		} // end of OrganicCat

		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof OrganicDog) {
				OrganicDog shelterDog = (OrganicDog) (shelterPet);
				if (shelterDog.getMadeAMess()) {
					cageStatus = "Needs Cleaning";
				} else {
					cageStatus = "Clean";
				}
				displayOrganicDogStatus(shelterDog, cageStatus);
			}
		} // end of Organic Dog

		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof RoboticCat) {
				RoboticCat shelterCat = (RoboticCat) (shelterPet);
				if (shelterCat.needsOil()) {
					oilLevel = " Needs Oil";
				} else {
					oilLevel = "";
				}
				displayRoboticCatStatus(shelterCat, oilLevel);
			}
		} // end of RoboticCat

		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof RoboticDog) {
				RoboticDog shelterDog = (RoboticDog) (shelterPet);
				if (shelterDog.needsOil()) {
					oilLevel = " Needs Oil";
				} else {
					oilLevel = "";
				}

				displayRoboticDogStatus(shelterDog, oilLevel);
			}
		} // end of RoboticDog

	} // end displayPetStatus()

	public static void displayOrganicCatStatus(OrganicCat shelterCat, String litterBoxStatus) {

		System.out.printf("%-25s", shelterCat.getName() + " (cat)");
		System.out.print("|");
		System.out.printf("%-8d", shelterCat.getHungerLevel());
		System.out.print("|");
		System.out.printf("%-8d", shelterCat.getThirstLevel());
		System.out.print("|");
		System.out.printf("%-15d", shelterCat.getActivityLevel());
		System.out.print("|");
		System.out.printf("%-16s", litterBoxStatus);
		System.out.print("|");
		System.out.printf("%-10d", shelterCat.getHappinessLevel());
		System.out.print("|");
		System.out.printf("%-7d", shelterCat.getHealthLevel());
		System.out.print("|");
		System.out.printf("%-10s", "");
		System.out.println();
	}

	public static void displayOrganicDogStatus(OrganicDog shelterDog, String cageStatus) {

		System.out.printf("%-25s", shelterDog.getName() + " (dog)");
		System.out.print("|");
		System.out.printf("%-8d", shelterDog.getHungerLevel());
		System.out.print("|");
		System.out.printf("%-8d", shelterDog.getThirstLevel());
		System.out.print("|");
		System.out.printf("%-15d", shelterDog.getActivityLevel());
		System.out.print("|");
		System.out.printf("%-16s", cageStatus);
		System.out.print("|");
		System.out.printf("%-10d", shelterDog.getHappinessLevel());
		System.out.print("|");
		System.out.printf("%-7d", shelterDog.getHealthLevel());
		System.out.print("|");
		System.out.printf("%-10s", "");
		System.out.println();
	}

	public static void displayRoboticCatStatus(RoboticCat shelterCat, String oilLevel) {

		double theOilLevel = shelterCat.getCurrentOilLevel();
		String oilLevelMinDec;
		if (theOilLevel == (long) theOilLevel) {
			oilLevelMinDec = String.format("%d", (long) theOilLevel);
		} else {
			oilLevelMinDec = String.format("%s", theOilLevel);
		}

		System.out.printf("%-25s", shelterCat.getName() + " (robotic cat)");
		System.out.print("|");
		System.out.printf("%-8s", ""); // hunger level n/a here
		System.out.print("|");
		System.out.printf("%-8s", ""); // thirst level n/a here
		System.out.print("|");
		System.out.printf("%-15s", ""); // activity level n/a here
		System.out.print("|");
		System.out.printf("%-16s", ""); // litter box status n/a here
		System.out.print("|");
		System.out.printf("%-10d", shelterCat.getHappinessLevel());
		System.out.print("|");
		System.out.printf("%-7d", shelterCat.getHealthLevel());
		System.out.print("|");
		System.out.printf("%-10s", oilLevelMinDec + oilLevel);
		System.out.println();
	}

	public static void displayRoboticDogStatus(RoboticDog shelterDog, String oilLevel) {

		double theOilLevel = shelterDog.getCurrentOilLevel();
		String oilLevelMinDec;
		if (theOilLevel == (long) theOilLevel) {
			oilLevelMinDec = String.format("%d", (long) theOilLevel);
		} else {
			oilLevelMinDec = String.format("%s", theOilLevel);
		}

		System.out.printf("%-25s", shelterDog.getName() + " (robotic dog)");
		System.out.print("|");
		System.out.printf("%-8s", ""); // hunger level n/a here
		System.out.print("|");
		System.out.printf("%-8s", ""); // thirst level n/a here
		System.out.print("|");
		System.out.printf("%-15s", ""); // activity level n/a here
		System.out.print("|");
		System.out.printf("%-16s", ""); // litter box status n/a here
		System.out.print("|");
		System.out.printf("%-10d", shelterDog.getHappinessLevel());
		System.out.print("|");
		System.out.printf("%-7d", shelterDog.getHealthLevel());
		System.out.print("|");
		System.out.printf("%-10s", oilLevelMinDec + oilLevel);
		System.out.println();
	}

	public static void displayMainMenu() {

		System.out.println("\nWhat would you like to do next?\n");
		System.out.println("\t1. Feed the pets");
		System.out.println("\t2. Water the pets");
		System.out.println("\t3. Walk the dogs");
		System.out.println("\t4. Play with a pet");
		System.out.println("\t5. Clean the dog cages");
		System.out.println("\t6. Clean the litter box");
		System.out.println("\t7. Oil the robotic pets");
		System.out.println("\t8. Adopt a pet");
		System.out.println("\t9. Admit a pet");
		System.out.println("\t10. Eat pizza");
		System.out.println("\t11. Quit");
	}

	// Display cats, dogs, robotic cats, then robotic dogs
	public static void displayPetNamesAndDescriptions(VirtualPetShelter myShelter) {

		Collection<VirtualPet> shelterPets = myShelter.getAllPets();

		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof OrganicCat) {
				formatBriefPetInfo(shelterPet, " (cat) ");
			}
		}
		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof OrganicDog) {
				formatBriefPetInfo(shelterPet, " (dog) ");
			}
		}
		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof RoboticCat) {
				formatBriefPetInfo(shelterPet, " (robotic cat) ");
			}
		}
		for (VirtualPet shelterPet : shelterPets) {
			if (shelterPet instanceof RoboticDog) {
				formatBriefPetInfo(shelterPet, " (robotic dog) ");
			}
		}
	}

	public static void formatBriefPetInfo(VirtualPet shelterPet, String petType) {
		String name = "[" + shelterPet.getName() + "]";
		System.out.printf("%-25s", name + petType);
		System.out.printf("%-25s", shelterPet.getDescription());
		System.out.println();
	}

	public static void handlePetActivity(VirtualPetShelter myShelter, String activity) {

		displayPetNamesAndDescriptions(myShelter);
		Scanner input = new Scanner(System.in);
		String petName = input.nextLine();
		// the user may have typed an invalid name, if so give them a message and
		// go back to the menu
		VirtualPet testPet = myShelter.getPet(petName);
		if (testPet == null) {
			System.out.println("Sorry, the name you typed does not match one of our pets.");
		} else if (activity.equals("play")) {
			myShelter.playWithAPet(petName);
			System.out.println("Thanks for playing with " + petName + "!\n\n");
		}
	}

	public static void handlePetAdoption(VirtualPetShelter myShelter) {

		displayPetNamesAndDescriptions(myShelter);
		Scanner input = new Scanner(System.in);
		String petName = input.nextLine();
		// the user may have typed an invalid name, if so give them a message and
		// go back to the menu
		VirtualPet testPet = myShelter.getPet(petName);
		if (testPet == null) {
			System.out.println("Sorry, the name you typed does not match one of our pets.");
		} else {
			myShelter.releasePet(petName);
			System.out.println(petName + " is going to a great home!\n\n");
		}
	}

	public static void handlePetAdmission(VirtualPetShelter myShelter) {

		Scanner input = new Scanner(System.in);

		System.out.println("What is your pet's name?");
		String petName = input.nextLine();

		System.out.println("Please provide a brief description of your pet:");
		String petDescription = input.nextLine();

		System.out.println("Enter a number (1-4) for the type of pet that would you like to admit:");
		System.out.println("1: Cat\n");
		System.out.println("2: Dog\n");
		System.out.println("3: Robotic Cat\n");
		System.out.println("4: Robotic Dog\n");
		String petType = input.nextLine();
		boolean validPetTypeInput = true;

		if (petName.isEmpty() || petDescription.isEmpty() || !validPetTypeInput) {
			System.out.println("Sorry, you must enter both a pet name and a description.");
		} else {
			switch (petType) {
			case "1":
				myShelter.addPet(new OrganicCat(petName, petDescription));
				break;
			case "2":
				myShelter.addPet(new OrganicDog(petName, petDescription));
				break;
			case "3":
				myShelter.addPet(new RoboticCat(petName, petDescription));
				break;
			case "4":
				myShelter.addPet(new RoboticDog(petName, petDescription));
				break;
			default:
				System.out.println("Sorry, you  must enter a valid pet type (1-4).");
			}
			System.out.println("Don't worry, we will find a great home for " + petName + "!\n\n");
		}
	}

}