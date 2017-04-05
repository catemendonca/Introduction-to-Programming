// Catarina Mendonça n43637
// Ines Correia n43638
// Marcia Santos n43600

// 4 MÃ‰TODOS POR CONCRETIZAR

public class Estado implements EstadoInterface {
	private double[] componentes;

	public Estado(double[] componentes) {
		this.componentes = new double[componentes.length];
		System.arraycopy(componentes, 0, this.componentes, 0,
				componentes.length);
	}

	public Estado(int dimensao) {
		componentes = new double[dimensao]; // inicializa componentes a 0.0
	}

	public int getDimensao() {
		return componentes.length;
	}

	public double getCoordenada(int i) {
		return componentes[i - 1];
	}

	public void setCoordenada(int i, double valor) {
		componentes[i - 1] = valor;
	}

	public Estado copiaEstado() {
		Estado novoEstado = new Estado(this.getDimensao());
		for (int i = 1; i <= this.getDimensao(); i++) {
			double coordenadaEstado = this.getCoordenada(i);
			novoEstado.setCoordenada(i, coordenadaEstado);
		}
		return novoEstado;
	}

	public double distancia(Estado outroEstado) {
		double soma = 0.0;
		for (int i = 1; i <= this.getDimensao(); i++) {
			double distancia = this.getCoordenada(i)
					- outroEstado.getCoordenada(i);
			soma += distancia * distancia;
		}
		return Math.sqrt(soma);
	}

	public boolean equals(Estado outroEstado, double delta) {
		boolean resultado = true;

		for (int i = 0; i < componentes.length && resultado; i++)
			if (Math.abs(componentes[i] - outroEstado.componentes[i]) > delta)
				resultado = false;

		return resultado;
	}

	public String toString() {
		StringBuilder representacao = new StringBuilder("(");

		int i;
		for (i = 0; i < componentes.length - 1; i++)
			representacao.append(componentes[i] + ",");
		representacao.append(componentes[i] + ")"); // i == componentes.length -
													// 1

		return representacao.toString();
	}

}
