import java.time.LocalDate;

public class Tessera_Magnetica {
	private int n_carta;
	private LocalDate data_emissione;
	private LocalDate data_scadenza;
	private int n_conto;
	private int PIN;

	public Tessera_Magnetica() {
		data_emissione = LocalDate.now(); // giorno dell'emissione
		data_scadenza = data_emissione.plusYears(10); // scade 10 anni dopo l'emissione
	}

	public int getN_carta() {
		return n_carta;
	}

	public LocalDate getData_emissione() {
		return data_emissione;
	}

	public LocalDate getData_scadenza() {
		return data_scadenza;
	}

	public int getN_conto() {
		return n_conto;
	}

	public int getPIN() {
		return PIN;
	}

	public void setN_carta(int n_carta) {
		this.n_carta = n_carta;
	}

	public void setData_emissione(LocalDate data_emissione) {
		this.data_emissione = data_emissione;
	}

	public void setData_scadenza(LocalDate data_scadenza) {
		this.data_scadenza = data_scadenza;
	}

	public void setN_conto(int n_conto) {
		this.n_conto = n_conto;
	}

	public void setPIN(int PIN) {
		this.PIN = PIN;
	}

	public void invia_n_carta() {
		// da implementare
	}
}