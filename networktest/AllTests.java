/*
 *  @(#)AllTests.java	Sep 8, 2016
 *
 *  (c) Copyright 2002, 2003 by Dyer Consulting
 *      Quebec, Canada
 *      All rights reserved.
 *
 *  This software contains confidential and proprietary information
 *  of Dyer Consulting ("Confidential Information").  You shall not disclose
 *  such Confidential Information and shall use it only in accordance with the
 *  terms of the license agreement you entered into with Dyer Consulting.
 *
 *  This software is provided "AS IS,".  No warrantee of any kind, express
 *  or implied, is included with this software; use at your own risk, responsibility
 *  for damages (if any) to anyone resulting from the use of this software rests
 *  entirely with the user even if Dyer Consulting has been advised of the
 *  possibility of such damages.
 *
 *  This software is not designed or intended for use in on-line control of
 *  aircraft, air traffic, aircraft navigation or aircraft communications; or in
 *  the design, construction, operation or maintenance of any nuclear
 *  facility. Licensee represents and warrants that it will not use or
 *  redistribute the Software for such purposes.
 *
 *  Distribute freely, except: don't remove my name from the source or
 *  documentation, mark your changes (don't blame me for your possible bugs),
 *  don't alter or remove any of this notice.
 *
 */

package networktest;

import junit.framework.*;

/**
 * JUnit test class to execute all JUNIT tests for the "Shannons Theorem" project.
 *
 */
public class AllTests extends TestCase {

	public AllTests(String name) { super(name);		}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(Test_ShannonsTheorem.suite());
		suite.addTest(Test_ShannonsModel.suite());
		suite.addTest(Test_ShannonsController.suite());
		return suite;
	}



	/*	STAND-ALONE ENTRY POINT -----------------------------------------	*/
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	 public static void main(String[] args) {
        System.out.println("Executing AllTests ...");
        junit.textui.TestRunner.run(suite());
    }

}	/*	End of CLASS:	AllTests.java			*/
