
// 4 MÃ‰TODOS POR CONCRETIZAR
// sugestÃ£o: ver primeiro Henon.java

public class Logistica extends SistemaDinamico implements LogisticaInterface {
	private double b;

	public Logistica(double b) {
		super(1);
		this.b = b;
	}

	public double getB() {
		return this.b;
	}

	public void setB(double valor) {
		this.b = valor;
	}

	// overriding
	public Estado novoEstado(Estado estadoActual) {
		Estado novoEstado = estadoActual.copiaEstado();
		double coordenadaEstado = novoEstado.getCoordenada(1);
		novoEstado.setCoordenada(1, this.getB() * coordenadaEstado
				* (1 - coordenadaEstado));
		return novoEstado;
	}

	// overriding
	public void iteraEstado(Estado estadoAlteravel) {
		estadoAlteravel = this.novoEstado(estadoAlteravel);
	}

	// overriding
	public String toString() {
		return "[logistica, b=" + b + "]";
	}

}
