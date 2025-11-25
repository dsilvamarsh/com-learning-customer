package com.learning.mapper;

import com.learning.bean.CustomerDto;
import com.learning.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer cust);
    Customer customerDtoToCustomer(CustomerDto dto);

}
