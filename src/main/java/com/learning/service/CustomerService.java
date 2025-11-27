package com.learning.service;

import com.learning.bean.AccountDto;
import com.learning.bean.CustomerDto;
import com.learning.entity.Account;
import com.learning.entity.Customer;
import com.learning.mapper.CustomerMapper;
import com.learning.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {


    private CustomerRepository custRepo;
    private CustomerMapper mapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto saveCustomer(CustomerDto dto){
        Customer cust = mapper.customerDtoToCustomer(dto);
        log.debug("Converting  Customer DTO to Bean {}",cust);
        cust=custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto pathCustomer(CustomerDto customerDto){
        log.debug("Find customer to patch with id : {}",customerDto.getId());
        Customer existingCustomer = custRepo.findById(customerDto.getId()).orElseThrow();
        log.debug("Found customer with daa {}",existingCustomer);
        Customer cust = mapper.patchCustomerDtoToCustomer(customerDto,existingCustomer);
        log.debug("Converting  Customer DTO to Bean {}",cust);
        cust=custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto saveCustomer(AccountDto accDto,Integer customerId){
        log.debug("Finding customer {}",customerId);
        Customer cust = custRepo.findById(customerId).orElseThrow();
        log.debug("Customer found {}",cust);
        Account account = mapper.accountDtoToAccount(accDto);
        log.debug("Converting Account DTO to Bean {}",account);
        cust.getAccountList().add(account);
        log.debug("Converting Customer DTO to Bean {}",cust);
        cust=custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }

    public CustomerDto pathCustomerAccount(CustomerDto cust) {
        log.debug("Account details recived for udpate {}",cust);
        Customer existingCustomer = custRepo.findById(cust.getId()).orElseThrow();

        Map<Integer,AccountDto> updateAccountMap = cust.getAccountList()
                .stream()
                .collect(Collectors.toMap(AccountDto::getId,account -> account));
       existingCustomer.getAccountList().stream()
                .forEach(acc -> {
                    if(updateAccountMap.get(acc.getId()) != null){
                        mapper.patchAccountDtoToAccount(updateAccountMap.get(acc.getId()),acc);
                    }
                });
        existingCustomer=   custRepo.save(existingCustomer);
        log.debug("Save completed accounts pattched {}",existingCustomer);
        return  mapper.customerToCustomerDto(existingCustomer);

    }
}
