
public class database_accidenti {
	private int n_tessera;
	private String tipo_accidente;
	private boolean tessera_accidentata;

	public database_accidenti(int n_tessera, String tipo_accidente, boolean tessera_accidentata) {
		this.n_tessera = n_tessera;
		this.tipo_accidente = tipo_accidente;
		this.tessera_accidentata = tessera_accidentata;
	}

	// default constructor
	public database_accidenti() {
		this(0, null, false);
	}

	// getters
	public int getN_tessera() {
		return n_tessera;
	}

	public String getTipo_accidente() {
		return tipo_accidente;
	}

	public boolean isTessera_accidentata() {
		return tessera_accidentata;
	}

	// setters
	public void setN_tessera(int n_tessera) {
		this.n_tessera = n_tessera;
	}

	public void setTipo_accidente(String tipo_accidente) {
		this.tipo_accidente = tipo_accidente;
	}

	public void setTessera_accidentata(boolean tessera_accidentata) {
		this.tessera_accidentata = tessera_accidentata;
	}
}
