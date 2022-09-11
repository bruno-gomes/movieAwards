package br.com.gomes.movie.resources;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gomes.movie.dto.IntervaloPremiosDTO;
import br.com.gomes.movie.services.FileValidationService;
import br.com.gomes.movie.services.PremioService;
import br.com.gomes.movie.services.exception.FileExtensionException;
import io.swagger.annotations.ApiOperation;

@RestController
public class AwardResource {

	private static final Logger LOG = LoggerFactory.getLogger(AwardResource.class);

	@Autowired
	private FileValidationService fileValidationService;
	
	@Autowired
	private PremioService premioService;

	@ApiOperation(value = "Upload de lista de premiacao de filmes do Golden Raspberry Awards CSV")
	@CrossOrigin
	@PostMapping("/uploadFile")
	public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file){
		Boolean resultado = true;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension.equals("csv") == false) {
			LOG.error("Arquivo invalido esperado csv, arquivo recebido: " + extension);
			throw new FileExtensionException("Arquivo invalido esperado csv, arquivo recebido: " + extension);
		} else {
			try {
				resultado = fileValidationService.validateFile(file);
			} catch (Exception e) {
				throw new FileExtensionException("Erro de IO: " + e.getMessage());

			}
			return ResponseEntity.ok().body(resultado);
		}

	}
	
	@ApiOperation(value = "Retorna lista dos que mais e menos ganharam premios")
	@CrossOrigin
	@GetMapping("/listarGanhadores")
	public ResponseEntity<IntervaloPremiosDTO> buscaPremios(){
		IntervaloPremiosDTO intevaloPremiosDto = null;
		
		intevaloPremiosDto = premioService.buscaGanhadoresPremios();
		
		return ResponseEntity.ok().body(intevaloPremiosDto);
	}

}
