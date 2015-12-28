package com.store;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.store.editors.CustomerEditor;
import com.store.editors.CustomerEditorInput;
import com.store.editors.InvoiceEditor;
import com.store.editors.InvoiceEditorInput;
import com.store.model.Customer;
import com.store.model.Invoice;


/**
 * Create new Customer handler
 * 
 * @author Jacek
 *
 */
public class AddCustomerHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
			IEditorInput input = new CustomerEditorInput(new Customer());
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	       
			try {
				CustomerEditor part = (CustomerEditor) window.getActivePage().openEditor(input, CustomerEditor.ID);
				part.makeDirty();
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return null;
	}
	
	
}
