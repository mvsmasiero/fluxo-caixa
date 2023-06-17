package br.dev.masiero.fluxocaixa.core.usecase;

import br.dev.masiero.fluxocaixa.core.dataprovider.LancamentoDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.FiltroLancamento;
import br.dev.masiero.fluxocaixa.core.entity.Lancamento;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;

public class BuscarLancamentosUseCase {

	private LancamentoDataProvider lancamentoDataProvider;
	
	public BuscarLancamentosUseCase(LancamentoDataProvider lancamentoDataProvider) {
		this.lancamentoDataProvider = lancamentoDataProvider;
	}
	
	public Paginacao<Lancamento> buscar(FiltroLancamento filtro){
		ajustarPadroes(filtro);
		
		return this.lancamentoDataProvider.buscar(filtro);
	}
	
	private void ajustarPadroes(FiltroLancamento filtro) {
		if (filtro.getCampoOrdenacao() == null) {
			filtro.setCampoOrdenacao(FiltroLancamento.CampoOrdenacao.DATA);
		}

		if (filtro.getTipoOrdenacao() == null) {
			filtro.setTipoOrdenacao(FiltroLancamento.TipoOrdenacao.ASC);
		}
		
		if (filtro.getQuantidadeRegistrosPagina() == 0) {
			filtro.setQuantidadeRegistrosPagina(20);
		}

	}
	
}
