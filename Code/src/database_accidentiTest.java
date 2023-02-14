import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class database_accidentiTest {

	@Test
	public void testCartaAccidentata() {
		database_accidenti db = new database_accidenti(22223333);
		assertTrue(db.cartaaccidentata(22223333));
	}

	@Test
	public void testCartaNonAccidentata() {
		database_accidenti db = new database_accidenti(11112222);
		assertFalse(db.cartaaccidentata(11112222));
	}

	@Test
	public void testGetN_carta() {
		database_accidenti db = new database_accidenti(22223333);
		assertEquals(22223333, db.getN_carta());
	}

	@Test
	public void testGetTipo_accidente() {
		database_accidenti db = new database_accidenti(22223333);
		assertEquals("Furto", db.getTipo_accidente());
	}

}
