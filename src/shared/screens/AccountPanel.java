package shared.screens;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import models.Person;
import shared.Constants;

public class AccountPanel extends RoundedPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111210618845993008L;
	
	//Fields
	private JLabel labUsername, labSignOut;
	private Person person;
	
	//Constructor
	public AccountPanel()
	{
		super(false);
		//Initialization
		this.person = new Person("", "");
		this.labUsername = new JLabel(this.person.getFullName());
		this.labSignOut = new JLabel("Sign Out");
		
		//Properties
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.labSignOut.setForeground(Constants.COLOR_HYPERLINK);
		
		//Add to panel
		this.add(this.labUsername);
		this.add(this.labSignOut);
	}
	
	//Public Methods
	/**
	 * Set the active account.
	 * @param person a Person object
	 */
	public void setAccount(Person person)
	{
		this.person = person;
		this.labUsername.setText(this.person.getFullName());
	}
}
