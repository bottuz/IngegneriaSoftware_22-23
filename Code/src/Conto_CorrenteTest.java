import static org.junit.Assert.*;

import org.junit.Test;

public class Conto_CorrenteTest {

	@Test
	public void testContoEsistente() {
		Conto_Corrente cc = new Conto_Corrente(22222);
		assertEquals(22222, cc.getN_conto());
	}

	@Test
	public void testImportoMinimo() {
		Conto_Corrente cc = new Conto_Corrente(11111);
		cc.setImporto_minimo(true);
		assertTrue(cc.getImporto_minimo());
		// Verificare che la modifica sia stata correttamente registrata nel database
	}

}
