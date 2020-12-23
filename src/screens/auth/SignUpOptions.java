package screens.auth;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import main.Main;
import shared.Constants;
import shared.Methods;
import shared.components.HyperlinkLabel;
import shared.components.LogoLabel;
import shared.screens.CenteredPage;
import shared.screens.SignUpOption;

public class SignUpOptions extends CenteredPage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3073109636606932906L;
	//Fields
	private JPanel panelMain;
	private JLabel labLogo, 
					labSignUp, 
					labSignIn;
	private SignUpOption adminOption, userOption;
	private JSeparator separator;
	
	//Constructor
	public SignUpOptions()
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
		this.panelMain = new JPanel();
		this.labLogo = new LogoLabel(LogoLabel.SMALL);
		this.labSignUp = new JLabel("Sign Up");
		this.labSignIn = new HyperlinkLabel("Sign In");
		this.adminOption = new SignUpOption("Admin", 
											"- Transfer Funds \n- Transaction history report from users",
											"Sign up as admin",
											null);
		this.userOption = new SignUpOption("User", 
											"- Receive funds from admin \n- Report expenditure to admin",
											"Sign up as user",
											null);
		this.separator = new JSeparator(JSeparator.VERTICAL);
		JPanel panelTop = new JPanel();
		JPanel panelOptions = new JPanel();
		JPanel panelCenter = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		panelTop.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelTop.setOpaque(false);
		panelOptions.setLayout(new GridLayout(0, 2, 50, 5));
		panelOptions.setOpaque(false);
		panelCenter.setLayout(new GridBagLayout());
		panelCenter.setOpaque(false);
		this.panelMain.setLayout(new BorderLayout());
		this.panelMain.setBackground(Color.WHITE);
		this.labSignUp.setFont(Constants.FONT_SUB_TITLE);
		this.labSignIn.setFont(Constants.FONT_SMALLER);
		this.labSignIn.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Main.changeScreen(new SignIn());
					}	
				});
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labSignIn);
		//Add to panelOptions
		panelOptions.add(this.adminOption);
		panelOptions.add(this.userOption);
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 5, 80);
		panelCenter.add(this.labLogo, c);			//Logo
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 25, 80);
		panelCenter.add(this.labSignUp, c);			//Sign Up label
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 50, 80);
		panelCenter.add(panelOptions, c);			//panelOptions
		//Add to panelMain
		panelMain.add(panelTop, BorderLayout.NORTH);
		panelMain.add(panelCenter, BorderLayout.CENTER);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new SignUpOptions();
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}
}
