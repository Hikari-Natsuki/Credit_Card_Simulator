package com.credit.credit_card.Customers.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.LoggerFactory;

import com.credit.credit_card.Customers.DTO.CustomerDTO;
import com.credit.credit_card.Customers.Model.CustomerModel;
import com.credit.credit_card.Customers.Service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    // Attributes
    private CustomerService cusService;

    // Builder
    public CustomerController(CustomerService cusService){
        this.cusService = cusService;
    }

    //Logger
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/register")
    public ResponseEntity<?> register_customer(@RequestBody CustomerDTO cusDTO){
        try{
            logger.info("Request sent to /register");
            logger.info("Data received: name = " + cusDTO.name() + " | ssn: " + cusDTO.ssn() +
                " | address: " + cusDTO.address() + " | email: " + cusDTO.email());
            CustomerModel cusModel = this.cusService.register_customer(cusDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("customer " + cusDTO.name() + " registered");
        } catch (IllegalArgumentException e){
            logger.warn("Bad request: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                                    "INTERNAL SERVER ERROR: " + ex.getMessage());
        }
    }
}
