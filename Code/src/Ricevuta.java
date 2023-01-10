import java.sql.Time;
import java.time.LocalDate;

public class Ricevuta {
	private int n_serie;
	private int n_carta;
	private Time ora_stampa;
	private int n_ATM;
	private int n_filiale;
	private float importo_prelievo;
	private LocalDate data_stampa;
	private boolean carta_ok;
	private Utente connectedAccount;

	public Ricevuta(int n_serie, int n_carta, Time ora_stampa, int n_ATM, int n_filiale, float importo_prelievo,
			LocalDate data_stampa, boolean carta_ok) {
		this.n_serie = n_serie;
		this.n_carta = n_carta;
		this.ora_stampa = ora_stampa;
		this.n_ATM = n_ATM;
		this.n_filiale = n_filiale;
		this.importo_prelievo = importo_prelievo;
		this.data_stampa = data_stampa;
		this.carta_ok = carta_ok;
	}

	// default constructor
	public Ricevuta() {
		this(0, 0, null, 0, 0, 0f, null, false);
	}

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

	public float getImporto_prelievo() {
		return importo_prelievo;
	}

	public LocalDate getData_stampa() {
		return data_stampa;
	}

	public boolean getCarta_ok() {
		return carta_ok;
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

	public void setImporto_prelievo(float importo_prelievo) {
		this.importo_prelievo = importo_prelievo;
	}

	public void setData_stampa(LocalDate data_stampa) {
		this.data_stampa = data_stampa;
	}

	public void setCarta_ok(boolean carta_ok) {
		this.carta_ok = carta_ok;
	}

	// funzioni

	public void leggi_importo() {
		// read transaction amount from user input
	}

	public void controlla_rimanenza_carta() {
		// check remaining balance on connected account
		if (connectedAccount.get_balance() < getImporto_prelievo()) {
			System.out.println("Insufficient funds on your account");
			return;
		}
	}

	public void stampa() {
		// print receipt with the specified details
		System.out.println("Transaction successful! Your receipt: ");
		System.out.println("Transaction Amount: " + importo_prelievo);
		System.out.println("Card Number: " + n_carta);
		System.out.println("Date & Time: " + data_stampa + " " + ora_stampa);
		System.out.println("ATM Location: ATM " + n_ATM + ", Branch " + n_filiale);
	}

	public void rilascia_ricevuta() {
		// release the receipt
		System.out.println("Please take your receipt.");
	}

	public void no_stampa() {
		// Skip the step of printing receipt
		System.out.println(
				"You choose not to print receipt, keep in mind that you still can check your transactions history.");
	}
}
