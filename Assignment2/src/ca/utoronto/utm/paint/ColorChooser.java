
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

/**
 * This class handles the ColorChooser panel for choosing colours with JColorChooser
 * @author repo_a2_realSlimShady
 *
 */
public class ColorChooser extends JPanel implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private JColorChooser colorChooser;
	private PaintPanel paintPanel;
	/**
	 * Constructor for ColorChooser, adds the action listener to it for changing colour, as well as sets its layout in the program
	 * @param paintPanel A PaintPanel object
	 */
	public ColorChooser(PaintPanel paintPanel) {
		super();
		this.paintPanel = paintPanel;
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		colorChooser.setPreviewPanel(new JPanel());
		this.add(colorChooser, BorderLayout.CENTER);
	}

	/**
	 * this function is called when the colour is changed in the ColorChooser pallet and sets the new colour for shapes and drawing to it.
	 */
	@Override
	public void stateChanged(ChangeEvent arg0) {
		Color newColor = colorChooser.getColor();
        this.paintPanel.getRenderingParameters().setColor(newColor); 
	}		
}
	

