package com.raketlabs.qr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * BSP-Supervised Financial Institution
 * 
 * @author Oral Hernandez
 *
 */

@Entity
@Table(name = "institution")
public class Institution {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "user_roles_generator")
    @SequenceGenerator(name = "user_roles_generator", sequenceName = "user_roles_sequence", initialValue = 1000)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "code")
    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
