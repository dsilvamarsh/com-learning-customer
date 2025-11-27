package com.learning.mapper;

import com.learning.bean.AccountDto;
import com.learning.bean.CustomerDto;
import com.learning.entity.Account;
import com.learning.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer cust);

    Customer customerDtoToCustomer(CustomerDto dto);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer patchCustomerDtoToCustomer(CustomerDto dto, @MappingTarget Customer existingCustomer);
    Account patchAccountDtoToAccount(AccountDto accountDto, @MappingTarget Account existingAccount);
    AccountDto accountToAccountDto(Account acc);

    Account accountDtoToAccount(AccountDto accountDto);
}
