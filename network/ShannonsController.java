/** network is the package for class placement */ 
package network;

import java.util.Observer;

/**
 * This is an interface. It defines the operations (methods) available in the controller.
 * @author    Byung Seon Kim
 * @version   1.1.0 Oct 2016
 */
public interface ShannonsController {
	/**
	 * Adds an observer to the internal list of observers.
	 * @param o observer
	 */
	public void addObserver(Observer o);
	/**
	 * The setBandwidth(double bandwidth) is the method to set the instance variable, bandwidth.
	 * @param bandwidth the double value of bandwidth to set
	 */
	public void setBandwidth(double bandwidth);
	/**
	 * The setSignalToNoise(double signalToNoise) is the method to set the instance variable, signal to noise ratio.
	 * @param signalToNoiseRatio the double value of signalToNoise to set
	 */
	public void setSignalToNoise(double signalToNoiseRatio);
	
} /* End of CLASS: ShannonsController.java	*/
