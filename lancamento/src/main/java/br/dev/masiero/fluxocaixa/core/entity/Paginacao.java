package br.dev.masiero.fluxocaixa.core.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paginacao<T> {

	private List<T> registros;
	private int paginaAtual;
	private long quantidadeTotalRegistros;
	
}
