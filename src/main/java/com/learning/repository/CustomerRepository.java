package com.learning.repository;

import com.learning.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //@Override
    //@Lock(LockMode.PESSIMISTIC_WRITE)
    @Query(value = """
            select cust.* from app.customer cust,app.account acc
                   where cust.id=acc.customer
                   and cust.id = :customerId
                   for update
            """)
    Optional<Customer> findByIdLocked(@Param(value = "customerId") Integer integer);
}
