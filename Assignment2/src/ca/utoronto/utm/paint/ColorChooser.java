
package ca.utoronto.utm.paint;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.colorchooser.*;

public class ColorChooser extends JFrame implements ChangeListener {

	protected JColorChooser colorChooser;
	protected JLabel title;
	private PaintPanel paintPanel;
	private View view;
	
	public ColorChooser(View view) {
		super();
		this.view = view;
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
        this.view.defaultColor = newColor;
	}		
}
	

