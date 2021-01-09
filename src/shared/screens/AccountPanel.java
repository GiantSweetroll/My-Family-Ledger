package shared.screens;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;

import main.Main;
import models.Admin;
import models.Person;
import screens.auth.SignIn;
import shared.Constants;
import shared.Globals;
import shared.components.HyperlinkLabel;

public class AccountPanel extends RoundedPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8111210618845993008L;
	
	//Fields
	private JLabel labUsername, labSignOut, labBalance;
	private Person person;
	
	//Constructor
	public AccountPanel()
	{
		super(false);
		//Initialization
		this.person = new Person("", "");
		this.labUsername = new HyperlinkLabel(this.person.getFullName());
		this.labSignOut = new HyperlinkLabel("Sign Out");
		this.labBalance = new JLabel("");
		
		//Properties
		this.setBackground(Color.WHITE);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.labSignOut.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Globals.activeUser = null;
						Main.changeScreen(new SignIn(), Constants.FRAME_SIGNIN);
					}
				});
		this.labUsername.setForeground(Color.BLACK);
		this.labUsername.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JDialog dialogProfile = new JDialog();
				dialogProfile.add(new MyProfilePage(person));
				dialogProfile.setModal(true);
				dialogProfile.setSize(700, 700);
				dialogProfile.setLocationRelativeTo(null);
				dialogProfile.setVisible(true);	
			}
		});
		//Add to panel
		this.add(this.labUsername);
		this.add(this.labBalance);
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
		if(!(person instanceof Admin)) {
			this.labBalance.setText("(Rp " + String.valueOf(Constants.DATABASE_SERVICE.getBalance(this.person.getID())) + ")");
		}
	}
}
