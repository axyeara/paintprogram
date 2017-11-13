package ca.utoronto.utm.paint;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(View.class.getName());
	
	private PaintModel model;		// This is Observable
	
	// The components that make this up
	private PaintPanel paintPanel;		// This is Observer
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooser colorFrame;
	private FillChooser fillFrame;
	private LineThickness strokePanel;
	
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		
		Container c=this.getContentPane();
		
		this.model=model;
		this.paintPanel = new PaintPanel(model);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		// c.add(new JButton("North"),BorderLayout.NORTH);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(paintPanel);
		this.colorFrame = new ColorChooser(paintPanel);
		this.fillFrame = new FillChooser(paintPanel);
		this.strokePanel = new LineThickness(paintPanel);
		
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		
		this.pack();
		// this.setSize(200,200);
		this.setVisible(true);
		
        // on ESC key clear current rubber-band
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape Pressed");
        getRootPane().getActionMap().put("Escape Pressed", new AbstractAction(){
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
            {
            	LOG.info("ESC Pressed, clear drawing shape");
            	if (paintPanel.getShapeManipulator() != null) {
            		paintPanel.getShapeManipulator().reset(); // clear current rubber-band.
            	}
            	model.changed(); // notify observers to clear rubber-band from the canvas.
            }
        });
	}

	public PaintPanel getPaintPanel(){
		return paintPanel;
	}
	
	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu("File");

		// a group of JMenuItems
		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		menu = new JMenu("Edit");

		// a group of JMenuItems
		menuItem = new JMenuItem("Cut");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Copy");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Paste");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Undo");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();// -------------
		menuItem = new JMenuItem("Clear");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Color");
		
		menuItem = new JMenuItem("Choose Color");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Fill");
		
		menuItem = new JMenuItem("Choose Fill");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menuItem = new JMenuItem("Cancel Fill");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		menu = new JMenu("Line Thickness");
		
		menuItem = new JMenuItem("Choose Line Thickness");
		menuItem.addActionListener(this);
		menu.add(menuItem);
			
		menuBar.add(menu);
		
		return menuBar;
	}

	/**
	 * Bug 2.4 3: repaint at appropriate time through PaintModel's method
	 * such as undo, redo, and clearPlacedDrawingCommands
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Choose Color") {
			this.colorFrame.setVisible(true);
		}
		else if (e.getActionCommand() == "Choose Fill") {
			this.fillFrame.setVisible(true);
			this.paintPanel.getRenderingParameters().setFillState(true);
		}
		else if (e.getActionCommand() == "Cancel Fill") {
			this.paintPanel.getRenderingParameters().setFillState(false);
		}
		
		else if (e.getActionCommand() == "Choose Line Thickness") {
			this.strokePanel.setVisible(true);
		}
		else if (e.getActionCommand() == "Undo") {
			LOG.info("Undo");
			this.model.undo();
		}
		else if (e.getActionCommand() == "Redo") {
			LOG.info("Redo");
			this.model.redo();
		}
		else if (e.getActionCommand() == "Clear") {
			LOG.info("Clear");
			this.model.clearPlacedDrawingCommands();
		}
	}

}
