package com.store.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer Model
 * 
 * @author Jacek
 *
 */
@Entity
@Table(name="customer")
public class Customer {
	
    private Long id;
    private String name;
    private String street;
    private String zipCode;
    private String city;
    private String nip;
    
	/**
	 * Get Customer Id
	 * @return id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { 
    	return id; 
	}

    /**
     * Set Customer Id
     * @param id
     */
    public void setId(Long id) { 
    	this.id = id; 
    }
    
    /**
     * Get Customer Name
     * @return
     */
    @Column(length = 50, nullable = false, name = "name")
    public String getName() {
        return name;
    }
    
    /**
     * Set Customer Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get Customer Street
     * @return
     */
    @Column(length = 50, nullable = false, name = "street")
    public String getStreet() {
        return street;
    }
    
    /**
     * Set Customer Street
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * Get Customer Zip Code
     * @return
     */
    @Column(length = 50, nullable = false, name = "zipCode")
    public String getZipCode() {
        return zipCode;
    }
    
    /**
     * Set Customer Zip Code
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
   
    /**
     * Get Customer City
     * @return
     */
    @Column(length = 50, nullable = false, name = "city")
    public String getCity() {
        return city;
    }
    
    /**
     * Set Customer City
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get Customer NIP
     * @return
     */
    @Column(length = 50, nullable = false, name = "nip")
    public String getNip() {
        return nip;
    }
    
    /**
     * Set Customer NIP
     * @param nip
     */
    public void setNip(String nip) {
        this.nip = nip;
    }
           
}
