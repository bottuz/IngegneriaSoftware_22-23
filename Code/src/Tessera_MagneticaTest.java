import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import org.junit.Test;

public class Tessera_MagneticaTest {

	@Test
	public void testCostruttoreEsistente() {
		// Crea una tessera esistente con n_carta = 1
		Tessera_Magnetica tessera = new Tessera_Magnetica(1);

		// Verifica che il numero della carta sia 1
		assertEquals(1, tessera.getN_carta());
	}

	@Test
	public void testCostruttoreNuovaTessera() {
		// Crea una nuova tessera
		Tessera_Magnetica tessera = new Tessera_Magnetica();

		// Verifica che il numero della carta sia diverso da 0
		assertNotEquals(0, tessera.getN_carta());

		// Verifica che la data di emissione sia il giorno corrente
		assertEquals(new Date(System.currentTimeMillis()), tessera.getData_emissione());

		// Verifica che la data di scadenza sia 10 anni dopo la data di emissione
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tessera.getData_emissione());
		calendar.add(Calendar.YEAR, 10);
		assertEquals(calendar.getTime(), tessera.getData_scadenza());

		// Verifica che il PIN sia diverso da 0
		assertNotEquals(0, tessera.getPIN());
	}

	@Test
	public void testEsisteTessera() {
		// Crea una nuova tessera
		Tessera_Magnetica tessera = new Tessera_Magnetica();
		int n_carta = tessera.getN_carta();

		// Verifica che la funzione esisteTESSERA restituisca true per la tessera appena
		// creata
		assertTrue(tessera.esisteTESSERA(n_carta));

		// Crea un nuovo numero di carta casuale che non esiste nella base di dati
		Random rand = new Random();
		int n_carta_inesistente = rand.nextInt();

		// Verifica che la funzione esisteTESSERA restituisca false per una carta
		// inesistente
		assertFalse(tessera.esisteTESSERA(n_carta_inesistente));
	}

}
