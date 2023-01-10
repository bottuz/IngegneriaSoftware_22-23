import java.sql.Time;
import java.util.Date;

public class Transazione {
	private int n_serie;
	private String tipo_transazione;
	private Time ora_transazione;
	private int n_ATM;
	private int n_filiale;
	private float importo_prelievo;
	private Date data_transazione;
	private boolean importo_minimo;

	public Transazione() {

	}

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

	public void setN_filiale(int n_filiale) {
		this.n_filiale = n_filiale;
	}

	public float getImporto_prelievo() {
		return importo_prelievo;
	}

	public void setImporto_prelievo(float importo_prelievo) {
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

	public void transazione_segnalata() {
		// da implementare
	}

}
