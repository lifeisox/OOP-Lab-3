/** network is the package for class placement */ 
package network;

/*	Java packages required by this class. */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the View in the MVC architecture. It has an instance variable of type ShannonsController.<br>
 * This class implements the Java interface Observer making it an object of type observer. It will observer<br>
 * an observable object (the model).<br>
 * Use Spinner<br>
 * @author    Byung Seon Kim
 * @version   1.1.0 Oct 2016
 */
@SuppressWarnings("serial")
public class ShannonsSpinner extends JPanel implements Observer {

	/* CONSTRUCTOR	-----------------------------------------------------	*/
	/**
	 * Constructor for initialization of instance variables.<br> The constructor passes ShannonsController as a parameter
	 * <br> In here, ShannonsTheorem will be controller.
	 * @param ctl ctl is a ShannonsController
	 */
	public ShannonsSpinner(ShannonsController ctl) { 
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
			maxDataRateBar.setValue((int) model.getMaximumDataRate());
			maxDataRateBar.repaint();

			// if the receiving number of observer is greater than max scroll bar, change the max value of scroll bar
			int value = (int) model.getBandwidth();
			bandwidthJSpinner.setValue(value); // display bandwidth
			bandwidthBar.setValue(value);
			bandwidthBar.repaint();

			value = (int) model.getSignalToNoise();
			signalToNoiseJSpinner.setValue(value); // display signal to noise
			signalToNoiseBar.setValue(value);
			signalToNoiseBar.repaint();
		}
	}

	/* HELPER METHODS	--------------------------------------------------	*/
	/** 
	 * Compose Screen layout to display ShannonsTheorem
	 * The screen consist of one JPanel for graphics and two JPanel for JSpinner of bandwidth and signal to noise
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

		// enable maximum data rate bar
		maxDataRateBar = new Bar(29901); // set max
		maxDataRateBar.setBounds(15, 15, 400, 40); 
		maxDataRateBar.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		maxDataRateBar.setColor(Color.RED);
		// enable bandwidth bar
		bandwidthBar = new Bar(3000); // set max
		bandwidthBar.setBounds(15, 15, 400, 40); 
		bandwidthBar.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		bandwidthBar.setColor(Color.BLUE);
		// enable signal to noise bar
		signalToNoiseBar = new Bar(30); // set max
		signalToNoiseBar.setBounds(15, 15, 400, 40); 
		signalToNoiseBar.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		signalToNoiseBar.setColor(Color.GREEN);

		JPanel graph = new JPanel(new GridLayout(1, 3));
		graph.add(maxDataRateBar);
		graph.add(bandwidthBar);
		graph.add(signalToNoiseBar);
		// compose Bandwidth input panel
		JPanel bandwidthPanel = createBandwidthPanel();
		// compose Signal to noise ration input panel
		JPanel signalToNoisePanel = createSignalToNoisePanel();
		// set up three panels from top to down
		this.setLayout(new GridLayout(3, 1));
		this.add(graph);
		this.add(bandwidthPanel);
		this.add(signalToNoisePanel);
	}

	/** 
	 * Compose panel layout to display bandwidth input
	 * @return Compose panel layout to display bandwidth input
	 */
	private JPanel createBandwidthPanel() {
		JPanel bandwidthPanel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding
		bandwidthPanel.setBounds(15, 15, 400, 40); 
		bandwidthPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		// I can assign from 1 to 3001 for bandwidth
		bandwidthJSpinner = new JSpinner();
		SpinnerNumberModel numModel = new SpinnerNumberModel(0, 0, 3000, 1); // initial value, minimum value, maximum value, step
		bandwidthJSpinner.setModel(numModel);
		bandwidthPanel.add(new JLabel("   Bankwidth (in hertz):"), BorderLayout.WEST); // just add label
		bandwidthPanel.add(bandwidthJSpinner, BorderLayout.CENTER);
		// Adds the specified change listener to receive any change events from this component. 
		bandwidthJSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setBandwidth((double)((Integer) bandwidthJSpinner.getValue()));
				// after that, data in observable will be changed and observable object occurs notify to observer
			}
		});

		return bandwidthPanel;
	}

	/** 
	 * Compose panel layout to display Signal to noise input
	 * @return Compose panel layout to display Signal to noise input
	 */
	private JPanel createSignalToNoisePanel() {
		JPanel signalToNoisePanel = new JPanel(new BorderLayout()); // create base panel object
		signalToNoisePanel.setBounds(15, 15, 400, 40); 
		signalToNoisePanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(5, 10, 5, 10)));
		// I can assign from 1 to 31 for signal to noise ratio
		signalToNoiseJSpinner = new JSpinner(); 
		SpinnerNumberModel numModel = new SpinnerNumberModel(0, 0, 3000, 1); // initial value, minimum value, maximum value, step
		signalToNoiseJSpinner.setModel(numModel);
		signalToNoisePanel.add(new JLabel("SignalToNoise (in DB):"), BorderLayout.WEST); // just add label
		signalToNoisePanel.add(signalToNoiseJSpinner, BorderLayout.CENTER);
		// Adds the specified change listener to receive any change events from this component. 
		signalToNoiseJSpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setSignalToNoise((double)((Integer)signalToNoiseJSpinner.getValue()));
				// after that, data in observable will be changed and observable object occurs notify to observer
			}
		});

		return signalToNoisePanel;
	}

	/** 
	 * Inner class to draw bar graph in a JPanel using repaint function
	 * @author Byung Seon Kim
	 *
	 */
	private class Bar extends JPanel {
		int maxValue;
		int value;
		Color color;

		public Bar ( int maxValue ) { this.maxValue = maxValue; }

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int height = this.getHeight();
			int width = this.getWidth();
			int y2 = height * value / maxValue;
			g.setColor(color); // set bar color
			g.fillRect(0, height - y2, width, height); // fill rectangle
			g.setColor(Color.YELLOW);
			g.drawString(String.valueOf(value), 10, height - 18);
		}

		public void setValue( int value ) { this.value = value; }
		public void setColor( Color color ) { this.color = color; }
	}

	/* ATTRIBUTES	-----------------------------------------------------	*/
	/** ShannonsController to save as controller */
	private ShannonsController controller; 
	/** JLabel to display Maximum data rate */
	private JLabel maxDataRateLBL; 
	/** JSpinner to display bandwidth */
	private JSpinner bandwidthJSpinner; 
	/** JSpinner to display signal to noise ratio */
	private JSpinner signalToNoiseJSpinner; 
	/** inner class object to display maximum data rate */
	private Bar maxDataRateBar; 
	/** inner class object to display bandwidth */
	private Bar bandwidthBar; 
	/** inner class object to display signal to noise  */
	private Bar signalToNoiseBar; 
} /* End of CLASS: ShannonsPanel.java	*/
