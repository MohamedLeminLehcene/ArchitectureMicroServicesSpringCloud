package org.sdia.billingservice.web;

import org.sdia.billingservice.entities.Bill;
import org.sdia.billingservice.feign.CustomerRestClient;
import org.sdia.billingservice.feign.ProductItemRestClient;
import org.sdia.billingservice.model.Customer;
import org.sdia.billingservice.model.Product;
import org.sdia.billingservice.repository.BllRepository;
import org.sdia.billingservice.repository.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BllRepository bllRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;


    public BillingRestController(BllRepository bllRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.bllRepository = bllRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){

        Bill bill = bllRepository.findById(id).get();

        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItem().forEach(productItem -> {
            Product product=productItemRestClient.getProductById(productItem.getProductID());
            productItem.setProduct(product);
        });
        return bill;
    }
}
