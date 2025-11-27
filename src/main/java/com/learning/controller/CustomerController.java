package com.learning.controller;

import com.learning.bean.AccountDto;
import com.learning.bean.CustomerDto;
import com.learning.mapper.CustomerMapper;
import com.learning.repository.CustomerRepository;
import com.learning.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class CustomerController {
    private final CustomerService custService;
    private final CustomerRepository custRepo;
    private final CustomerMapper mapper;

    @PreAuthorize("hasRole('viewer')")
    @GetMapping(path = "/customer/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Integer id) {
        log.debug("Find customer with id {}", id);
        log.debug("Recived data {}",custRepo.findById(id).get());
        return ResponseEntity.ok(mapper.customerToCustomerDto(custRepo.findById(id).get()));
    }

    @PreAuthorize("hasRole('writer') ")
    @PostMapping(path = "/customer")
    public ResponseEntity<CustomerDto> getCustomer(@RequestBody CustomerDto custDto) {

        log.debug("Recived customer with details {}", custDto);
         custDto =custService.saveCustomer(custDto);
        log.debug("Customer after saving to DB {}",custDto);

        return ResponseEntity.ok(custDto);

    }
    @PreAuthorize("hasRole('writer') ")
    @PatchMapping(path = "/customer")
    public ResponseEntity<CustomerDto> pathcCustomer(@RequestBody CustomerDto custDto) {

        log.debug("Recived pathc customer with details {}", custDto);
        custDto =custService.pathCustomer(custDto);
        log.debug("Customer after saving to DB {}",custDto);

        return ResponseEntity.ok(custDto);

    }


    @PreAuthorize("hasRole('writer')")
    @PostMapping(path = "/customer/{custId}/account")
    public ResponseEntity<CustomerDto> saveAccount(@RequestBody AccountDto accountDto, @PathVariable Integer custId){
        log.debug("Recived account information {}",accountDto);
        CustomerDto custDto =custService.saveCustomer(accountDto,custId);

        return ResponseEntity.ok(custDto);
    }
}
