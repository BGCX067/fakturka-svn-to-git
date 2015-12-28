package com.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Invoice Item Model
 * 
 * @author Jacek
 *
 */
@Entity
@Table(name="invoice_item")
public class InvoiceItem {

    private Long id;
    
    private Product product;
    
    private Integer quantity=1;
    
    /**
     * Get Invoice Item Id 
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { 
    	return id; 
	}

    /**
     * Set Invoice Item Id
     * @param id
     */
    public void setId(Long id) { 
    	this.id = id; 
    }
    
    /**
     * Get Invoide Product
     * @return
     */
    @OneToOne 
    @JoinColumn(name="product_id") 
    public Product getProduct() {
		return product;
	}
    
    /**
     * Set Invoice Product
     * @param product
     */
    public void setProduct(Product product) {
		this.product = product;
	}
    
    /**
     * Get Invoice Item Quantity
     * @return
     */
    @Column(nullable = false, name = "quantity")
    public Integer getQuantity() {
		return quantity;
	}
    
    /**
     * Set Invoice Item Quantity
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
