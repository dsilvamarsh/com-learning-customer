package com.learning.bean;

import com.learning.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AccountDto {
    private Integer id;
    private AccountType accountType;
    private UUID accountNumber;
    private BigDecimal balance;

}
