package br.com.gomes.movie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gomes.movie.dto.IntervaloPremiosDTO;
import br.com.gomes.movie.dto.MovieInfoDTO;
import br.com.gomes.movie.dto.ResultadoPremioDTO;
import br.com.gomes.movie.dto.StatusDTO;
import br.com.gomes.movie.entity.Premios;
import br.com.gomes.movie.repository.PremioRepository;
import br.com.gomes.movie.services.port.PremioServicePort;
import br.com.gomes.movie.utils.PremioComprator;

@Service
public class PremioService implements PremioServicePort{
	
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


	@Override
	public IntervaloPremiosDTO buscaGanhadoresPremios() {
		
		List<Premios> allWinnersList = repository.findByWinner(true);
		IntervaloPremiosDTO intervaloPremios = null;
		
		intervaloPremios = processaGanhadores(allWinnersList);
		
		
		return intervaloPremios;
	}
	
	private IntervaloPremiosDTO processaGanhadores(List<Premios> allWinnersList) {
		IntervaloPremiosDTO intervaloDto = new IntervaloPremiosDTO();
		List<ResultadoPremioDTO> lista = new ArrayList<>();
		ResultadoPremioDTO resultadoPremioDto =  null;
		
		/*allWinnersList.stream().map(line -> {
			
			Stream<Premios> subListFiltter = allWinnersList.stream().filter(subItem -> subItem.getProducers().equalsIgnoreCase(line.getProducers()));
						
		});*/
		
		allWinnersList.sort(new PremioComprator());
		
		for (int indexMaster = 0; indexMaster < allWinnersList.size(); indexMaster++) {
			
			for (int subIndex = indexMaster+1; subIndex < allWinnersList.size(); subIndex++) {
				
				if(allWinnersList.get(indexMaster).getProducers().contains(allWinnersList.get(subIndex).getProducers())) {
					resultadoPremioDto = new ResultadoPremioDTO();
					 resultadoPremioDto.setFollowingWin(allWinnersList.get(subIndex).getYear());
					 resultadoPremioDto.setPreviusWin(allWinnersList.get(indexMaster).getYear());
					 resultadoPremioDto.setInterval( allWinnersList.get(subIndex).getYear() - allWinnersList.get(indexMaster).getYear());
					 resultadoPremioDto.setProducer(allWinnersList.get(indexMaster).getProducers());
					 
					 lista.add(resultadoPremioDto);
				}
			}
		}
		
		int maxInterval = lista.stream().mapToInt(v -> v.getInterval()).max().orElse(-1);
		int minInterval = lista.stream().mapToInt(v -> v.getInterval()).min().orElse(-1);
		
		intervaloDto.setMin( lista.stream().filter(m -> m.getInterval() == minInterval).collect(Collectors.toList())  );
		intervaloDto.setMax( lista.stream().filter(m -> m.getInterval() == maxInterval).collect(Collectors.toList())  );
		
		return intervaloDto;
	}


	@Override
	public StatusDTO removeAll() {
		try {
			repository.deleteAll();
		}catch (Exception e) {
			return new StatusDTO("Erro no processo de Delete All", false);
		}
		return new StatusDTO("Delete All realizado com sucesso", true);
	}
	
	

}
