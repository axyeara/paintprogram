package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategyFactory;
import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategy;
import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategyFactoryImpl;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private static final Logger LOG = Logger.getLogger(PaintModel.class.getName());
	
	private PaintPanel paintPanel; // So we can talk to our parent or other components of the view
	private int numClicks = 0;
	private JButton firstBt;
	private JButton secondBt;
	private List<JButton> buttons = new ArrayList<JButton>();
	private ShapeManipulatorStrategyFactory manipulatorFactory =  new ShapeManipulatorStrategyFactoryImpl();
	
	public ShapeChooserPanel(PaintPanel paintPanel) {	
		this.paintPanel = paintPanel;
		
		ImageIcon rectangle = new ImageIcon(ShapeChooserPanel.class.getClassLoader()
                .getResource("images/rectangle.gif"));
		ImageIcon circle = new ImageIcon(ShapeChooserPanel.class.getClassLoader()
                .getResource("images/circle.gif"));
		ImageIcon square = new ImageIcon(ShapeChooserPanel.class.getClassLoader()
                .getResource("images/square.gif"));
		ImageIcon squiggle = new ImageIcon(ShapeChooserPanel.class.getClassLoader()
                .getResource("images/squiggle.png"));
		ImageIcon polyline = new ImageIcon(ShapeChooserPanel.class.getClassLoader()
                .getResource("images/polyline.gif"));
		
		ImageIcon[] buttonIcons = {circle, rectangle, square, squiggle, polyline};
		String[] buttonLabels = {"Circle", "Rectangle", "Square", "Squiggle", "Polyline"};
		this.setLayout(new GridLayout(buttonIcons.length, 1));
		int index = 0;
		for (ImageIcon icon : buttonIcons) {
			JButton button = new JButton(icon);
			button.setActionCommand(buttonLabels[index]);
			button.setBackground(Color.WHITE);
			this.add(button);
			this.buttons.add(button);
			button.addActionListener(this);
			index++;
		}
	}
	
	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton button: this.buttons) {
			if (button.isEnabled() == false) {
				button.setEnabled(true);
				
			}
			JButton firstbt = (JButton)e.getSource();
			firstbt.setEnabled(false);
		}

		// Bug 2.4 Strategy Factory
		ShapeManipulatorStrategy sm = manipulatorFactory.create(e.getActionCommand(), paintPanel);
		paintPanel.setShapeManupulator(sm);
	}

	
}
