package org.sdia.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}




	//@Bean
	RouteLocator routeLocator(RouteLocatorBuilder builder){
			/*
			builder.routes()
						.route(r -> r.path("/products/**")
								.uri("http://localhost:8083")
						)
						.route(r -> r.path("/customers/**")
						.uri("http://localhost:8082")
						)
						.build();
 */
		return
				builder.routes()
						.route(r -> r.path("/products/**")
								.uri("lb://PRODUCT-SERVICE")
						)
						.route(r -> r.path("/customers/**")
						.uri("lb://CUSTOMER-SERVICE")
						)
						.build();
	}

/*
				builder.routes()
						.route(r -> r.path("/products/**")
								.uri("http://localhost:8083")
						)
						.route(r -> r.path("/customers/**")
								.uri("http://localhost:8082")
						)
						.build();
 */


	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties){
		return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
	}








}


