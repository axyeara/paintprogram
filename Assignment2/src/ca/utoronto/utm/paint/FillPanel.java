
package ca.utoronto.utm.paint;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 	This class handles the two methods of drawing a shape by either filling them or just outlining them
 * @author repo_a2_realSlimShady
 *
 */
public class FillPanel extends JPanel implements ActionListener{
	private JButton button1;
	private JButton button2;
	private PaintPanel paintPanel;
	
	 
	/**
	 * 	This constructor creates the buttons of outline and fill as well as sets their action listeners
	 * @param paintPanel A PaintPanel object
	 */
	public FillPanel(PaintPanel paintPanel) {
		this.paintPanel = paintPanel;
		this.setLayout(new GridLayout(2, 1));
		ImageIcon[] buttonIcons = {//incase we ever add more buttons to this panel use an array.
                new ImageIcon(getClass().getClassLoader().getResource("images/outline.png")), 
                new ImageIcon(getClass().getClassLoader().getResource("images/fill.png"))
        };
		
		
		button1 = new JButton(buttonIcons[0]);
		button2 = new JButton(buttonIcons[1]);
		button1.setEnabled(false);
		button1.setActionCommand("Outline");
		button2.setActionCommand("Solid");
		this.add(button1);
		button1.setBackground(Color.GREEN);
		button1.addActionListener(this);
		this.add(button2);
		button2.setBackground(Color.white);
		button2.addActionListener(this);
	}

	/**
	 * This method handles what happens when a button is pressed to change the fill or outline mode.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==button1.getActionCommand()) {
			button1.setEnabled(false);
			button1.setBackground(Color.green);
			button2.setEnabled(true);
			button2.setBackground(Color.white);
			this.paintPanel.getRenderingParameters().setFillState(e.getActionCommand()); 
			//disable button that is pressed and enable the other one
			//and pass in the new fill state.
			
		}
		else {
			button2.setEnabled(false);
			button2.setBackground(Color.green);
			button1.setEnabled(true);
			button1.setBackground(Color.white);
			this.paintPanel.getRenderingParameters().setFillState(e.getActionCommand()); 
			
			
		}
	}
}


