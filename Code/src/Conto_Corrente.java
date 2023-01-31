import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

public class Conto_Corrente {
	private int n_conto;
	private Boolean importo_minimo;
	private Double bilancio;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////	

	// costruttore esistente sul db
	public Conto_Corrente(int n_conto) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM conto_corrente where n_conto ='" + n_conto + "'");
			if (rs.next()) {
				n_conto = rs.getInt("n_conto");
				importo_minimo = rs.getBoolean("importo_minimo");
				setBilancio(rs.getDouble("bilancio"));
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// costruttore nuovo conto corrente
	public Conto_Corrente() {
		// genero N_conto per utente di 5 int
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			n_conto = rand.nextInt(999999);
		}
		importo_minimo = false;
		bilancio = 0.0;
		// carico nel DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"INSERT into conto_corrente VALUES('" + n_conto + "','" + importo_minimo + "','" + bilancio + "')");
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "CONTO CORRENTE CREATO CORRETTAMENTE!");
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

//////////////////////////////////////////////////////////////////////////////
//									GET/SET									//
//////////////////////////////////////////////////////////////////////////////

	public int getN_conto() {
		return n_conto;
	}

	public Boolean getImporto_minimo() {
		return importo_minimo;
	}

	public Double getBilancio() {
		return bilancio;
	}

	public void setBilancio(Double bilancio) {
		this.bilancio = bilancio;
	}

	public void setN_conto(int n_conto) {
		this.n_conto = n_conto;
	}

	public void setImporto_minimo(Boolean importo_minimo) {
		this.importo_minimo = importo_minimo;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	public void PrelevaContante(int qtaprelievo, int conto) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			statement = connection
					.prepareStatement("UPDATE conto_corrente SET bilancio = bilancio - ? WHERE n_conto = ?");

			statement.setDouble(1, qtaprelievo);
			statement.setInt(2, conto);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				bilancio -= qtaprelievo;
				JOptionPane.showMessageDialog(null, "Hai prelevato: " + qtaprelievo + "; NUOVO SALDO: " + bilancio);
			} else {
				JOptionPane.showMessageDialog(null, "Prelievo non riuscito. Verifica il numero del conto.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void depositaContante(int qtadeposito, int conto) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			statement = connection
					.prepareStatement("UPDATE conto_corrente SET bilancio = bilancio + ? WHERE n_conto = ?");

			statement.setDouble(1, qtadeposito);
			statement.setInt(2, conto);

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				bilancio += qtadeposito;
				JOptionPane.showMessageDialog(null, "Hai depositato: " + qtadeposito + "; NUOVO SALDO: " + bilancio);
			} else {
				JOptionPane.showMessageDialog(null, "Deposito non riuscito. Verifica il numero del conto.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void rilascia_carta() {
		// da implementare
	}

	public void scrivi_importo_transazione() {
		// da implementare
	}
}