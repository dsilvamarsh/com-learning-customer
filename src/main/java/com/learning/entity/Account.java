package com.learning.entity;

import com.learning.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Table("account")
public class Account {
    @Id
    private Integer id;
    private UUID accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
}
