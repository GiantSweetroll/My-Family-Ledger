package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import main.Main;
import shared.Constants;
import shared.Methods;
import shared.components.HintPasswordField;
import shared.components.HintTextField;
import shared.components.HyperlinkLabel;
import shared.components.LogoLabel;
import shared.screens.CenteredPage;

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
		this.labLogo = new LogoLabel(LogoLabel.SMALL);
		this.labSignIn = new JLabel("Sign In");
		this.labSignUp = new HyperlinkLabel("Sign Up");
		this.tfEmail = new HintTextField("Email");
		this.tfPass = new HintPasswordField("Password");
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
		this.labSignUp.setFont(Constants.FONT_SMALLER);
		this.labSignUp.addMouseListener(new MouseListener()
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						Main.changeScreen(new SignUpOptions());
					}

					@Override
					public void mouseEntered(MouseEvent arg0){}

					@Override
					public void mouseExited(MouseEvent arg0) {}

					@Override
					public void mousePressed(MouseEvent arg0) {}

					@Override
					public void mouseReleased(MouseEvent arg0) {}
					
				});
		this.labSignIn.setFont(Constants.FONT_SUB_TITLE);
		this.tfEmail.setColumns(10);
		this.tfPass.setColumns(10);
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
		panelCenter.add(this.labLogo, c);			//Logo
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 25, 80);
		panelCenter.add(this.labSignIn, c);			//Sign In Label
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 5, 80);
		c.fill = GridBagConstraints.HORIZONTAL;
		panelCenter.add(this.tfEmail,c);			//Email text field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfPass, c);			//Password text field
		Gbm.newGridLine(c);
		panelCenter.add(this.butSignIn, c);			//Sign in button
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(20, 80, 50, 80);
		panelCenter.add(this.labForgotPass, c);		//Forgot password label
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
