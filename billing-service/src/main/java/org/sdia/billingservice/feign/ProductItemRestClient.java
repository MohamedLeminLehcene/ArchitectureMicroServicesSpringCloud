package org.sdia.billingservice.feign;

import org.sdia.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductItemRestClient {

    @GetMapping(path = "/products")
    PagedModel<Product> findAll();;
    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);
}
