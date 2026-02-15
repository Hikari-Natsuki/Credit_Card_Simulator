package com.credit.credit_card.Customers.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerModel {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "ssn", nullable = false)
    private String ssn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "registration_date")
    private LocalDateTime  registration_date;

    //builder
    public CustomerModel(String ssn, String name, String address, String email,
            LocalDateTime  registration_date) {
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.email = email;
        this.registration_date = registration_date;
    }

    //Getters and Setters
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime  getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDateTime  registration_date) {
        this.registration_date = registration_date;
    }
    
    // Void builder
    public CustomerModel(){}
}
