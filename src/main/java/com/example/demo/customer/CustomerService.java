package com.example.demo.customer;

import com.example.demo.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        log.info("getCustomers was called");
        return customerRepository.findAll();
    }

    public Customer getCustomer(Long id){
        return customerRepository
                .findById(id)
                .orElseThrow(() -> {
                    NotFoundException notFoundException = new NotFoundException("customer with id " + id + " doesn't exist");
                    log.error("error in getCustomer {}", id, notFoundException.toString());
                    return notFoundException;
                });
    }
}