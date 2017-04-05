
/**
 * Classe molde ("template") para classes que representam sistemas dinamicos.
 * <p>
 * Devera ser estendida para sistemas dinamicos especificos.
 * <br>
 * Contem metodos que deverao ser substituidos ("overriden") nas subclasses.
 */
public class SistemaDinamico {

  /**
   * A dimensao deste SistemaDinamico.
   * <p>
   * Eh igual a dimensao dos estados sobre os quais a dinamica itera.
   */
  private final int dimensao;

  /**
   * Constroi um SistemaDinamico com dimensao dimensao.
   * <br>
   * Requer dimensao >= 1.
   *
   * @param dimensao A dimensao do SistemaDinamico
   */
  public SistemaDinamico(int dimensao) {
    this.dimensao = dimensao;    
  }

  /**
   * Devolve a dimensao deste SistemaDinamico.
   *
   * @return A dimensao do SistemaDinamico
   */
  public int getDimensao() {
    return dimensao;
  }

  /*
  //  Esta versão é substituída por outra mais simples, que usa copiaEstado().
  //  No entanto, esta é mais fácil de alterar para dinâmicas concretas.
  public Estado novoEstado(Estado estadoActual) { 
    Estado novo = new Estado(estadoActual.getDimensao());

    for (int i = 1; i <= estadoActual.getDimensao(); i++)
      novo.setCoordenada(i,estadoActual.getCoordenada(i));

    return novo;
  }
  */

  /**
   * Obtem o proximo Estado, a partir de estadoActual, por accao da dinamica.
   * <p>
   * Corresponde portanto a uma iteracao da dinamica.
   * <br>
   * Requer estadoActual.getDimensao() == getDimensao().
   * <br>
   * Por omissao, cria um estado igual a estadoActual.
   * <br>
   * ESTE METODO TEM DE SER REVOGADO (OVERRIDEN) EM SUBCLASSES.
   *
   * @param estadoActual O estado a iterar
   */
  public Estado novoEstado(Estado estadoActual) {
      return estadoActual.copiaEstado();
  }

  /**
   * Modifica estadoAlteravel, por accao da dinamica.
   * <p>
   * Corresponde portanto a uma iteracao da dinamica, mas para estados mutaveis.
   * <br>
   * Requer estadoAlteravel.getDimensao() == getDimensao().
   * <br>
   * Por omissao, aplica a transformacao identidade a estadoAlteravel.
   * <br>
   * ESTE METODO TEM DE SER REVOGADO (OVERRIDEN) EM SUBCLASSES.
   *
   * @param estadoAlteravel O estado a alterar
   */
  public void iteraEstado(Estado estadoAlteravel) { }

  /**
   * Obtem uma representacao textual deste SistemaDinamico.
   * <p>
   * A representacao deve ser adequada para usar com System.out.println().
   * <br>
   * ESTE METODO DEVE SER REVOGADO (OVERRIDEN) EM SUBCLASSES.
   *
   * @return Uma representacao textual deste SistemaDinamico
   */
  public String toString() {
    return "[sistema dinamico com dimensao " + dimensao + "]";
  }

}

