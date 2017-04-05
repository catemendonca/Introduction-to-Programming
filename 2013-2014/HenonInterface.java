
/**
 * Interface para uma classe que representa a aplicacao de Henon.
 * <p>
 * A aplicacao de Henon eh caracterizada por dois parametros, a e b.
 * <br>
 * A classe devera ainda substituir ("override") metodos de SistemaDinamico.
 */
public interface HenonInterface {

  /**
   * Devolve o valor do parametro a desta Henon.
   *
   * @return O parametro a da Henon
   */
  double getA() ;

  /**
   * Devolve o valor do parametro b desta Henon.
   *
   * @return O parametro b da Henon
   */
  double getB() ;

  /**
   * Atribui um  valor ao parametro a desta Henon.
   *
   * @param valor O valor a atribuir ao parametro a da Henon
   */
  void setA(double valor) ;

  /**
   * Atribui um  valor ao parametro b desta Henon.
   *
   * @param valor O valor a atribuir ao parametro b da Henon
   */
  void setB(double valor) ;

}

