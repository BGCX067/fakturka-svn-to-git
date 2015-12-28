package com.store.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.store.Activator;
import com.store.ICommandIds;
import com.store.PresentationModel;
import com.store.editors.CustomerEditor;
import com.store.editors.CustomerEditorInput;
import com.store.model.Customer;

/**
 * Customer View Panel
 * 
 * @author Jacek
 *
 */
public class CustomerViewPanel extends Composite {

    private TableViewer tableViewer = null;
    private Composite buttonsPanel = null;
    private Button addButton = null;
    private ViewPart view;
    
    public CustomerViewPanel(ViewPart view, Composite parent, int style) {
        super(parent, style);
        this.view = view;
        initialize();
    }

    private void initialize() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.verticalSpan = 2;
        gridData.grabExcessVerticalSpace = true;
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        
        Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));
		TableColumnLayout layout = new TableColumnLayout();
		composite.setLayout(layout);
		
        tableViewer = new TableViewer(composite, SWT.SINGLE | SWT.H_SCROLL
    			| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        
        PresentationModel model = Activator.getDefault().getModel();
        
        tableViewer.getTable().setLayoutData(gridData);
        
        tableViewer.getTable().setHeaderVisible(true);
        tableViewer.getTable().setLinesVisible(true);
        
        TableViewerColumn tableViewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
        TableColumn tblclmnFirst = tableViewerColumn.getColumn();
		layout.setColumnData(tblclmnFirst, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnFirst.setText("Nazwisko");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnLast = tableViewerColumn_1.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnLast, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnLast.setText("Ulica");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnTitle = tableViewerColumn_2.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnTitle, new ColumnWeightData(4,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnTitle.setText("Kod pocztowy");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnEmail = tableViewerColumn_3.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnEmail, new ColumnWeightData(6,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnEmail.setText("Miasto");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(
				tableViewer, SWT.NONE);
		TableColumn tblclmnEmail4 = tableViewerColumn_4.getColumn();
		// Specify width using weights
		layout.setColumnData(tblclmnEmail4, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnEmail4.setText("Nip");
		
		tableViewer.setContentProvider( new IStructuredContentProvider() {
			
			@Override
			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			}
			
			@Override
			public void dispose() {}
			
			@Override
			public Object[] getElements(Object input) {
				PresentationModel pm = (PresentationModel) input;
				return pm.getCustomers().toArray();
			}
		});
		
		tableViewer.setLabelProvider(new ITableLabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener arg0) {
			}
			
			@Override
			public boolean isLabelProperty(Object arg0, String arg1) {
				return false;
			}
			
			@Override
			public void dispose() {
			}
			
			@Override
			public void addListener(ILabelProviderListener arg0) {
			}
			
			@Override
			public String getColumnText(Object value, int col) {
				Customer c = (Customer) value;
				String label = null;
				switch (col) {
				case 0:
					label = c.getName();
				break;
				case 1:
					label = c.getStreet();
				break;
				case 2: 
					label = c.getZipCode();
				break;
				case 3:
					label = c.getCity();
				break;
				case 4:
					label = c.getNip();
				}
				
				return label;
			}
			
			@Override
			public Image getColumnImage(Object arg0, int arg1) {
				return null;
			}
		});
		
		tableViewer.setInput(model);
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent arg0) {
				IStructuredSelection sel = (IStructuredSelection) tableViewer.getSelection();
				if (!sel.isEmpty()) {
					Customer selCust = (Customer) sel.getFirstElement();
					   IEditorInput input = new CustomerEditorInput(selCust);
					CustomerEditor part;
					try {
						part = (CustomerEditor) view.getViewSite().getWorkbenchWindow().getActivePage().openEditor(input, CustomerEditor.ID);						
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
        this.setLayout(gridLayout);
        createButtonsPanel();
        setSize(new Point(300, 200));
    }

    /**
     * This method initializes buttonsPanel	
     *
     */
    private void createButtonsPanel() {
        GridData gridData1 = new GridData();
        gridData1.grabExcessVerticalSpace = true;
        gridData1.verticalAlignment = GridData.FILL;
        gridData1.horizontalAlignment = GridData.BEGINNING;
        buttonsPanel = new Composite(this, SWT.NONE);
        buttonsPanel.setLayout(new GridLayout());
        buttonsPanel.setLayoutData(gridData1);
        addButton = new Button(buttonsPanel, SWT.NONE);
        addButton.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {				 
				IHandlerService handlerService = (IHandlerService) view.getSite().getService(IHandlerService.class);				
				try {
					handlerService.executeCommand(ICommandIds.CMD_ADD_CUSTOMER, null);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
					
			}		
		});
        addButton.setText("Nowy klient");
    }

	public void refresh() {
		tableViewer.refresh();
		
	}

}
