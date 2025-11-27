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
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {


    private CustomerRepository custRepo;
    private CustomerMapper mapper;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto saveCustomer(CustomerDto dto) {
        Customer cust = mapper.customerDtoToCustomer(dto);
        log.debug("Converting  Customer DTO to Bean {}", cust);
        cust = custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto pathCustomer(CustomerDto customerDto) {
        log.debug("Find customer to patch with id : {}", customerDto.getId());
        Customer existingCustomer = custRepo.findById(customerDto.getId()).orElseThrow();
        log.debug("Found customer with daa {}", existingCustomer);
        Customer cust = mapper.patchCustomerDtoToCustomer(customerDto, existingCustomer);
        log.debug("Converting  Customer DTO to Bean {}", cust);
        cust = custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto saveCustomer(AccountDto accDto, Integer customerId) {
        log.debug("Finding customer {}", customerId);
        Customer cust = custRepo.findById(customerId).orElseThrow();
        log.debug("Customer found {}", cust);
        Account account = mapper.accountDtoToAccount(accDto);
        log.debug("Converting Account DTO to Bean {}", account);
        cust.getAccountSet().add(account);
        log.debug("Converting Customer DTO to Bean {}", cust);
        cust = custRepo.save(cust);
        log.debug("Customer saved in the DB ");
        return mapper.customerToCustomerDto(cust);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CustomerDto pathCustomerAccount(Set<AccountDto> accountDto, Integer custId) {
        log.debug("Account details recived for udpate customer : {}, Account : {}", accountDto,custId);
        Customer existingCustomer = custRepo.findById(custId).orElseThrow();
        Map<Integer,AccountDto> newAccDtoMap=accountDto.stream().collect(Collectors.toMap(AccountDto::getId, acc->acc));
        existingCustomer.setAccountSet(existingCustomer.getAccountSet().stream().map(account -> {
            if(newAccDtoMap.containsKey(account.getId())){
                mapper.patchAccountDtoToAccount(newAccDtoMap.get(account.getId()),account);
            }
            return account;
        }).collect(Collectors.toSet()));

        existingCustomer = custRepo.save(existingCustomer);
        log.debug("Save completed accounts pattched {}", existingCustomer);
        return mapper.customerToCustomerDto(existingCustomer);

    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveAccount(Set<AccountDto> accountDtoSet, Integer custId) {

        log.debug("Fetch customer with ID : {}",custId);
        Customer customer =custRepo.findById(3).orElseThrow();
       Set<Account> accountSet= accountDtoSet.stream().map(accountDto -> {
                    return mapper.accountDtoToAccount(accountDto);
                }
        ).collect(Collectors.toSet());
        customer.getAccountSet().addAll(accountSet);
        custRepo.save(customer);
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteCustomerAccount(Set<AccountDto> accountDtoSet, Integer custId) {
        log.debug("Find customer {}",custId);
        Customer customer = custRepo.findById(custId).orElseThrow();
        Set<Account> removeAccountSet = accountDtoSet.stream().map(mapper::accountDtoToAccount).collect(Collectors.toSet());

        customer.getAccountSet().removeAll(removeAccountSet);
        custRepo.save(customer);
    }
}
