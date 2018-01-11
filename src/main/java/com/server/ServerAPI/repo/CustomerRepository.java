package com.server.ServerAPI.repo;

import java.util.List;

import com.server.ServerAPI.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}