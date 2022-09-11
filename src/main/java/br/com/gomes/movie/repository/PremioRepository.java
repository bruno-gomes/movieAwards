package br.com.gomes.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gomes.movie.entity.Premios;

//public interface PremioRepository extends CrudRepository<Premios, Long >{
public interface PremioRepository extends JpaRepository<Premios, Long >{
	
	//@Query("SELECT p FROM Premios p WHERE p.winner = true")
	public List<Premios> findByWinner(boolean winner);

}
