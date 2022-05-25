package com.cliente.cadastro.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.cadastro.model.Cliente;
import com.cliente.cadastro.model.ClientePagamentos;
import com.cliente.cadastro.model.Pagamentos;
import com.cliente.cadastro.repository.ClienteRepository;
import com.cliente.cadastro.repository.PagamentoRepository;

@RestController
public class PagamentoController {
	
	@Autowired
	private PagamentoRepository pagRepository;
	
	@Autowired
	private ClienteRepository clieRepository;
	
	@RequestMapping("cliente/pagamentos")
	public List<ClientePagamentos> getAllPagamentos() {
		List<Cliente> cliente = clieRepository.findAll();
		List<ClientePagamentos> clientePag = new ArrayList<ClientePagamentos>();
		int qtd = cliente.size();
		ClientePagamentos cPag = new ClientePagamentos();
		int i;
		for(i=0;i<qtd;i++) {
			Pageable pageable = null;
			cPag.setCliente(cliente.get(i));
			cPag.setPagamento(getAllClientesPag(cliente.get(i).getId(), pageable));
			clientePag.add(cPag);
			System.out.println(i + " - " + clientePag.get(i).getCliente().getNome());
		}
		for(i=0;i<qtd;i++) {
			System.out.println(i + " - " + clientePag.get(i).getCliente().getNome());
		}
		System.out.println("Encerra Consulta\n");
		return clientePag;
	}
	
	@RequestMapping("cliente/{cliente_id}/pagamentos")
    public Page<Pagamentos> getAllClientesPag(@PathVariable (value = "cliente_id") Long cliente_id,
    													Pageable pageable) {
        return pagRepository.findByClienteId(cliente_id, pageable);
    }
	
	@RequestMapping("cliente/{cliente_id}/pagamentos/{pagamento_id}")
    public Optional<Pagamentos> getOnePag(@PathVariable (value = "cliente_id") Long cliente_id,
    							@PathVariable (value = "pagamento_id") Long pagamento_id) {
        return pagRepository.findByIdAndClienteId(cliente_id, pagamento_id);  
    }
	
	@PostMapping("/cliente/{cliente_id}/pagamentos")
    public Pagamentos createPagamento(@PathVariable (value = "cliente_id") Long cliente_id,
    									@RequestBody Pagamentos pagamento) {
            Optional<Cliente> cliente = clieRepository.findById(cliente_id);
        	pagamento.setCliente(cliente.get());
            return pagRepository.save(pagamento);
    }
//
    @DeleteMapping("/cliente/{cliente_id}/pagamentos/{pagamento_id}")
    public ResponseEntity<?> deletePagamento(@PathVariable (value = "cliente_id") Long cliente_id,
                              @PathVariable (value = "pagamento_id") Long pagamento_id) {
        return pagRepository.findByIdAndClienteId(pagamento_id, cliente_id).map(pagamento -> {
            pagRepository.delete(pagamento);
            return ResponseEntity.ok().build();
        }).orElseThrow();
    }

}
