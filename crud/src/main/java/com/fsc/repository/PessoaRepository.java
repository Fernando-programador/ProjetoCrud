package com.fsc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.fsc.models.Pessoa;



@Repository
@EnableJpaRepositories
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

List<Pessoa> findByVegano(boolean vegano);	
	

List<Pessoa> findByNomeContaining(String nome);
}
