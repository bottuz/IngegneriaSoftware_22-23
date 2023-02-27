import java.sql.Time;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * La classe Ricevuta gestisce le ricevute.
 * 
 * @author botta
 * @version 1.4.3
 */
public class Ricevuta {
	private int n_serie;
	private int n_carta;
	private Time ora_stampa;
	private int n_ATM;
	private int n_filiale;
	private double importo_prelievo;
	private Date data_stampa;
	private boolean carta_ok;
	private Transazione transazione;
//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Costruisce l'oggetto Ricevuta a partire da una ricevuta presente nel DB.
	 * 
	 * @param n_serie
	 */
	public Ricevuta(int n_serie) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ricevuta WHERE n_serie='" + n_serie + "'");
			if (rs.next()) {
				this.n_serie = n_serie;
				n_carta = rs.getInt("n_carta");
				ora_stampa = rs.getTime("ora_stampa");
				n_ATM = rs.getInt("n_atm");
				n_filiale = rs.getInt("n_filiale");
				importo_prelievo = rs.getDouble("importo_prelievo");
				data_stampa = rs.getDate("data_stampa");
				carta_ok = rs.getBoolean("carta_ok");
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Costruisce una Ricevuta e la aggiunge al DB.
	 * 
	 * @param n_serie
	 * @param n_carta
	 * @param n_atm
	 * @param n_filiale
	 * @param importo_prelievo
	 * @param carta_ok
	 */
	public Ricevuta(int n_serie, int n_carta, int n_atm, int n_filiale, Double importo_prelievo, Boolean carta_ok) {
		ora_stampa = new Time(System.currentTimeMillis());
		data_stampa = new Date(System.currentTimeMillis());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT INTO ricevuta VALUES('" + n_serie + "','" + n_carta + "','"
					+ ora_stampa + "','" + n_atm + "','" + n_filiale + "','" + importo_prelievo + "','" + data_stampa
					+ "','" + carta_ok + ")");
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "RICEVUTA CREATA CON SUCCESSO!");
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
	 * Il metodo ritorna il numero di serie dell'oggetto Ricevuta.
	 * 
	 * @return numero di serie
	 */
	public int getN_serie() {
		return n_serie;
	}

	/**
	 * Il metodo ritorna il numero di carta dell'oggetto Ricevuta.
	 * 
	 * @return numero di carta
	 */
	public int getN_carta() {
		return n_carta;
	}

	/**
	 * Il metodo ritorna l'ora dell'oggetto Ricevuta.
	 * 
	 * @return ora
	 */
	public Time getOra_stampa() {
		return ora_stampa;
	}

	/**
	 * Il metodo ritorna il numero di ATM dell'oggetto Ricevuta.
	 * 
	 * @return numero di ATM
	 */
	public int getN_ATM() {
		return n_ATM;
	}

	/**
	 * Il metodo ritorna il numero di filiale dell'oggetto Ricevuta.
	 * 
	 * @return numero di filiale
	 */
	public int getN_filiale() {
		return n_filiale;
	}

	/**
	 * Il metodo ritorna l'importo prelevato dell'oggetto Ricevuta.
	 * 
	 * @return importo prelevato
	 */
	public double getImporto_prelievo() {
		return importo_prelievo;
	}

	/**
	 * Il metodo ritorna la data dell'oggetto Ricevuta.
	 * 
	 * @return data
	 */
	public Date getData_stampa() {
		return data_stampa;
	}

	/**
	 * Il metodo ritorna il true se la carta dell'oggetto Ricevuta è ok.
	 * 
	 * @return true o false
	 */
	public boolean getCarta_ok() {
		return carta_ok;
	}

	/**
	 * Il metodo ritorna l'oggetto transazione dell'oggetto Ricevuta.
	 * 
	 * @return transazione
	 */
	public Transazione getTransazione() {
		return transazione;
	}

	/**
	 * Il metodo imposta l'oggetto Transazione dell'oggetto Ricevuta.
	 * 
	 * @param transazione
	 */
	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	/**
	 * Il metodo imposta il numero di serie dell'oggetto Ricevuta.
	 * 
	 * @param n_serie
	 */
	public void setN_serie(int n_serie) {
		this.n_serie = n_serie;
	}

	/**
	 * Il metodo imposta il numero di carta dell'oggetto Ricevuta.
	 * 
	 * @param n_carta
	 */
	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	/**
	 * Il metodo imposta l'ora dell'oggetto Ricevuta.
	 * 
	 * @param ora_stampa
	 */
	public void setOra_stampa(Time ora_stampa) {
		this.ora_stampa = ora_stampa;
	}

	/**
	 * Il metodo imposta il numero di ATM dell'oggetto Ricevuta.
	 * 
	 * @param n_ATM
	 */
	public void setN_ATM(int n_ATM) {
		this.n_ATM = n_ATM;
	}

	/**
	 * Il metodo imposta il numero di filiale dell'oggetto Ricevuta.
	 * 
	 * @param n_filiale
	 */
	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	/**
	 * Il metodo imposta l'importo prelevato dell'oggetto Ricevuta.
	 * 
	 * @param importo_prelievo
	 */
	public void setImporto_prelievo(double importo_prelievo) {
		this.importo_prelievo = importo_prelievo;
	}

	/**
	 * Il metodo imposta la data dell'oggetto Ricevuta.
	 * 
	 * @param date
	 */
	public void setData_stampa(Date date) {
		this.data_stampa = date;
	}

	/**
	 * Il metodo imposta true se la carta è ok dell'oggetto Ricevuta.
	 * 
	 * @param carta_ok
	 */
	public void setCarta_ok(boolean carta_ok) {
		this.carta_ok = carta_ok;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

//	// funzioni
//	public void controlla_rimanenza_carta() {
//		// check remaining balance on connected account
//	}
//
//	public void stampa() {
//		// print receipt with the specified details
//	}
//
//	public void rilascia_ricevuta() {
//		// release the receipt
//	}
//
//	public void no_stampa() {
//		// Skip the step of printing receipt
//	}
}
