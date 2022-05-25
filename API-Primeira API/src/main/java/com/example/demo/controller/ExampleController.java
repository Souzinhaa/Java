package com.example.demo.controller;

import com.example.demo.model.Example;
import com.example.demo.repository.ExampleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class ExampleController {
	
	@Autowired
    private ExampleRepository repository;
	
	@GetMapping
    public List<Example> listAll() {
        return repository.findAll();
    }
    
    @PostMapping
    public Example newCliente(@RequestBody Example Example) {
        return repository.save(Example);
    }
    
    @DeleteMapping
    public void delCliente(@RequestBody Example cliente) {
        repository.delete(cliente);
    }
    
    @PutMapping
    public Example updateCliente(@RequestBody Example Example) {
        return repository.save(Example);
    }

}
