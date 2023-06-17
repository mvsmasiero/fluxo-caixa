package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiltroSaldoDiarioRest {

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataInicial;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataFinal;

	private String tipoOrdenacao;
	private String pagina;
	private String quantidadeRegistrosPagina;

}
