import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

	public void inserisce_carta() {
		// da implementare
	}

	public void inserisce_PIN() {
		// da implementare
	}

	public void sceglie_transazione() {
		// da implementare
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