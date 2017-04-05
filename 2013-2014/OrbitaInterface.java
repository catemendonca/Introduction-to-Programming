
/**
 * Interface para uma classe que representa uma orbita de um sistema dinamico.
 * <p>
 * A orbita eh gerada a partir de um estado inicial.
 * <br>
 * A orbita eh aumentada por iteracao de uma dinamica a escolher.
 */
public interface OrbitaInterface {

  /**
   * Itera esta Orbita.
   * <p>
   * A Orbita mantem os Estados iterados ate ao momento, e acrescenta novos.
   * <br>
   * O tamanho da Orbita eh aumentado na quantidade iteracoesAdicionais.
   * <br>
   * Requer sd.getDimensao() == getDimensao().
   * <br>
   * Requer iteracoesAdicionais >= 0.
   *
   * @param sd O sistema dinamico usado para iterar esta Orbita
   * @param iteracoesAdicionais O numero de iteracoes a efectuar
   */
  void itera(SistemaDinamico sd, int iteracoesAdicionais) ;

  /**
   * Ver tambem: {@see #itera(SistemaDinamico sd, int iteracoesAdicionais)}
   * <p>
   * Esta versao: indica o valor absoluto maximo aceitavel para coordenadas.
   * <br>
   * Exemplo: nenhuma coordenada de um Estado pode ter valor superior a 1.0e5.
   * <br>
   * O objectivo eh detectar situacoes de "fuga para o infinito".
   * <br>
   * Caso seja ultrapassado valorMaxEstado, as iteracoes sao interrompidas.
   * <br>
   * Requer valorMaxEstado > 0.0.
   *
   * @param valorMaxEstado O valor absoluto max. tolerado p/ qualquer coordenada
   */
  void itera(SistemaDinamico sd, int iteracoesAdicionais,
                    double valorMaxEstado) ;

  /**
   * Devolve a dimensao desta Orbita.
   * <p>
   * A dimensao da Orbita eh igual a dimensao de cada um dos seus estados.
   *
   * @return A dimensao da Orbita
   */
  int getDimensao() ;

  /**
   * Devolve o Estado actual desta Orbita.
   * <p>
   * O Estado actual eh o que resultou da mais recente iteracao da dinamica.
   * <br>
   * Caso nao tenha ocorrido nenhuma iteracao, eh devolvido o Estado inicial.
   *
   * @return O Estado actual
   */
  Estado estadoActual() ;

  /**
   * Devolve o tamanho desta Orbita.
   * <p>
   * O tamanho eh igual a (nr. de iteracoes + 1), o que inclui o Estado inicial.
   * <br>
   * Caso nao tenha ocorrido nenhuma iteracao, eh devolvido 1.
   *
   * @return O tamanho da Orbita
   */
  int getTamanho() ;

  /**
   * Devolve o periodo desta Orbita, caso esta seja identificada como periodica.
   * <p>
   * O criterio para detectar o retorno a um Estado eh "igual a menos de delta".
   * <br>
   * O valor de delta tem de ser positivo, e deve ser "pequeno".
   * <br>
   * Eh indicado um valor limite para o periodo, na pesquisa.
   * <br>
   * Devolve 0 caso nao encontre periodicidade ate ao valor limite.
   * <br>
   * Devolve -1 caso nao encontre periodicidade, sem ter concluido a pesquisa.
   * <br>
   * -1 so ocorre se a Orbita for demasido curta para concluir a pesquisa.
   * <br>
   * O metodo requer limiteBusca >= 1.
   *
   * @param limiteBusca Valor limite para a pesquisa de periodo
   * @param delta Medida do afastamento maximo tolerado entre estados "iguais"
   * @return O periodo da Orbita, caso seja encontrado; 0 ou -1, caso nao o seja
   */
  int encontraPeriodo(int limiteBusca, double delta) ;

  /**
   * Averigua se uma Orbita eh ponto fixo, i.e., se tem periodo 1.
   * <p>
   * O criterio para detectar o retorno a um Estado eh "igual a menos de delta".
   * <br>
   * O valor de delta tem de ser positivo, e deve ser "pequeno".
   * <br>
   * Requer getTamanho() >= 2.
   *
   * @param delta Medida do afastamento maximo tolerado entre estados "iguais"
   * @return true caso a Orbita tenha periodo 1, false caso contrario
   */
  boolean ehPontoFixo(double delta) ;

  /**
   * Averigua se uma Orbita tem periodo 2.
   * <p>
   * O criterio para detectar o retorno a um Estado eh "igual a menos de delta".
   * <br>
   * O valor de delta tem de ser positivo, e deve ser "pequeno".
   * <br>
   * Requer getTamanho() >= 3.
   *
   * @param delta Medida do afastamento maximo tolerado entre estados "iguais"
   * @return true caso a Orbita tenha periodo 2, false caso contrario
   */
  boolean ehPeriodoDois(double delta) ;

  /**
   * Encontra o ciclo limite de uma Orbita.
   * <p>
   * O criterio para detectar o retorno a um Estado eh "igual a menos de delta".
   * <br>
   * O valor de delta tem de ser positivo, e deve ser "pequeno".
   * <br>
   * Eh indicado um valor limite para o periodo, na pesquisa.
   * <br>
   * Devolve null caso nao encontre periodicidade ate ao valor limite.
   * <br>
   * Devolve null caso nao encontre periodicidade, sem ter concluido a pesquisa.
   * <br>
   * O metodo requer limiteBusca >= 1.
   * <br>
   * O metodo requer encontraPeriodo(limiteBusca, delta) >= 1.
   * <br>
   * Se as pre-condicoes forem respeitadas, nunca eh devolvido null.
   *
   * @param limiteBusca Valor limite para a pesquisa de periodo
   * @param delta Medida do afastamento maximo tolerado entre estados "iguais"
   * @return O ciclo limite da Orbita, caso seja encontrado; null, caso contr.
   */
  Orbita encontraCicloLimite(int limiteBusca, double delta) ;

  /**
   * Obtem uma representacao textual desta Orbita.
   * <p>
   * A representacao deve ser adequada para usar com System.out.println().
   *
   * @return Uma representacao textual desta Orbita
   */
  String toString() ;

}

