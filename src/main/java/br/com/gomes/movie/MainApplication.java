package br.com.gomes.movie;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.gomes.movie.config.FileStorageProperties;


@SpringBootApplication
@ComponentScan({"br.com.gomes.movie.config","br.com.gomes.movie.services", "br.com.gomes.movie.resources"})
@EnableConfigurationProperties({ FileStorageProperties.class })
public class MainApplication implements CommandLineRunner {
	
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
	}

}
