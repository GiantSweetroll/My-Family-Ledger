package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import main.Main;
import models.Account;
import models.Admin;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.TextFieldHintListener;
import shared.components.HintPasswordField;
import shared.components.HintTextField;
import shared.components.HyperlinkLabel;
import shared.components.LogoLabel;
import shared.components.WarningLabel;
import shared.screens.CenteredPage;

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
					labBack,
					labWarning;
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
		this.tfAdminID = new HintTextField("Admin ID");
		
		//Properties
		this.tfAdminID.getDocument().addDocumentListener(new TextFieldHintListener(this.tfAdminID, "Admin ID"));
		this.tfAdminID.setToolTipText("Ask your Admin for their ID to link your accounts");
	}
	private void initCenter()
	{
		//Initialization
		this.panelMain = new JPanel(new BorderLayout());
		this.logo = new LogoLabel(LogoLabel.SMALL);
		this.labSignUp = new JLabel("Sign Up");
		this.tfFirstName = new HintTextField("First Name");
		this.tfLastName = new HintTextField("Last Name");
		this.tfEmail = new HintTextField("Email");
		this.tfPass = new HintPasswordField("Password");
		this.tfConfirmPass = new HintPasswordField("Confirm Password");
		this.labWarning = new WarningLabel();
		this.butSignUp = new JButton("Sign up");
		this.labBack = new HyperlinkLabel("Back");
		this.taTnC = new JTextPane();
		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		StyledDocument doc = this.taTnC.getStyledDocument();
		SimpleAttributeSet centerAtt = new SimpleAttributeSet();
		
		//Properties
		panelCenter.setBackground(Color.WHITE);
		panelTop.setBackground(Color.WHITE);
		this.panelMain.setBackground(Color.WHITE);
		this.labSignUp.setFont(Constants.FONT_SUB_TITLE);
		this.labBack.setFont(Constants.FONT_SMALLER);
		this.labBack.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						Main.changeScreen(new SignUpOptions());
					}
				});
		this.tfFirstName.getDocument().addDocumentListener(new TextFieldHintListener(this.tfFirstName, "First Name"));
		this.tfLastName.getDocument().addDocumentListener(new TextFieldHintListener(this.tfLastName, "Last Name"));
		this.tfEmail.getDocument().addDocumentListener(new TextFieldHintListener(this.tfEmail, "Email"));
		this.tfPass.getDocument().addDocumentListener(new TextFieldHintListener(this.tfPass, "Password"));
		this.tfConfirmPass.getDocument().addDocumentListener(new TextFieldHintListener(this.tfConfirmPass, "Confirm Password"));
		this.butSignUp.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSignUp.setForeground(Color.WHITE);
		this.butSignUp.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String fName = ((HintTextField) tfFirstName).getData().trim();
						String lName = ((HintTextField) tfLastName).getData().trim();
						String email = ((HintTextField) tfEmail).getData().trim();
						String pass = new String(tfPass.getPassword());
						String pass2 = new String(tfConfirmPass.getPassword());
						String adminID = tfAdminID == null ? null : ((HintTextField) tfAdminID).getData();
						
						//Check if all fields are filled
						if (fName.equals("") || email.equals("") || pass.equals("") || pass2.equals("") || adminID != null && adminID.equals(""))
						{
							labWarning.setText("Please fill in all required fields");
						}
						else
						{
							//Check if password match
							if (!pass.equals(pass2)) 
							{
								labWarning.setText("Password does not match");
							}
							else
							{
								//Sign Up to database
								if (asAdmin)
								{
									Admin a = new Admin(fName, lName, email, pass);
									Constants.DATABASE_SERVICE.insert(a);
									//Login
									Globals.activeUser = (Admin) Constants.DATABASE_SERVICE.getLogin(a.getEmail(), a.getPassword());
									Main.changeScreen(new SignUpAdminConfirm(Globals.activeUser.getID()));
								}
								else
								{
									//Check if the inputed Admin ID is valid.
									Admin a = Constants.DATABASE_SERVICE.getAdmin(Integer.parseInt(adminID));
									if (a == null)
									{
										labWarning.setText("Invalid Admin ID");
									}
									else
									{
										//Create new account for balance
										Account acc = new Account(0d);
										Constants.DATABASE_SERVICE.insert(acc);
										//TODO: Get Account ID
										
									}
								}
							}
						}
					}
				});
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
			this.initTFAdminID();	//Initialization moved here to prevent double checking
			Gbm.newGridLine(c);
			panelCenter.add(this.tfAdminID,c);			//Admin ID
		}
		Gbm.newGridLine(c);
		panelCenter.add(this.labWarning, c);			//Warning label
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
