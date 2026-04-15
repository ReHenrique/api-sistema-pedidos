package com.meu.sistema_pedidos_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.meu.sistema_pedidos_springboot.dto.EnderecoViaCepDTO;
import com.meu.sistema_pedidos_springboot.dto.PessoaRequestDTO;
import com.meu.sistema_pedidos_springboot.model.Endereco;
import com.meu.sistema_pedidos_springboot.model.Pessoa;
import com.meu.sistema_pedidos_springboot.repository.PessoaRepository;
import com.meu.sistema_pedidos_springboot.service.ViaCepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@CrossOrigin(origins = "http://localhost:4200") 
@RestController 
@RequestMapping("/pessoas") 
@Tag(name="Pessoas", description = "Operações p/ gerenciamento de pessoas")
public class PessoaController {
	@Autowired 
    private PessoaRepository pessoaRepository;
	
	@Autowired
	private ViaCepService viaCepService;

    @GetMapping 
    @Operation(
    		summary = "Lista todas as pessoas",
    		description = "Retorna uma lista completa de pessoas cadastradas no banco"
    )
    public List<Pessoa> buscarTodas() {
        
        return pessoaRepository.findAll();
    }
    
    @GetMapping("/cep/{cep}")
    @Operation(
    		summary="Buscar endereço ViaCep",
    		description="Consome a API publica ViaCEP e retorna os dados da rua."
    )
    public EnderecoViaCepDTO buscarCepApiExterna(@PathVariable String cep) {
    	return viaCepService.buscarEnderecoPorCep(cep);
    }
    
    
    @GetMapping("/{id}") 
    @Operation(
    		summary = "Busca a pessoa pelo ID",
    		description = "Retorna os detalhes de uma única pessoa com base no número de identificação fornecido na URL.")
    public Pessoa buscarPorId(@PathVariable Integer id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    
    @PostMapping 
    @Operation(
	        summary = "Cadastra uma nova pessoa", 
	        description = "Recebe os dados de uma pessoa no corpo da requisição (JSON), preenche endereço via CEP automaticamente e o salva no banco de dados."
	    )
    public ResponseEntity<Pessoa> salvar(@RequestBody @Valid PessoaRequestDTO dto) {
    	EnderecoViaCepDTO dados = viaCepService.buscarEnderecoPorCep(dto.cep());
    	
    	Endereco endereco = new Endereco();
    	endereco.setCep(dto.cep());
    	endereco.setNumero(dto.numero());
    	endereco.setComplemento(dto.complemento());
    	endereco.setLogradouro(dados.logradouro());
    	endereco.setBairro(dados.bairro());
    	endereco.setLocalidade(dados.localidade());
    	endereco.setUf(dados.uf());
   
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        pessoa.setCpf(dto.cpf());
        pessoa.setDataNascimento(dto.dataNascimento());
        pessoa.setEndereco(endereco);
        
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }
    
    @PutMapping("/{id}")
    @Operation(
	        summary = "Atualiza uma pessoa existente", 
	        description = "Substitui os dados de uma pessoa cadastrado pelo novo objeto enviado no corpo da requisição."
	    )
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
    @Operation(
	        summary = "Remove uma pessoa do banco", 
	        description = "Deleta permanentemente a pessoa do banco de dados com base no ID fornecido."
	    )
    public String apagar(@PathVariable Integer id) {
    	pessoaRepository.deleteById(id);
    	
    	return "Cadastro apagado!";
    }
}
