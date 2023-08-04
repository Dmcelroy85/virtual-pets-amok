package pets_amok;
import static org.junit.Assert.*;

import org.junit.Test;

public class RoboticCatTest {

	// test with minimal constructor data supplied (name and description)
	@Test
	public void shouldBeAbleToCreateCatObject() {
		RoboticCat underTest = new RoboticCat("", "");
		assertNotNull(underTest);
	}

	@Test
	public void tickShouldReturnZero() {
		RoboticCat cat1 = new RoboticCat("fluffy", "white cat");
		int check1 = cat1.tick();
		assertEquals(0, check1);
	}

	@Test
	public void statsForHappinessStaySameAfter15TicksWithNoPlay() {
		RoboticCat cat1 = new RoboticCat("fluffy", "white cat");
		int hpLevelB4 = cat1.getHappinessLevel();
		for (int i = 1; i < 15; i++) {
			cat1.tick();
		}
		int hpLevelAfter = cat1.getHappinessLevel();
		assertEquals(0, (hpLevelAfter - hpLevelB4));
	}

	@Test
	public void statsForHealthStaySameAfter15TicksWithNoPlay() {
		RoboticCat cat1 = new RoboticCat("fluffy", "white cat");
		int hpLevelB4 = cat1.getHealthLevel();
		for (int i = 1; i < 15; i++) {
			cat1.tick();
		}
		int hpLevelAfter = cat1.getHealthLevel();
		assertEquals(0, (hpLevelAfter - hpLevelB4));
	}

	@Test
	public void statsForOilStaySameAfter15TicksWithNoPlay() {
		RoboticCat cat1 = new RoboticCat("fluffy", "white cat");
		double hpLevelB4 = cat1.getCurrentOilLevel();
		for (int i = 1; i < 15; i++) {
			cat1.tick();
		}
		double hpLevelAfter = cat1.getCurrentOilLevel();
		assertEquals(0.0, (hpLevelAfter - hpLevelB4), .01);
	}

}