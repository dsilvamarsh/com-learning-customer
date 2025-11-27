package com.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@Table("customer")
public class Customer {
    @Id
    private Integer id;
    private String name;
    private Integer age;
    //@MappedCollection(idColumn = "customer_id")
    private Set<Account> accountSet;
}
