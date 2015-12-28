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
import com.store.model.Product;

public class ProductEditorPanel extends Composite {

    private Label label1 = null;
    private Text nameField = null;
    private Label label2 = null;
    private Text priceField = null;
    private Label label3 = null;
    private Text vatField = null;

    
    DataBindingContext ctx = new DataBindingContext();
    
    AbstractEditor editor;
    
    public ProductEditorPanel(AbstractEditor editor, Composite parent, int style) {
        super(parent, style);
        
        this.editor = editor;
        initialize();
        bindFieldsToModel();
        addModifyListener();
        
    }

    protected void bindField(Control field, Object bean, String propertyName, IValidator validator, boolean decorate) {
        
        IObservableValue modelValue = PojoProperties.value(propertyName).observe(bean);
        
        IObservableValue widgetValue = WidgetProperties.text(SWT.Modify).observe(field);
        UpdateValueStrategy strategy = null;
        
        if (validator != null) {
			strategy = new UpdateValueStrategy();
			strategy.setBeforeSetValidator(validator);
        }
		Binding bindValue = ctx.bindValue(widgetValue, modelValue, strategy, null);
		// Add some decorations
		if (decorate) {
			ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
		}
    }
    
    private void bindFieldsToModel() {
    	  ProductEditorInput input = (ProductEditorInput) editor.getEditorInput();
    	  Product product = input.getProduct();
    	  
    	  bindField(nameField, product, "name", new NonEmptyValidator("Nazwa jest wymagana"), true);
    	  bindField(priceField, product, "price", null, true);    
          bindField(vatField, product, "vat", null, true);
   
    }
    
    ModifyListener modifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			editor.makeDirty();
		}
	};
    
    private void addModifyListener() {
    	nameField.addModifyListener(modifyListener);
    	priceField.addModifyListener(modifyListener);
    	vatField.addModifyListener(modifyListener);
    }
    
    private void initialize() {
  
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
        label1.setText("Nazwa");
        nameField = new Text(this, SWT.BORDER);
        nameField.setLayoutData(gridData);
        
        label2 = new Label(this, SWT.NONE);
        label2.setText("Cena");
        priceField = new Text(this, SWT.BORDER);
        priceField.setLayoutData(gridData1);
        
        label3 = new Label(this, SWT.NONE);
        label3.setText("Vat");
        vatField = new Text(this, SWT.BORDER);
        vatField.setLayoutData(gridData2);
        
        
        
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
