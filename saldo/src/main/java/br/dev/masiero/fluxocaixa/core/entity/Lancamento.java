package br.dev.masiero.fluxocaixa.core.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lancamento {

	private long id;
	private String descricao;
	private LocalDate data;
	private BigDecimal valor;

}
