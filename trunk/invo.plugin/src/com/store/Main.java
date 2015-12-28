package com.store;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperPrint;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.ReportViewer;
import com.jasperassistant.designer.viewer.ViewerComposite;
import com.store.dao.CustomerDao;
import com.store.model.Customer;

public class Main {

    public Main() {
        // TODO Auto-generated constructor stub
    }
	
	/**
	 * Launch the application.
	 *
	 * @param args
	 */
    public static void main(String[] args) {
        /*
        Display display = new Display ();
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());
        shell.setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,true));
        shell.setText("Fakturka");
        ViewerComposite viewerComposite;
        
        viewerComposite = new ViewerComposite(shell,SWT.BORDER);
        viewerComposite.setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,true));
        
        
        shell.open ();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
      */
        new Main().foo();
     }

    public void foo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
        
        Customer customer = new Customer();
        
        CustomerDao customerDao = new CustomerDao();
        customerDao.setEntityManager(em);
        
        customerDao.persist(customer);
        
        List<Customer> customers = customerDao.getAll();
        System.out.println(customers);
     }
    
    
    
}