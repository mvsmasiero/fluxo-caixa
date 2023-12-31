package br.dev.masiero.fluxocaixa.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaldoDiario {

	private LocalDate data;
	private BigDecimal valor;

}
