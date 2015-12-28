package com.store.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.jasperassistant.designer.viewer.ReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;

public class ReportView extends ViewPart {

	public static final String ID = "invo.view.report";

	// Using the SWTJasperViewer
	private ViewerComposite viewerComposite;

	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);

		viewerComposite = new ViewerComposite(container, SWT.BORDER);
		viewerComposite.setLayoutData(new GridData(GridData.FILL,
				GridData.FILL, true, true));

	}

	public void setFocus() {

	}

	public ReportViewer getReportViewer() {
		return (ReportViewer) viewerComposite.getReportViewer();
	}

	public Composite getReportViewerComposite() {
		return viewerComposite;
	}
}