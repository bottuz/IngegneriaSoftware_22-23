import static org.junit.Assert.*;

import org.junit.Test;

public class atmTest {

	// verifica se il numero di serie e il numero di filiale dell'oggetto creato
	// sono entrambi uguali a 1.
	// PASSA
	@Test
	public void testAtmCreation() {
		atm atm = new atm("Bergamo");
		assertEquals(1, atm.getN_serie());
		assertEquals(1, atm.getN_filiale());
	}
}
