
package ca.utoronto.utm.paint;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooser extends JPanel implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private JColorChooser colorChooser;
	private PaintPanel paintPanel;
	
	public ColorChooser(PaintPanel paintPanel) {
		super();
		this.paintPanel = paintPanel;
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorChooser.setPreviewPanel(new JPanel());
		this.add(colorChooser, BorderLayout.CENTER);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		Color newColor = colorChooser.getColor();
        this.paintPanel.getRenderingParameters().setColor(newColor); 
	}		
}
	

