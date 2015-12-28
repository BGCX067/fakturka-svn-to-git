package com.store.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Invoice Model
 * 
 * @author Jacek
 *
 */
@Entity
@Table(name="invoice")
public class Invoice {
	
    private Long id;
    
    private String invoiceNo;
    private Date saleDate = new Date();
    
    private Customer customer;
    
    private List<InvoiceItem> items = new ArrayList<InvoiceItem>();
    
    /**
     * Get Invoice Id
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() { 
    	return id; 
	}

    /**
     * Set Invoice Id
     * @param id
     */
    public void setId(Long id) { 
    	this.id = id; 
    }
    
    /**
     * Get Customer object
     * @return Customer
     */
    @OneToOne 
    @JoinColumn(name="customer_id") 
    public Customer getCustomer() {
		return customer;
	}
    
    /**
     * Set Customer object
     * @param customer
     */
    public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    /**
     * Get Invoice Products
     * @return
     */
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="invoice_id")
    public List<InvoiceItem> getItems() {
		return items;
	}
    
    /**
     * Set Invoice Products
     * @param items
     */
    public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
    
    /**
     * Get Invoide Number
     * @return
     */
    public String getInvoiceNo() {
		return invoiceNo;
	}
    
    /**
     * Set Invoice Number
     * @param invoiceNo
     */
    public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
    
    /**
     * Get Invoice Sale Date
     * @return
     */
    public Date getSaleDate() {
		return saleDate;
	}
    
    /**
     * Set Invoice Sale Date
     * @param saleDate
     */
    public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}


}
