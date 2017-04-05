import java.io.*;

public class ExecutaFase2 {

	public static void main(String[] args) throws IOException {

		Logistica novaLogistica = new Logistica(3.8);

		Estado estado1 = new Estado(1);
		estado1.setCoordenada(1, 0.40);

		Estado estado2 = new Estado(1);
		estado2.setCoordenada(1, 0.41);

		Estado estado3 = new Estado(1);
		estado3.setCoordenada(1, 0.42);

		Orbita orbita1 = new Orbita(estado1);
		Orbita orbita2 = new Orbita(estado2);
		Orbita orbita3 = new Orbita(estado3);

		orbita1.itera(novaLogistica, 20);
		orbita2.itera(novaLogistica, 20);
		orbita3.itera(novaLogistica, 20);

		orbita1.escreveFich("orbita1");
		orbita2.escreveFich("orbita2");
		orbita3.escreveFich("orbita3");

		BufferedReader ler1 = new BufferedReader(new FileReader("orbita1"));
		BufferedReader ler2 = new BufferedReader(new FileReader("orbita2"));
		BufferedReader ler3 = new BufferedReader(new FileReader("orbita3"));

		double[] tamanho1 = new double[orbita1.getTamanho()];
		double[] tamanho2 = new double[orbita2.getTamanho()];
		double[] tamanho3 = new double[orbita3.getTamanho()];
		String[] s = new String[2];

		for (int i = 0; i < orbita1.getTamanho(); i++) {
			s = ler1.readLine().split(" ");
			tamanho1[i] = Double.parseDouble(s[1]);
			s = ler2.readLine().split(" ");
			tamanho2[i] = Double.parseDouble(s[1]);
			s = ler3.readLine().split(" ");
			tamanho3[i] = Double.parseDouble(s[1]);
		}

		double[][][] vectorParaGrafico = new double[3][20 + 1][2];
		for (int k = 0; k <= 20; k++) {
			vectorParaGrafico[0][k][0] = k;
			vectorParaGrafico[0][k][1] = tamanho1[k];
			vectorParaGrafico[1][k][0] = k;
			vectorParaGrafico[1][k][1] = tamanho2[k];
			vectorParaGrafico[2][k][0] = k;
			vectorParaGrafico[2][k][1] = tamanho3[k];
		}

		Graficos.mostraGraficos(vectorParaGrafico, new String[] { "iteracao",
				"x", "Logistica - 3 condicoes iniciais" });

		Henon hen = new Henon(1.4, 0.3);

		estado1 = new Estado(2);
		estado1.setCoordenada(1, 0.60);
		estado1.setCoordenada(2, 0.20);

		estado2 = new Estado(2);
		estado2.setCoordenada(1, 0.61);
		estado2.setCoordenada(2, 0.20);

		orbita1 = new Orbita(estado1);
		orbita2 = new Orbita(estado2);

		orbita1.itera(hen, 20);
		orbita2.itera(hen, 20);

		orbita1.escreveFich("henon1");
		orbita2.escreveFich("henon2");

		ler1 = new BufferedReader(new FileReader("henon1"));
		ler2 = new BufferedReader(new FileReader("henon2"));

		double[] henon1 = new double[orbita1.getTamanho()];
		double[] henon2 = new double[orbita2.getTamanho()];
		s = new String[3];

		for (int i = 0; i < orbita1.getTamanho(); i++) {
			s = ler1.readLine().split(" ");
			henon1[i] = Double.parseDouble(s[1]);
			s = ler2.readLine().split(" ");
			henon2[i] = Double.parseDouble(s[1]);
		}

		vectorParaGrafico = new double[2][20 + 1][2];
		for (int k = 0; k <= 20; k++) {
			vectorParaGrafico[0][k][0] = k;
			vectorParaGrafico[0][k][1] = henon1[k];
			vectorParaGrafico[1][k][0] = k;
			vectorParaGrafico[1][k][1] = henon2[k];
		}

		Graficos.mostraGraficos(vectorParaGrafico, new String[] { "iteracao",
				"x", "Henon - 2 condicoes iniciais" });

	}
}
