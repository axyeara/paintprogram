package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private int numClicks = 0;
	private JButton firstBt;
	private JButton secondBt;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public ShapeChooserPanel(View view) {	
		this.view=view;
		
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
		this.view.getPaintPanel().setMode(e.getActionCommand());
		//System.out.println(e.getActionCommand());
	}

	
}
