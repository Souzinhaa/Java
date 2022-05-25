package com.cliente.cadastro.enums;

import lombok.Getter;

@Getter
public enum EnumsPagamentos {
	
	DEBITO(1),
	CREDITO(2),
	DINHEIRO(3),
	PIX(4);
	
	private int forma;
	
	EnumsPagamentos(int i) {
		this.forma = i;
	}
}
