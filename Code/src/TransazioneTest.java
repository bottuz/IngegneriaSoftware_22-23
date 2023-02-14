import static org.junit.Assert.assertEquals;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TransazioneTest {

	private static Connection con;
	@SuppressWarnings("unused")
	private static Statement stmt;

	@BeforeClass
	public static void setup() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
		stmt = con.createStatement();
	}

	@AfterClass
	public static void teardown() throws Exception {
		con.close();
	}

	// Il test crea una nuova istanza della classe "Transazione" utilizzando il
	// costruttore con argomenti, e poi verifica che i campi dell'istanza abbiano i
	// valori attesi utilizzando il metodo "assertEquals".
	// PASSA
	@Test
	public void testCostruttoreEsistente() throws Exception {
		Transazione t = new Transazione("Prelievo", 1, 1, 11112222, 100);
		assertEquals("Prelievo", t.getTipo_transazione());
		assertEquals(1, t.getN_ATM());
		assertEquals(1, t.getN_filiale());
		assertEquals(11112222, t.getN_carta());
		assertEquals(100, t.getImporto());
	}

}
