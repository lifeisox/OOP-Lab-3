/*
 *  @(#)Test_model.java	Sep 8, 2016
 *
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
import network.ShannonsModel;

/**
 * JUnit tests for the model class from the "network" package.
 * @author Byung Seon Kim
 * @version 1.0.0
 */
public class Test_ShannonsModel extends TestCase {

	public Test_ShannonsModel() { super(); }

	public static Test suite() { return new TestSuite(Test_ShannonsModel.class); }

	protected void setUp() throws Exception { System.out.println("Test_model Begin");	}

	protected void tearDown() throws Exception { System.out.println("Test_model End"); }

	/**
 	 * Test the ShannonsModel() Constructor.
 	 */
	public void testConstructors() {
      System.out.println("\tExecuting Test_model.testConstructors");
		model = new ShannonsModel();
		String msg = "\t\tTest_model.testConstructors: Expected model is null";
		assertNotNull(msg, model);
	}


	/**
	 * Test the Accessors: getBandwidth() and getSignalToNoise().
	 */
	public void testAccessors() {
      System.out.println("\tExecuting Test_model.testAccessors");
		model = new ShannonsModel();
		String msg = "\t\tTest_model.testAccessors: Expected model is null";
		assertNotNull(msg, model);
		
		model.setBandwidth(3000.0);
		msg = "\t\tTest_model.testAccessors: Expected getBandwidth() is 3000.0";
		assertTrue(msg, model.getBandwidth() == 3000.0);
		
		model.setSignalToNoise(1000.0);
		msg = "\t\tTest_model.testAccessors: Expected getSignalToNoise() is 1000.0";
		assertTrue(msg, model.getSignalToNoise() == 1000.0);
	}


	/**
	 * Test the mutators/modifiers: setBandwidth() and setSignalToNoise().
	 */
	public void testMutators() {
      System.out.println("\tExecuting Test_model.testMutators");
		model = new ShannonsModel();
		String msg = "\t\tTest_model.testMutators: Expected model is null";
		assertNotNull(msg, model);

		model.setBandwidth(3000.0);
		msg = "\t\tTest_model.testMutators: Expected getBandwidth() is 3000.0";
		assertTrue(msg, model.getBandwidth() == 3000.0);
		
		model.setSignalToNoise(1000.0);
		msg = "\t\tTest_model.testMutators: Expected getSignalToNoise() is 1000.0";
		assertTrue(msg, model.getSignalToNoise() == 1000.0);
	}

	/**
	 * Test behaviors in valid case and invalid case: maximumDataRate() and toString().
	 */
	public void testBehaviors() {
      System.out.println("\tExecuting Test_model.testBehaviors");
		model = new ShannonsModel();
		String msg = "\t\tTest_model.testBehaviors: Expected model is null";
		assertNotNull(msg, model);

		/* Testing maximumDataRate() using valid data: Standard parameters for a telephone line */
		model.setBandwidth(3000.0);
		model.setSignalToNoise(30.0);
		double mdr = model.getMaximumDataRate();
		msg = "\t\tTest_model.testBehaviors: Expected maximumDataRate() to be equal to 29901.6787 within the margin of error 0.001";
		assertEquals(msg, 29901.6787, mdr, 0.001); // The result should be a little less than 30 kbps. It's a valid data.
		
		/* Testing maximumDataRate() using invalid data */
		msg = "\t\tTest_model.testBehaviors: Expected getMaximumDataRate() to be Infinity";
		model.setBandwidth(5000000000.0);
		model.setSignalToNoise(1000000000.0);
		mdr = model.getMaximumDataRate();
		assertEquals(msg, Double.POSITIVE_INFINITY, mdr); // The result should be infinity. It's a invalid data.

		/* Testing toString(): Standard parameters for a telephone line */
		model.setBandwidth(3000.0);
		model.setSignalToNoise(30.0);
		mdr = model.getMaximumDataRate();
		msg = "\t\tTest_model.testBehaviors: Expected toString() to be equal to the value";
		
		assertEquals(msg, model.toString(), "The MDR is " + String.format("%.2f", model.getMaximumDataRate()) + 
				" bits/sec\nwhen Bandwidth is " + model.getBandwidth() +
				" Hz and S/N ratio is " + model.getSignalToNoise() + "."); // The result should be a little less than 30 kbps. It's a valid data.
	}
	
	/*	STAND-ALONE ENTRY POINT -----------------------------------------	*/
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	public static void main(String[] args) {
      System.out.println("Executing Test_model suite");
      junit.textui.TestRunner.run(suite());
  }



   /* ATTRIBUTES	-----------------------------------------------	*/
   private ShannonsModel model = null;

} /*	End of CLASS:	Test_model.java				*/
