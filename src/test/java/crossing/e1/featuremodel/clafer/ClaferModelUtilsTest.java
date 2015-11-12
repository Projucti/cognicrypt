/**
 * Copyright 2015 Technische Universität Darmstadt
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
 * @author Ram
 *
 */
package crossing.e1.featuremodel.clafer;

import static org.junit.Assert.*;
import org.clafer.ast.AstClafer;
import org.clafer.ast.AstConcreteClafer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import crossing.e1.configurator.ReadConfig;

public class ClaferModelUtilsTest {
	private String path = new ReadConfig().getPath("claferPath");
	// any of the tasks name
	private String claferName = "EncryptionUsingDigest";
	ClaferModel claferModel;
	String abstarctClaferName = "Digest";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		claferModel = new ClaferModel(new ReadConfig().getPath("claferPath"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link crossing.e1.featuremodel.clafer.ClaferModelUtils#isAbstract(org.clafer.ast.AstClafer)}
	 * .
	 */
	@Test
	public final void testIsAbstract() {
		AstClafer clafer = claferModel.getClafersByName(abstarctClaferName)
				.get(0).getSuperClafer();
		assertTrue("Failed to detect abstract clafer",
				ClaferModelUtils.isAbstract(clafer));
		clafer = claferModel.getClafersByName(claferName).get(0);
		assertFalse("Failed to detect abstract clafer",
				ClaferModelUtils.isAbstract(clafer));

	}

	/**
	 * Test method for
	 * {@link crossing.e1.featuremodel.clafer.ClaferModelUtils#findClaferByName(org.clafer.ast.AstClafer, java.lang.String)}
	 * .
	 */
	@Test
	public final void testFindClaferByName() {
		String childClaferName = "c0_outputSize";
		AstConcreteClafer clafer = claferModel.getClafersByName(claferName)
				.get(0);
		AstClafer childclafer = ClaferModelUtils.findClaferByName(clafer,
				childClaferName);
		assertNotNull("failed to find the child clafer with given name",
				childclafer);
		childclafer = ClaferModelUtils.findClaferByName(clafer,
				"TestCaseClaferName");
		assertNull("False positive,child clafer found with an invalid name",
				childclafer);

	}

	/**
	 * Test method for
	 * {@link crossing.e1.featuremodel.clafer.ClaferModelUtils#trimScope(java.lang.String)}
	 * .
	 */
	@Test
	public final void testTrimScope() {
		String beforeTrimValue="c0_testString";
		String comparableValue="TestString";
		assertEquals("Failed to trim string value", ClaferModelUtils.trimScope(beforeTrimValue), comparableValue);
		assertNotEquals("Failed to trim string value", beforeTrimValue, comparableValue);
	
	}

}
