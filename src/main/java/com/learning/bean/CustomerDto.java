package com.learning.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerDto {

    private Integer id;
    private String name;
    private Integer age;
}
