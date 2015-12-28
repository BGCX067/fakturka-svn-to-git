package com.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product Model
 * 
 * @author Jacek
 *
 */
@Entity
@Table(name="product")
public class Product {
    
	private Long id;
    private String name;
	private double price;
	private float vat;
	
	
	/**
	 * Get Product Id
	 * @return id
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { 
    	return id; 
	}

    /**
     * 
     * @param id
     */
    public void setId(Long id) { 
    	this.id = id; 
	}
    
    
    @Column(length = 50, nullable = false, name = "name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(length = 50, nullable = false, name = "price")
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    

    @Column(length = 50, nullable = false, name = "vat")
    public float getVat() {
        return vat;
    }
    
    public void setVat(float vat) {
        this.vat = vat;
    }
    
    
    
    
    
    
    
}
