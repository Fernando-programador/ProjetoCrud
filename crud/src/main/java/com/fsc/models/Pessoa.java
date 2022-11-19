package com.fsc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "nome", length = 50, unique = true)
	private String nome;

	@NotBlank
	@Column(name = "idade", length = 3, unique = false)
	private Integer idade;

	@Column(name = "vegano", length = 15)
	private boolean vegano;

	public Pessoa() {

	}

	public Pessoa(Long id, @NotBlank String nome, @NotBlank Integer idade, boolean vegano) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.vegano = vegano;
	}

	public Pessoa(String nome2, Integer idade2, boolean b) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public boolean isVegano() {
		return vegano;
	}

	public void setVegano(boolean vegano) {
		this.vegano = vegano;
	}

	@Override
	public String toString() {
		return "Peesoa [id=" + id + ", com nome=" + nome + ", tem a idade de " + idade
				+ ". Uma pergunta você é vegano? " + vegano + "]";
	}

}
