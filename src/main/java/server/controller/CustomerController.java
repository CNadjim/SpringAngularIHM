package server.controller;

import java.util.ArrayList;
import java.util.List;

import server.model.Customer;
import server.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping(value="/",  produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        Iterable<Customer> customers = repository.findAll();
        customers.forEach(list::add);
        return list;
    }

    @PostMapping(value="/add")
    public Customer postCustomer(@RequestBody Customer customer) {
        repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
        return customer;
    }


    @DeleteMapping(value="/delete/{id}")
    public void deleteCustomer(@PathVariable long id){
        repository.delete(id);
    }

    @GetMapping(value="/getByLastName/{lastName}",  produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getCustomer(@PathVariable String lastName){
        List<Customer> customers = repository.findByLastName(lastName);
        return customers ;
    }
}