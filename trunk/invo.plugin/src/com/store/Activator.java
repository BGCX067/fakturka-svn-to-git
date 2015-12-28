package com.store;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.hibernate.ejb.Ejb3Configuration;
import org.osgi.framework.BundleContext;

import com.store.dao.CustomerDao;
import com.store.dao.ProductDao;
import com.store.model.Customer;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "invo.plugin"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private PresentationModel model;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	public PresentationModel getModel() {
		return model;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
       
        CustomerDao customerDao = new CustomerDao();
        customerDao.setEntityManager(em);
        ProductDao productDao = new ProductDao();
        productDao.setEntityManager(em);
        
        model = new PresentationModel();
        model.setCustomerDao(customerDao);
        model.setProductDao(productDao);
        
        //TODO add missing dao 
 
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
