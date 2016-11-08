/** network is the package for class placement */ 
package network;
/*
 *  @(#)ShannonsModel.java   1.1 YY/MM/DD
 *
 *  This is the domain class, ShannonsModel.
 *  It contains the data structure for the ShannosTheorem.
 *  Copyright (c) 2016, Byung Seon Kim. All rights reserved.
 *  
 */

/*	Java packages required by this class. */
import java.util.Observable;

/**
 * 
 * This is the observable object model. This class extends the java class Observable making<br>
 * it not only an object of type ShannonsModel, but also an object of type observable<br>
 * <br>
 * @author    Byung Seon Kim
 * @version   1.1.0 Oct 2016
 * 
 */
public class ShannonsModel extends Observable {
	
	/* CONSTRUCTOR	-----------------------------------------------------	*/
	/**
	 * Constructor for initialization of instance variables, but it is nothing to do now.
	 */
	public ShannonsModel() { }
	
	/* ACCESSORS	-----------------------------------------------------	*/

	/**
	 * The getBandwidth() is the method to be return the instance variable, bandwidth.
	 * @return the double value of the instance variable, bandwidth
	 */
	public double getBandwidth() { return bandwidth; }
	
	/**
	 * The getSignalToNoise() is get the method to be return the instance variable, sinalToNoise.
	 * @return the double value of the instance variable, sinalToNoise
	 */
	public double getSignalToNoise() { return signalToNoise; }
	
	/* MODIFIERS	-----------------------------------------------------	*/
	/**
	 * The setBandwidth(double bandwidth) is the method to set the instance variable, bandwidth.
	 * @param bandwidth the double value of bandwidth to set
	 */
	public void setBandwidth(double bandwidth) { 
		this.bandwidth = bandwidth;
		setChanged(); // Sets the internal flag that indicates this observable has changed
		notifyObservers();  // Checks the internal flag to see if the observable has changed
	}

	/**
	 * The setSignalToNoise(double signalToNoise) is the method to set the instance variable, signalToNoise.
	 * @param signaltonoise the double value of signalToNoise to set
	 */
	public void setSignalToNoise(double signaltonoise) { 
		this.signalToNoise = signaltonoise;
		setChanged(); // Sets the internal flag that indicates this observable has changed
		notifyObservers(); // Checks the internal flag to see if the observable has changed
	}
	
	/*	NORMAL BEHAVIOR -------------------------------------------------	*/
	/**
	 * The maximumDataRate() is the method to return maximum data rate(MDR) that is computed using bandwidth and signalToNoise.
	 * @return the maximum data rate as a double
	 */
	public double getMaximumDataRate() { return maximumDataRate(bandwidth, signalToNoise); }
	
	/**
	 * The to String() is the method t return all data entered by user and displays maximum data rate
	 * @return String containing bandwidth, signal-to-noise ratio, and MDR (maximum data rate).
	 */
	@Override
	public String toString() {
		return "The MDR is " + String.format("%.2f", maximumDataRate(bandwidth, signalToNoise)) + " bits/sec\nwhen Bandwidth is " + bandwidth +
				" Hz and S/N ratio is " + signalToNoise + ".";
	}
	
	/* HELPER METHODS	--------------------------------------------------	*/
	/**
	 * The function for calculation of maximum data rate
	 * @param hertz the double value of bandwidth
	 * @param snr the double value of signalToNoise
	 * @return the maximum data rate as a double
	 */
	private static double maximumDataRate(double hertz, double snr) {
		return hertz * (Math.log(1.0 + Math.pow(10.0, snr / 10.0)) / Math.log(2));
	}

	/* ATTRIBUTES	-----------------------------------------------------	*/
	/** instance variable for bandwidth */
	private double bandwidth;
	/** instance variable for signal to noise (SNR) */
	private double signalToNoise; 
	
} /* End of CLASS: ShannonsModel.java */
