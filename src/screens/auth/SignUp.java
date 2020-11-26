package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import giantsweetroll.gui.swing.Gbm;
import shared.CenteredPage;
import shared.Constants;
import shared.LogoLabel;
import shared.Methods;
import shared.TextFieldHintListener;

public class SignUp extends CenteredPage
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
						tfEmail,
						tfAdminID;
	private JTextPane taTnC;
	private JPasswordField tfPass, tfConfirmPass;
	private JButton butSignUp;
	private boolean asAdmin;
	
	//Constructor
	public SignUp(boolean asAdmin)
	{
		//Initialization
		super();
		this.asAdmin = asAdmin;
		this.initCenter();
		
		//Properties
		this.setCenterPanel(this.panelMain);
	}
	
	//Private Methods
	private void initTFAdminID()
	{
		//Initialization
		this.tfAdminID = new JTextField();
		
		//Properties
		this.tfAdminID.getDocument().addDocumentListener(new TextFieldHintListener(this.tfAdminID, "Admin ID"));
	}
	private void initCenter()
	{
		//Initialization
		this.panelMain = new JPanel(new BorderLayout());
		this.logo = new LogoLabel();
		this.labSignUp = new JLabel("Sign Up");
		this.tfFirstName = new JTextField();
		this.tfLastName = new JTextField();
		this.tfEmail = new JTextField();
		this.tfPass = new JPasswordField();
		this.tfConfirmPass = new JPasswordField();
		this.butSignUp = new JButton("Sign up");
		this.labBack = new JLabel("Back");
		this.taTnC = new JTextPane();
		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		StyledDocument doc = this.taTnC.getStyledDocument();
		SimpleAttributeSet centerAtt = new SimpleAttributeSet();
		if (!this.asAdmin)
		{
			this.initTFAdminID();
		}
		
		//Properties
		panelCenter.setBackground(Color.WHITE);
		panelTop.setBackground(Color.WHITE);
		this.panelMain.setBackground(Color.WHITE);
		this.labSignUp.setFont(Constants.FONT_SUB_TITLE);
		this.labBack.setFont(Constants.FONT_SMALLER);
		this.labBack.setForeground(Constants.COLOR_HYPERLINK);
		this.tfFirstName.getDocument().addDocumentListener(new TextFieldHintListener(this.tfFirstName, "First Name"));
		this.tfLastName.getDocument().addDocumentListener(new TextFieldHintListener(this.tfLastName, "Last Name"));
		this.tfEmail.getDocument().addDocumentListener(new TextFieldHintListener(this.tfEmail, "Email"));
		this.tfPass.getDocument().addDocumentListener(new TextFieldHintListener(this.tfPass, "Password"));
		this.tfConfirmPass.getDocument().addDocumentListener(new TextFieldHintListener(this.tfConfirmPass, "Confirm Password"));
		this.butSignUp.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSignUp.setForeground(Color.WHITE);
		this.taTnC.setFont(Constants.FONT_SMALLER);
		this.taTnC.setEditable(false);
		this.taTnC.setForeground(Constants.COLOR_TEXT_GRAY);
		this.taTnC.setText("By signing up you agree to our\nTerms & Conditions");
		StyleConstants.setAlignment(centerAtt, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), centerAtt, false);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labBack);
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 100, 5, 100);
		panelCenter.add(this.logo, c);					//Logo
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 100, 25, 100);
		panelCenter.add(this.labSignUp, c);				//Sign Up
		c.insets = new Insets(5, 100, 5, 100);
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		panelCenter.add(this.tfFirstName, c);			//First name text field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfLastName, c);			//Last name text field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfEmail, c);				//Email text field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfPass, c);				//Password text field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfConfirmPass, c);			//Confirm Password text field
		if (!this.asAdmin)
		{
			Gbm.newGridLine(c);
			panelCenter.add(this.tfAdminID,c);			//Admin ID
		}
		c.insets = new Insets(20, 100, 5, 100);
		Gbm.newGridLine(c);
		panelCenter.add(this.butSignUp, c);				//Sign up button
		c.insets = new Insets(10, 100, 20, 100);
		Gbm.newGridLine(c);
		panelCenter.add(this.taTnC, c);					//Terms and Conditions
		//Add to panelMain
		this.panelMain.add(panelTop, BorderLayout.NORTH);
		this.panelMain.add(panelCenter, BorderLayout.CENTER);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new SignUp(false);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}
}
