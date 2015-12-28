package com.store.editors.validator;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

/**
 * Required fields Validator
 * 
 * @author Jacek
 *
 */
public class NonEmptyValidator implements IValidator {
	String message;
	
	public NonEmptyValidator(String message) {
	this.message = message;
	}
	@Override
	public IStatus validate(Object value) {
		if (value instanceof String) {
			String s = (String) value;
			return s.trim().length()>0?ValidationStatus.ok():ValidationStatus.error(message);
		}
		return null;
	}
	
	//jdbc:hsqldb:file:testdb", "sa", ""
}
