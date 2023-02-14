import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Tessera_Magnetica {
	private int n_carta;
	private Date data_emissione;
	private Date data_scadenza;
	private int PIN;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	// costruttore esistente sul db
	public Tessera_Magnetica(int n_carta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tessera_magnetica where n_carta ='" + n_carta + "'");
			if (rs.next()) {
				this.n_carta = n_carta;
				data_emissione = rs.getDate("data_emissione");
				data_scadenza = rs.getDate("data_scadenza");
				PIN = rs.getInt("PIN");
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// costruttore nuova tessera
	public Tessera_Magnetica() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 10);
		data_emissione = new Date(); // giorno dell'emissione
		data_scadenza = calendar.getTime(); // scade 10 anni dopo l'emissione
		// genero PIN per utente
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			PIN = rand.nextInt(999999);
		}
		for (int i = 0; i < 8; i++) {
			n_carta = rand.nextInt(999999);
		}
		// carico nel DB
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT into tessera_magnetica VALUES('" + n_carta + "','" + data_emissione
					+ "','" + data_scadenza + "','" + PIN + "')");
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

	public int getN_carta() {
		return n_carta;
	}

	public Date getData_emissione() {
		return data_emissione;
	}

	public Date getData_scadenza() {
		return data_scadenza;
	}

	public int getPIN() {
		return PIN;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public void setData_emissione(Date data_emissione) {
		this.data_emissione = data_emissione;
	}

	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}

	public void setPIN(int PIN) {
		this.PIN = PIN;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	// funzione per verificare se esiste una TESSERA cercato dal n_carta
	public boolean esisteTESSERA(int n_carta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM conto_corrente where n_carta ='" + n_carta + "'");
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

	public void invia_n_carta() {
		// da implementare
	}
}