package ca.utoronto.utm.paint;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
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
	private ColorChooser colorPanel;
	private FillPanel fillPanel;
	private LineThickness strokePanel;
	/**
	 * this constructor handles creating and placing all the respective panels together
	 * @param model A PaintModel object
	 */
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
		this.colorPanel = new ColorChooser(paintPanel);
		this.fillPanel = new FillPanel(paintPanel);
		this.strokePanel = new LineThickness(paintPanel);
		
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		c.add(this.colorPanel,BorderLayout.PAGE_END);
		this.colorPanel.setPreferredSize(new Dimension(200,300));
		c.add(this.strokePanel,BorderLayout.NORTH);
		c.add(this.fillPanel,BorderLayout.EAST);

		
		JScrollPane scrollBar = new JScrollPane(paintPanel);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(0, 0, 800, 450);
        c.add(scrollBar);
		this.pack();
		// this.setSize(200,200);
		this.setVisible(true);
		JOptionPane.showMessageDialog(paintPanel, "Welcome to Paint Application. To Begin Please Select a Shape and then a Color.");
	
		
        // on ESC key clear current rubber-band
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Escape Pressed");
        getRootPane().getActionMap().put("Escape Pressed", new AbstractAction(){
			private static final long serialVersionUID = 1L;
			/**
			 * function to clear drawing shape when esc is pressed 
			 */
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
/**
 * JMenuBar used to be used more extensively until panels were placed directly onto screen, but it still handles undo, redo, clear canvas
 * @return
 */
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

		return menuBar;
	}

	/**
	 * Bug 2.4 3: repaint at appropriate time through PaintModel's method
	 * such as undo, redo, and clearPlacedDrawingCommands
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Choose Color") {
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
