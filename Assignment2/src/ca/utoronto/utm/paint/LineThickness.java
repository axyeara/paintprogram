package ca.utoronto.utm.paint;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * this class handles the line thickness bar for when a user is on outline mode using JSlider
 * @author repo_a2_realSlimShady
 *
 */
public class LineThickness extends JPanel implements ChangeListener {
	
	static final int strokeMin = 0;
	static final int strokeMax = 50;
	static final int strokeInit = 0;
	private PaintPanel paintPanel;
	private View view;
	private JSlider slider;
	private JLabel text;
	
	/**
	 * this constructor creates the LineThickness JSlider at the top of the screen and sets up the action listener for it
	 * @param paintPanel A PaintPanel object
	 */
	public LineThickness(PaintPanel paintPanel) {
		super();
		this.paintPanel = paintPanel;	
		this.setLayout(new GridLayout(2, 1));
		text = new JLabel("Line Thickness",SwingConstants.CENTER);
		this.add(text);
		slider = new JSlider(strokeMin,strokeMax,strokeInit);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		this.add(slider);
		slider.addChangeListener(this);
		
	}
	
	/**
	 * 
	 * This method is called when the slider is moved and updates the stroke thickness of the current shape being drawn.
	 * 	NOTE: the shape drawing handles to get the stroke thickness if on outline mode
	 */
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int stroke = (int)source.getValue();
			this.paintPanel.getRenderingParameters().setStroke(stroke);
		}
		
	}
	
}
	

	
