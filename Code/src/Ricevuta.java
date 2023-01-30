import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

	// costruttore esistente sul db
	public Ricevuta(int n_serie) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ricevuta WHERE n_serie='" + n_serie + "'");
			if (rs.next()) {
				n_serie = rs.getInt("n_serie");
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

	// costruttore nuova ricevuta
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

	// getters
	public int getN_serie() {
		return n_serie;
	}

	public int getN_carta() {
		return n_carta;
	}

	public Time getOra_stampa() {
		return ora_stampa;
	}

	public int getN_ATM() {
		return n_ATM;
	}

	public int getN_filiale() {
		return n_filiale;
	}

	public double getImporto_prelievo() {
		return importo_prelievo;
	}

	public Date getData_stampa() {
		return data_stampa;
	}

	public boolean getCarta_ok() {
		return carta_ok;
	}

	public Transazione getTransazione() {
		return transazione;
	}

	public void setTransazione(Transazione transazione) {
		this.transazione = transazione;
	}

	// setters
	public void setN_serie(int n_serie) {
		this.n_serie = n_serie;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public void setOra_stampa(Time ora_stampa) {
		this.ora_stampa = ora_stampa;
	}

	public void setN_ATM(int n_ATM) {
		this.n_ATM = n_ATM;
	}

	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	public void setImporto_prelievo(double importo_prelievo) {
		this.importo_prelievo = importo_prelievo;
	}

	public void setData_stampa(Date date) {
		this.data_stampa = date;
	}

	public void setCarta_ok(boolean carta_ok) {
		this.carta_ok = carta_ok;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	// funzioni
	public ArrayList<Transazione> storico(int n_carta) {
		ArrayList<Transazione> transactions = new ArrayList<Transazione>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM transazione where n_carta ='" + n_carta + "'");

			while (rs.next()) {
				int n_serie = rs.getInt("n_serie");
				String tipo_transazione = rs.getString("tipo_transazione");
				Time ora_transazione = rs.getTime("ora_transazione");
				int n_ATM = rs.getInt("n_atm");
				int n_filiale = rs.getInt("n_filiale");
				Double importo_prelievo = rs.getDouble("importo_prelievo");
				Date data_transazione = rs.getDate("data_transazione");
				Boolean importo_minimo = rs.getBoolean("importo_minimo");

				Transazione transaction = new Transazione(n_serie, tipo_transazione, ora_transazione, n_ATM, n_filiale,
						importo_prelievo, data_transazione, importo_minimo, n_carta);
				transactions.add(transaction);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return transactions;
	}

	public void leggi_importo() {
		// read transaction amount from user input
	}

	public void controlla_rimanenza_carta() {
		// check remaining balance on connected account
	}

	public void stampa() {
		// print receipt with the specified details
	}

	public void rilascia_ricevuta() {
		// release the receipt
	}

	public void no_stampa() {
		// Skip the step of printing receipt
	}
}
