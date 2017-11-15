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

import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategy;
import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategyFactory;
import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategyFactoryImpl;
import ca.utoronto.utm.paint.shape.RenderableShape;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * This class handles the panel for creating the buttons to choose drawing mode
 * @author repo_a2_realSlimShady
 *
 */
class ShapeChooserPanel extends JPanel implements ActionListener {
	private static final Logger LOG = Logger.getLogger(PaintModel.class.getName());
	
	private PaintPanel paintPanel; // So we can talk to our parent or other components of the view
	private int numClicks = 0;
	private JButton firstBt;
	private JButton secondBt;
	private List<JButton> buttons = new ArrayList<JButton>();
	private ShapeManipulatorStrategyFactory manipulatorFactory =  new ShapeManipulatorStrategyFactoryImpl();
	/**
	 * this constructor handles creates the buttons to choose drawing mode with images and adding the action listeners to them
	 * @param paintPanel A PaintPanel object
	 */
	public ShapeChooserPanel(PaintPanel paintPanel) {	
		this.paintPanel = paintPanel;
		
		ImageIcon[] buttonIcons = {
                new ImageIcon(getClass().getClassLoader().getResource("images/circle.png")), 
                new ImageIcon(getClass().getClassLoader().getResource("images/rectangle.png")),
        new ImageIcon(getClass().getClassLoader().getResource("images/square.png")),
        new ImageIcon(getClass().getClassLoader().getResource("images/squiggle.png")),
        new ImageIcon(getClass().getClassLoader().getResource("images/polyline.png")),
        new ImageIcon(getClass().getClassLoader().getResource("images/oval.png")),
        new ImageIcon(getClass().getClassLoader().getResource("images/triangle.png"))
        };
		
		String[] buttonLabels = {RenderableShape.LABEL_CIRCLE, RenderableShape.LABEL_RECTANGLE, RenderableShape.LABEL_SQUARE, RenderableShape.LABEL_SQUIGGLE, RenderableShape.LABEL_POLYLINE, RenderableShape.LABEL_OVAL, RenderableShape.LABEL_TRIANGLE};
		this.setLayout(new GridLayout(buttonIcons.length, 1));
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		int index = 0;
		for (String label : buttonLabels) {
			JButton button = new JButton(buttonIcons[index]);
			button.setActionCommand(label);
			button.setBackground(Color.WHITE);
			this.add(button);
			this.buttons.add(button);
			button.addActionListener(this);
			index++;
			
		}
	}
	
	/**
	 * disables the pressed button and enables the previously pressed ones
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton button: this.buttons) {
			if (button.isEnabled() == false) {
				button.setEnabled(true);
				button.setBackground(Color.WHITE);
				
			}
			JButton firstbt = (JButton)e.getSource();
			firstbt.setEnabled(false);
			firstbt.setBackground(Color.GREEN);
		}

		// Bug 2.3, Bug 2.4: Strategy Factory for creating different ShapeManipulatorStrategy instances
		ShapeManipulatorStrategy sm = manipulatorFactory.create(e.getActionCommand(), paintPanel);
		// then, install it in to the PaintPanel (Bug 2.3)
		paintPanel.setShapeManupulator(sm);
	}

	
}
