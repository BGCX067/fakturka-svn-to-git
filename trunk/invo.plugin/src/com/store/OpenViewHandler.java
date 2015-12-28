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

public class OpenViewHandler extends AbstractHandler {

	
	@Override
public Object execute(ExecutionEvent event) throws ExecutionException {
	    IEditorInput input = new IEditorInput() {
            
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
        };
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
