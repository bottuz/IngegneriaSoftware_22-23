import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * La classe Tessera_Magnetica gestisce le tessere magnetiche.
 * 
 * @author botta
 * @version 1.4.3
 */
public class Tessera_Magnetica {
	private int n_carta;
	private Date data_emissione;
	private Date data_scadenza;
	private int PIN;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Costruisce l'oggetto Tessera_Magnetica da una tessera magnetica presente sul
	 * DB.
	 * 
	 * @param n_carta
	 */
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

	/**
	 * Costruisce l'oggetto Tessera_Magnetica e lo aggiunge al DB.
	 */
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

	/**
	 * Il metodo ritorna il numero di carta dell'oggetto Tessera_Magnetica.
	 * 
	 * @return numero di carta
	 */
	public int getN_carta() {
		return n_carta;
	}

	/**
	 * Il metodo ritorna la data di emissione dell'oggetto Tessera_Magnetica.
	 * 
	 * @return data emissione
	 */
	public Date getData_emissione() {
		return data_emissione;
	}

	/**
	 * Il metodo ritorna la data di scadenza dell'oggetto Tessera_Magnetica.
	 * 
	 * @return data scadenza
	 */
	public Date getData_scadenza() {
		return data_scadenza;
	}

	/**
	 * Il metodo ritorna il PIN dell'oggetto Tessera_Magnetica.
	 * 
	 * @return PIN
	 */
	public int getPIN() {
		return PIN;
	}

	/**
	 * Il metodo imposta il numero di carta dell'oggetto Tessera_Magnetica.
	 * 
	 * @param n_carta
	 */
	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	/**
	 * Il metodo imposta la data di emissione dell'oggetto Tessera_Magnetica.
	 * 
	 * @param data_emissione
	 */
	public void setData_emissione(Date data_emissione) {
		this.data_emissione = data_emissione;
	}

	/**
	 * Il metodo imposta la data di scadenza dell'oggetto Tessera_Magnetica.
	 * 
	 * @param data_scadenza
	 */
	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}

	/**
	 * Il metodo imposta il PIN dell'oggetto Tessera_Magnetica.
	 * 
	 * @param PIN
	 */
	public void setPIN(int PIN) {
		this.PIN = PIN;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Il metodo ritorna true se esiste sul DB la Tessera_Magnetica cercata.
	 * 
	 * @param n_carta
	 * @return true o false
	 */
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

//	public void invia_n_carta() {
//		// da implementare
//	}
}