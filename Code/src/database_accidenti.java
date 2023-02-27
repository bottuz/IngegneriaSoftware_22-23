import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * La classe database_accidenti gestisce le carte accidentate (es. furto...).
 * 
 * @author botta
 * @version 1.4.3
 */
public class database_accidenti {
	private int n_carta;
	private String tipo_accidente;

//////////////////////////////////////////////////////////////////////////////
//								COSTRUTTORI									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Costruisce l'oggetto Database_accidenti a partire da un Database accidentato
	 * esistente sul DB.
	 * 
	 * @param tesserasospetta
	 */
	public database_accidenti(int tesserasospetta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM database_accidenti WHERE n_carta='" + tesserasospetta + "'");
			if (rs.next()) {
				setN_carta(rs.getInt("n_carta"));
				setTipo_accidente(rs.getString("tipo_accidente"));
			}
			con.close();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Costruisce un oggetto Database_accidenti e lo aggiunge al DB.
	 * 
	 * @param n_carta
	 * @param tipo_accidente
	 */
	public database_accidenti(String n_carta, String tipo_accidente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			String query = "INSERT INTO database_accidenti (n_carta, tipo_accidente) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(n_carta));
			pstmt.setString(2, tipo_accidente);
			@SuppressWarnings("unused")
			int rowsInserted = pstmt.executeUpdate();
			con.close();
			pstmt.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

//////////////////////////////////////////////////////////////////////////////
//									GET/SET									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Il metodo ritorna il numero di carta di una carta accidentata.
	 * 
	 * @return numero di carta
	 */
	public int getN_carta() {
		return n_carta;
	}

	/**
	 * Il metodo ritorna il tipo di accidente di una carta accidentata.
	 * 
	 * @return tipo di accidente
	 */
	public String getTipo_accidente() {
		return tipo_accidente;
	}

	/**
	 * Il metodo imposta il numero di carta dell' oggetto.
	 * 
	 * @param n_carta
	 */
	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	/**
	 * Il metodo imposta il tipo di accidente dell' oggetto.
	 * 
	 * @param tipo_accidente
	 */
	public void setTipo_accidente(String tipo_accidente) {
		this.tipo_accidente = tipo_accidente;
	}

//////////////////////////////////////////////////////////////////////////////
//								FUNZIONI									//
//////////////////////////////////////////////////////////////////////////////

	/**
	 * Il metodo ritorna true se la carta è accidentata.
	 * 
	 * @param n_carta
	 * @return true o false
	 */
	public boolean cartaaccidentata(int n_carta) {
		database_accidenti db = new database_accidenti(n_carta);
		if (db.tipo_accidente != null) {
			return true; // ritorna true se accidentata
		} else {
			return false; // ritorna false se tessera valida
		}
	}
}
