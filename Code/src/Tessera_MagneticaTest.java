import static org.junit.Assert.*;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class Tessera_MagneticaTest {

	// l test verifica se, data una tessera magnetica con numero di carta uguale a
	// 11112222, il costruttore della classe Tessera_Magnetica ha creato
	// correttamente
	// l'oggetto e se la funzione "getN_carta" restituisce il valore 11112222
	// (assertEquals). Quindi, il test si aspetta che il numero di carta della
	// tessera creata sia 11112222.
	// PASSA
	@Test
	public void testCostruttoreEsistente() {
		// Crea una tessera esistente con n_carta = 1
		Tessera_Magnetica tessera = new Tessera_Magnetica(11112222);

		// Verifica che il numero della carta sia 1
		assertEquals(11112222, tessera.getN_carta());
	}

	// Il test verifica se il costruttore della classe Tessera_Magnetica crea
	// correttamente una nuova tessera magnetica. In particolare, il test verifica
	// che il numero di carta della nuova tessera sia diverso da 0
	// (assertNotEquals), che la data di emissione sia il giorno corrente
	// (assertEquals), che la data di scadenza sia 10 anni dopo la data di emissione
	// (assertEquals), e che il PIN della tessera sia diverso da 0 (assertNotNull)
	// GENERA FAILURE ---> l'ora creata e dall'oggetto e quella creata in locale
	// differiscono di pochi secondi!!
	@Test
	public void testCostruttoreNuovaTessera() {
		// Crea una nuova tessera
		Tessera_Magnetica tessera = new Tessera_Magnetica();
		Date d = new Date();
		// Verifica che il numero della carta sia diverso da 0
		assertNotEquals(0, tessera.getN_carta());

		// Verifica che la data di emissione sia il giorno corrente
		assertEquals(d, tessera.getData_emissione());

		// Verifica che la data di scadenza sia 10 anni dopo la data di emissione
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tessera.getData_emissione());
		calendar.add(Calendar.YEAR, 10);
		assertEquals(calendar.getTime(), tessera.getData_scadenza());

		// Verifica che il PIN sia diverso da 0
		assertNotEquals(0, tessera.getPIN());
	}

}
