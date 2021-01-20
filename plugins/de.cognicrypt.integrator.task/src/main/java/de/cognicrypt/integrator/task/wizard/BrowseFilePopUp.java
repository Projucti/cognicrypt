package de.cognicrypt.integrator.task.wizard;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.cognicrypt.core.Constants;
import de.cognicrypt.integrator.task.widgets.*;


public class BrowseFilePopUp extends Dialog {
	PageForTaskIntegratorWizard pageForTaskIntegratorWizard;
	CompositeChoiceForModeOfWizard compositeChoiceForModeOfWizard;
	CompositeBrowseForFile compCryslTemplate;
	CompositeBrowseForFile compCryslTemplateAtInit;
	LinkedList<CompositeBrowseForFile> listOfCompCryslTemplate = new LinkedList<CompositeBrowseForFile>();
	Dimension windowSize;
	private int counterAddedIdentifiert = 0;
	
	public BrowseFilePopUp(final Shell parentShell, PageForTaskIntegratorWizard pageForTaskIntegratorWizard, CompositeChoiceForModeOfWizard compositeChoiceForModeOfWizard) {
		super(parentShell);
		this.pageForTaskIntegratorWizard = pageForTaskIntegratorWizard;
		this.compositeChoiceForModeOfWizard = compositeChoiceForModeOfWizard;
		windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// get init siez for counter to continue autogenerated ids
		try {
			counterAddedIdentifiert = compositeChoiceForModeOfWizard.getIdCounter();
		} catch (Exception e) {
			// not warning because this only means there are no identifieres at the moment;
		}
	}
	
	protected Control createDialogArea(Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(1, false));

		//v1
		/*final Label reminder = new Label(container, SWT.NONE);
		reminder.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		reminder.setText("Please make sure your identifier is unique!");

		final Composite compositeBrowse = new Composite(container, SWT.NONE);
		compositeBrowse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		compositeBrowse.setLayout(new GridLayout(1, false));
		
		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		compCryslTemplate = new CompositeBrowseForFile(composite, SWT.NONE,
				Constants.WIDGET_DATA_LOCATION_OF_CRYSLTEMPLATE_FILE, new String[] { "*.java" },
				"Select crysl template file that contains the code details", pageForTaskIntegratorWizard);
		compCryslTemplate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		listOfCompCryslTemplate.add(compCryslTemplate);*/
		
		// v2
		final Label reminder = new Label(container, SWT.NONE);
		reminder.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		reminder.setText("Please make sure your identifier is unique!");
		
		final Composite compositeAllModes = new Composite(container, SWT.NONE);
		compositeAllModes.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		compositeAllModes.setVisible(true);
		compositeAllModes.setLayout(new GridLayout(1, false));
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(compositeAllModes,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinWidth((int) (/*windowSize.getWidth()*0.27)*/ 950));
		scrolledComposite.setMinHeight((int) (/*windowSize.getHeight()*0.1)*/150));

		final Composite composite = new Composite(scrolledComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		scrolledComposite.setContent(composite);

		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		composite_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		final Composite composite_3 = new Composite(composite, SWT.NONE);
		composite_3.setLayout(new GridLayout());
		composite_3.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		compCryslTemplateAtInit = new CompositeBrowseForFile(composite_2, SWT.NONE,
				Constants.WIDGET_DATA_LOCATION_OF_CRYSLTEMPLATE_FILE, new String[] { "*.java" },
				"Select crysl template file that contains the code details", pageForTaskIntegratorWizard, compositeChoiceForModeOfWizard);
		compCryslTemplateAtInit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		compCryslTemplateAtInit.setTxtBoxOption("File " + counterAddedIdentifiert);
		counterAddedIdentifiert++;
		
		listOfCompCryslTemplate.add(compCryslTemplateAtInit);
		
		final Button btnAdd = new Button(composite_3, SWT.NONE);
		btnAdd.setText("Add");

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				compCryslTemplate = new CompositeBrowseForFile(composite_2, SWT.NONE,
						Constants.WIDGET_DATA_LOCATION_OF_CRYSLTEMPLATE_FILE, new String[] { "*.java" },
						"Select crysl template file that contains the code details", pageForTaskIntegratorWizard, compositeChoiceForModeOfWizard);
				compCryslTemplate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
				compCryslTemplate.setTxtBoxOption("File " + counterAddedIdentifiert);
				counterAddedIdentifiert++;

				listOfCompCryslTemplate.add(compCryslTemplate);

				scrolledComposite.layout(true, true);
				scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		});
		
		return container;
	}
	
	@Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Selection dialog");
    }

    @Override
    protected Point getInitialSize() {
        return new Point((int) (950/*windowSize.getWidth()*0.3*/), (int) (300/*windowSize.getHeight()*0.2*/)); // 1000, 350
    }
    
    LinkedList<String> listOfIdentifier = new LinkedList<String>();
    
    @Override 
    protected void okPressed() {
		// check if id was used already and is in the template list 
    	boolean warningIdAlreadyUsed = false;
    	try {
	    	ArrayList<String> listOfIdentifierTemplateList = compositeChoiceForModeOfWizard.getIdentifiers();
	    	for(int i = 0; i < listOfCompCryslTemplate.size(); i++) {
	    		for(int k = 0; k < listOfIdentifierTemplateList.size(); k++) {
		    		if(listOfCompCryslTemplate.get(i).getTxtBoxOption().equals(listOfIdentifierTemplateList.get(k))) {
		    			listOfCompCryslTemplate.remove(i); //check that it actually isn't added
		    			warningIdAlreadyUsed = true;
		    		}
	    		}
	    	}
    	}catch (Exception e) {}

		// check if identifier is unique in terms of the add window
    	boolean warning = false;
		for(int i = 0; i < listOfCompCryslTemplate.size(); i++) {
			int identifierUnique = 0;
			for(int k = 0; k < listOfIdentifier.size(); k++) {
				if (listOfIdentifier.get(k).equals(listOfCompCryslTemplate.get(i).getTxtBoxOption())) {
					identifierUnique++;
					compositeChoiceForModeOfWizard.addTemplate(listOfCompCryslTemplate.get(i).getTxtBoxOption() + ":Copy(" + identifierUnique + ")", new File(listOfCompCryslTemplate.get(i).getText()));
				}
			}
			
			if(identifierUnique != 0)
				warning = true;
			else 
				compositeChoiceForModeOfWizard.addTemplate(listOfCompCryslTemplate.get(i).getTxtBoxOption(), new File(listOfCompCryslTemplate.get(i).getText()));
			
			identifierUnique = 0;
			listOfIdentifier.add(listOfCompCryslTemplate.get(i).getTxtBoxOption());
		}
		
		// add warnings
		if(warningIdAlreadyUsed) {
    		MessageDialog.openError(getShell(), "Warning", "Because one or more identifier you chose are already in use the chosen file or files could not be added!");
    		warningIdAlreadyUsed = false;
    	}
		
		if(warning)
			MessageDialog.openError(getShell(), "Warning", "You used the same identifier more than once therefore the identifier was changed!");
		
		compositeChoiceForModeOfWizard.setIdCounter(counterAddedIdentifiert);
		super.okPressed();
    }
}
