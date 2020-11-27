package shared.screens;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.Constants;

public class NameEmailPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885054658167183324L;
	//Fields
	private JLabel labName, labEmail;
	
	//Constructor
	public NameEmailPanel(String name, String email)
	{
		//Initialization
		this.labName = new JLabel("Name");
		this.labEmail = new JLabel("Email");
		
		//Properties
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.labName.setFont(Constants.FONT_GENERAL_BOLD);
		this.labEmail.setForeground(Constants.COLOR_TEXT_GRAY);
		
		//Add to panel
		this.add(this.labName);
		this.add(this.labEmail);
	}
	
	
	//Testing
	public static void main(String args[])
	{
		//Initialization
		JFrame frame = new JFrame();
		NameEmailPanel nep = new NameEmailPanel("Chongyun", "chongyun@gmail.com");
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(nep);
		
		frame.setVisible(true);
	}
	
}
