package br.com.gomes.movie.services.port;

import java.util.List;

import br.com.gomes.movie.dto.IntervaloPremiosDTO;
import br.com.gomes.movie.dto.MovieInfoDTO;

public interface PremioServicePort {

	public void saveAll(List<MovieInfoDTO> movieInfoDTO);
	
	public IntervaloPremiosDTO buscaGanhadoresPremios();
}
