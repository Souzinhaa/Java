package com.cliente.cadastro.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

public class ClientePagamentos {

	private Cliente cliente;
	private Page<Pagamentos> pagamento;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Page<Pagamentos> getPagamento() {
		return pagamento;
	}
	public void setPagamento(Page<Pagamentos> pagamento) {
		this.pagamento = pagamento;
	}
	
	public void reset() {
		this.cliente = null;
		this.pagamento = null;
	}
	
	
	
	
}
