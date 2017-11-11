package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Panel so the user can adjust linethickness 
 * @author morganwi
 *
 */
public class LineThicknessPanel extends JPanel implements ChangeListener{
	static final int strokeMin = 0;
	static final int strokeMax = 50;
	static final int strokeInit = 25;
	private View view;
	private JSlider slider;
	private JLabel text;
	
	/**
	 * constructor
	 * @param view this View
	 */
	
	public LineThicknessPanel(View view) {
		this.view = view;
		
		this.setLayout(new GridLayout(2, 1));
		text = new JLabel("Line Thickness",SwingConstants.CENTER);
		this.add(text);
		slider = new JSlider(strokeMin,strokeMax,strokeInit);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		this.add(slider);
		slider.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		this.view.getPaintPanel().setLineThickness(slider.getValue());
		
	}
}
