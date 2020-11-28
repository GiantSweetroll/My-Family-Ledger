package shared.screens;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Person;
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
		this.labName = new JLabel(name);
		this.labEmail = new JLabel(email);
		
		//Properties
		this.setOpaque(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.labName.setFont(Constants.FONT_GENERAL_BOLD);
		this.labEmail.setForeground(Constants.COLOR_TEXT_GRAY);
		
		//Add to panel
		this.add(this.labName);
		this.add(this.labEmail);
	}
	
	//Public Methods
	/**
	 * Get the full name.
	 * @return
	 */
	public String getFullName()
	{
		return this.labName.getText();
	}
	/**
	 * Set the full name to be displayed.
	 * @param name
	 */
	public void setFullName(String name)
	{
		this.labName.setText(name);
	}
	/**
	 * Get the email text.
	 * @return
	 */
	public String getEmail()
	{
		return this.labEmail.getText();
	}
	/**
	 * Set the email text.
	 * @param email
	 */
	public void setEmail(String email)
	{
		this.labEmail.setText(email);
	}
	/**
	 * Set the name and email to be displayed. 
	 * This method is equivalent to calling both the setFullName() and setEmail() methods.
	 * @param name
	 * @param email
	 */
	public void setNameEmail(String name, String email)
	{
		this.setFullName(name);
		this.setEmail(email);
	}
	/**
	 * Set the name and email to be displayed. The name and email will be taken from the Person object.
	 * This method is equivalent to calling both the setFullName() and setEmail() methods.
	 * @param person a Person class object.
	 */
	public void setNameEmail(Person person)
	{
		this.setFullName(person.getFullName());
		this.setEmail(person.getEmail());
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
