/********************************************************************************
 * Copyright (c) 2015-2019 TU Darmstadt, Paderborn University
 * 

 * http://www.eclipse.org/legal/epl-2.0. SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/

/**
 *
 */
package de.cognicrypt.integrator.task.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.eclipse.jface.dialogs.MessageDialog;

import de.cognicrypt.codegenerator.question.Answer;
import de.cognicrypt.codegenerator.question.Question;
import de.cognicrypt.codegenerator.tasks.Task;
import de.cognicrypt.core.Constants;
import de.cognicrypt.integrator.task.controllers.Validator;

public class IntegratorModel {
	private File locationOfCryslTemplate;
	private File iconFile;
	private File jsonFile;
	private File locationImportFile;
	private boolean isGuidedModeChosen;
	private boolean isImportModeChosen;
	private final Task task;
	
	private boolean debug;
	
	private HashMap<String, File> cryslTemplateFiles;
	private final ArrayList<Question> questions;

	private static IntegratorModel instance = new IntegratorModel();
	
	/**
	 * 
	 * @return singleton object
	 */
	public static IntegratorModel getInstance() {
		return instance;
	}
	
	/**
	 * Resets the singleton object that contains the plugin state
	 */
	public static void resetInstance() {
		instance = new IntegratorModel();
	}
	
	private IntegratorModel() {
		super();
		task = new Task();
		cryslTemplateFiles = new HashMap<String, File>();
		questions = new ArrayList<>();
	}
	
	
	/**
	 * 
	 * @param templateFilePath to be added
	 * @return true if the added template was the first one
	 * @throws Exception if warning has to be shown
	 */
	public boolean addTemplate(String templateFilePath) throws Exception {
		
		boolean newTaskName = false;
		
		if (templateFilePath == null) 
			return false; // user canceled the file dialog
		
		// Set the task name or verify that it's equal
		String[] filePathParts = templateFilePath.split("(\\/|\\\\)");
		String taskName = filePathParts[filePathParts.length - 1].replace(".java", "");
		
		if(!debug && Validator.checkIfTaskNameAlreadyExists(taskName)) { // can not be tested because TaskJSONReader requires the plugin bundle
			throw new Exception(Constants.ERROR_TASK_ALREADY_INTEGRATED);
		}
		
		if (getTaskName() == null) {
			setTaskName(taskName);
			newTaskName = true;
		}else if (!taskName.contentEquals(IntegratorModel.getInstance().getTaskName())) {
			throw new Exception(Constants.ERROR_DIFFERENT_TASK_NAME);
		}
		
		// Extract package line from the template's source code
		String packageLine = "";

		Scanner scanner;
		try {
			scanner = new Scanner(new File(templateFilePath));
		} catch (FileNotFoundException e1) {
			throw new Exception(Constants.ERROR_FILE_NOT_FOUND);
		}
		
		while (packageLine.contentEquals("")) {

			if(!scanner.hasNextLine()) {
				scanner.close();
				throw new Exception(Constants.ERROR_NO_PACKAGE);
			}

			String[] expr = scanner.nextLine().split(";");

			// Lines may contain multiple expressions
			for(String e : expr) {
				String line = e.trim();
				if (line.startsWith("package")) {
					packageLine = line;
					break;
				}
				if (line.contains("class")) {
					break;
				}
			}

		}
		scanner.close();
		
		// Extract identifier
		String[] packageParts = packageLine.split("\\.");
		String templateIdentifier = packageParts[packageParts.length - 1].replace(taskName, "").replace(";", "");

		cryslTemplateFiles.put(templateIdentifier, new File(templateFilePath));
		
		return newTaskName;
	}
	
	/**
	 * @throws Exception if templates is empty or single template's identifier does not match the task name (task information page is incomplete)
	 */
	public void checkTemplatesDec() throws Exception {
		// Template list is empty
		if(isTemplatesEmpty())
			throw new Exception(Constants.ERROR_BLANK_TEMPLATE_LIST);
				
		// Single template identifier does not match the task name
		if(getIdentifiers().size() == 1 && !getIdentifiers().get(0).isEmpty())
			throw new Exception(Constants.ERROR_SINGLE_TEMPLATE_ID);
	}

	/**
	 * 
	 * @param templateIdentifier to be removed
	 * @return true if last template was removed
	 * @throws Exception if template is used in an answer and therefor not removed
	 */
	public boolean removeTemplate(String templateIdentifier) throws Exception {
		
		for(Question q : questions) {
			for(Answer a : q.getAnswers()) {
				if(a.getOption().contentEquals(templateIdentifier)) {
					throw new Exception(Constants.ERROR_TEMPLATE_IS_USED_IN_ANSWER);
				}
			}
		}


		cryslTemplateFiles.remove(templateIdentifier);

		if(isTemplatesEmpty()) {
			setTaskName(null);
			return true;
		}

		return false;
	}
	
	
	/**
	 * Creates a new answer and adds it to the question
	 * @param questionIndex
	 */
	public void addAnswer(int questionIndex) {
		int answerIndex = questions.get(questionIndex).getAnswers().size();
		
		Answer a = new Answer();
		a.setValue("");
		a.setOption(getIdentifiers().get(answerIndex % getIdentifiers().size()));
		
		if(answerIndex == 0)
			a.setDefaultAnswer(true);
		
		questions.get(questionIndex).getAnswers().add(a);
	}
	
	/**
	 * Removes an answer and updates the default answer
	 * @param answerIndex to be removed from the question
	 * @param questionIndex
	 */
	public void removeAnswer(int questionIndex, int answerIndex) {
		
		boolean wasDefaultAnswer = getAnswer(questionIndex, answerIndex).isDefaultAnswer();
		
		getQuestion(questionIndex).getAnswers().remove(answerIndex);
		
		if(wasDefaultAnswer && !getQuestion(questionIndex).getAnswers().isEmpty())
			getAnswer(questionIndex, 0).setDefaultAnswer(true);
	}
	
	/**
	 * 
	 * @param questionIndex
	 * @throws Exception if question text is empty
	 */
	public void checkQuestionDec(int questionIndex) throws Exception {
		if(getQuestion(questionIndex).getQuestionText().isEmpty())
			throw new Exception(Constants.ERROR_MESSAGE_BLANK_QUESTION_NAME);
	}
	
	/**
	 * 
	 * @param questionIndex
	 * @throws Exception if no answers were given or an answer's text is empty
	 */
	public void checkAnswersDec(int questionIndex) throws Exception {
		
		ArrayList<Answer> answers = getQuestions().get(questionIndex).getAnswers(); 

		if(answers.isEmpty())
			throw new Exception(Constants.ERROR_BLANK_ANSWERS_LIST);

		boolean isAnswerTextEmpty = false;

		for(Answer a : answers) {
			isAnswerTextEmpty |= a.getValue().isEmpty();
		}

		if(isAnswerTextEmpty)
			throw new Exception(Constants.ERROR_EMPTY_ANSWER_TEXT);
	}
	
	
	/**
	 * 
	 * @param debug can be set to true for unit testing without plugin dependencies
	 */
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	

	public String getTaskName() {
		return task.getName();
	}
	
	/**
	 * 
	 * @return task name without special characters
	 */
	public String getTrimmedTaskName() {
		return getTaskName().replaceAll("[^A-Za-z0-9]", "");
	}


	public void setTaskName(final String taskName) {
		task.setName(taskName);
		
		
		if(taskName != null) {
			task.setDescription(taskName);
			
		}
	}
	
	public void setQuestionsJSONFile() {
		task.setQuestionsJSONFile(Constants.ECLIPSE_LOC_TASKDESC_DIR + "/" + getTaskName() + Constants.JSON_EXTENSION);
	}

	public File getIconFile() {
		return iconFile;
	}

	public void setLocationOfIconFile(File locationOfIconFile) {
		this.iconFile = locationOfIconFile;
	}

	public File getImportFile() {
		return locationImportFile;
	}

	public void setLocationOfImportFile(File locationOfImportFile) {
		this.locationImportFile = locationOfImportFile;
	}

	public File getJSONFile() {
		return this.jsonFile;
	}

	public void setJSONFile(final File locationOfJSONFile) {
		this.jsonFile = locationOfJSONFile;
	}
	
	public boolean isGuidedModeChosen() {
		return this.isGuidedModeChosen;
	}

	public void setGuidedModeChosen(final boolean isGuidedModeChosen) {
		this.isGuidedModeChosen = isGuidedModeChosen;
	}

	public boolean isImportModeChosen() {
		return this.isImportModeChosen;
	}

	public void setImportModeChosen(final boolean isImportModeChosen) {
		this.isImportModeChosen = isImportModeChosen;
	}
	
	public Task getTask() {
		return this.task;
	}

	public String getTaskDescription() {
		return task.getTaskDescription();
	}

	public void setTaskDescription(final String taskDescription) {
		task.setTaskDescription(taskDescription);
	}
	
	public String getDescription() {
		return task.getDescription();
	}

	public void setDescription(String description) {
		task.setDescription(description);
	}

	public File getLocationOfCryslTemplate() {
		return locationOfCryslTemplate;
	}

	public void setLocationOfCryslTemplate(File locationOfCryslTemplate) {
		this.locationOfCryslTemplate = locationOfCryslTemplate;
	}
	
	public HashMap<String, File> getCryslTemplateFiles() {
		return cryslTemplateFiles;
	}
	
	public boolean isTemplatesEmpty() {
		return cryslTemplateFiles.isEmpty();
	}
	
	
	public boolean contains(String identifier) {
		return cryslTemplateFiles.containsKey(identifier);
	}
	
	public List<String> getIdentifiers(){
		ArrayList<String> identifiers = new ArrayList<String>();
		identifiers.addAll(cryslTemplateFiles.keySet());
		return identifiers;
	}
	
	public File getTemplate(String identifier){
		return cryslTemplateFiles.get(identifier);
	}
	
	public ArrayList<Question> getQuestions() {
		return questions;
	}
	
	public Question getQuestion(int questionIndex) {
		return questions.get(questionIndex);
	}
	
	public Answer getAnswer(int questionIndex, int answerIndex) {
		return questions.get(questionIndex).getAnswers().get(answerIndex);
	}
}
