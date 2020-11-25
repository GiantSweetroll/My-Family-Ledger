package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import shared.CenteredPage;
import shared.Constants;
import shared.Methods;

public class SignIn extends CenteredPage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4877032747183645341L;

	//Fields
	private JPanel panelMain;
	private JLabel labLogo, 
					labSignIn,
					labSignUp,
					labForgotPass;	
	private JTextField tfEmail;
	private JPasswordField tfPass;
	private JButton butSignIn;
	
	//Constructor
	public SignIn()
	{
		//Initialization
		super();
		this.initCenter();
		
		//Properties
		this.setCenterPanel(this.panelMain);
	}
	
	//Private Methods
	private void initCenter()
	{
		//Initialization
		this.panelMain = new JPanel(new BorderLayout());
		this.labLogo = new JLabel(new ImageIcon());	//TODO: Place app's icon
		this.labSignIn = new JLabel("Sign In");
		this.labSignUp = new JLabel("Sign Up");
		this.tfEmail = new JTextField(10);
		this.tfPass = new JPasswordField(10);
		this.butSignIn = new JButton("Sign in");
		this.labForgotPass = new JLabel("Forgot your password?");
		JPanel panelTop = new JPanel();
		JPanel panelCenter = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelTop.setBackground(Color.WHITE);
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.setBackground(Color.WHITE);
		this.labLogo.setBackground(Color.YELLOW);
		this.labSignUp.setForeground(Constants.COLOR_HYPERLINK);
		this.labSignUp.setFont(Constants.FONT_SMALLER);
		this.labSignIn.setFont(Constants.FONT_SUB_TITLE);
		this.butSignIn.setFont(Constants.FONT_SMALLER);
		this.butSignIn.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSignIn.setForeground(Color.WHITE);
		this.labForgotPass.setForeground(Constants.COLOR_HYPERLINK);
		this.labForgotPass.setFont(Constants.FONT_SMALLER);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labSignUp);
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 5, 80);
		panelCenter.add(this.labLogo, c);
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 25, 80);
		panelCenter.add(this.labSignIn, c);
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 5, 80);
		c.fill = GridBagConstraints.HORIZONTAL;
		panelCenter.add(this.tfEmail,c);
		Gbm.newGridLine(c);
		panelCenter.add(this.tfPass, c);
		Gbm.newGridLine(c);
		panelCenter.add(this.butSignIn, c);
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(20, 80, 50, 80);
		panelCenter.add(this.labForgotPass, c);
		//Add to panelMain
		this.panelMain.add(panelTop, BorderLayout.NORTH);
		this.panelMain.add(panelCenter, BorderLayout.CENTER);
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new SignIn();
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}
}
