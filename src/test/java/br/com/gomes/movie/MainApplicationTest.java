package br.com.gomes.movie;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.gomes.movie.dto.IntervaloPremiosDTO;
import br.com.gomes.movie.dto.StatusDTO;
import br.com.gomes.movie.repository.PremioRepository;
import br.com.gomes.movie.services.FileValidationService;
import br.com.gomes.movie.services.PremioService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainApplicationTest {
	
	private static final String HOST = "http://localhost:";
	private static final String BASE_PATH = "/movie-awards/movie";
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private FileValidationService serviceFileValidation;
	
	@Autowired
	private PremioService servicePremio;
	
	@Autowired
	private PremioRepository repository;
	
	@Test
	void fluxoRegistrarArquivo() {
		
		ResponseEntity<StatusDTO> response = restCallFileUpload();
		
		assertEquals(200, response.getStatusCodeValue());
		
		assertEquals("Arquivo salvo com sucesso", response.getBody().getStatusMessage());
		
		repository.deleteAll();
	}

	
	
	@Test
	void fluxoEnviaArquivoEConsultaPremios() {
		//parte 1 - Enviar arquivo
		ResponseEntity<StatusDTO> response = restCallFileUpload();
		assertEquals(200, response.getStatusCodeValue());
		
		//parte 2 - chamada serviço resultado premios
		ResponseEntity<IntervaloPremiosDTO> intervaloPremioResponse = restTemplate.getForEntity(HOST + port + BASE_PATH + "/listarGanhadores", IntervaloPremiosDTO.class);
		
		assertEquals(200, intervaloPremioResponse.getStatusCodeValue());
		assertEquals(1 , intervaloPremioResponse.getBody().getMin().get(0).getInterval() );
		assertEquals(99 , intervaloPremioResponse.getBody().getMax().get(0).getInterval() );
		
		//clear all
		repository.deleteAll();
	}
	
	
	
	private Resource getTestFile() {
		String pathDirectory = getClass().getClassLoader().getResource("files/cenarioTesteIntegrado.csv").getFile();
		return new FileSystemResource(pathDirectory);
	}
	
	private ResponseEntity<StatusDTO> restCallFileUpload() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", getTestFile());
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity	 = new HttpEntity<>(body, headers);

		ResponseEntity<StatusDTO> response = restTemplate.postForEntity(HOST + port + BASE_PATH + "/uploadFile", requestEntity, StatusDTO.class);
		return response;
	}

}
