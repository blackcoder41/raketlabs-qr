package com.raketlabs.qr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "qr_form")
public class QRForm {
    
    @Id
    @GeneratedValue(generator = "user_roles_generator")
    @SequenceGenerator(name = "user_roles_generator", sequenceName = "user_roles_sequence", initialValue = 1000)
    private Long id;
    
    @Column(name = "acquirer_id")
    private String acquirerId;
    
    @Column(name = "account_name")
    private String accountName;
    
    @Column(name = "account_number")
    private String accountNumber;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @Column(name = "city")
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcquirerId() {
        return acquirerId;
    }

    public void setAcquirerId(String acquirerId) {
        this.acquirerId = acquirerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }    
    
}
