/** network is the package for class placement */ 
package network;
/*
 *  @(#)ShannonsTheorem.java   1.1 YY/MM/DD
 *
 *  The main class, ShannonsTheorem has an association with the model class, ShannonsModel.
 *  Copyright (c) 2016, Byung Seon Kim. All rights reserved.
 *  
 */

/*	Java packages required by this class. */
import java.awt.GridLayout;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * 
 * This class is for calculation of Channel Capacity by Shannon, which is to demonstrate Lab 3.<br>
 * A class that implements an interface "realizes" the interface (as we sal in UML). It provides<br>
 * an implementation for the methods defined in the interface (or it will defer the implementation<br>
 * to a child class (sub-class). <br>
 * <br>
 * @author    Byung Seon Kim
 * @version   1.1.0 Oct 2016
 * 
 */
public class ShannonsTheorem implements ShannonsController {
	
	/* CONSTRUCTOR	-----------------------------------------------------	*/
	/**
	 * Default constructor to create the object for reference
	 */
	public ShannonsTheorem() {
		model = new ShannonsModel();
	}
	
	/* ACCESSORS	-----------------------------------------------------	*/
	/**
	 * The getBandwidth() is the method to be return the bandwidth.
	 * @return the double value of the instance variable, bandwidth
	 */
	public double getBandwidth() { return getModel().getBandwidth(); }
	
	/**
	 * The getSignalToNoise() is get the method to be return the sinalToNoise.
	 * @return the double value of the instance variable, sinalToNoise
	 */
	public double getSignalToNoise() { return getModel().getSignalToNoise(); }
	
	/* MODIFIERS	-----------------------------------------------------	*/
	/**
	 * The setBandwidth(double bandwidth) is the method to set the bandwidth.
	 * @param bw the double value of bandwidth to set
	 */
	@Override
	public void setBandwidth(double bw) { getModel().setBandwidth(bw);	}

	/**
	 * The setSignalToNoise(double signalToNoise) is the method to set the signalToNoise.
	 * @param snr the double value of signalToNoise to set
	 */
	@Override
	public void setSignalToNoise(double snr) { getModel().setSignalToNoise(snr); }
	
	/*	NORMAL BEHAVIOR -------------------------------------------------	*/
	/**
	 * The maximumDataRate() is the method to return maximum data rate(MDR) 
	 * that is computed using bandwidth and signalToNoise.
	 * @return the maximum data rate as a double
	 */
	public double getMaximumDataRate() { return getModel().getMaximumDataRate(); }

	/**
	 * Adds an observer to the internal list of observers.
	 * @param o an observer to be added
	 */
	@Override
	public void addObserver(Observer o) {
		getModel().addObserver(o);
	}
	
	/* HELPER METHODS	--------------------------------------------------	*/
	private ShannonsModel getModel() { return model; } // get ShannonsModel object
	
	@SuppressWarnings("unused")
	private void setModel(ShannonsModel model) { this.model = model; } // set ShannonsModel object
	
	// create all GUI elements
	private void initGUI() {
		
		ShannonsPanel basicPanel = new ShannonsPanel(this);
		ShannonsSlider sliderPanel = new ShannonsSlider(this);
		ShannonsSpinner spinnerPanel = new ShannonsSpinner(this);
		
		this.addObserver(basicPanel);
		this.addObserver(sliderPanel);
		this.addObserver(spinnerPanel);
		
		JFrame jFrame = new JFrame("Shannon's Theorem for Lab 3");
		jFrame.setSize(600, 550);
		GridLayout root = new GridLayout(3,  1);
		root.setHgap(10);
		root.setVgap(5);

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(root);
		jFrame.getContentPane().add(basicPanel);
		jFrame.getContentPane().add(sliderPanel);
		jFrame.getContentPane().add(spinnerPanel);
	    jFrame.setVisible(true);
	}
	
	/*	ENTRY POINT for STAND-ALONE OPERATION ---------------------------	*/
	/**
	 * Entry point "main()" as required by the JVM. 
	 * @param  args   Standard command line parameters (arguments) as a	string array.
	 */
	public static void main(String[] args) {
		ShannonsTheorem shannonsTheorem = new ShannonsTheorem();
		shannonsTheorem.initGUI();
	}

	/* ATTRIBUTES	-----------------------------------------------------	*/
	private ShannonsModel model; // The reference attribute of type ShannonsModel

	
} /* End of CLASS: ShannonsTheorem.java	*/
