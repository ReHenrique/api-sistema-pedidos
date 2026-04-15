package com.meu.sistema_pedidos_springboot.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity 
public class Pessoa {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column (name = "id_pessoa")
    private Integer idPessoa;
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @Embedded
    private Endereco endereco;
    @Column(unique = true, length = 14) 
    private String cpf;
    
    public Pessoa() {
		
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
    
    
    
}
