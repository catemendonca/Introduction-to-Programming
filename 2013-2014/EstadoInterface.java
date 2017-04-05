
/**
 * Interface para uma classe que representa um estado de uma dinamica.
 * <p>
 * Um estado eh caracterizado pelas suas coordenadas em R^n.
 */
public interface EstadoInterface { 

  /**
   * Devolve a dimensao deste Estado.
   *
   * @return A dimensao do Estado
   */
  int getDimensao() ;

  /**
   * Devolve o valor da i-esima coordenada deste Estado.
   * <p>
   * Requer 1 <= i <= getDimensao()
   *
   * @param i A i-esima coordenada do Estado, sendo a primeira coordenada i=1
   * @return O valor da i-esima coordenada do Estado
   */
  double getCoordenada(int i) ;

  /**
   * Atribui um valor a i-esima coordenada deste Estado.
   * <p>
   * Requer 1 <= i <= getDimensao()
   *
   * @param i A i-esima coordenada do Estado, sendo a primeira coordenada i=1
   * @param valor O valor a atribuir a i-esima coordenada do Estado
   */
  void setCoordenada(int i, double valor) ;

  /**
   * Copia este Estado.
   * <p>
   * Este metodo comporta-se como um clone().
   *
   * @return Um novo Estado igual a este Estado
   */
  Estado copiaEstado() ;

  /**
   * Calcula a distância Euclideana entre este Estado e outro Estado.
   * <p>
   * Requer this.getDimensao() == outroEstado.getDimensao()
   *
   * @param outroEstado Estado em relacao ao qual eh medida a distancia
   * @return A distancia entre este Estado e o outro Estado
   */
  double distancia(Estado outroEstado) ;

  /**
   * Modificacao do equals() habitual, para "igual a menos de um delta".
   * <p>
   * Compara aproximadamente este Estado com outro Estado.
   * <br>
   * delta mede o afastamento maximo tolerado.
   * <br>
   * O valor de delta tem de ser positivo, e deve ser "pequeno".
   * <br>
   * Requer this.getDimensao() == outroEstado.getDimensao().
   *
   * @param outroEstado Estado que eh comparado com este Estado
   * @param delta Medida do afastamento maximo tolerado entre estados "iguais"
   * @return true se os estados forem "iguais", false caso contrario
   */
  boolean equals(Estado outroEstado, double delta) ;

  /**
   * Obtem uma representacao textual deste Estado.
   * <p>
   * A representacao deve ser adequada para usar com System.out.println().
   *
   * @return Uma representacao textual deste Estado
   */
  String toString() ;

}

