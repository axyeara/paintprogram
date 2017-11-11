package ca.utoronto.utm.paint;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

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
