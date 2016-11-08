/** network is the package for class placement */ 
package network;

/*	Java packages required by this class. */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * This class is the View in the MVC architecture. It has an instance variable of type ShannonsController.<br>
 * This class implements the Java interface Observer making it an object of type observer. It will observer<br>
 * an observable object (the model).<br>
 * Use TextField <br>
 * @author    Byung Seon Kim
 * @version   1.1.0 Oct 2016
 */
@SuppressWarnings("serial")
public class ShannonsPanel extends JPanel implements Observer {

	/* CONSTRUCTOR	-----------------------------------------------------	*/
	/**
	 * Constructor for initialization of instance variables.<br> The constructor passes ShannonsController as a parameter
	 * <br> In here, ShannonsTheorem will be controller.
	 * @param ctl ctl is a ShannonsController
	 */
	public ShannonsPanel(ShannonsController ctl) { 
		controller = ctl; 
		maxDataRateLBL = new JLabel();
		initGUI();
	}

	/* ACCESSORS	-----------------------------------------------------	*/
	/**
	 * Getter for maxDataRateLBL JLabel in order to display maximum data rate
	 * @return return JLabel contained maximum data rate
	 */
	public JLabel getMaxDataRateLBL() {
		return maxDataRateLBL;
	}

	/* MODIFIERS	-----------------------------------------------------	*/
	/**
	 * Setter to set maxDataRateLBL JLabel in order to display maximum data rate
	 * @param ctl ctl is JLabel contained maximum data rate to change
	 */
	public void setMaxDataRateLBL(JLabel ctl) {
		this.maxDataRateLBL = ctl;
	}

	/**
	 * set the controller to parameter ctl
	 * @param ctl ctl is he parameter to set
	 */
	public void setController(ShannonsController ctl) {
		this.controller = ctl;
	}

	/*	NORMAL BEHAVIOR -------------------------------------------------	*/
	/**
	 * Called when a change has occurred in the state of the observable.
	 * In this case, when data updated, MDL label should be changed
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ShannonsModel) {
			ShannonsModel model = (ShannonsModel) o;
			maxDataRateLBL.setText(String.format("%.2f", model.getMaximumDataRate())); // display the max data rate
			bandwidthJTextField.setText(String.format("%.0f", model.getBandwidth()));
			signalToNoiseJTextField.setText(String.format("%.0f", model.getSignalToNoise()));
		}
	}

	/* HELPER METHODS	--------------------------------------------------	*/
	/** 
	 * Compose Screen layout to display ShannonsTheorem (GUI)
	 */
	private void initGUI() {
		// compose Maximum data rate display panel
		JPanel mdrPanel = new JPanel();
		// make border and padding
		mdrPanel.setBounds(15, 15, 400, 40); 
		mdrPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		mdrPanel.setLayout(new GridLayout(1, 2));
		JLabel message = new JLabel("Maximum data rate via Shannons Theorem = ");
		// vertical alignment -> center
		message.setVerticalAlignment(JLabel.CENTER);
		mdrPanel.add(message);
		maxDataRateLBL.setVerticalAlignment(JLabel.CENTER);
		mdrPanel.add(maxDataRateLBL);
		
		// compose Bandwidth input panel
		JPanel bandwidthPanel = createBandwidthPanel();
		// compose Signal to noise ration input panel
		JPanel signalToNoisePanel = createSignalToNoisePanel();
		// set up three panels from top to down
      
		this.setLayout(new GridLayout(3, 1));
		this.add(mdrPanel);
		this.add(bandwidthPanel);
		this.add(signalToNoisePanel);
	}
	
	/** 
	 * Compose panel layout to display bandwidth input
	 * @return Return JPanel that have a layout for bandwidth input
	 */
	private JPanel createBandwidthPanel() {
		JPanel bandwidthPanel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding
		bandwidthPanel.setBounds(15, 15, 400, 40); 
		bandwidthPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		bandwidthJTextField = new JTextField(); // create input field for bandwidth
		bandwidthPanel.add(new JLabel("   Bankwidth (in hertz):"), BorderLayout.WEST); // just add label
		bandwidthPanel.add(bandwidthJTextField, BorderLayout.CENTER);
		// Adds the specified key listener to receive key events from this component. 
		// If l is null, no exception is thrown and no action is performed. 
//		bandwidthJTextField.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//				double htz = Double.parseDouble(bandwidthJTextField.getText());
//				if (htz > 3000) htz = 3000;
//				controller.setBandwidth(htz);
//				} catch(NumberFormatException exception) {
//				JOptionPane.showMessageDialog(ShannonsPanel.this, 
//						"Numeric data ONLY!!! Please enter a valid number!", "Warning Error!", JOptionPane.ERROR_MESSAGE);
//				}
//			}
//		});
		

		bandwidthJTextField.addKeyListener(new KeyListener() {
			// change to be able to input numeric key
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					e.consume();
				} 
			}
			// change data in observable and observable object occurs notify to observer
			@Override
			public void keyReleased(KeyEvent e) { 
				try {
					double htz = Double.parseDouble(bandwidthJTextField.getText());
					if (htz > 3000) htz = 3000;
					controller.setBandwidth(htz);
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(ShannonsPanel.this, 
							"Numeric data ONLY!!! Please enter a valid number!", "Warning Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) { }
		});

		return bandwidthPanel;
	}
	
	/**
	 * Compose panel layout to display Signal to noise input
	 * @return Return JPanel that have a layout for Signal to Noise input
	 */
	private JPanel createSignalToNoisePanel() {
		JPanel signalToNoisePanel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding
		signalToNoisePanel.setBounds(15, 15, 400, 40); 
		signalToNoisePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		signalToNoiseJTextField = new JTextField(); // create input field for bandwidth
		signalToNoisePanel.add(new JLabel("SignalToNoise (in DB):"), BorderLayout.WEST); // just add label
		signalToNoisePanel.add(signalToNoiseJTextField, BorderLayout.CENTER);
		// Adds the specified key listener to receive key events from this component. 
		// If l is null, no exception is thrown and no action is performed. 
		signalToNoiseJTextField.addKeyListener(new KeyListener() {
			// change to be able to input numeric key
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					e.consume();
				}
			}
			// change data in observable and observable object occurs notify to observer
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					double stn = Double.parseDouble(signalToNoiseJTextField.getText());
					if (stn > 30) stn = 30;
					controller.setSignalToNoise(stn);
				} catch(NumberFormatException exception) {
					JOptionPane.showMessageDialog(ShannonsPanel.this, 
							"Numeric data ONLY!!! Please enter a valid number!", "Warning Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			@Override
			public void keyPressed(KeyEvent e) { }
		});

		return signalToNoisePanel;
	}
	
	
	/* ATTRIBUTES	-----------------------------------------------------	*/
	/** ShannonsController to save as controller */
	private ShannonsController controller; 
	/** JLabel to display Maximum data rate */
	private JLabel maxDataRateLBL; 
	/** JTextField to display bandwidth */
	private JTextField bandwidthJTextField; 
	/** JTextField to display signal to noise ratio */
	private JTextField signalToNoiseJTextField; 
	
} /* End of CLASS: ShannonsPanel.java	*/
