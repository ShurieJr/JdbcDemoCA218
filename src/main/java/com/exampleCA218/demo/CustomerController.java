package com.exampleCA218.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    //requests
    @PostMapping
    public void addCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
    }
    @GetMapping("/all")
    public List<Customer> getCustomers(){
      return  customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @DeleteMapping("{id}")
    public void deleteCustomerById(@PathVariable int id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("{id}")
    public void updateCustomerById(@PathVariable int id ,
                                   @RequestBody Customer customer){
        customer.setId(id);
        customerService.updateCustomer(customer);
    }
}
