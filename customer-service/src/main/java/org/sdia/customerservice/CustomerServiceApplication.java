package org.sdia.customerservice;

import org.sdia.customerservice.entities.Customer;
import org.sdia.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			customerRepository.save(new Customer(null,"Mohamed","mohamed@gmail.com"));
			customerRepository.save(new Customer(null,"Yassine","yassine@gmail.com"));
			customerRepository.save(new Customer(null,"Salima","salima@gmail.com"));

			customerRepository.findAll().forEach(customer -> {
				System.out.println(customer.toString());
			});

		};


	}


}
