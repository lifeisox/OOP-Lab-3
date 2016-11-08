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
import network.ShannonsPanel;
import network.ShannonsSlider;
import network.ShannonsSpinner;
import network.ShannonsTheorem;

/**
 * JUnit tests for the shannonsTheorem class from the "network" package.
 * @author Byung Seon Kim
 * @version 1.0.0
 */
public class Test_ShannonsController extends TestCase {

	public Test_ShannonsController() { super(); }

	public static Test suite() { return new TestSuite(Test_ShannonsController.class); }

	protected void setUp() throws Exception { 
		System.out.println("Test_shannonsController Begin");	
		
	}

	protected void tearDown() throws Exception { System.out.println("Test_shannonsController End"); }

	/**
	 * Test behaviors in observer pattern. Check the change of ShannonsTheorem reflects three views
	 */
	public void testBehaviors() {
		System.out.println("\tExecuting Test_shannonsController.testBehaviors");

		shannonsTheorem = new ShannonsTheorem();
		shannonsPanel = new ShannonsPanel(shannonsTheorem);
		shannonsSlider = new ShannonsSlider(shannonsTheorem);
		shannonsSpinner = new ShannonsSpinner(shannonsTheorem);
		
		shannonsTheorem.addObserver(shannonsPanel);
		shannonsTheorem.addObserver(shannonsSlider);
		shannonsTheorem.addObserver(shannonsSpinner);
		
		/* Standard parameters for a telephone line : set values to observable */
		shannonsTheorem.setBandwidth(3000.0);
		shannonsTheorem.setSignalToNoise(30.0);
		double mdr = shannonsTheorem.getMaximumDataRate();
		String mdrString = String.format("%.2f", mdr);
		String msg = "\t\tTest_shannonsController.testBehaviors: Expected getMaximumDataRate() of ShannonsTheorem to be equal to max data rate of Views";
		
		// Expected getMaximumDataRate() of ShannonsTheorem to be equal to max data rate of Views.
		// ShannonsPanel observer test
		assertEquals(msg, mdrString, shannonsPanel.getMaxDataRateLBL().getText()); 
		// ShannonsSlider observer test
		assertEquals(msg, mdrString, shannonsSlider.getMaxDataRateLBL().getText()); 
		// ShannonsSpinner observer test
		assertEquals(msg, mdrString, shannonsSpinner.getMaxDataRateLBL().getText()); 

	}
	
	/*	STAND-ALONE ENTRY POINT -----------------------------------------	*/
	/**
	 *	Main line for standalone operation.
	 *	@param	args	Standard string command line parameters.
	 */
	public static void main(String[] args) {
      System.out.println("Executing Test_shannonsController suite");
      junit.textui.TestRunner.run(suite());
  }



   /* ATTRIBUTES	-----------------------------------------------	*/
   private ShannonsTheorem shannonsTheorem = null;
   private ShannonsPanel shannonsPanel = null;
   private ShannonsSlider shannonsSlider = null;
   private ShannonsSpinner shannonsSpinner = null;

} /*	End of CLASS:	Test_ShannonsTheorem.java				*/
