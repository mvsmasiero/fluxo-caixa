package br.dev.masiero.fluxocaixa.core.dataprovider;

import java.time.LocalDate;
import java.util.List;

import br.dev.masiero.fluxocaixa.core.entity.FiltroSaldo;
import br.dev.masiero.fluxocaixa.core.entity.Paginacao;
import br.dev.masiero.fluxocaixa.core.entity.SaldoDiario;

public interface SaldoDiarioDataProvider {

	public SaldoDiario incluir(SaldoDiario lancamento);
	
	public void excluir(LocalDate data);
	
	public SaldoDiario atualizar(SaldoDiario lancamento);
	
	public SaldoDiario obter(LocalDate data);
	
	public Paginacao<SaldoDiario> buscar(FiltroSaldo filtro);

	public boolean existe(LocalDate data);
	
	public List<SaldoDiario> recuperarTodosDesde(LocalDate data);
	
	public void atualizar(List<SaldoDiario> saldoDiarioList);
}
