package com.meu.sistema_pedidos_springboot.controller;

import com.meu.sistema_pedidos_springboot.model.Pessoa;
import com.meu.sistema_pedidos_springboot.repository.PessoaRepository;

import dto.PessoaRequestDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/pessoas") 

public class PessoaController {
	@Autowired 
    private PessoaRepository pessoaRepository;

    @GetMapping 
    public List<Pessoa> buscarTodas() {
        
        return pessoaRepository.findAll();
    }

    @GetMapping("/{id}") 
    public Pessoa buscarPorId(@PathVariable Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    
    @PostMapping 
    public Pessoa salvar(@RequestBody @Valid PessoaRequestDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setDataNascimento(dto.dataNascimento());
        return pessoaRepository.save(pessoa);
    }
    
    @PutMapping("/{id}")
    public Pessoa atualizar(@PathVariable Integer id, @RequestBody Pessoa pessoaAtualizada) {
    	Pessoa pessoaExistente = pessoaRepository.findById(id).orElse(null);
    	
    	if(pessoaExistente != null) {
    		pessoaExistente.setNome(pessoaAtualizada.getNome());
    		pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
    		
    		return pessoaRepository.save(pessoaExistente);
    	}
    	return null;
    }
    
    @DeleteMapping("/{id}")
    public String apagar(@PathVariable Integer id) {
    	pessoaRepository.deleteById(id);
    	
    	return "Cadastro apagado!";
    }
}
