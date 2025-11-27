package com.learning.tests;

import com.learning.bean.AccountDto;
import com.learning.bean.CustomerDto;
import com.learning.enums.AccountType;
import com.learning.mapper.CustomerMapper;
import com.learning.repository.CustomerRepository;
import com.learning.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerTest {

    @Autowired
    private  CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper mapper;


    //@Test
    void contextLoads(){
        assertThat(customerService).isNotNull();
    }

    //@Test
    void createCustomer(){
        CustomerDto customer = CustomerDto.builder().age(23).name("Faith").build();
        customerService.saveCustomer(customer);
    }

   //@Test
    void createAccount(){
        AccountDto account1 = AccountDto.builder().accountType(AccountType.SAVINGS).balance(BigDecimal.valueOf(45000)).accountNumber(UUID.randomUUID()).build();
        AccountDto account2 = AccountDto.builder().accountType(AccountType.CURRENT).balance(BigDecimal.valueOf(0)).accountNumber(UUID.randomUUID()).build();

        customerService.saveAccount(Set.of(account1,account2),3);
    }

    //@Test
    void addAccount(){
        CustomerDto customerDto = CustomerDto.builder().age(59).name("Michal").build();
        AccountDto account1 = AccountDto.builder().accountType(AccountType.OVERDUE).balance(BigDecimal.valueOf(5000)).accountNumber(UUID.randomUUID()).build();
        customerDto.setAccountSet(Set.of(account1));
        customerService.saveCustomer(customerDto);
    }


    //@Test
    void patchAccount(){

        AccountDto account1 = AccountDto.builder().id(8).accountType(AccountType.OVERDUE).balance(BigDecimal.valueOf(35000)).build();
        customerService.pathCustomerAccount(Set.of(account1),3);
    }

    @Test
    void deleteAccount(){

        AccountDto account1 = AccountDto.builder().id(11).build();
        customerService.deleteCustomerAccount(Set.of(account1),5);
    }
}
