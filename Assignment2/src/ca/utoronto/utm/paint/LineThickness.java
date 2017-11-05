package ca.utoronto.utm.paint;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LineThickness extends JFrame implements ChangeListener {
	
	static final int strokeMin = 0;
	static final int strokeMax = 50;
	static final int strokeInit = 25;
	private View view;
	
	public LineThickness(View view) {
		super();
		this.view = view;
		
		// creates a label.
		JLabel sliderLabel = new JLabel("Line Thickness", JLabel.CENTER);
		sliderLabel.setPreferredSize(new Dimension(20,20));
		
		
		
		
		// creates the slider.
		JSlider lineThickness = new JSlider(JSlider.HORIZONTAL, strokeMin, strokeMax, 
				strokeInit);
		
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
			this.view.defaultStroke = stroke;
		}
		
	}
	
}
	

	
