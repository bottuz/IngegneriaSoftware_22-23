import java.util.Date;

public class Utente {
	private String username;
	private String password;
	private int n_conto;
	private int n_carta;
	private float balance;
	private String nome;
	private String cognome;
	private Date annoNascita;
	private String residenza;
	private Tessera_Magnetica tessera;
	private Conto_Corrente cc;

	public Utente(String username, String password) {
		this.username = username;
		this.password = password;
		balance = 0;
		tessera = new Tessera_Magnetica();
		cc = new Conto_Corrente();
	}

	public Utente(String username, String password, float balance) {
		this.username = username;
		this.password = password;
		this.balance = balance;
		tessera = new Tessera_Magnetica();
		cc = new Conto_Corrente();
	}

	public Utente(String username, String password, String nome, String cognome, Date annoNascita, String residenza) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.annoNascita = annoNascita;
		this.residenza = residenza;
		this.balance = 0;
		tessera = new Tessera_Magnetica();
		cc = new Conto_Corrente();
	}

	public String get_username() {
		return username;
	}

	public String get_password() {
		return password;
	}

	public float get_balance() {
		return balance;
	}

	public int getN_conto() {
		return n_conto;
	}

	public int getN_carta() {
		return n_carta;
	}

	public void set_username(String username) {
		this.username = username;
	}

	public void set_password(String password) {
		this.password = password;
	}

	public void set_balance(float balance) {
		this.balance = balance;
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