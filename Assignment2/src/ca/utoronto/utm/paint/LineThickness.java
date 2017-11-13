package ca.utoronto.utm.paint;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineThickness extends JFrame implements ChangeListener {
	
	static final int STROKE_MIN = 0;
	static final int STROKE_MAX = 50;
	private PaintPanel paintPanel;
	
	public LineThickness(PaintPanel paintPanel) {
		super();
		this.paintPanel = paintPanel;
		
		// creates a label.
		JLabel sliderLabel = new JLabel("Line Thickness", JLabel.CENTER);
		sliderLabel.setPreferredSize(new Dimension(20,20));
		
		
		
		
		// creates the slider.
		JSlider lineThickness = new JSlider(JSlider.HORIZONTAL, STROKE_MIN, STROKE_MAX, 
				paintPanel.getRenderingParameters().getStroke());
		
		lineThickness.addChangeListener(this);
		//turn on labels at major tick marks.
		lineThickness.setMajorTickSpacing(10);
		lineThickness.setMinorTickSpacing(1);
		lineThickness.setPaintTicks(true);
		lineThickness.setPaintLabels(true);
		lineThickness.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
		Font font = new Font("Serif", Font.ITALIC, 15);
		
		//add the components to the panel
		this.getContentPane().add(lineThickness, BorderLayout.CENTER);
		this.getContentPane().add(sliderLabel, BorderLayout.PAGE_END);
		this.pack();
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if (!source.getValueIsAdjusting()) {
			int stroke = (int)source.getValue();
			this.paintPanel.getRenderingParameters().setStroke(stroke);
		}
		
	}
	
}
	

	
