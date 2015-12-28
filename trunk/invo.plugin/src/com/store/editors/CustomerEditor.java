package com.store.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.store.Activator;
import com.store.model.Customer;

public class CustomerEditor extends AbstractEditor {
    public static final String ID = "invo.editor.customer";
	private CustomerEditorPanel panel;
    
    @Override
    public void doSave(IProgressMonitor monitor) {
    	if (panel.validate()) {
    		Customer cust = ((CustomerEditorInput) getEditorInput()).getCustomer();
    		
    		if (cust.getId() == null) {
    			Activator.getDefault().getModel().addCustomer(cust);
    		}
    		else {
    			Activator.getDefault().getModel().modifyCustomer(cust);
    		}
    		clear();
    	}
    	monitor.setCanceled(true);
    }


    @Override
    public void createPartControl(Composite parent) {
        
        panel = new CustomerEditorPanel(this, parent, SWT.NONE);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    
}
