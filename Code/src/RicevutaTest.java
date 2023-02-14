//import static org.junit.Assert.*;
//
//import java.sql.Date;
//import java.sql.Time;
//
//import org.junit.Test;
//
//public class RicevutaTest {
//
//	@Test
//	public void testGettersAndSetters() {
//		int n_serie = 50517;
//		int n_carta = 11112222;
//		Time ora_stampa = new Time(System.currentTimeMillis());
//		int n_ATM = 1;
//		int n_filiale = 1;
//		double importo_prelievo = 1000.00;
//		Date data_stampa = new Date(System.currentTimeMillis());
//		boolean carta_ok = true;
//
//		Ricevuta r = new Ricevuta(n_serie, n_carta, n_ATM, n_filiale, importo_prelievo, carta_ok);
//
//		assertEquals(n_serie, r.getN_serie());
//		assertEquals(n_carta, r.getN_carta());
//		assertEquals(ora_stampa, r.getOra_stampa());
//		assertEquals(n_ATM, r.getN_ATM());
//		assertEquals(n_filiale, r.getN_filiale());
//		assertEquals(importo_prelievo, r.getImporto_prelievo(), 0.0);
//		assertEquals(data_stampa, r.getData_stampa());
//		assertEquals(carta_ok, r.getCarta_ok());
//	}
//
//	@Test
//	public void testExistingConstructor() {
//		int n_serie = 50517;
//
//		Ricevuta r = new Ricevuta(n_serie);
//
//		assertEquals(n_serie, r.getN_serie());
//		// controllare gli altri valori se disponibili nel database
//	}
//
//}
