package com.store.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.store.model.Customer;

public class CustomerEditorInput implements IEditorInput {
	private Customer customer;
	
	public CustomerEditorInput(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
    @Override
    public Object getAdapter(Class arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getToolTipText() {
        // TODO Auto-generated method stub
        return "Tiolle";
    }
    
    @Override
    public IPersistableElement getPersistable() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Klient ";
    }
    
    @Override
    public ImageDescriptor getImageDescriptor() {
    	return ImageDescriptor.getMissingImageDescriptor();
    }
    
    @Override
    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj instanceof CustomerEditorInput) {
    	CustomerEditorInput c = (CustomerEditorInput) obj;    	
    	return isEqual (customer.getId(), c.getCustomer().getId());
    	}
    	return false;
    }
    
    static boolean isEqual(Object o1, Object o2) {
    	if (o1 == null && o2 == null) {
    		return false;
    	}
    	else if (o1 != null) {
    		return o1.equals(o2);
    	}
    	else {
    		return o2.equals(o1);
    	}
    }
}
