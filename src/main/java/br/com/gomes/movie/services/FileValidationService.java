/**
 * 
 */
package br.com.gomes.movie.services;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.gomes.movie.dto.MovieInfoDTO;
import br.com.gomes.movie.services.exception.FileStorageException;

/**
 * @author gomes.bruno
 *
 */
@Service
public class FileValidationService {
	
	@Autowired
	private PremioService premioService;
	
	private static final Logger LOG = LoggerFactory.getLogger(FileValidationService.class);
	
	private final String year = "year", title = "title", studios =  "studios", producers = "producers", winner = "winner";

	public boolean validateFile(MultipartFile file) {
		boolean resultadoOperacao = true;
		try {
			List<MovieInfoDTO> moviesInfo = new ArrayList<>();
			Reader reader = new InputStreamReader(file.getInputStream());
            CSVFormat csvFormat = CSVFormat.DEFAULT.withAllowMissingColumnNames(false)
                    .withFirstRecordAsHeader()
                    .withSkipHeaderRecord()
                    .withDelimiter(';');
            CSVParser csvParser = new CSVParser(reader, csvFormat);
            List<String> headers = csvParser.getHeaderNames();
            headers = headers.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
            
            if (headers.contains(year.toLowerCase()) 
            		&& headers.contains(title.toLowerCase())
            		&& headers.contains(studios.toLowerCase())
            	    && headers.contains(producers.toLowerCase())
            	    && headers.contains(winner.toLowerCase())
            		) {
            	  for (CSVRecord csvRecord : csvParser) {
            		  moviesInfo.add(validaEMonta(csvRecord));            		  
                  }
            	  csvParser.close();
            	  
            	  //enviar para save
            	  saveFile(moviesInfo);
            	
            }
            else {
            	 csvParser.close();
            	 resultadoOperacao = false;
            	LOG.error("Arquivo com cabeçalho incorreto, formato esperado: year;title;studios;producers;winner");
                throw new FileStorageException("Arquivo com cabeçalho incorreto, formato esperado: year;title;studios;producers;winner");
            }
			
		}catch (Exception e) {
			resultadoOperacao = false;
			throw new FileStorageException("Erro ao tentar ler o arquivo verifique sua extensão é valida e seu conteudo está intrego e com cabeçalho e se o separador é ';' " + file.getOriginalFilename(), e);
		}
		
		
		return resultadoOperacao;
	}
	
	private MovieInfoDTO validaEMonta(CSVRecord csvRecord) {
		MovieInfoDTO info = new MovieInfoDTO();
		info.setYear(csvRecord.get(year));
		info.setProducers(csvRecord.get(producers));
		info.setTitle(csvRecord.get(title));
		info.setStudios(csvRecord.get(studios));
		info.setWinner(csvRecord.get(winner));
		
		return info;
	}
	
	private void saveFile(List<MovieInfoDTO> moviesInfo) {
		
		premioService.saveAll(moviesInfo);
		
	}

}
