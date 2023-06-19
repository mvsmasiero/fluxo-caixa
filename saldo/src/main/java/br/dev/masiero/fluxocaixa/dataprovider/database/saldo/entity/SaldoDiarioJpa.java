package br.dev.masiero.fluxocaixa.dataprovider.database.saldo.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "SALDO_DIARIO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaldoDiarioJpa {

	@Id
	@Column(name = "DATA", insertable = true, unique = true, updatable = false)
	private LocalDate data;

	@Column(name = "VALOR")	
	private BigDecimal valor;

}