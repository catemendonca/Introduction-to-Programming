/**
 * Uma classe de teste para o trabalho de programacao 2013-14.
 * <p>
 * O metodo main() usa diversos servicos das classes fornecedoras.
 */
public class ExecutaDinamica {

  public static void main(String[] args) {

    final double DELTA = 0.00001; // para as comparações com "zero"

    // medindo a distância entre dois estados
    double distanciaMedida =
      (new Estado(new double[]{1.0,2.0})).distancia(new Estado(new double[]{1.0,7.0}));
    System.out.println("Os dois estado de teste distam " + distanciaMedida);

    // testando a Logística
    Logistica myLogi = new Logistica(3.3);
    System.out.println(myLogi);

    Estado estadoIteravelL = new Estado(new double[]{0.2});
    System.out.println(estadoIteravelL);
    for (int k = 1; k < 20; k++) {
      myLogi.iteraEstado(estadoIteravelL);
      System.out.println(estadoIteravelL);
    }

    Estado estadoReescritoL = new Estado(new double[]{0.2});
    System.out.println(estadoReescritoL);
    for (int k = 1; k < 20; k++) {
      estadoReescritoL = myLogi.novoEstado(estadoReescritoL);
      System.out.println(estadoReescritoL);
    }

    // testando Hénon
    Henon myHenon = new Henon(1.4, 0.3);
    System.out.println("\n" + myHenon);

    Estado estadoIteravelH = new Estado(new double[]{0.6, 0.2});
    System.out.println(estadoIteravelH);
    for (int k = 1; k < 20; k++) {
      myHenon.iteraEstado(estadoIteravelH);
      System.out.println(estadoIteravelH);
    }

    Estado estadoReescritoH = new Estado(new double[]{0.6, 0.2});
    System.out.println(estadoReescritoH);
    for (int k = 1; k < 20; k++) {
      estadoReescritoH = myHenon.novoEstado(estadoReescritoH);
      System.out.println(estadoReescritoH);
    }

    // testando uma órbita com Hénon
    Orbita myHenonOrbit = new Orbita(new Estado(new double[]{0.6, 0.2}));
//    myHenonOrbit.itera(myHenon, 1100);
    myHenonOrbit.itera(myHenon, 19);
    System.out.println("\n" + myHenonOrbit);

    // visualizando uma órbita com Hénon
    myHenonOrbit.projecta2D(1, 2, 0, myHenonOrbit.getTamanho() - 1,
                            new String[]{"x", "y", "Henon - orbita completa"});
    /* testes que correram bem
    // o mesmo, mas com limites temporais exagerados
    myHenonOrbit.projecta2D(1, 2, -27, myHenonOrbit.getTamanho() + 33,
                            new String[]{"x", "y", "Henon - orbita completa"});
    // visualizando uma órbita com Hénon, dando menos informação
    myHenonOrbit.projecta2D(1, 2);
    */
    // visualizando uma série temporal com Hénon
    myHenonOrbit.serieTemporal(1, 0, myHenonOrbit.getTamanho() - 1,
             new String[]{"iteracao", "x", "Henon - serie temporal completa"});
    /* teste que correu bem
    // visualizando uma série temporal com Hénon, dando menos informação
    myHenonOrbit.serieTemporal(1);
    */
    // visualizando uma grande órbita com Hénon
    myHenonOrbit.itera(myHenon, 6000);
    myHenonOrbit.projecta2D(1, 2, 0, myHenonOrbit.getTamanho() - 1,
                            new String[]{"x", "y", "Henon - 6019 iteracoes"});

    // encontrando o período de uma órbita da Logística (neste caso: 2)
    Orbita myLogiOrbit = new Orbita(new Estado(new double[]{0.2}));
    myLogiOrbit.itera(myLogi, 19);
    System.out.println("\n" + myLogiOrbit);
    int p = myLogiOrbit.encontraPeriodo(8, DELTA);
    if (p > 0)
      System.out.println("Esta orbita tem periodo " + p);
    else
      if (p == 0)
        System.out.println("Nao foi encontrado um periodo");
      else
        System.out.println("Nao foi possivel concluir a pesquisa devido a " +
                           "a orbita ser demasido curta");

    // perguntando se o período de uma órbita da Logística é 1 (resp., 2)
    if (myLogiOrbit.ehPontoFixo(DELTA))
      System.out.println("Esta orbita eh um ponto fixo");
    if (myLogiOrbit.ehPeriodoDois(DELTA))
      System.out.println("Esta orbita tem periodo 2");

    // encontrando o ciclo limite de uma órbita da Logística com período 2
    System.out.println("Ciclo limite desta orbita (com periodo 2):");
    System.out.println(myLogiOrbit.encontraCicloLimite(8, DELTA));

  }

}

