import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

	// loginTEST
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWORD = "admin";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private atm atm;
	ArrayList<Transazione> transactions;

	public LoginTest() {
	}

	@Test
	public void testMainMethod() {
		LOGIN window = new LOGIN();
		LOGIN.main(new String[0]);
		assertTrue(window.frame.isVisible());
	}

	@Test
	public void testLoginConstructor() {
		LOGIN login = new LOGIN();
		assertNotNull(login);
	}

	@Test
	public void testInitialize() {
		assertNotNull(frame);
		assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
		assertTrue(frame.getContentPane().getLayout() == null);
		assertEquals("BANCA D'ITALIA-bergamo", frame.getTitle());

		assertNotNull(textField);
		assertEquals(10, textField.getColumns());
		assertNotNull(passwordField);

		assertNotNull(atm);
		assertEquals("bergamo", atm.getLuogo());

		JButton btnNewButton = (JButton) frame.getContentPane().getComponent(3);
		assertNotNull(btnNewButton);
		assertEquals("Login", btnNewButton.getText());

		JLabel lblNewLabel = (JLabel) frame.getContentPane().getComponent(0);
		assertNotNull(lblNewLabel);
		assertEquals("NCarta", lblNewLabel.getText());

		JLabel lblNewLabel_1 = (JLabel) frame.getContentPane().getComponent(1);
		assertNotNull(lblNewLabel_1);
		assertEquals("PIN", lblNewLabel_1.getText());
	}

	@Test
	private void TestCreateConnection() {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			assertTrue(connection != null);
			connection.close();
		} catch (ClassNotFoundException e) {
			fail("Failed to load database driver");
		} catch (SQLException e) {
			fail("Failed to connect to database" + e);
		}
	}
}
