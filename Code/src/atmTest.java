import static org.junit.Assert.*;

import org.junit.Test;

public class atmTest {

	@Test
	public void testAtmCreation() {
		atm atm = new atm("Bergamo");
		assertEquals(1, atm.getN_serie());
		assertEquals(1, atm.getN_filiale());
	}
}
