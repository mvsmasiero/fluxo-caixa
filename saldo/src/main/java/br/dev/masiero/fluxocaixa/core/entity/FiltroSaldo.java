package br.dev.masiero.fluxocaixa.core.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiltroSaldo {

	public enum TipoOrdenacao {
		ASC, DESC;
	}

	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private TipoOrdenacao tipoOrdenacao;
	private int pagina;
	private int quantidadeRegistrosPagina;

}
