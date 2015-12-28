package com.store.editors;

import java.io.ObjectInputStream.GetField;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.store.editors.validator.NonEmptyValidator;
import com.store.model.Customer;

public class CustomerEditorPanel extends Composite {

    private Label label1 = null;
    private Text nameField = null;
    private Label label2 = null;
    private Text streetField = null;
    private Label label3 = null;
    private Text zipCodeField = null;
    private Label label4 = null;
    private Label label5 = null;
    private Text cityField = null;
    private Text nipField = null;
    
    DataBindingContext ctx = new DataBindingContext();
    
    AbstractEditor editor;
    
    public CustomerEditorPanel(AbstractEditor editor, Composite parent, int style) {
        super(parent, style);
        
        this.editor = editor;
        initialize();
        bindFieldsToModel();
        addModifyListener();
        
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
    	  CustomerEditorInput input = (CustomerEditorInput) editor.getEditorInput();
    	  Customer customer = input.getCustomer();
    	  
    	  bindField(nameField, customer, "name", new NonEmptyValidator("Nazwisko jest wymagane"), true);
    	  bindField(streetField, customer, "street", new NonEmptyValidator("Ulica jest wymagana"), true);    
          bindField(zipCodeField, customer, "zipCode", new NonEmptyValidator("Kod pocztowy jest wymagany"), true);
          bindField(cityField, customer, "city", new NonEmptyValidator("Miasto jest wymagany"), true);
          bindField(nipField, customer, "nip", new NonEmptyValidator("Nip jest wymagany"), true);     
    }
    
    ModifyListener modifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			editor.makeDirty();
		}
	};
    
    private void addModifyListener() {
    	nameField.addModifyListener(modifyListener);
    	streetField.addModifyListener(modifyListener);
    	zipCodeField.addModifyListener(modifyListener);
    	cityField.addModifyListener(modifyListener);
    	nipField.addModifyListener(modifyListener);
    }
    
    private void initialize() {
        GridData gridData4 = new GridData();
        gridData4.horizontalAlignment = GridData.FILL;
        gridData4.verticalAlignment = GridData.CENTER;
        GridData gridData3 = new GridData();
        gridData3.horizontalAlignment = GridData.FILL;
        gridData3.verticalAlignment = GridData.CENTER;
        GridData gridData2 = new GridData();
        gridData2.horizontalAlignment = GridData.FILL;
        gridData2.verticalAlignment = GridData.CENTER;
        GridData gridData1 = new GridData();
        gridData1.horizontalAlignment = GridData.FILL;
        gridData1.verticalAlignment = GridData.CENTER;
        GridData gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.horizontalAlignment = GridData.FILL;
        label1 = new Label(this, SWT.NONE);
        label1.setText("Nazwisko");
        nameField = new Text(this, SWT.BORDER);
        nameField.setLayoutData(gridData);
        label2 = new Label(this, SWT.NONE);
        label2.setText("Ulica");
        streetField = new Text(this, SWT.BORDER);
        
        
        streetField.setLayoutData(gridData1);
        label3 = new Label(this, SWT.NONE);
        label3.setText("Kod pocztowy");
        zipCodeField = new Text(this, SWT.BORDER);
        zipCodeField.setLayoutData(gridData2);
        label4 = new Label(this, SWT.NONE);
        label4.setText("Miasto");
        cityField = new Text(this, SWT.BORDER);
        cityField.setLayoutData(gridData3);
        label5 = new Label(this, SWT.NONE);
        label5.setText("NIP");
        nipField = new Text(this, SWT.BORDER);
        nipField.setLayoutData(gridData4);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 10;
        this.setLayout(gridLayout);
        setSize(new Point(300, 200));
    }

    public boolean validate() {
    	boolean result = true;
    	for (Object o : ctx.getValidationStatusProviders()) {
    		ValidationStatusProvider vsp = (ValidationStatusProvider) o;
    		IObservableValue ss = vsp.getValidationStatus();
    		IStatus st = (IStatus) ss.getValue();
    		if (!st.isOK()) {
    			result = false;
    			break;
    		}
    	}
    	return result;
    }
}
