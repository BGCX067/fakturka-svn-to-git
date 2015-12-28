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
import com.store.model.Product;

public class ProductEditor extends AbstractEditor {
    public static final String ID = "invo.editor.product";
	private ProductEditorPanel panel;
    
    @Override
    public void doSave(IProgressMonitor monitor) {
    	if (panel.validate()) {
    		Product prod = ((ProductEditorInput) getEditorInput()).getProduct();
    		
    		if (prod.getId() == null) {
    			Activator.getDefault().getModel().addProduct(prod);
    		}
    		else {
    			Activator.getDefault().getModel().modifyProduct(prod);
    		}
    		clear();
    	}
    	monitor.setCanceled(true);
    }


    @Override
    public void createPartControl(Composite parent) {
        
        panel = new ProductEditorPanel(this, parent, SWT.NONE);
        panel.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    
}
