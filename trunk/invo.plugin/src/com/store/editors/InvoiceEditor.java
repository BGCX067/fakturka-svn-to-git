package com.store.editors;

import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;

import com.store.Activator;
import com.store.editors.validator.NonEmptyValidator;
import com.store.model.Customer;
import com.store.model.Invoice;
import com.store.model.InvoiceItem;
import com.store.model.Product;
import com.store.views.ReportView;

public class InvoiceEditor extends AbstractEditor {
    public static final String ID = "invo.editor.invoice";
	
    private Group cstGroup = null;
    private Composite invoiceData = null;
    private TableViewer itemsTable = null;
    private Composite buttons = null;
    private Composite buttons1 = null;
    private Button addButton = null;
    private Button printButton = null;
    private Button deleteButton = null;
    private Composite topPanel = null;
    private Label label1 = null;
    private Text invoiceNoField = null;
    private Label label2 = null;
    private Text date = null;
    private Label label3 = null;
    private Combo nameCombo = null;
    private Label label4 = null;
    private Label nipField = null;
    private Label label5 = null;
    private ComboViewer comboViewer = null;
    private Label addressLabel = null;
    
    private Composite parent;
    private DataBindingContext ctx = new DataBindingContext();
    
    private Invoice invoice;
    
    @Override
    protected void setInput(IEditorInput input) {
    	super.setInput(input);
    	this.invoice = ((InvoiceEditorInput) input).getInvoice();
    }
    
    @Override
    public void doSave(IProgressMonitor monitor) {
    	//if (panel.validate()) {
    		//Customer cust = ((CustomerEditorInput) getEditorInput()).getCustomer();
    		
    	//	if (cust.getId() == null) {
    		//	Activator.getDefault().getModel().addCustomer(cust);
    	//	}
    		//clear();
    	//}
    	//monitor.setCanceled(true);
    }

  protected void bindField(Control field, Object bean, String propertyName, IValidator validator, boolean decorate) {
        
        IObservableValue modelValue = PojoProperties.value(propertyName).observe(bean);
        
        IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(field);

		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setBeforeSetValidator(validator);
		
		Binding bindValue = ctx.bindValue(widgetValue, modelValue, strategy, null);
		// Add some decorations
		if (decorate) {
			ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
		}
    }
    
    private void bindFieldsToModel() {
   
    	bindField(invoiceNoField, invoice, "invoiceNo", new NonEmptyValidator("Numer faktury jest wymagany"), true);
    	bindField(date, invoice, "saleDate", null, false);
  }
    

    @Override
    public void createPartControl(Composite parent) {
        this.parent = parent;
        initialize();
        
        bindFieldsToModel();
    }
    
    ItemsContentProvider itemsContentProvider = new ItemsContentProvider();
    
    class ItemsContentProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {}

		@Override
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {}

		@Override
		public Object[] getElements(Object arg0) {
			return invoice.getItems().toArray();
		}
   
    	
    }
    
    class ProductsListProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {}

		@Override
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {}

		@Override
		public Object[] getElements(Object arg0) {
			return Activator.getDefault().getModel().getProducts().toArray();
		}
   
    	
    }
    
    
    class CustomersListProvider implements IStructuredContentProvider {

		@Override
		public void dispose() {}

		@Override
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {}

		@Override
		public Object[] getElements(Object arg0) {
			return Activator.getDefault().getModel().getCustomers().toArray();
		}
   
    	
    }
    
    ItemsLabelProvider itemsLabelProvider = new ItemsLabelProvider();
    
    class ItemsLabelProvider implements ITableLabelProvider {
		
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
			InvoiceItem p = (InvoiceItem) value;
			String label = null;
			switch (col) {
			case 0:
				label = p.getProduct()!=null?p.getProduct().getName():"";
			break;
			case 1:
				label ="" + (p.getProduct()!=null?p.getProduct().getPrice():"");
			break;
			case 2: 
				label = "" + p.getQuantity();
			break;
			case 3:
				label ="" + (p.getProduct()!=null?p.getProduct().getVat():"");
			break;
			}
			
			return label;
		}
		
		@Override
		public Image getColumnImage(Object arg0, int arg1) {
			return null;
		}
	};
	
	
	class QuantityEditingSupport extends EditingSupport {

		private final TableViewer viewer;
	
		public QuantityEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			TextCellEditor textEditor = new TextCellEditor(viewer.getTable());
	        ((Text) textEditor.getControl()).addVerifyListener(
			
	         new VerifyListener() {
	                public void verifyText(VerifyEvent e) {
	                    e.doit = "0123456789".indexOf(e.text) >= 0;  
	                }
	            });
	        
			return textEditor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			InvoiceItem person = (InvoiceItem) element;
			return ""+person.getQuantity();
		}

		@Override
		protected void setValue(Object element, Object value) {
			InvoiceItem ii = (InvoiceItem) element;
			ii.setQuantity( Integer.parseInt((String) value));
			
			viewer.refresh();
		}
	}
	
	
	
	class ProductEditingSupport extends EditingSupport {

		private final TableViewer viewer;
	
		public ProductEditingSupport(TableViewer viewer) {
			super(viewer);
			this.viewer = viewer;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			ComboBoxViewerCellEditor editor = new ComboBoxViewerCellEditor(viewer.getTable());
			editor.setContentProvider(new ProductsListProvider());
			editor.setInput(this);
			editor.setLabelProvider(new ILabelProvider() {
				
				@Override
				public void removeListener(ILabelProviderListener listener) {}
				
				@Override
				public boolean isLabelProperty(Object element, String property) {
					return false;
				}
				
				@Override
				public void dispose() {}
				
				@Override
				public void addListener(ILabelProviderListener listener) {}
				
				@Override
				public String getText(Object element) {
					Product p = (Product)element;
					return p.getName();
				}
				
				@Override
				public Image getImage(Object element) {
					return null;
				}
			});
			return editor;
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected Object getValue(Object element) {
			InvoiceItem person = (InvoiceItem) element;
			return person.getProduct();
		}

		@Override
		protected void setValue(Object element, Object value) {
			InvoiceItem ii = (InvoiceItem) element;
			ii.setProduct((Product)value);
			
			viewer.refresh();
		}
	}
    
    private void initialize() {
        createTopPanel();
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = GridData.FILL;
        
        Composite composite = new Composite(parent, SWT.NONE);
     		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
     				1));
     		TableColumnLayout layout = new TableColumnLayout();
     		composite.setLayout(layout);
     		
        itemsTable = new TableViewer(composite,  SWT.SINGLE | SWT.H_SCROLL
    			| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        itemsTable.getTable().setLinesVisible(true);
        itemsTable.getTable().setHeaderVisible(true);
        itemsTable.getTable().setLayoutData(gridData);
           
        
        TableViewerColumn tableViewerColumn = new TableViewerColumn(
        		itemsTable, SWT.NONE);
        TableColumn tblclmnFirst = tableViewerColumn.getColumn();
		layout.setColumnData(tblclmnFirst, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnFirst.setText("Nazwa produktu");
		tableViewerColumn.setEditingSupport( new ProductEditingSupport(itemsTable));
		
		
		TableViewerColumn tableViewerColumn1 = new TableViewerColumn(
        		itemsTable, SWT.NONE);
        TableColumn tblclmnSec = tableViewerColumn1.getColumn();
		layout.setColumnData(tblclmnSec, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnSec.setText("Cena");

		TableViewerColumn tableViewerColumn2 = new TableViewerColumn(
        		itemsTable, SWT.NONE);
        TableColumn tblclmnThr = tableViewerColumn2.getColumn();
		layout.setColumnData(tblclmnThr, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnThr.setText("Iloœæ");
		
		tableViewerColumn2.setEditingSupport(new QuantityEditingSupport(itemsTable));
		
		TableViewerColumn tableViewerColumn3 = new TableViewerColumn(
        		itemsTable, SWT.NONE);
        TableColumn tblclmnFor = tableViewerColumn3.getColumn();
		layout.setColumnData(tblclmnFor, new ColumnWeightData(2,
				ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnFor.setText("VAT");

		itemsTable.setContentProvider( itemsContentProvider);
		itemsTable.setLabelProvider(itemsLabelProvider);
		
		itemsTable.setInput(this);
		
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        parent.setLayout(gridLayout);
        
        createButtons();
        createButtons1();
    }

    /**
     * This method initializes cstGroup	
     *
     */
    private void createCstGroup() {
        GridData gridData11 = new GridData();
        gridData11.horizontalSpan = 3;
        gridData11.horizontalAlignment = GridData.FILL;
        gridData11.verticalAlignment = GridData.CENTER;
        gridData11.grabExcessHorizontalSpace = true;
        GridLayout gridLayout3 = new GridLayout();
        gridLayout3.numColumns = 4;
        GridData gridData7 = new GridData();
        gridData7.horizontalAlignment = GridData.FILL;
        gridData7.grabExcessHorizontalSpace = true;
        gridData7.verticalAlignment = GridData.FILL;
        cstGroup = new Group(topPanel, SWT.NONE);
        cstGroup.setLayoutData(gridData7);
        cstGroup.setLayout(gridLayout3);
        cstGroup.setText("Dane klienta");
        label3 = new Label(cstGroup, SWT.NONE);
        label3.setText("Nazwisko:");
        createNameCombo();
        label4 = new Label(cstGroup, SWT.NONE);
        label4.setText("NIP:");
        nipField = new Label(cstGroup, SWT.NONE);
        nipField.setText("111-111-1111-1111");
        label5 = new Label(cstGroup, SWT.NONE);
        label5.setText("Adres:");
        addressLabel = new Label(cstGroup, SWT.NONE);
        addressLabel.setText("Label");
        addressLabel.setLayoutData(gridData11);
    }

    /**
     * This method initializes invoiceData	
     *
     */
    private void createInvoiceData() {
        GridData gridData10 = new GridData();
        gridData10.horizontalAlignment = GridData.FILL;
        gridData10.verticalAlignment = GridData.CENTER;
        GridData gridData9 = new GridData();
        gridData9.horizontalAlignment = GridData.FILL;
        gridData9.grabExcessHorizontalSpace = true;
        gridData9.verticalAlignment = GridData.CENTER;
        GridLayout gridLayout2 = new GridLayout();
        gridLayout2.numColumns = 2;
        GridData gridData6 = new GridData();
        gridData6.horizontalAlignment = GridData.FILL;
        gridData6.grabExcessHorizontalSpace = true;
        gridData6.verticalAlignment = GridData.FILL;
        invoiceData = new Composite(topPanel, SWT.NONE);
        invoiceData.setLayoutData(gridData6);
        invoiceData.setLayout(gridLayout2);
        label1 = new Label(invoiceData, SWT.NONE);
        label1.setText("Numer faktury:");
        invoiceNoField = new Text(invoiceData, SWT.BORDER);
        invoiceNoField.setLayoutData(gridData9);
        label2 = new Label(invoiceData, SWT.NONE);
        label2.setText("Data sprzeda¿y:");
        date = new Text(invoiceData, SWT.BORDER);
        date.setEditable(false);
        date.setLayoutData(gridData10);
    }

    /**
     * This method initializes buttons	
     *
     */
    private void createButtons() {
        GridData gridData5 = new GridData();
        gridData5.horizontalAlignment = GridData.FILL;
        gridData5.verticalAlignment = GridData.CENTER;
        GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.FILL;
        gridData4.verticalAlignment = GridData.CENTER;
        GridData gridData3 = new GridData();
        gridData3.grabExcessVerticalSpace = false;
        gridData3.verticalAlignment = GridData.FILL;
        gridData3.horizontalAlignment = GridData.BEGINNING;
        buttons = new Composite(parent, SWT.NONE);
        buttons.setLayout(new GridLayout());
        buttons.setLayoutData(gridData3);
        addButton = new Button(buttons, SWT.NONE);
        addButton.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {				 
				addNewInoiceItem();
			}		
		});
        
        addButton.setText("Dodaj");
        addButton.setLayoutData(gridData4);
        deleteButton = new Button(buttons, SWT.NONE);
        deleteButton.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {				 
				deleteSelectedItem();
			}		
		});
        
        deleteButton.setText("Usuñ");
        deleteButton.setLayoutData(gridData5);
    }

    private void addNewInoiceItem() {
    	invoice.getItems().add(new InvoiceItem());
    	itemsTable.refresh();
    }
    
    private void deleteSelectedItem() {
    	
    }
    
    /**
     * This method initializes buttons1	
     *
     */
    private void createButtons1() {
        GridData gridData2 = new GridData();
        gridData2.horizontalAlignment = GridData.CENTER;
        gridData2.grabExcessHorizontalSpace = true;
        gridData2.verticalAlignment = GridData.CENTER;
        GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.FILL;
        gridData1.horizontalSpan = 2;
        gridData1.verticalAlignment = GridData.CENTER;
        buttons1 = new Composite(parent, SWT.NONE);
        buttons1.setLayout(new GridLayout());
        buttons1.setLayoutData(gridData1);
        printButton = new Button(buttons1, SWT.NONE);
        printButton.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {				 

				Invoice invoice = ((InvoiceEditorInput)getEditorInput()).getInvoice();				
				JasperPrint print = Activator.getDefault().getModel().generateReport(invoice);
				
				try {
					ReportView rv = (ReportView) getSite().getWorkbenchWindow().getActivePage().
							showView(ReportView.ID);
					rv.getReportViewer().setDocument(print);
				
				} catch (PartInitException es) {
					// TODO Auto-generated catch block
					es.printStackTrace();
				}					
			}		
		});
        printButton.setText("Drukuj");
        printButton.setLayoutData(gridData2);
    }

    /**
     * This method initializes topPanel	
     *
     */
    private void createTopPanel() {
        GridLayout gridLayout1 = new GridLayout();
        gridLayout1.numColumns = 2;
        gridLayout1.makeColumnsEqualWidth = false;
        GridData gridData8 = new GridData();
        gridData8.horizontalAlignment = GridData.FILL;
        gridData8.horizontalSpan = 2;
        gridData8.verticalAlignment = GridData.FILL;
        topPanel = new Composite(parent, SWT.NONE);
        createCstGroup();
        topPanel.setLayout(gridLayout1);
        createInvoiceData();
        topPanel.setLayoutData(gridData8);
    }

    /**
     * This method initializes nameCombo	
     *
     */
    private void createNameCombo() {
        GridData gridData12 = new GridData();
        gridData12.grabExcessHorizontalSpace = true;
        gridData12.verticalAlignment = GridData.CENTER;
        gridData12.horizontalAlignment = GridData.FILL;
        nameCombo = new Combo(cstGroup, SWT.NONE);
        nameCombo.setLayoutData(gridData12);
        comboViewer = new ComboViewer(nameCombo);
        comboViewer.setContentProvider( new CustomersListProvider());
        comboViewer.setLabelProvider(new ILabelProvider() {
			
			@Override
			public void removeListener(ILabelProviderListener listener) {}
			
			@Override
			public boolean isLabelProperty(Object element, String property) {
				return false;
			}
			
			@Override
			public void dispose() {}
			
			@Override
			public void addListener(ILabelProviderListener listener) {}
			
			@Override
			public String getText(Object element) {
				Customer p = (Customer)element;
				return p.getName();
			}
			
			@Override
			public Image getImage(Object element) {
				return null;
			}
		});
        comboViewer.setInput(this);
        
        comboViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				Customer selCust = (Customer) ((IStructuredSelection)comboViewer.getSelection()).getFirstElement();
				if (selCust != null) {
					invoice.setCustomer(selCust);
					nipField.setText( selCust.getNip());
					addressLabel.setText( selCust.getStreet()+" " + selCust.getZipCode() +" "+selCust.getCity());
				}
			}
		});
    }

}
