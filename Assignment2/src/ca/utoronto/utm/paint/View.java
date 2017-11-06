package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
/**
 * This is the top level View+Controller, it contains other aspects of the View+Controller.
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private PaintModel model;		// This is Observable
	
	// The components that make this up
	private PaintPanel paintPanel;		// This is Observer
	private ShapeChooserPanel shapeChooserPanel;
	private ColorChooser colorFrame;
	private FillChooser fillFrame;
	private LineThickness strokePanel;
	Color defaultColor = Color.RED;
	Color fillColor;
	boolean fillState = false;
	int defaultStroke;
	
	
	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());
		
		Container c=this.getContentPane();
		// c.add(new JButton("North"),BorderLayout.NORTH);
		// c.add(new JButton("South"),BorderLayout.SOUTH);
		// c.add(new JButton("East"),BorderLayout.EAST);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.colorFrame = new ColorChooser(this);
		this.fillFrame = new FillChooser(this);
		this.strokePanel = new LineThickness(this);
		
		c.add(this.shapeChooserPanel,BorderLayout.WEST);
		this.model=model;
		this.paintPanel = new PaintPanel(model, this);
		c.add(this.paintPanel, BorderLayout.CENTER);
		
		this.pack();
		// this.setSize(200,200);
		this.setVisible(true);
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

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Choose Color") {
			this.colorFrame.setVisible(true);
		}
		else if (e.getActionCommand() == "Choose Fill") {
			this.fillFrame.setVisible(true);
			this.fillState = true;
		}
		else if (e.getActionCommand() == "Cancel Fill") {
			this.fillState = false;
		}
		
		else if (e.getActionCommand() == "Choose Line Thickness") {
			this.strokePanel.setVisible(true);
		}
	}
}
