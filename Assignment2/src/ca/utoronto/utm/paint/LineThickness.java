package ca.utoronto.utm.paint;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineThickness extends JPanel implements ChangeListener {
	
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
		this.add(sliderLabel, BorderLayout.SOUTH);
		this.add(lineThickness, BorderLayout.NORTH);
	
		
	
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider sliderSource = (JSlider)e.getSource();
		this.view.getPaintPanel().setLineThickness(sliderSource.getValue());
		
	}
	
}
	

	
