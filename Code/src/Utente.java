import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utente {
	private int n_conto;
	private int n_carta;
	private int PIN;
	private String nome;
	private String cognome;
	private Date annoNascita;
	private String residenza;
	private Tessera_Magnetica tessera;
	private Conto_Corrente cc;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	// costruttore esistente sul db
	public Utente(int n_carta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM utente where n_carta ='" + n_carta + "'");
			if (rs.next()) {
				setN_conto(rs.getInt("n_conto"));
				setN_carta(n_carta);
				setPIN(rs.getInt("PIN"));
				setNome(rs.getString("nome"));
				setCognome(rs.getString("cognome"));
				setAnnoNascita(rs.getDate("anno_nascita"));
				setResidenza(rs.getString("residenza"));
				tessera = new Tessera_Magnetica(n_carta);
				cc = new Conto_Corrente(n_conto);
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public Utente(int n_conto, String cognome) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM utente where n_conto ='" + n_conto + "' AND cognome='" + cognome + "'");
			if (rs.next()) {
				setN_conto(rs.getInt("n_conto"));
				setN_carta(rs.getInt("n_carta"));
				setPIN(rs.getInt("PIN"));
				setNome(rs.getString("nome"));
				setCognome(rs.getString("cognome"));
				setAnnoNascita(rs.getDate("anno_nascita"));
				setResidenza(rs.getString("residenza"));
				tessera = new Tessera_Magnetica(rs.getInt("n_carta"));
				cc = new Conto_Corrente(rs.getInt("n_conto"));
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// costruttore nuovo utente
	public Utente(String nome, String cognome, Date annoNascita, String residenza) {
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.residenza = residenza;
		cc = new Conto_Corrente();
		tessera = new Tessera_Magnetica();
		n_carta = tessera.getN_carta();
		n_conto = cc.getN_conto();
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			PIN = rand.nextInt(999999);
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT into utente VALUES('" + n_conto + "','" + n_carta + "','" + PIN
					+ "','" + nome + "','" + cognome + "','" + annoNascita + "','" + residenza + "')");
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "TESSERA CREATA CORRETTAMENTE!");
			}
			con.close();
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

	public int getN_carta() {
		return n_carta;
	}

	public void setN_conto(int n_conto) {
		this.n_conto = n_conto;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(Date annoNascita) {
		this.annoNascita = annoNascita;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public int getPIN() {
		return PIN;
	}

	public void setPIN(int pIN) {
		PIN = pIN;
	}

	public Tessera_Magnetica getTessera() {
		return tessera;
	}

	public void setTessera(Tessera_Magnetica tessera) {
		this.tessera = tessera;
	}

	public Conto_Corrente getCc() {
		return cc;
	}

	public void setCc(Conto_Corrente cc) {
		this.cc = cc;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	// serve a verificare l'esistenza di un utente dal COGNOME
	public boolean esisteUTENTE(int n_conto, String cognome) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM utente where cognome ='" + cognome + "' AND n_conto='" + n_conto + "'");
			if (rs.next()) {
				return true;
			}
			con.close();
			stmt.close();
			rs.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	// Serve a modificare il PIN utente {5 interi}
	public void modificaPIN(String PIN) {
		String url = "jdbc:mysql://localhost:3306/ATM";
		String username = "admin";
		String password = "admin";
		String query = "UPDATE utente SET pin = ? WHERE n_conto = ?";

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(query)) {
			int n_conto = this.n_conto; // N_CONTO dell'utente per cui si vuole cambiare il pin
			String newPin = PIN; // Nuovo pin

			stmt.setString(1, newPin);
			stmt.setInt(2, n_conto);
			@SuppressWarnings("unused")
			int rowsUpdated = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "PIN MODIFICATO CORRETTAMENTE");
			setPIN(Integer.parseInt(newPin));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void cancellaUTENTE() {
		String url = "jdbc:mysql://localhost:3306/ATM";
		String username = "admin";
		String password = "admin";
		String queryconto = "DELETE FROM conto_corrente WHERE n_conto = ?";
		String querytessera = "DELETE FROM tessera_magnetica WHERE n_carta = ?";
		String queryutente = "DELETE FROM utente WHERE n_conto = ?";

		// elimino utente
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(queryutente)) {
			int n_conto = this.n_conto; // N_CONTO dell'utente che si vuole eliminare

			stmt.setInt(1, n_conto);
			@SuppressWarnings("unused")
			int rowsUpdated = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "UTENTE CANCELLATO!!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		// elimino tessera
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(querytessera)) {
			int n_carta = this.n_carta; // N_CARTA della tessera che si vuole eliminare

			stmt.setInt(1, n_carta);
			@SuppressWarnings("unused")
			int rowsUpdated = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "TESSERA CANCELLATA!!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		// elimino conto
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(queryconto)) {
			int n_conto = this.n_conto; // N_CONTO del CC che si vuole eliminare

			stmt.setInt(1, n_conto);
			@SuppressWarnings("unused")
			int rowsUpdated = stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "CONTO CORRENTE CANCELLATO!!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public void richiede_ricevuta() {
		// da implementare
	}

	public void ritira_ricevuta() {
		// da implementare
	}

	public void ritira_carta() {
		// da implementare
	}
}