package ca.utoronto.utm.paint;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class ShapeChooserPanel extends JPanel implements ActionListener {
	private View view; // So we can talk to our parent or other components of the view
	private JButton button;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public ShapeChooserPanel(View view) {	
		this.view=view;
		this.buttons = buttons;
		
		
		
		
		String[] buttonLabels = { "Circle", "Rectangle", "Squiggle", "Square" ,"Polyline" };
		this.setLayout(new GridLayout(buttonLabels.length,1));
		int index = 0;
		for (String label : buttonLabels) {
			button = new JButton(label);
			button.setActionCommand(label);		
			button.setOpaque(true);
			this.add(button);
			this.buttons.add(button);
			button.setBackground(Color.white);
			button.addActionListener(this);
			index++;
		}
		this.add(new FillPanel(this.view));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonSelected = (JButton)e.getSource();
		for (JButton button: this.buttons) {
			if (!(button.isEnabled())) {
				button.setEnabled(true);
				button.setBackground(Color.white);
			}
		}
		buttonSelected.setEnabled(false);
		buttonSelected.setBackground(Color.GREEN);	
		
		this.view.getPaintPanel().setMode(e.getActionCommand());	
	}
}
