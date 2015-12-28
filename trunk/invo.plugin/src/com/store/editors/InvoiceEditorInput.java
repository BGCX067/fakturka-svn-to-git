package com.store.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.store.model.Invoice;

public class InvoiceEditorInput implements IEditorInput {
	private Invoice invoice;
	
	public InvoiceEditorInput(Invoice product) {
		this.invoice = product;
	}
	
	public Invoice getInvoice() {
		return invoice;
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
        return "Faktura ";
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
    	if (obj instanceof InvoiceEditorInput) {
    	InvoiceEditorInput c = (InvoiceEditorInput) obj;    	
    	return isEqual (invoice.getId(), c.getInvoice().getId());
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
