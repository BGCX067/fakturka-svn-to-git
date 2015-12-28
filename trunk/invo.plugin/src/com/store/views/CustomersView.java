package com.store.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class CustomersView extends ViewPart {

	public static final String ID = "invo.view.customers";
	CustomerViewPanel mainPanel;
	
	public void createPartControl(Composite parent) {
	    mainPanel = new CustomerViewPanel(this, parent, SWT.NONE);
	    mainPanel.setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	public void setFocus() {
	}

	public void refresh() {
		mainPanel.refresh();
	}
}
