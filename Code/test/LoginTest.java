import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginTest {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWORD = "admin";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

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
