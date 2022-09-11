package br.com.gomes.movie.services.port;

import java.util.List;

import br.com.gomes.movie.dto.IntervaloPremiosDTO;
import br.com.gomes.movie.dto.MovieInfoDTO;
import br.com.gomes.movie.dto.StatusDTO;

public interface PremioServicePort {

	public void saveAll(List<MovieInfoDTO> movieInfoDTO);
	
	public IntervaloPremiosDTO buscaGanhadoresPremios();
	
	public StatusDTO removeAll();
}
