package com.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@Builder
@AllArgsConstructor
public class Customer {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}
