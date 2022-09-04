package br.com.gomes.movie.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomes.movie.dto.MovieInfoDTO;
import br.com.gomes.movie.entity.Premios;
import br.com.gomes.movie.repository.PremioRepository;

@Service
public class PremioService {
	
	@Autowired
	private PremioRepository repository;
	
	
	public void saveAll(List<MovieInfoDTO> movieInfoDTO) {
		
		List<Premios> premios = movieInfoDTO.stream().map(movieInfo -> {
			Premios premio = new Premios();
			premio.setYear(Integer.parseInt(movieInfo.getYear()));
			premio.setTitle(movieInfo.getTitle());
			premio.setProducers(movieInfo.getProducers());
			premio.setStudios(movieInfo.getStudios());
			premio.setWinner( (!movieInfo.getWinner().isBlank()  && movieInfo.getWinner().equalsIgnoreCase("yes") ) ? true : false);
			
			return premio; 
					
		}).collect(Collectors.toList());
		
		
		repository.saveAll(premios);
	}

}
