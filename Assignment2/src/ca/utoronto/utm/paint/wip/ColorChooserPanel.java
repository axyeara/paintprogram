package ca.utoronto.utm.paint.wip;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ca.utoronto.utm.paint.wip.View;

public class ColorChooserPanel extends JPanel implements ChangeListener {
	private JColorChooser colorChooser;
	private View view;

	public ColorChooserPanel(View view) {
		super(new BorderLayout());
		this.view = view;
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorChooser.setPreviewPanel(new JPanel());
		this.add(colorChooser, BorderLayout.CENTER);
		
	}
	
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		Color sourceColor = colorChooser.getColor();
		this.view.getPaintPanel().setColor(sourceColor);
		
	}
	
}
