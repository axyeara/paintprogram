
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

public class FillChooser extends JFrame implements ChangeListener {

	protected JColorChooser colorChooser;
	protected JLabel title;
	private PaintPanel paintPanel;
	
	public FillChooser(PaintPanel paintPanel) {
		super();
		this.paintPanel = paintPanel;
		title = new JLabel("Choose your color!", JLabel.CENTER);
		title.setForeground(Color.BLACK);
		title.setOpaque(true);
		title.setPreferredSize(new Dimension(10,10));
		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.add(title, BorderLayout.CENTER);
		
		//Sets up the color chooser
		colorChooser = new JColorChooser(title.getForeground());
		colorChooser.getSelectionModel().addChangeListener(this);
		
		//added the components to the panel
		this.getContentPane().add(titlePanel, BorderLayout.CENTER);
		this.getContentPane().add(colorChooser, BorderLayout.PAGE_END);
		this.pack();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		Color newColor = colorChooser.getColor();
        title.setForeground(newColor);
        this.paintPanel.setLineColor(newColor);
	}		
}
	

