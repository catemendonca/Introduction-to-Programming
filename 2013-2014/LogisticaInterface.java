
/**
 * Interface para uma classe que representa a aplicacao Logistica.
 * <p>
 * A Logistica eh caracterizada pelo seu unico parametro.
 * <br>
 * A classe devera ainda substituir ("override") metodos de SistemaDinamico.
 */
public interface LogisticaInterface {

  /**
   * Devolve o valor do parametro desta Logistica.
   *
   * @return O parametro da Logistica
   */
  double getB() ;

  /**
   * Atribui um  valor ao parametro desta Logistica.
   *
   * @param valor O valor a atribuir ao parametro da Logistica
   */
  void setB(double valor) ;

}

