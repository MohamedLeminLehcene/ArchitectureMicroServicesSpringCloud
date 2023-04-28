package org.sdia.inventroyservice;

import org.sdia.inventroyservice.entities.Product;
import org.sdia.inventroyservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventroyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventroyServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null,"Ordinateur",7888,12));
			productRepository.save(new Product(null,"Imprimante",900,12));
			productRepository.save(new Product(null,"SmartPhone",1000,12));
			productRepository.findAll().forEach(product -> {
				System.out.println(product.getName());
			});

		};
	}

}
