package com.fsc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.fsc.models.Pessoa;
import com.fsc.repository.PessoaRepository;

@RestController
@RequestMapping("/api")
public class PessoaResource {

	@Autowired
	private PessoaRepository repository;
	
	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> obetrTodos(@RequestParam(required=false)String pessoa){
		try {
			
			List<Pessoa> pessoas = new ArrayList<>();
			if(pessoa == null)
				repository.findAll().forEach(pessoas::add);
			else
				repository.findByNomeContaining(pessoa).forEach(pessoas::add);
			
			if(pessoa.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(pessoas, HttpStatus.OK);
					
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);		}
	}
	
	
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> obterPorId(@PathVariable("id") long id){
		Optional<Pessoa> pessoaId = repository.findById(id);
		
		if(pessoaId.isPresent()) {
			return new ResponseEntity<>(pessoaId.get(), HttpStatus.OK);
			
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> criarPessoa(Pessoa pessoa){
		try {
			Pessoa pessoaAdd = repository.save(new Pessoa(
					pessoa.getNome(),
					pessoa.getIdade(),
					false));
			return new ResponseEntity<>(pessoaAdd, HttpStatus.CREATED);	
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/pessoa/{id}")
	public ResponseEntity<Pessoa> atualizarPeesoa(@PathVariable("id") long id, 
			 Pessoa pessoa){
		Optional<Pessoa> pessoaUp = repository.findById(id);
		if(pessoaUp.isPresent()) {
			Pessoa pessoaAdd = pessoaUp.get();
			pessoaAdd.setNome(pessoa.getNome());
			pessoaAdd.setIdade(pessoa.getIdade());
			pessoaAdd.setVegano(pessoa.isVegano());
		return new ResponseEntity<>(repository.save(pessoaAdd), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	  @DeleteMapping("/pessoa/{id}")
	  public ResponseEntity<HttpStatus> deletarPessoa(@PathVariable("id") long id) {
	    try {
	      repository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	@DeleteMapping("/pessoa")
	  public ResponseEntity<HttpStatus> deletarTodos() {
	    try {
	      repository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }

	  @GetMapping("/pessoa/vegano")
	  public ResponseEntity<List<Pessoa>> obterPorVegano() {
	    try {
	      List<Pessoa> pessoas = repository.findByVegano(true);

	      if (pessoas.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(pessoas, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
