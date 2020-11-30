package screens.auth;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.TextAreaManager;
import shared.Constants;
import shared.Methods;
import shared.screens.CenteredPage;
import shared.screens.LogoLabel;

public class SignUpAdminConfirm extends CenteredPage
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8539652939195790606L;
	
	//Fields
	private JLabel labTitle;
	private JButton button;
	private LogoLabel logo;
	private JTextArea ta;
	private int id;
	
	//Constructor
	public SignUpAdminConfirm(int id)
	{
		//Initialization
		super();
		this.labTitle = new JLabel("Last Step!");
		this.logo = new LogoLabel();
		this.ta = new JTextArea(10, 20);
		this.button = new JButton("Ok");
		this.id = id;
		JPanel panel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		this.labTitle.setFont(Constants.FONT_SUB_TITLE);
		TextAreaManager.autoConfigureTextArea(this.ta, false);
		this.ta.setBorder(null);
		this.ta.setText("Thank you for registering as an admin. Please see below for your Admin ID:\n\n" +
						(id==0? "" : id) + 
						"\n\n" +
						"Share this to your other members so their account will be registered with you as the admin. You can view this again on the \"My Profile\" page.");
		this.button.setBackground(Constants.COLOR_BUTTON_BASE);
		this.button.setForeground(Color.WHITE);
		
		//Add to panel
		Gbm.goToOrigin(c);
		panel.add(this.logo, c);		//Logo
		Gbm.newGridLine(c);
		panel.add(this.labTitle, c);	//Title
		Gbm.newGridLine(c);
		c.insets = new Insets(10, 30, 5, 30);
		panel.add(this.ta, c);			//Text Area
		Gbm.newGridLine(c);
		panel.add(this.button, c);
		
		//Add to main panel
		this.setCenterPanel(panel);
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		SignUpAdminConfirm admin = new SignUpAdminConfirm(0);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(admin);
		
		frame.setVisible(true);
	}
}
