
// 5 MÃ‰TODOS POR CONCRETIZAR

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Orbita implements OrbitaInterface {
	private Estado[] registo;
	// private static final int tamanhoInicialRegisto = 1000;
	private static final int tamanhoInicialRegisto = 1; // apenas estado inicial
	private int iteracoes; // iteraÃ§Ãµes jÃ¡ executadas; valor inicial = 0

	public Orbita(Estado estadoInicial) {
		registo = new Estado[tamanhoInicialRegisto];
		registo[0] = estadoInicial.copiaEstado();
	}

	public Orbita(int dimensao) { // inicializa na origem, com dimensÃ£o
									// dimensao
		registo = new Estado[tamanhoInicialRegisto];
		registo[0] = new Estado(dimensao);
		// for (int i = 1; i <= dimensao; i++) // redundante, pois inicializa a
		// 0.0
		// registo[0].setCoordenada(i,0.0);
	}

	private void aumentaTamanhoRegisto(int acrescento) {
		Estado[] novoRegisto = new Estado[registo.length + acrescento];
		for (int k = 0; k <= iteracoes; k++)
			// de iteracoes+1 a registo.length-1
			novoRegisto[k] = registo[k].copiaEstado(); // o registo estÃ¡ a null
		registo = novoRegisto;
	}

	public void itera(SistemaDinamico sd, int iteracoesAdicionais) {
		int iteracoesActualizadas = iteracoes + iteracoesAdicionais;
		if (iteracoesActualizadas > registo.length - 1)
			aumentaTamanhoRegisto(Math.max(iteracoesAdicionais,
					(int) (registo.length * 1.2)));
		for (int k = iteracoes; k < iteracoesActualizadas; k++)
			registo[k + 1] = sd.novoEstado(registo[k]);
		iteracoes = iteracoesActualizadas;
	}

	public void itera(SistemaDinamico sd, int iteracoesAdicionais,
			double valorMaxEstado) {
		int iteracoesActualizadas = iteracoes + iteracoesAdicionais;
		if (iteracoesActualizadas > registo.length - 1)
			aumentaTamanhoRegisto(Math.max(iteracoesAdicionais,
					(int) (registo.length * 1.2)));
		boolean orbitaDivergiu = false;
		int k = iteracoes;
		for (; k < iteracoesActualizadas && !orbitaDivergiu; k++) {
			registo[k + 1] = sd.novoEstado(registo[k]);
			for (int i = 1; i <= getDimensao() && !orbitaDivergiu; i++)
				if (Math.abs(registo[k + 1].getCoordenada(i)) > valorMaxEstado)
					orbitaDivergiu = true;
		}
		// iteracoes = iteracoesActualizadas;
		iteracoes = k; // Ã³rbita pode acabar prematuramente
	}

	public int getDimensao() {
		return registo[0].getDimensao();
	}

	public Estado estadoActual() {
		Estado estadoActual;
		if (iteracoes != 1) {
			estadoActual = registo[iteracoes];
		} else {
			estadoActual = registo[0];
		}
		return estadoActual;
	}

	public int getTamanho() {
		return iteracoes + 1; // a iteraÃ§Ã£o 0 (que Ã© o valor inicial) tambÃ©m
								// conta
	}

	public int encontraPeriodo(int limiteBusca, double delta) {
		int periodo;
		boolean encontrado = false; // encontrou perÃ­odo?
		boolean pesquisaEncurtada = false; // Ã³rbita Ã© demasiado curta p/
											// pesquisa?
		if (limiteBusca >= getTamanho()) { // programaÃ§Ã£o defensiva,
											// aceitÃ¡vel
			limiteBusca = getTamanho() - 1;
			pesquisaEncurtada = true;
		}

		for (periodo = 1; periodo <= limiteBusca && !encontrado; periodo++)
			if (registo[iteracoes].equals(registo[iteracoes - periodo], delta))
				encontrado = true;
		periodo = periodo - 1; // para compensar o Ãºltimo incremento no for

		if (!encontrado)
			if (pesquisaEncurtada) // nÃ£o conseguiu testar todos os perÃ­odos
				periodo = -1;
			else
				// conseguiu testar todos os perÃ­odos, mas nÃ£o encontrou
				// periodicidade
				periodo = 0;

		return periodo;
	}

	public boolean ehPontoFixo(double delta) {
		int tamanho1 = getTamanho();
		boolean pontoFixo;
		if (encontraPeriodo(tamanho1, delta) == 1 && getTamanho() >= 2) {
			pontoFixo = true;
		} else {
			pontoFixo = false;
		}
		return pontoFixo;
	}

	public boolean ehPeriodoDois(double delta) {
		int tamanho2 = getTamanho();
		boolean periodoDois;
		if (encontraPeriodo(tamanho2, delta) == 2 && getTamanho() >= 3) {
			periodoDois = true;
		} else {
			periodoDois = false;
		}
		return periodoDois;
	}

	public Orbita encontraCicloLimite(int limiteBusca, double delta) {
		Orbita j = null;
		int periodo = this.encontraPeriodo(limiteBusca, delta);
		if (periodo >= 1) {
			int n = this.getTamanho();
			j = new Orbita(registo[n - periodo]);
			j.aumentaTamanhoRegisto(periodo - 1);
			int g = 1;
			for (int i = n - periodo + 1; i <= n - 1; i++) {
				j.registo[g] = this.registo[i].copiaEstado();
				g++;
			}
			j.iteracoes = periodo - 1;
		}

		return j;
	}

	/**
	 * Um equals() simplificado, entre orbitas; eh "a menos de delta".
	 * <p>
	 * NAO PEDIDO NO INTERFACE OrbitaInterface. <br>
	 * O criterio para detectar estados coincidentes eh
	 * "igual a menos de delta". <br>
	 * O valor de delta tem de ser positivo, e deve ser "pequeno". <br>
	 * Requer getDimensao() == outraOrbita.getDimensao(). <br>
	 * NÃ£o requer getTamanho() == outraOrbita.getTamanho().
	 * 
	 * @param outraOrbita
	 *            a Orbita que eh comparada com esta Orbita
	 * @param delta
	 *            Medida do afastamento maximo tolerado entre estados "iguais"
	 * @return true caso esta Orbita seja "igual" a outraOrbita, false c.c.
	 */
	public boolean equals(Orbita outraOrbita, double delta) {
		boolean resultado = true;
		int tamanho = Math.min(iteracoes, outraOrbita.iteracoes);

		for (int k = 0; k <= tamanho && resultado; k++)
			if (!registo[k].equals(outraOrbita.registo[k], delta))
				resultado = false;

		return resultado;
	}

	public String toString() {
		StringBuilder representacao = new StringBuilder("Orbita:\n"
				+ registo[0] + "\n");

		for (int k = 1; k <= iteracoes; k++)
			representacao.append(registo[k] + "\n");

		return representacao.toString();
	}

	/**
	 * Mostra (projecta) uma parte desta Orbita num grafico a 2D.
	 * <p>
	 * NAO PEDIDO NO INTERFACE OrbitaInterface. <br>
	 * Tem de se seleccionar duas das coordenadas dos estados, para o grafico. <br>
	 * Se getDimensao() == 1, entao coordX e coordY tem de ser a mesma (a
	 * unica).
	 * 
	 * @param coordX
	 *            A coordenada a usar nas abcissas de pontos do grafico
	 * @param coordY
	 *            A coordenada a usar nas ordenadas de pontos do grafico
	 * @param inicio
	 *            A primeira iteracao a fazer parte do grafico
	 * @param fim
	 *            A ultima iteracao a fazer parte do grafico
	 * @param legendas
	 *            Textos para legendar o grafico, segundo as convencoes: <br>
	 *            ver {@see Graficos#mostraGrafico(double[][] dados, String[]
	 *            legendas)}
	 */
	public void projecta2D(int coordX, int coordY, int inicio, int fim,
			String[] legendas) {
		// programacao defensiva, justificÃ¡vel
		if (inicio < 0)
			inicio = 0;
		if (fim > iteracoes)
			fim = iteracoes;

		double[][] vectorParaGrafico = new double[fim - inicio + 1][2];
		for (int k = inicio; k <= fim; k++) {
			vectorParaGrafico[k][0] = registo[k].getCoordenada(coordX);
			vectorParaGrafico[k][1] = registo[k].getCoordenada(coordY);
		}

		Graficos.mostraGrafico(vectorParaGrafico, legendas);
	}

	/**
	 * Mostra (projecta) esta Orbita completa num grafico a 2D.
	 * <p>
	 * NAO PEDIDO NO INTERFACE OrbitaInterface. <br>
	 * Tem de se seleccionar duas das coordenadas dos estados, para o grafico. <br>
	 * Se getDimensao() == 1, entao coordX e coordY tem de ser a mesma (a
	 * unica). <br>
	 * Nesta versao, a legendagem do grafico eh automatica.
	 * 
	 * @param coordX
	 *            A coordenada a usar nas abcissas de pontos do grafico
	 * @param coordY
	 *            A coordenada a usar nas ordenadas de pontos do grafico
	 */
	public void projecta2D(int coordX, int coordY) {
		double[][] vectorParaGrafico = new double[iteracoes + 1][2];
		for (int k = 0; k <= iteracoes; k++) {
			vectorParaGrafico[k][0] = registo[k].getCoordenada(coordX);
			vectorParaGrafico[k][1] = registo[k].getCoordenada(coordY);
		}

		Graficos.mostraGrafico(vectorParaGrafico, new String[] {
				"coordenada 1", "coordenada 2", "orbita" });
	}

	/**
	 * Mostra a serie temporal de uma coordenada de uma parte desta Orbita.
	 * <p>
	 * NAO PEDIDO NO INTERFACE OrbitaInterface. <br>
	 * Tem de se seleccionar uma das coordenadas dos estados, para o grafico.
	 * 
	 * @param coordenada
	 *            A coordenada a usar na serie temporal
	 * @param inicio
	 *            A primeira iteracao a fazer parte do grafico
	 * @param fim
	 *            A ultima iteracao a fazer parte do grafico
	 * @param legendas
	 *            Textos para legendar o grafico, segundo as convencoes: <br>
	 *            ver {@see Graficos#mostraGrafico(double[][] dados, String[]
	 *            legendas)}
	 */
	public void serieTemporal(int coordenada, int inicio, int fim,
			String[] legendas) {
		// programacao defensiva, justificÃ¡vel
		if (inicio < 0)
			inicio = 0;
		if (fim > iteracoes)
			fim = iteracoes;

		double[][] vectorParaGrafico = new double[fim - inicio + 1][2];
		for (int k = inicio; k <= fim; k++) {
			vectorParaGrafico[k][0] = k;
			vectorParaGrafico[k][1] = registo[k].getCoordenada(coordenada);
		}

		Graficos.mostraGrafico(vectorParaGrafico, legendas);
	}

	/**
	 * Mostra a serie temporal de uma coordenada desta Orbita completa.
	 * <p>
	 * NAO PEDIDO NO INTERFACE OrbitaInterface. <br>
	 * Tem de se seleccionar uma das coordenadas dos estados, para o grafico. <br>
	 * Nesta versao, a legendagem do grafico eh automatica.
	 * 
	 * @param coordenada
	 *            A coordenada a usar na serie temporal
	 */
	public void serieTemporal(int coordenada) {
		double[][] vectorParaGrafico = new double[iteracoes + 1][2];
		for (int k = 0; k <= iteracoes; k++) {
			vectorParaGrafico[k][0] = k;
			vectorParaGrafico[k][1] = registo[k].getCoordenada(coordenada);
		}

		Graficos.mostraGrafico(vectorParaGrafico, new String[] { "iteracao",
				"coordenada", "serie temporal" });
	}

	public void escreveFich(String nomeFicheiro) throws IOException {
		BufferedWriter f_out = new BufferedWriter(new FileWriter(nomeFicheiro));
		for (int i = 0; i <= iteracoes; i++) {
			f_out.write(Integer.toString(i)); // escreve o resultado desejado no
												// ficheiro
			for (int j = 1; j <= registo[i].getDimensao(); j++)
				f_out.write(" " + registo[i].getCoordenada(j));
			f_out.newLine();
		}

		f_out.close();
	}

	public void escreveFich(int inicio, int fim, String nomeFicheiro)
			throws IOException {
		BufferedWriter f_out = new BufferedWriter(new FileWriter(nomeFicheiro));
		for (int i = inicio; i <= fim; i++) {
			f_out.write(Integer.toString(i)); // escreve o resultado desejado no
												// ficheiro
			for (int j = 1; j <= registo[i].getDimensao(); j++)
				f_out.write(" " + registo[i].getCoordenada(j));
			f_out.newLine();
		}

		f_out.close();
	}

}
