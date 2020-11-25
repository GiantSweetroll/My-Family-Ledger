package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import giantsweetroll.gui.swing.Gbm;
import shared.CenteredPage;
import shared.Constants;
import shared.LogoLabel;

public class SignUpAdmin extends CenteredPage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9023797595035623318L;

	//Fields
	private JPanel panelMain;
	private LogoLabel logo;
	private JLabel labSignUp, 
					labBack;
	private JTextField tfFirstName,
						tfLastName,
						tfEmail;
	private JTextArea taTnC;
	private JPasswordField tfPass, tfConfirmPass;
	private JButton butSignUp;
	
	//Constructor
	public SignUpAdmin()
	{
		//Initialization
		super();
	}
	
	//Private Methods
	private void initCenter()
	{
		//Initialization
		this.panelMain = new JPanel(new BorderLayout());
		this.tfFirstName = new JTextField(20);
		this.tfLastName = new JTextField(20);
		this.tfEmail = new JTextField(20);
		this.tfPass = new JPasswordField(20);
		this.tfConfirmPass = new JPasswordField(20);
		this.butSignUp = new JButton("Sing up");
		this.labBack = new JLabel("Back");
		this.taTnC = new JTextArea("By signing up you agree to our Terms & Conditions");
		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.labSignUp.setFont(Constants.FONT_SUB_TITLE);
		this.labBack.setFont(Constants.FONT_SMALLER);
		this.labBack.setForeground(Constants.COLOR_HYPERLINK);
		this.butSignUp.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSignUp.setForeground(Color.WHITE);
		this.taTnC.setFont(Constants.FONT_SMALLER);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labBack);
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 5, 80);
		panelCenter.add(this.logo, c);			
		Gbm.newGridLine(c);
		panelCenter.add(this.labSignUp, c);
	}
}
