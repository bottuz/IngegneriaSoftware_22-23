import static org.junit.Assert.*;

import org.junit.Test;

public class Conto_CorrenteTest {

	// creazione di un oggetto Conto_Corrente e la verifica del suo numero di conto
	// PASSA
	@Test
	public void testContoEsistente() {
		Conto_Corrente cc = new Conto_Corrente(22222);
		assertEquals(22222, cc.getN_conto());
	}

	// creazione di un oggetto Conto_Corrente e la verifica del suo importo minimo
	// PASSA
	@Test
	public void testImportoMinimo() {
		Conto_Corrente cc = new Conto_Corrente(11111);
		cc.setImporto_minimo(true);
		assertTrue(cc.getImporto_minimo());
	}

}
