package br.dev.masiero.fluxocaixa.core.usecase;

import br.dev.masiero.fluxocaixa.core.dataprovider.SaldoDiarioDataProvider;
import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;

public class BuscarSaldoDiarioUseCase {

	private SaldoDiarioDataProvider saldoDiarioDataProvider;
	
	public BuscarSaldoDiarioUseCase(SaldoDiarioDataProvider saldoDiarioDataProvider) {
		this.saldoDiarioDataProvider = saldoDiarioDataProvider;
	}
	
	public Paginacao<SaldoDiario> buscar(FiltroSaldo filtro){
		ajustarPadroes(filtro);
		
		return this.saldoDiarioDataProvider.buscar(filtro);
	}
	
	private void ajustarPadroes(FiltroSaldo filtro) {
		if (filtro.getTipoOrdenacao() == null) {
			filtro.setTipoOrdenacao(FiltroSaldo.TipoOrdenacao.ASC);
		}
		
		if (filtro.getQuantidadeRegistrosPagina() == 0) {
			filtro.setQuantidadeRegistrosPagina(20);
		}

	}
	
}
