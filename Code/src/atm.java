
public class atm {
	private int n_serie;
	private int n_filiale;
	private boolean carta_ok;

	public atm() {

	}

	public int getN_serie() {
		return n_serie;
	}

	public void setN_serie(int n_serie) {
		this.n_serie = n_serie;
	}

	public int getN_filiale() {
		return n_filiale;
	}

	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	public boolean isCarta_ok() {
		return carta_ok;
	}

	public void setCarta_ok(boolean carta_ok) {
		this.carta_ok = carta_ok;
	}

	public void richiedi_carta() {
		// prompt user to insert card
	}

	public void richiedi_PIN() {
		// prompt user to enter PIN
	}

	public void verifica_carta() {
		// check if card is valid
	}

	public void segnala_transazione() {
		// log transaction details
	}

	public void verifica_limite_conto() {
		// check if account has sufficient funds
	}

	public void eroga_transazione() {
		// perform transaction (e.g. withdraw cash)
	}

	public void controlla_rimanenza_carta() {
		// check remaining balance on card
	}

	public void eroga_ricevuta() {
		// print receipt
	}

	public void richiesta_rilascio_carta() {
		// prompt user to remove card
	}

	public void rilascia_carta() {
		// release card
	}

	public void visualizza_transazioni() {
		// display transaction history
	}

	public void connetti_conto() {
		// connect to account (e.g. fetch account details from database)
	}
}
