package com.credit.credit_card.Customers.Service;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import com.credit.credit_card.Customers.DTO.CustomerDTO;
import com.credit.credit_card.Customers.Model.CustomerModel;
import com.credit.credit_card.Customers.Repository.CustomerRepository;

@Service
public class CustomerService {
    //Import CustomerRepository
    private CustomerRepository cusRep;

    // Builder from the class
    public CustomerService(CustomerRepository cusRep){
        this.cusRep = cusRep;
    }

    //Method for save a customer
    public CustomerModel register_customer(CustomerDTO cusDTO){
        if(cusDTO.name() == null || cusDTO.name().isBlank() ||
           cusDTO.address() == null || cusDTO.address().isBlank() ||
           cusDTO.ssn() == null || cusDTO.ssn().isBlank() ||
           cusDTO.email() == null || cusDTO.email().isBlank()) {

            throw new IllegalArgumentException("You must fill out all the information");
        }
        try{
            // Object
            CustomerModel cusModel = new CustomerModel(
                cusDTO.ssn(), 
                cusDTO.name(),
                cusDTO.address(),
                cusDTO.email(),
                LocalDateTime.now()
            );
            // Save object
            return this.cusRep.save(cusModel);
        } catch(Exception e){
            throw new IllegalArgumentException("Error registering customer: " + e.getMessage());
        }
    }
}
