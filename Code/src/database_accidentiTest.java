import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class database_accidentiTest {

	// verifica se il valore restituito dal metodo cartaaccidentata() è true quando
	// viene passato l'argomento 22223333.
	// PASSA
	@Test
	public void testCartaAccidentata() {
		database_accidenti db = new database_accidenti(22223333);
		assertTrue(db.cartaaccidentata(22223333));
	}

	// verifica se, dato un numero di carta (11112222), la funzione
	// "cartaaccidentata" del database degli incidenti restituisce falso
	// (assertFalse). Quindi, il test si aspetta che il proprietario associato a
	// quella carta non abbia mai avuto incidenti alla carta.
	// PASSA
	@Test
	public void testCartaNonAccidentata() {
		database_accidenti db = new database_accidenti(11112222);
		assertFalse(db.cartaaccidentata(11112222));
	}

	// Il test verifica se, dato un numero di carta (22223333), la funzione
	// "getN_carta" del database degli incidenti restituisce lo stesso numero di
	// carta (assertEquals). Quindi, il test si aspetta che il numero di carta
	// restituito dalla funzione sia uguale al numero di carta passato come
	// parametro al momento della creazione dell'oggetto database_accidenti.
	// PASSA
	@Test
	public void testGetN_carta() {
		database_accidenti db = new database_accidenti(22223333);
		assertEquals(22223333, db.getN_carta());
	}

	// Il test verifica se, dato un numero di carta (22223333), la funzione
	// "getTipo_accidente" del database degli incidenti restituisce la stringa
	// "Furto" (assertEquals). Quindi, il test si aspetta che il tipo di incidente
	// restituito dalla funzione sia "Furto" per il conducente associato a quella
	// patente
	// PASSA
	@Test
	public void testGetTipo_accidente() {
		database_accidenti db = new database_accidenti(22223333);
		assertEquals("Furto", db.getTipo_accidente());
	}

}
