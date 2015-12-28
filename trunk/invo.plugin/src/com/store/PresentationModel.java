package com.store;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.eclipse.core.runtime.internal.adaptor.ContextFinder;

import com.store.dao.CustomerDao;
import com.store.model.Customer;
import com.store.model.Invoice;
import com.store.model.InvoiceItem;
import com.store.views.CustomersView;

import com.store.dao.ProductDao;
import com.store.model.Product;
import com.store.views.ProductsView;


public class PresentationModel {
	
	private CustomerDao customerDao;
	private ProductDao productDao;
	
	public PresentationModel() {
	}
	
	public List<Customer> getCustomers() {
		return customerDao.getAll();
	}
	
	public void addCustomer(Customer customer) {
		customerDao.persist(customer);
		refreshCustomersView();
	}
	
	public void modifyCustomer(Customer customer) {
		customerDao.persist(customer);
		refreshCustomersView();
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	
	private void refreshCustomersView() {
		
		CustomersView view = (CustomersView) 
				Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(CustomersView.ID);
		
		if (view != null) {
			view.refresh();
		}
	}
	
	/**
	 * Products functions
	 * 
	 */
	public List<Product> getProducts() {
		return productDao.getAll();
	}
	
	public void addProduct(Product product) {
		productDao.persist(product);
		refreshProductsView();
	}
	
	public void modifyProduct(Product product) {
		productDao.persist(product);
		refreshProductsView();
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	
	private void refreshProductsView() {
		
		ProductsView view = (ProductsView) 
				Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ProductsView.ID);
		
		if (view != null) {
			view.refresh();
		}
	}	
	
	
	public JasperPrint generateReport(Invoice invoice) {
		
		JasperPrint myJPrint = null;
	
		try {
		JasperReport jasperReport = null;
		
		InputStream is = 
				PresentationModel.class.getClassLoader().getResourceAsStream("/com/store/Faktura_subreport.jasper");
		
		JasperReport jasperSubReport = (JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(is);
		
		
		jasperReport = (JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(
				PresentationModel.class.getClassLoader().getResourceAsStream("/com/store/Faktura.jasper"));
		 
		//Passing parameters to the report
		Map parameters = new HashMap();
		parameters.put("SUBREPORT", jasperSubReport);
		 
		//Filling the report with data from
		//the database based on the parameters passed.
		List<Invoice> list = Arrays.asList(invoice);
		
		myJPrint = JasperFillManager.fillReport( jasperReport, parameters, new JRBeanCollectionDataSource(list) );
		 
		}catch(JRException jrExp){
		jrExp.printStackTrace();
		}catch (Exception ee){
			ee.printStackTrace();
		}
		return myJPrint;
		}
	

	public static Invoice getDummyInvoice() {
		
		Customer c = new Customer();
		c.setName("Franek kimono");
		c.setCity("Kraków");
		c.setNip("34323452454234");
		c.setStreet("B³otna");
		c.setZipCode("34-555");
		
		Invoice invoice = new Invoice();
		invoice.setCustomer(c);
		invoice.setInvoiceNo("FV 001");
		invoice.setSaleDate(new Date());
		
		Product p = new Product();
		p.setName("Wielkie czarne dildo");
		p.setPrice(500.0);
		p.setVat(23);
		
		Product p2 = new Product();
		p2.setName("Dmuchana lala, model Pamela 2000");
		p2.setPrice(120.0);
		p2.setVat(23);
		
		InvoiceItem item = new InvoiceItem();
		item.setProduct(p);
		item.setQuantity(1);
		
		InvoiceItem item2 = new InvoiceItem();
		item2.setProduct(p2);
		item2.setQuantity(1);
		
		invoice.getItems().add(item);
		invoice.getItems().add(item2);
		
		return invoice;
	}
}
