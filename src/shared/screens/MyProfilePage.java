package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;
import javax.swing.JPanel;

import giantsweetroll.gui.swing.Gbm;
import screens.auth.SignIn;
import shared.Constants;
import shared.Methods;
import shared.components.LogoLabel;
import shared.screens.CenteredPage;
import giantsweetroll.ImageManager;

public class MyProfilePage extends CenteredPage{
	
	ImageIcon closeIcon = createImageIcon("/resources/closeicon.png", "Close");
	private JPanel mainPanel;
	private JLabel myProfileLabel;
	private JLabel usernameLabel;
	private JLabel lastNameLabel;
	private JLabel emailLabel;
	private JLabel resetPassLabel;
	private JLabel closeIconLabel;
	private JButton okButton;
	Border border = BorderFactory.createLineBorder(Constants.COLOR_TEXT_GRAY, 3);
	
	public MyProfilePage(){
		
		super();
		this.init();
		this.setCenterPanel(this.mainPanel);
		
	}
	
	private void init() { 
		this.mainPanel = new JPanel(new BorderLayout()); 
		this.myProfileLabel = new JLabel("My Profile");
		this.usernameLabel = new JLabel("Username");
		this.lastNameLabel = new JLabel("Last Name");
		this.emailLabel = new JLabel("Email");
		this.resetPassLabel = new JLabel("Reset Password");
		this.closeIconLabel = new JLabel(closeIcon);
		this.okButton = new JButton("OK");
		JPanel topPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topPanel.setBackground(Color.WHITE);
		
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.WHITE);
		
		this.closeIconLabel.setMinimumSize(new Dimension(25, 25));
		this.closeIconLabel.setPreferredSize(new Dimension(25, 25));
		this.closeIconLabel.setMaximumSize(new Dimension(25, 25));
		
		this.myProfileLabel.setFont(Constants.FONT_SUB_TITLE);
		
		this.usernameLabel.setFont(Constants.FONT_GENERAL);
		this.usernameLabel.setForeground(Constants.COLOR_TEXT_GRAY);
		this.usernameLabel.setBorder(border);
		
		this.lastNameLabel.setFont(Constants.FONT_GENERAL);
		this.lastNameLabel.setForeground(Constants.COLOR_TEXT_GRAY);
		this.lastNameLabel.setBorder(border);
		
		this.emailLabel.setFont(Constants.FONT_GENERAL);
		this.emailLabel.setForeground(Constants.COLOR_TEXT_GRAY);
		this.emailLabel.setBorder(border);
		
		this.resetPassLabel.setForeground(Constants.COLOR_HYPERLINK);
		this.resetPassLabel.setFont(Constants.FONT_SMALLER);
		
		this.okButton.setFont(Constants.FONT_SMALLER);
		this.okButton.setBackground(Constants.COLOR_BUTTON_BASE);
		this.okButton.setForeground(Color.WHITE);
		
		topPanel.add(closeIconLabel);
		
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 30, 80);
		centerPanel.add(this.myProfileLabel, c);		// My Profile
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 5, 80);
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(this.usernameLabel, c);			// Username
		Gbm.newGridLine(c);
		centerPanel.add(this.lastNameLabel, c);			// LastName
		Gbm.newGridLine(c);
		centerPanel.add(this.emailLabel, c);			// Email
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.NONE;
		centerPanel.add(this.resetPassLabel, c);		// Reset Password
		Gbm.newGridLine(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(20, 80, 80, 80);
		centerPanel.add(this.okButton, c);				// Ok Button
		
		this.mainPanel.add(topPanel, BorderLayout.NORTH);
		this.mainPanel.add(centerPanel, BorderLayout.CENTER);
		
	}
	
	private ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(ImageManager.scaleImage((new ImageIcon(imgURL, description)).getImage(), 16, 16));
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new MyProfilePage();
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}

}
