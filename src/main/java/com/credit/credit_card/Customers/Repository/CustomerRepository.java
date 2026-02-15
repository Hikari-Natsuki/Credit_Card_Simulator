package com.credit.credit_card.Customers.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.credit_card.Customers.Model.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer>{
    Optional <CustomerModel> findById(Integer id);
}
