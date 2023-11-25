package modelo;

public class Casilla {
	private int posFila;
	private int posColumna;
	private boolean mina;
	private int numMinasAlrededor;
	private boolean abierta;
	
	public int getPosFila() {
		return posFila;
	}

	
	public boolean isAbierta() {
		return abierta;
	}


	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}



	public int getNumMinasAlrededor() {
		return numMinasAlrededor;
	}


	public Casilla(int posFila, int posColumna) {
		this.posFila = posFila;
		this.posColumna = posColumna;
	}


	public int getPosColumna() {
		return posColumna;
	}


	public boolean isMina() {
		return mina;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}
	public void incrementarNumeroMinasAlrededor() {
		this.numMinasAlrededor++;
	}
}
