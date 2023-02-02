import java.sql.Time;
import java.util.Random;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transazione {
	private int n_serie;
	private String tipo_transazione;
	private int n_ATM;
	private int n_filiale;
	private int n_carta;
	private int importo;
	private java.sql.Date data_transazione;
	private Time ora_transazione;

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
				n_ATM = rs.getInt("n_atm");
				n_filiale = rs.getInt("n_filiale");
				n_carta = rs.getInt("n_carta");
				setImporto(rs.getInt("importo"));
				data_transazione = rs.getDate("data_transazione");
				ora_transazione = rs.getTime("ora_transazione");
			}
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// costruttore nuova TRANSAZIONE
	public Transazione(String tipo_transazione, int n_atm, int n_filiale, int n_carta, int importo) {
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			n_serie = rand.nextInt(999999);
		}
		this.tipo_transazione = tipo_transazione;
		this.n_ATM = n_atm;
		this.n_filiale = n_filiale;
		this.setImporto(importo);
		this.n_carta = n_carta;
		data_transazione = new java.sql.Date(System.currentTimeMillis());
		ora_transazione = new Time(System.currentTimeMillis());

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			String query = "INSERT INTO transazione (n_serie, tipo_transazione, n_atm, n_filiale,n_carta, importo, data_transazione,ora_transazione) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, n_serie);
			stmt.setString(2, tipo_transazione);
			stmt.setInt(3, n_atm);
			stmt.setInt(4, n_filiale);
			stmt.setInt(5, n_carta);
			stmt.setInt(6, importo);
			stmt.setDate(7, data_transazione);
			stmt.setTime(8, ora_transazione);
			stmt.executeUpdate();
			// JOptionPane.showMessageDialog(null, "TRANSAZIONE CREATA CON SUCCESSO!");
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	// mi serve questo costruttore per lo storico di ricevuta
	public Transazione(int n_serie, String tipo_transazione, int n_ATM, int n_filiale, int n_carta, int importo,
			java.sql.Date data_transazione, Time ora_transazione) {
		this.n_serie = n_serie;
		this.tipo_transazione = tipo_transazione;
		this.n_ATM = n_ATM;
		this.n_filiale = n_filiale;
		this.n_carta = n_carta;
		this.setImporto(importo);
		this.data_transazione = data_transazione;
		this.ora_transazione = ora_transazione;
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

	public int getImporto() {
		return importo;
	}

	public void setImporto(int importo) {
		this.importo = importo;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	public java.sql.Date getData_transazione() {
		return data_transazione;
	}

	public void setData_transazione(java.sql.Date data_transazione) {
		this.data_transazione = data_transazione;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	public void transazione_segnalata() {
		// da implementare
	}

}
