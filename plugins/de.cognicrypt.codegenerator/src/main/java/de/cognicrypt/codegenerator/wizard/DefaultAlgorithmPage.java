package de.cognicrypt.codegenerator.wizard;

import java.util.Map;

import org.clafer.instance.InstanceClafer;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.PlatformUI;

import de.cognicrypt.codegenerator.Constants;
import de.cognicrypt.codegenerator.featuremodel.clafer.InstanceGenerator;
import de.cognicrypt.codegenerator.tasks.Task;
import de.cognicrypt.codegenerator.utilities.Labels;


public class DefaultAlgorithmPage extends WizardPage implements Labels {

	private Composite control;
	private Group codePreviewPanel;
	private Task selectedTask;
	private Button defaultAlgorithmCheckBox;
	private Text code;
	private final InstanceGenerator instanceGenerator;
	private InstanceClafer value;

	public DefaultAlgorithmPage(final InstanceGenerator inst,final Task selectedTask) {
		super(Labels.DEFAULT_ALGORITHM_PAGE);
		setTitle("Best solution for task: " + selectedTask.getDescription());
		setDescription(Labels.DESCRIPTION_DEFAULT_ALGORITHM_PAGE);
		this.instanceGenerator = inst;
	}

	
	@Override
	public void createControl(final Composite parent) {
		Label algorithmClass;
		Label labelDefaultAlgorithm;
		this.control = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout(1, false);
		this.control.setLayout(layout);
		
		/** To display the Help view after clicking the help icon
		 * @param help_id_2 
		 *        This id refers to HelpContexts_1.xml
		 */
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.control, "de.cognicrypt.codegenerator.help_id_3");
		
		final Composite compositeControl = new Composite(this.control, SWT.NONE);
		compositeControl.setLayout(new GridLayout(2, false));
		labelDefaultAlgorithm = new Label(compositeControl, SWT.NONE);
		labelDefaultAlgorithm.setText(Labels.defaultAlgorithm);
		final Map<String, InstanceClafer> inst = this.instanceGenerator.getInstances();//Only the first Instance,which is the most secure one, will be displayed
		
		algorithmClass= new Label(compositeControl, SWT.NONE);
		String firstInstance = inst.keySet().toArray()[0].toString();
		algorithmClass.setText(firstInstance);
		setValue(DefaultAlgorithmPage.this.instanceGenerator.getInstances().get(firstInstance));
		setPageComplete(true);

		algorithmClass.setToolTipText("Default Algorithm combination");

		this.codePreviewPanel = new Group(this.control, SWT.NONE);
		this.codePreviewPanel.setText(Constants.CODE_PREVIEW);
		final Font boldFont = new Font(this.codePreviewPanel.getDisplay(), new FontData(Constants.ARIAL, 10, SWT.BOLD));
		this.codePreviewPanel.setFont(boldFont);
		setControl(this.control);
		
		this.code = new Text(this.codePreviewPanel, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		this.code.setLayoutData(new GridData(GridData.FILL_BOTH));
		this.code.setBounds(10, 20, 520, 146);
		this.code.setEditable(false);	
		new Label(control, SWT.NONE);
		
		code.setToolTipText("This is the preview of the code, that will be generated into your Java project");
		
		defaultAlgorithmCheckBox = new Button(control, SWT.CHECK);
		defaultAlgorithmCheckBox.setSelection(true);
		if(instanceGenerator.getNoOfInstances()==1){
			//if there is only one instance, then the user can generate the code only for the default algorithm combination. 
			//Thus, the combo box will be disabled which prevents the user from moving to the next page. 
			defaultAlgorithmCheckBox.setEnabled(false);
		}
		defaultAlgorithmCheckBox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getWizard().getContainer().updateButtons();
			}
		});
		defaultAlgorithmCheckBox.setText("I like to generate the code for the default algorithm into my Java project");
		
		final ControlDecoration deco = new ControlDecoration(defaultAlgorithmCheckBox, SWT.TOP | SWT.LEFT );
        Image image = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION)
		.getImage();
		
		deco.setDescriptionText("If you want to view other possible algorithm combinations,\nplease uncheck and click 'Next'");
		deco.setImage(image);
		deco.setShowOnlyOnFocus(false);
		
			
	}


	public Task getTask() {
		return this.selectedTask;
	}

	
	public boolean isDefaultAlgorithm() {
		return this.defaultAlgorithmCheckBox.getSelection();
    }
	
	public InstanceClafer getValue() {
		return this.value;
	}
	
	
	@Override
	public void setPageComplete(final boolean complete) {
		super.setPageComplete(complete);
	}
	
	public void setValue(final InstanceClafer instanceClafer) {
		this.value = instanceClafer;
	}
	
	
	@Override
	public boolean canFlipToNextPage() {
		//Can go to next page only if the check box is unchecked
		if(this.defaultAlgorithmCheckBox.getSelection()==true)
		return false;
		return true;
			
	}
	
	@Override
	public void setVisible( boolean visible ) {
	  super.setVisible( visible );
	  if(visible) {
	    control.setFocus();
	  }
	}
	
}
