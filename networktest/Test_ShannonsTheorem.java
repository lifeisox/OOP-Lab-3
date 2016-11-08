/*
 *  @(#)Test_shannonsTheorem.java	Sep 8, 2016
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
import network.ShannonsTheorem;

/**
 * JUnit tests for the shannonsTheorem class from the "network" package.
 * @author Byung Seon Kim
 * @version 1.0.0
 */
public class Test_ShannonsTheorem extends TestCase {

	public Test_ShannonsTheorem() { super(); }

	public static Test suite() { return new TestSuite(Test_ShannonsTheorem.class); }

	protected void setUp() throws Exception { System.out.println("Test_shannonsTheorem Begin");	}

	protected void tearDown() throws Exception { System.out.println("Test_shannonsTheorem End"); }

	/**
 	 * Test the ShannonsTheorem() Constructor.
 	 */
	public void testConstructors() {
      System.out.println("\tExecuting Test_shannonsTheorem.testConstructors");
		shannonsTheorem = new ShannonsTheorem();
		String msg = "\t\tTest_shannonsTheorem.testConstructors: Expected shannonsTheorem is null";
		assertNotNull(msg, shannonsTheorem);
	}


	/**
	 * Test the Accessors: getBandwidth() and getSignalToNoise().
	 */
	public void testAccessors() {
		System.out.println("\tExecuting Test_shannonsTheorem.testAccessors");
		shannonsTheorem = new ShannonsTheorem();
		String msg = "\t\tTest_shannonsTheorem.testAccessors: Expected shannonsTheorem is null";
		assertNotNull(msg, shannonsTheorem);
		
		shannonsTheorem.setBandwidth(3000.0);
		msg = "\t\tTest_shannonsTheorem.testAccessors: Expected getBandwidth() is 3000.0";
		assertTrue(msg, shannonsTheorem.getBandwidth() == 3000.0);
		
		shannonsTheorem.setSignalToNoise(1000.0);
		msg = "\t\tTest_shannonsTheorem.testAccessors: Expected getSignalToNoise() is 1000.0";
		assertTrue(msg, shannonsTheorem.getSignalToNoise() == 1000.0);
	}


	/**
	 * Test the mutators/modifiers: setBandwidth() and setSignalToNoise().
	 */
	public void testMutators() {
		System.out.println("\tExecuting Test_shannonsTheorem.testMutators");
		shannonsTheorem = new ShannonsTheorem();
		String msg = "\t\tTest_shannonsTheorem.testMutators: Expected shannonsTheorem is null";
		assertNotNull(msg, shannonsTheorem);

		shannonsTheorem.setBandwidth(3000.0);
		msg = "\t\tTest_shannonsTheorem.testMutators: Expected getBandwidth() is 3000.0";
		assertTrue(msg, shannonsTheorem.getBandwidth() == 3000.0);
		
		shannonsTheorem.setSignalToNoise(1000.0);
		msg = "\t\tTest_shannonsTheorem.testMutators: Expected getSignalToNoise() is 1000.0";
		assertTrue(msg, shannonsTheorem.getSignalToNoise() == 1000.0);
	}

	/**
	 * Test behaviors in valid case and invalid case: maximumDataRate().
	 */
	public void testBehaviors() {
      System.out.println("\tExecuting Test_shannonsTheorem.testBehaviors");
		shannonsTheorem = new ShannonsTheorem();
		String msg = "\t\tTest_shannonsTheorem.testBehaviors: Expected shannonsTheorem is null";
		assertNotNull(msg, shannonsTheorem);

		/* Standard parameters for a telephone line */
		shannonsTheorem.setBandwidth(3000.0);
		shannonsTheorem.setSignalToNoise(30.0);
		double mdr = shannonsTheorem.getMaximumDataRate();
		msg = "\t\tTest_shannonsTheorem.testBehaviors: Expected getMaximumDataRate() to be equal to 29901.6787 within the margin of error 0.001";
		assertEquals(msg, 29901.6787, mdr, 0.001); // The result should be a little less than 30 kbps. It's a valid data.
		
		msg = "\t\tTest_shannonsTheorem.testBehaviors: Expected getMaximumDataRate() to be Infinity";
		shannonsTheorem.setBandwidth(5000000000.0);
		shannonsTheorem.setSignalToNoise(1000000000.0);
		mdr = shannonsTheorem.getMaximumDataRate();
		assertEquals(msg, Double.POSITIVE_INFINITY, mdr); // The result should be infinity. It's a invalid data.
	}
	
	/*	STAND-ALONE ENTRY POINT -----------------------------------------	*/
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	public static void main(String[] args) {
      System.out.println("Executing Test_shannonsTheorem suite");
      junit.textui.TestRunner.run(suite());
  }



   /* ATTRIBUTES	-----------------------------------------------	*/
   private ShannonsTheorem shannonsTheorem = null;

} /*	End of CLASS:	Test_ShannonsTheorem.java				*/
