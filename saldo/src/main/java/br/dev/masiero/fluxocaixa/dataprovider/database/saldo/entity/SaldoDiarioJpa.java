package br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;


@Entity(name = "SALDO_DIARIO")
@Data
@Builder
public class SaldoDiarioJpa {

	@Id
	@Column(name = "DATA", insertable = true, unique = true, updatable = false)
	private LocalDate data;

	@Column(name = "VALOR")	
	private BigDecimal valor;

}