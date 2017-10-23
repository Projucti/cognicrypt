/**
 * Copyright 2015-2017 Technische Universitaet Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * @author Stefan Krueger
 *
 */

package crossing.e1.configurator;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

/**
 * This class comprises all constants that are used by the plugin.
 *
 */
public class Constants {

	public static enum GUIElements {
		combo, text, itemselection,button
	}

	//The plugin is bundled in a jar archive and the file separator within jar files is / (see: https://stackoverflow.com/questions/24749007/how-to-use-file-separator-for-a-jar-file-resource).
	//Use this file separator for all paths within the plugin space.
	public static final String innerFileSeparator = "/";

	//Use this file separator for all paths outside the plugin space.
	public static final String outerFileSeparator = System.getProperty("file.separator");;
	public static final String lineSeparator = System.getProperty("line.separator");

	public static final String JavaNatureID = "org.eclipse.jdt.core.javanature";

	private static final String rsrcPath = "src" + innerFileSeparator + "main" + innerFileSeparator + "resources" + innerFileSeparator;

	//JSON task file
	public static final String jsonTaskFile = rsrcPath + "Tasks" + innerFileSeparator + "tasks.json";;

	public static final String pathToPropertyfiles = rsrcPath + "Labels.properties";

	// Task descriptions

	// if the next question page depends on user input, the Page object encodes this as a nextPageID as opposed to the one that the last page points to
	public static final int QUESTION_PAGE_NO_STATIC_NEXT_PAGE_ID = -2;

	// the last page points to this virtual nextPageID
	public static final int QUESTION_PAGE_LAST_PAGE_NEXT_ID = -1;

	// the answer does not point to a next page, so in this case the page links to a next one statically
	public static final int ANSWER_NO_NEXT_ID = -2;
	
	// the given answer makes the wizard end
	public static final int ANSWER_NO_FOLLOWING_QUESTION_NEXT_ID = -1;

	// Clafer Instance Generation
	public static final String claferPath = rsrcPath + "ClaferModel" + innerFileSeparator + "SymmetricEncryptionTask.js";
	public static final String PATH_FOR_CONFIG_XML = "/Configurator.xml";
	public static final String XML_FILE_NAME = rsrcPath + "ClaferModel/Encrypt_CryptoTasks.xml";

	// Input for Code Generation
	public static final String pathToXSLFile = rsrcPath + "XSLTemplates" + innerFileSeparator + "JCA.xsl";
	public static final String pathToClaferInstanceFolder = rsrcPath + "ClaferInstance" + innerFileSeparator;
	public static final String pathToClaferInstanceFile = "claferInstance.xml";
	public static final String NameOfTemporaryMethod = "templateUsage";
	public static final String pathsForLibrariesinDevProject = "libs";

	// Output of Code Generation
	private static final String AdditionalOutputFile = "Output.java";
	public static final String PackageName = "Crypto";
	public static final String CodeGenerationCallFile = innerFileSeparator + Constants.PackageName + innerFileSeparator + AdditionalOutputFile;

	// Error Messages
	public static final String NoFileOpenedErrorMessage = "There is no file opened to generate the source code in. Will generate output file instead.";
	public static final String NoJavaFileOpenedErrorMessage = "The currently open file is not a java file. Will generate output file instead.";
	public static final String CodeGenerationErrorMessage = "An error occured during template generation.";
	public static final String FilesDoNotExistErrorMessage = "At least one of the files necessary for template generation does not exist.";
	public static final String NoRunMethodFoundInTemporaryOutputFileErrorMessage = "XSL Template does not contain method " + NameOfTemporaryMethod + ".";
	public static final String NoTemporaryOutputFile = "Temporary output file does not exist.";
	public static final String NoFileandNoProjectOpened = "No file opened and no project selected.";
	public static final String NO_POSSIBLE_COMBINATIONS_ARE_AVAILABLE = "No possible combinations are available for chosen values. Please modify your preferences and try agin.\n \n You can use  \n>= insted of >\n<= instead of <\nto make your selection generic.";
	public static final String NO_POSSIBLE_COMBINATIONS_BEGINNER = "No possible combinations are available for chosen values. Please modify your preferences and try agin.";
	public static final String PLEASE_SELECT = "Please select project directory to launch the configurator";
	public static final String ERROR_MESSAGE_NO_PROJECT = "There is no Java project in your workspace. Please create one and restart CogniCrypt.";
	public static final String ERROR_MESSAGE_NO_ADDITIONAL_RES_DIRECTORY = "No directory for additional resources found.";

	public static final String JAVA = "java";
	public static final String ALGORITHM = "algorithm";
	public static final String ADVANCED_MODE = "Advanced Mode";

	public static final String Package = "Package";
	public static final String Description = "description";
	public static final String Imports = "Imports";
	public static final String Import = "Import";
	public static final String Task = "task";
	public static final String Code = "code";
	public static final String Type = "type";
	public static final String[] xmlimportsarr = { "java.security.InvalidAlgorithmParameterException", "java.security.InvalidKeyException", "java.security.NoSuchAlgorithmException", "java.security.NoSuchAlgorithmException", "javax.crypto.SecretKey", "javax.crypto.BadPaddingException", "javax.crypto.Cipher", "javax.crypto.IllegalBlockSizeException", "javax.crypto.NoSuchPaddingException", "java.security.SecureRandom", "javax.crypto.spec.IvParameterSpec", "javax.crypto.spec.SecretKeySpec", "java.security.spec.InvalidKeySpecException", "java.util.List;" };

	// Types
	public static final String INTEGER = "Integer";
	public static final String STRING = "String";
	public static final String BOOLEAN = "Boolean";

	public static final int INT_HIGH = 600;
	public static final int INT_LOW = -17;
	public static final String INSTANCE_DETAILS = "Instance Details";
	public static final String ARIAL = "Arial";
	public static final String SELECT_JAVA_PROJECT = "Select a Java Project :";
	public static final String SELECT_TASK = "Select Task :";
	public static final String NO_XML_INSTANCE_FILE_TO_WRITE = "No xml instance file to write.";
	public static final String CLAFER_ALGORITHM = "_Algorithm";

	//Flags for default project selection
	public static boolean WizardActionFromContextMenuFlag = false;
	
	// Constants for the Task Integrator Wizard.
	// Page constants
	
	public static final String PAGE_NAME_FOR_MODE_OF_WIZARD = "pageForChoiceOfModeOfWizard";
	public static final String PAGE_TITLE_FOR_MODE_OF_WIZARD = "Please select the mode for the wizard";
	public static final String PAGE_DESCRIPTION_FOR_MODE_OF_WIZARD = "Here you can update the basic details of the task, and the mode in which the wizard will run.";
	
	public static final String PAGE_NAME_FOR_CLAFER_FILE_CREATION = "pageForClaferFileCreation";
	public static final String PAGE_TITLE_FOR_CLAFER_FILE_CREATION = "Add the variablity modelling here";
	public static final String PAGE_DESCRIPTION_FOR_CLAFER_FILE_CREATION = "Here you can add features to the variability model here.";
	
	public static final String PAGE_NAME_FOR_XSL_FILE_CREATION = "pageForXSLFileCreation";
	public static final String PAGE_TITLE_FOR_XSL_FILE_CREATION = "Add data for the code generation";
	public static final String PAGE_DESCRIPTION_FOR_XSL_FILE_CREATION = "Here you can details for the code generation.";
	
	public static final String PAGE_NAME_FOR_HIGH_LEVEL_QUESTIONS = "pageForHighLevelQuestions";
	public static final String PAGE_TITLE_FOR_HIGH_LEVEL_QUESTIONS = "Add the high level qustions and their dependencies here";
	public static final String PAGE_DESCRIPTION_FOR_HIGH_LEVEL_QUESTIONS = "Here you can add the questions that will be asked to the end user, and the dependencies to the "+
																			"variability modelling and the code generation.";
	
	// Widget constants
	// Labels
	public static final String LABEL_BROWSE_BUTTON = "Browse";
	// Dimensions
	public static final int UI_WIDGET_HEIGHT_NORMAL = 29;
	// Constants for the composites
	public static final String WIDGET_DATA_NAME_OF_THE_TASK = "NameOfTheTask";
	public static final String WIDGET_DATA_LIBRARY_LOCATION_OF_THE_TASK = "LibraryLocationOfTheTask";
	public static final String WIDGET_DATA_LOCATION_OF_CLAFER_FILE = "LocationOfClaferFile";
	public static final String WIDGET_DATA_LOCATION_OF_XSL_FILE = "LocationOfXSLFile";
	public static final String WIDGET_DATA_LOCATION_OF_JSON_FILE = "LocationOfJSONFile";
	public static final String WIDGET_DATA_IS_CUSTOM_LIBRARY_REQUIRED = "isCustomLibraryRequired";
	public static final String WIDGET_DATA_IS_GUIDED_MODE_CHOSEN = "isGuidedModeChosen";
	public static final String WIDGET_DATA_IS_GUIDED_MODE_FORCED = "isGuidedModeForced";
	
	// Constants for the drop down for the library type on the mode selection page
	public static final String WIDGET_CONTENT_EXISTING_LIBRARY = "Existing Library";
	public static final String WIDGET_CONTENT_CUSTOM_LIBRARY = "Custom Library";
	
	//Constants for the drop down for the xsl tag on the xsl code page
	public static final String XSL_VARIABLE_TAG="xslVariable";
	public static final String XSL_SELECT_TAG="select";
	public static final String XSL_IF_TAG ="if";
	public static final String XSL_RESULT_DOCUMENT="result-document";
	public static final String XSL_APPLY_TEMPLATES ="apply-templates";
	public static final String XSL_CHOOSE_TAG="choose";
	public static final String XSL_WHEN_TAG="when";
	public static final String XSL_OTHERWISE_TAG="otherwise";
	
	// Default bounds for the composites
	public static final Point DEFAULT_SIZE_FOR_TI_WIZARD = new Point(1000, 500);
	public static final Rectangle RECTANGLE_FOR_COMPOSITES = new Rectangle(0, 0, 887, 500 - 10 -10 ); //897 - 10
	public static final Rectangle RECTANGLE_FOR_FIRST_BUTTON_FOR_NON_MODE_SELECTION_PAGES = new Rectangle(897, 10, 83, 29); //1000 - 83 - 10 -10
	public static final Rectangle RECTANGLE_FOR_SECOND_BUTTON_FOR_NON_MODE_SELECTION_PAGES = new Rectangle(897, 49, 83, 29); //1000 - 83 - 10 -10
	//public static final Rectangle RECTANGLE_FOR_GRANULAR_CLAFER_UI_ELEMENT = new Rectangle(10, 10, 744, 280);
	public static final int WIDTH_FOR_GRANULAR_CLAFER_UI_ELEMENT = 744;
	public static final int HEIGHT_FOR_GRANULAR_CLAFER_UI_ELEMENT = 280;
	
	// Form  data for CompositeGranularUIForClaferFeature
	public static final int RIGHT_VALUE_FOR_GRANULAR_CLAFER_UI_SUB_ELEMENT = 736;
	
	public static final int PADDING_BETWEEN_GRANULAR_UI_ELEMENTS = 10;
	public static final int PADDING_BETWEEN_SMALLER_UI_ELEMENTS = 3;
	
	public static final int WIDTH_FOR_CLAFER_FEATURE_PROPERTY_UI_ELEMENT = 409;
	public static final int HEIGHT_FOR_CLAFER_FEATURE_PROPERTY_UI_ELEMENT = 37;
	
	// TypeOfTargetDataForSmallUIElements int values. Add the type of data that needs to be shown on the composite here.
	public static final int FEATURE_PROPERTY = 0;
	public static final int FEATURE_CONSTRAINT = 1;
	
	// source for the contents of this enum : https://www.w3.org/TR/xslt20/
	public static enum XSLTags{
		XSL_VARIABLE_TAG("Variable","<xsl:variable>","</xsl:variable>", new String[]{"name",
																					"select",
																					"as"
																					/*,
																					 * "static",
																					 * "visibility"*/
																					}),
		XSL_VALUE_OF_TAG("Value of","<xsl:value-of/>","", new String[]{"select", "separator" , "disable-output-escaping"}),
		XSL_IF_TAG("If","<xsl:if>","</xsl:if>",new String[]{"test"}),
		XSL_RESULT_DOCUMENT("Result Document","<xsl:result-document>","</xsl:result-document>", new String[]{"href",
																											"format",
																											"validation",
																											"type",
																											"method",
																											"byte-order-mark",
																											"cdata-section-elements",
																											"doctype-public",
																											"doctype-system",
																											"encoding",
																											"escape-uri-attributes",
																											"include-content-type",
																											"indent",
																											"media-type",
																											"normalization-form",
																											"omit-xml-declaration",
																											"standalone",
																											"undeclare-prefixes",
																											"use-character-maps",
																											"output-version"
																											/*,
																											"allow-duplicate-names",
																											"build-tree",
																											"html-version",
																											"item-separator",
																											"json-node-output-method",
																											"parameter-document",
																											"suppress-indentation",*/																									
																											}),
		XSL_APPLY_TEMPLATES("Apply Templates","<xsl:apply-templates />","", new String[]{"select",
																						"mode"
																							}),
		XSL_CHOOSE_TAG("Choose","<xsl:choose>","</xsl:choose>", new String[]{}),
		XSL_WHEN_TAG("When","<xsl:when test =\"\">","</xsl:when>", new String[]{"select"
																				}),
		XSL_OTHERWISE_TAG("Otherwise","<xsl:otherwise>","</xsl:otheriwse>", new String[]{});
		
		private final String XSLTagFaceName;
		private final String XSLBeginTag;
		private final String XSLEndTag;
		private final String[] XSLAttributes;
		/**
		 * @param XSLTagFaceNameParam
		 * @param XSLBeginTagParam
		 * @param XSLEndTagParam
		 */
		private XSLTags(String XSLTagFaceNameParam, String XSLBeginTagParam, String XSLEndTagParam, String[] XSLAttributesParam) {
			XSLTagFaceName = XSLTagFaceNameParam;
			XSLBeginTag = XSLBeginTagParam;
			XSLEndTag = XSLEndTagParam;
			XSLAttributes = XSLAttributesParam;
		}
		/**
		 * @return the xSLTagFaceName
		 */
		public String getXSLTagFaceName() {
			return XSLTagFaceName;
		}
		/**
		 * @return the xSLBeginTag
		 */
		public String getXSLBeginTag() {
			return XSLBeginTag;
		}
		/**
		 * @return the xSLEndTag
		 */
		public String getXSLEndTag() {
			return XSLEndTag;
		}
		/**
		 * @return the xSLAttributes
		 */
		public String[] getXSLAttributes() {
			return XSLAttributes;
		}
		
		
	}
	
	public static enum FeatureConstraintRelationship{
		EQUAL("="),
		NOTEQUAL("!="),
		LESSTHAN("<"),
		GREATERTHAN(">"),
		LESSTHANEQUALTO("<="),
		GREATERTHANEQUALTO(">="),
		AND("and"),
		OR("or");
		
		private final String operatorValue;
		
		FeatureConstraintRelationship(String operatorValue){
			this.operatorValue = operatorValue;
		}

		/**
		 * @return the operatorValue
		 */
		public String getOperatorValue() {
			return operatorValue;
		}

		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 * Just return the operator value instead of the name.
		 */
		@Override
		public String toString() {			
			return  operatorValue;
		}
		
		
	}
	
	public static enum FeatureType{
		CONCRETE,
		ABSTRACT;

		/* 
		 * Although "concrete" is never used in the actual cfr file, "abstract" is used. Hence the toString() returns lower case.
		 */
		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
		
	}
	
	
	
	
}
