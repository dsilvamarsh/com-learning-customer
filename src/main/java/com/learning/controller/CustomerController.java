package com.learning.controller;

import com.learning.bean.CustomerDto;
import com.learning.mapper.CustomerMapper;
import com.learning.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class CustomerController {

    private final CustomerRepository custRepo;
    private final CustomerMapper mapper;

    @PreAuthorize("hasRole('viewer')")
    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Integer id) {
        log.debug("Find customer with id {}", id);
        return ResponseEntity.ok(mapper.customerToCustomerDto(custRepo.findById(id).get()));
    }

    @PreAuthorize("hasRole('writer') ")
    @PostMapping(path = "/customer")
    public ResponseEntity<CustomerDto> getCustomer(@RequestBody CustomerDto cust) {

        log.debug("Recived customer with details {}", cust);
        log.debug("After converting the dto : {}",mapper.customerDtoToCustomer(cust));
        return ResponseEntity.ok(mapper.customerToCustomerDto(custRepo.save(mapper.customerDtoToCustomer(cust))));

    }
}
