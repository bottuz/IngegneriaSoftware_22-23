
public class Conto_Corrente {
	private int n_conto;
	private Boolean importo_minimo;

	public Conto_Corrente() {
		n_conto = 0;
		importo_minimo = false;
	}

	public int getN_conto() {
		return n_conto;
	}

	public Boolean getImporto_minimo() {
		return importo_minimo;
	}

	public void setN_conto(int n_conto) {
		this.n_conto = n_conto;
	}

	public void setImporto_minimo(Boolean importo_minimo) {
		this.importo_minimo = importo_minimo;
	}

	public void controlla_conto() {
		// da implementare
	}

	public void approva_limite_conto() {
		// da implementare
	}

	public void rilascia_carta() {
		// da implementare
	}

	public void scrivi_importo_transazione() {
		// da implementare
	}
}