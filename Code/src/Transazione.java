import java.sql.Time;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transazione {
	private int n_serie;
	private String tipo_transazione;
	private Time ora_transazione;
	private int n_ATM;
	private int n_filiale;
	private Double importo_prelievo;
	private Date data_transazione;
	private boolean importo_minimo;
	private int n_carta;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	// costruttore esistente sul db
	public Transazione(int n_serie) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM transazione WHERE n_serie='" + n_serie + "'");
			if (rs.next()) {
				n_serie = rs.getInt("n_serie");
				tipo_transazione = rs.getString("tipo_transazione");
				ora_transazione = rs.getTime("ora_transazione");
				n_ATM = rs.getInt("n_atm");
				n_filiale = rs.getInt("n_filiale");
				importo_prelievo = rs.getDouble("importo_prelievo");
				data_transazione = rs.getDate("data_transazione");
				importo_minimo = rs.getBoolean("importo_minimo");
				n_carta = rs.getInt("n_carta");
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// costruttore nuova TRANSAZIONE
	public Transazione(String tipo_transazione, int n_atm, int n_filiale, Double importo_prelievo,
			Boolean importo_minimo, int n_carta) {
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			n_serie = rand.nextInt(999999);
		}
		this.tipo_transazione = tipo_transazione;
		ora_transazione = new Time(System.currentTimeMillis());
		this.n_ATM = n_atm;
		this.n_filiale = n_filiale;
		this.importo_prelievo = importo_prelievo;
		data_transazione = new Date();
		this.importo_minimo = importo_minimo;
		this.n_carta = n_carta;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("INSERT INTO atm VALUES('" + n_serie + "','" + tipo_transazione + "','"
					+ ora_transazione + "','" + n_atm + "','" + n_filiale + "','" + importo_prelievo + "','"
					+ data_transazione + "','" + importo_minimo + "','" + n_carta + "')");
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "TRANSAZIONE CREATA CON SUCCESSO!");
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// mi serve questo costruttore per lo storico di ricevuta
	public Transazione(int n_serie, String tipo_transazione, Time ora_transazione, int n_ATM, int n_filiale,
			Double importo_prelievo, Date data_transazione, Boolean importo_minimo, int n_carta) {
		this.n_serie = n_serie;
		this.tipo_transazione = tipo_transazione;
		this.ora_transazione = ora_transazione;
		this.n_ATM = n_ATM;
		this.n_filiale = n_filiale;
		this.importo_prelievo = importo_prelievo;
		this.data_transazione = data_transazione;
		this.importo_minimo = importo_minimo;
		this.n_carta = n_carta;
	}

//////////////////////////////////////////////////////////////////////////////
//									GET/SET									//
//////////////////////////////////////////////////////////////////////////////

	public int getN_serie() {
		return n_serie;
	}

	public void setN_serie(int n_serie) {
		this.n_serie = n_serie;
	}

	public String getTipo_transazione() {
		return tipo_transazione;
	}

	public void setTipo_transazione(String tipo_transazione) {
		this.tipo_transazione = tipo_transazione;
	}

	public Time getOra_transazione() {
		return ora_transazione;
	}

	public void setOra_transazione(Time ora_transazione) {
		this.ora_transazione = ora_transazione;
	}

	public int getN_ATM() {
		return n_ATM;
	}

	public void setN_ATM(int n_ATM) {
		this.n_ATM = n_ATM;
	}

	public int getN_filiale() {
		return n_filiale;
	}

	public int getN_carta() {
		return n_carta;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	public Double getImporto_prelievo() {
		return importo_prelievo;
	}

	public void setImporto_prelievo(Double importo_prelievo) {
		this.importo_prelievo = importo_prelievo;
	}

	public Date getData_transazione() {
		return data_transazione;
	}

	public void setData_transazione(Date data_transazione) {
		this.data_transazione = data_transazione;
	}

	public boolean getImporto_minimo() {
		return importo_minimo;
	}

	public void setImporto_minimo(boolean importo_minimo) {
		this.importo_minimo = importo_minimo;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	public void transazione_segnalata() {
		// da implementare
	}

}
