package com.learning;


import com.learning.bean.AccountDto;
import com.learning.bean.CustomerDto;
import com.learning.enums.AccountType;
import com.learning.repository.CustomerRepository;
import com.learning.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = {"com.learning"})
@Slf4j
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }


    //Patch operation
   // @Bean
    public CommandLineRunner commandLineRunner(CustomerService service, CustomerRepository repo) {

        return args -> {
            //CustomerDto cust = CustomerDto.builder().id(2).build();
            AccountDto accountDto1 = AccountDto.builder().id(5).balance(BigDecimal.valueOf(5555)).build();
            AccountDto accountDto2 = AccountDto.builder().id(6).accountType(AccountType.SAVINGS).build();
            //cust.setAccountList(Set.of(accountDto));
            //CustomerDto custResp =service.pathCustomer(cust);
            CustomerDto custResp = service.pathCustomerAccount(Set.of(accountDto1,accountDto2),2);

            log.debug("Customer  saved {}", custResp);

        };
    }
}