// 4 MÃ‰TODOS POR CONCRETIZAR

public class Henon extends SistemaDinamico implements HenonInterface {
	private double a, b;

	public Henon(double a, double b) {
		super(2);
		this.a = a;
		this.b = b;
	}

	public double getA() {
		return this.a;
	}

	public double getB() {
		return this.b;
	}

	public void setA(double valor) {
		this.a = valor;
	}

	public void setB(double valor) {
		this.b = valor;
	}

	// overriding
	public Estado novoEstado(Estado estadoActual) {
		Estado novo = new Estado(2);

		double x = estadoActual.getCoordenada(1);
		double y = estadoActual.getCoordenada(2);
		novo.setCoordenada(1, 1 - a * x * x + y);
		novo.setCoordenada(2, b * x);

		return novo;
	}

	// overriding
	public void iteraEstado(Estado estadoAlteravel) {
		double x = estadoAlteravel.getCoordenada(1);
		double y = estadoAlteravel.getCoordenada(2);
		estadoAlteravel.setCoordenada(1, 1 - a * x * x + y);
		estadoAlteravel.setCoordenada(2, b * x);
	}

	// overriding
	public String toString() {
		return "[Henon, a=" + a + ", b=" + b + "]";
	}

}
