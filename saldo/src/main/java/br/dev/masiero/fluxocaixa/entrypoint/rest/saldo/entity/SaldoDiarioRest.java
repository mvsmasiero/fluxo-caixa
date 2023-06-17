package br.dev.masiero.fluxocaixa.entrypoint.rest.saldo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SaldoDiarioRest {

	private LocalDate data;
	private BigDecimal valor;
	
}
