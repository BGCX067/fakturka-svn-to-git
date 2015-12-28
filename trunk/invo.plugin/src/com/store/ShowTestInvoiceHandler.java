package com.store;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.store.editors.CustomerEditorInput;
import com.store.editors.InvoiceEditor;
import com.store.editors.InvoiceEditorInput;
import com.store.model.Customer;
import com.store.model.Invoice;
import com.store.views.ReportView;

import org.eclipse.ui.IEditorInput;
import com.store.model.Invoice;
import com.store.editors.InvoiceEditor;
import com.store.editors.InvoiceEditorInput;

/**
 * Create new Invoice handler
 * 
 * @author Jacek
 *
 */
public class ShowTestInvoiceHandler extends AbstractHandler {

		
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		  	IEditorInput input = new InvoiceEditorInput(new Invoice());
		  	IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	       
			try {
				InvoiceEditor part = (InvoiceEditor) window.getActivePage().openEditor(input, InvoiceEditor.ID);
				part.makeDirty();
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
				
		return null;
	}
	
	
}