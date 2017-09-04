/**
 * 
 */
package crossing.e1.taskintegrator.wizard;

import java.nio.file.Paths;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;

import crossing.e1.configurator.Constants;
import crossing.e1.taskintegrator.models.ModelAdvancedMode;
import crossing.e1.taskintegrator.widgets.CompositeChoiceForModeOfWizard;


/**
 * @author rajiv
 *
 */
public class PageForTaskIntegratorWizard extends WizardPage {
	private Composite compositeChoiceForModeOfWizard = null;
	
	/**
	 * Create the wizard.
	 */
	public PageForTaskIntegratorWizard(String name, String title, String description) {
		super(name);
		setTitle(title);
		setDescription(description);
		this.setPageComplete(false);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
				
		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
			
		switch(this.getName()){
			case Constants.PAGE_NAME_FOR_MODE_OF_WIZARD:
				compositeChoiceForModeOfWizard = new CompositeChoiceForModeOfWizard(container, SWT.NONE);
				break;
			case Constants.PAGE_NAME_FOR_CLAFER_FILE_CREATION:
				break;
			case Constants.PAGE_NAME_FOR_XSL_FILE_CREATION:
				break;
			case Constants.PAGE_NAME_FOR_HIGH_LEVEL_QUESTIONS:
				break;
		}
			
		
	}
	
	private void setDataForAdvancedMode(Composite compositeChoiceForModeOfWizard){
		// Check if this call is from the initial call to the getNextPage.
		if(compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_LIBRARY_LOCATION_OF_THE_TASK)!=null){
			TaskIntegrationWizard wizard = (TaskIntegrationWizard) this.getWizard();		
			wizard.setObjectForDataInNonGuidedMode(
				new ModelAdvancedMode(
					compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_NAME_OF_THE_TASK).toString(), 
					Paths.get(compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_LIBRARY_LOCATION_OF_THE_TASK).toString()), 
					Paths.get(compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_LOCATION_OF_CLAFER_FILE).toString()), 
					Paths.get(compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_LOCATION_OF_XSL_FILE).toString()), 
					Paths.get(compositeChoiceForModeOfWizard.getData(Constants.WIDGET_DATA_LOCATION_OF_JSON_FILE).toString())
						)
					);
		}
		
	}

	@Override
	public IWizardPage getNextPage() {
		switch(this.getName()){
			case Constants.PAGE_NAME_FOR_MODE_OF_WIZARD:	
				//TODO move this to performFinish().
				setDataForAdvancedMode(compositeChoiceForModeOfWizard);
				break;
			case Constants.PAGE_NAME_FOR_CLAFER_FILE_CREATION:
				break;
			case Constants.PAGE_NAME_FOR_XSL_FILE_CREATION:
				break;
			case Constants.PAGE_NAME_FOR_HIGH_LEVEL_QUESTIONS:
				break;
		}
		return super.getNextPage();
	}
	
	
}
