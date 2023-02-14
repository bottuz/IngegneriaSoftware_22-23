import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Date;

public class UtenteTest {

	// Viene creato un nuovo oggetto "Utente" con alcune informazioni, e poi viene
	// verificato che i dati siano stati impostati correttamente. In particolare, si
	// controlla che il nome, il cognome e l'indirizzo di residenza siano corretti,
	// che il numero di carta e di conto siano stati generati correttamente, che il
	// PIN sia stato generato correttamente e che la tessera magnetica e il conto
	// corrente siano stati creati correttamente
	// PASSA
	@Test
	public void testCostruttore() {
		Utente utente = new Utente("Mario", "Rossi", new Date(), "Via Roma 123");

		// verifica che i dati inseriti nel costruttore siano stati impostati
		// correttamente
		assertEquals("Mario", utente.getNome());
		assertEquals("Rossi", utente.getCognome());
		assertEquals("Via Roma 123", utente.getResidenza());

		// verifica che il numero di carta e di conto siano stati generati correttamente
		assertNotNull(utente.getN_carta());
		assertNotNull(utente.getN_conto());

		// verifica che il PIN sia stato generato correttamente
		assertNotEquals(0, utente.getPIN());

		// verifica che la tessera magnetica e il conto corrente siano stati creati
		// correttamente
		assertNotNull(utente.getTessera());
		assertNotNull(utente.getCc());
	}
}