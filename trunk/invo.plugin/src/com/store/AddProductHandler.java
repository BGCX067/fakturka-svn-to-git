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

import com.store.editors.ProductEditor;
import com.store.editors.ProductEditorInput;
import com.store.model.Customer;
import com.store.model.Product;

public class AddProductHandler extends AbstractHandler {

	
	@Override
public Object execute(ExecutionEvent event) throws ExecutionException {
	    IEditorInput input = new ProductEditorInput(new Product());
       IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
       
		try {
			ProductEditor part = (ProductEditor) window.getActivePage().openEditor(input, ProductEditor.ID);
			part.makeDirty();
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return null;
}
}
