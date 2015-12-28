package com.store;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.store.editors.ProductEditor;
import com.store.editors.ProductEditorInput;
import com.store.model.Product;
import com.store.views.CustomersView;
import com.store.views.ProductsView;
import com.store.views.ReportView;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "invo.perspective";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		
		IFolderLayout left =
                layout.createFolder("left", IPageLayout.LEFT, (float) 0.5, editorArea);
		
		left.addView(CustomersView.ID);
        left.addView(ProductsView.ID);
       	
                
        layout.addView(ReportView.ID,  IPageLayout.BOTTOM, 0.5f, editorArea);
            
		//IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 0.5f, editorArea);
		//folder.addPlaceholder(View.ID + ":*");
		//folder.addView(View.ID);
		
		//layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
