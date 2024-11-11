package io.github.publications.publicationsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PublicationsapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicationsapiApplication.class, args);
	}

}
