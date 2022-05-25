package com.cliente.cadastro.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.cadastro.model.Cliente;
import com.cliente.cadastro.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
    private ClienteRepository repository;

	@GetMapping("/cliente")
    public Iterable<Cliente> getAllClientes() {
        return repository.findAll();
    }
    
    @GetMapping("/cliente/buscaid/{id}")
    public Optional<Cliente> listById(@PathVariable(value = "id") long id) {
        return repository.findById(id);
    }
    
    @GetMapping("/cliente/buscanome/{nome}")
    public List<Optional<Cliente>> listByNome(@PathVariable(value = "nome") String nome) {

    	List<Long> clientesId = new ArrayList<Long>();
    	List<Optional<Cliente>> clientesBuscados = new ArrayList<Optional<Cliente>>();
    	
    	repository.findAll().stream().forEach(c -> {
    		String[] nomes = c.getNome().split(" ");
    		for(String nm : nomes){
    			if(nm.toLowerCase().equals(nome.toLowerCase())) {
    				clientesId.add(c.getId());
    			}
    		}
    	});
    	
    	clientesId.stream().forEach(id -> {
    		clientesBuscados.add(repository.findById(id));
    	});
    	
    	return clientesBuscados;
    }
    
    /*@GetMapping("/busca/{nome}")
    public Optional<DetalhamentoClientesModel> findByName(@PathVariable(value = "nome") String nome) {
        int i;
        Iterable<DetalhamentoClientesModel> clientes = repository.findAll();
        int cont = (int) clientes.spliterator().getExactSizeIfKnown();
        System.out.println(clientes.iterator());
        for(i=0;i<cont;i++) {
        	
        }
        
        long ad = 32;
    	
    	return repository.findById(ad);
    }*/
    
    @PostMapping(value="/cliente", consumes = {"application/json"})
    public Cliente newCliente(@RequestBody Cliente cliente) {
    	System.out.println("Novo cliente salvo");
        return repository.save(cliente);
    }
    
    @DeleteMapping(value="/del/{id}")
    public void delCliente(@PathVariable(value = "id") long id) {
        repository.deleteById(id);
        
    }
    
    @PutMapping
    public Cliente updateCliente(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }
	
}
