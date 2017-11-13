package ca.utoronto.utm.paint.wip;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * the panel to switch between Fill and outline drawing mode
 * @author morganwi
 *
 */

public class FillPanel extends JPanel implements ActionListener{
	private View view;
	private JButton button1;
	private JButton button2;
	
	//constructor 
	
	public FillPanel(View view) {
		this.view = view;
		this.setLayout(new GridLayout(1, 2));
		button1 = new JButton("Outline");
		button2 = new JButton("Solid");
		button1.setEnabled(false);
		button1.setActionCommand("Outline");
		button2.setActionCommand("Solid");
		this.add(button1);
		button1.setBackground(Color.white);
		button1.addActionListener(this);
		this.add(button2);
		button2.setBackground(Color.white);
		button2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==button1.getActionCommand()) {
			button1.setEnabled(false);
			button1.setBackground(Color.green);
			button2.setEnabled(true);
			button2.setBackground(Color.white);
			this.view.getPaintPanel().setFillState(e.getActionCommand());
		}
		else {
			button2.setEnabled(false);
			button2.setBackground(Color.green);
			button1.setEnabled(true);
			button1.setBackground(Color.white);
			this.view.getPaintPanel().setFillState(e.getActionCommand());
		}
	}
}
