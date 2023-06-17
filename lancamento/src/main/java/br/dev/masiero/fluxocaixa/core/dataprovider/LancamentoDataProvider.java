package br.dev.masiero.fluxocaixa.core.dataprovider;

import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;

public interface LancamentoDataProvider {

	public Lancamento incluir(Lancamento lancamento);
	
	public void excluir(long id);
	
	public Lancamento atualizar(Lancamento lancamento);
	
	public Lancamento visualizar(long id);
	
	public Paginacao<Lancamento> buscar(FiltroLancamento filtro);

	public boolean existe(long id);
	
}
