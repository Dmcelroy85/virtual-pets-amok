package pets_amok;

import static org.junit.Assert.*;
import org.junit.Test;

public class VirtualPetShelterTest {

	@Test
	public void litterBoxWasteLevelEqualsZeroAtConstruction() {
		VirtualPetShelter underTest = new VirtualPetShelter();
		assertEquals(0, underTest.getLitterBoxWasteLevel());
		underTest.addPet(new OrganicCat("Fluffy", "very furry cat"));
		assertEquals(0, underTest.getLitterBoxWasteLevel());
	}

	@Test
	public void litterBoxWasteLevelGreaterThanZeroAfterTick() {
		VirtualPetShelter underTest = new VirtualPetShelter();
		underTest.addPet(new OrganicCat("Fluffy", "very furry cat"));
		underTest.tick();
		underTest.tick();
		assertTrue(underTest.getLitterBoxWasteLevel() >= 1);
	}

}