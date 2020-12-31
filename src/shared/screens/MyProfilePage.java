package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.ImageManager;
import giantsweetroll.gui.swing.Gbm;
import models.Admin;
import models.Person;
import shared.Constants;
import shared.Methods;
import shared.components.AppButton;
import shared.components.HyperlinkLabel;
import shared.components.ResetPasswordPanel;

public class MyProfilePage extends CenteredPage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9175176544360313055L;
	ImageIcon closeIcon = createImageIcon("/resources/closeicon.png", "Close");
	private JPanel mainPanel;
	private JLabel myProfileLabel;
	private JTextField tfUsername, tfLastname, tfEmail;
	private JLabel resetPassLabel, closeIconLabel, adminIDLabel;
	private AppButton okButton;
	private JDialog dialogResetPassword;
	Border border = BorderFactory.createLineBorder(Constants.COLOR_TEXT_GRAY, 3);
	
	public MyProfilePage(Person person){
		
		super();
		this.init(person);
		this.setCenterPanel(this.mainPanel);
		
	}
	
	private void init(Person person) { 
		this.mainPanel = new JPanel(new BorderLayout()); 
		this.myProfileLabel = new JLabel("My Profile");
		this.tfUsername = new JTextField("Username");
		this.tfLastname = new JTextField("Last Name");
		this.tfEmail = new JTextField("Email");
		this.resetPassLabel = new HyperlinkLabel("Reset Password");
		this.closeIconLabel = new JLabel(closeIcon);
		this.adminIDLabel = new JLabel();
		this.okButton = new AppButton("OK");
		this.dialogResetPassword = new JDialog();
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
		
		
		this.tfUsername.setFont(Constants.FONT_GENERAL);
		this.tfUsername.setBorder(border);
		this.tfUsername.setText(person.getFirstName());
		
		this.tfLastname.setFont(Constants.FONT_GENERAL);
		this.tfLastname.setBorder(border);
		this.tfLastname.setText(person.getLastName());
		
		this.tfEmail.setFont(Constants.FONT_GENERAL);
		this.tfEmail.setBorder(border);
		this.tfEmail.setText(person.getEmail());
		
		this.resetPassLabel.setForeground(Constants.COLOR_HYPERLINK);
		this.resetPassLabel.setFont(Constants.FONT_SMALLER);
		this.resetPassLabel.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Methods.closeThisWindow(MyProfilePage.this);
						dialogResetPassword.add(new ResetPasswordPanel(person));
						dialogResetPassword.setModal(true);
						dialogResetPassword.setSize(700, 700);
						dialogResetPassword.setLocationRelativeTo(null);
						dialogResetPassword.setVisible(true);
						
					}
				});
		
		this.adminIDLabel.setFont(Constants.FONT_GENERAL);
		this.adminIDLabel.setBorder(border);
		int adminid = Constants.DATABASE_SERVICE.getAdminID(person.getEmail());
		this.adminIDLabel.setText(String.valueOf(adminid));
		
		if (person instanceof Admin)
		{
			this.adminIDLabel.setVisible(true);
			
		}
		else
		{
			this.adminIDLabel.setVisible(false);
		}
	
		
		this.okButton.setFont(Constants.FONT_SMALLER);
		this.okButton.setBackground(Constants.COLOR_BUTTON_BASE);
		this.okButton.setForeground(Color.WHITE);
		this.okButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent evt) {
						Methods.closeThisWindow(MyProfilePage.this);
						String firstname = tfUsername.getText().trim();
						String lastname = tfLastname.getText().trim();
						String email = tfEmail.getText().trim();
						person.setFirstName(firstname);
						person.setLastName(lastname);
						person.setEmail(email);
						Constants.DATABASE_SERVICE.update(person.getID(), person);                             
						btnSavedaction(evt); 
						
						
					}
				});
		
		this.closeIconLabel.addMouseListener(new MouseAdapter()
				{
				 @Override
	             public void mouseClicked(MouseEvent e) {
					 Methods.closeThisWindow(MyProfilePage.this);
				 }
				}
				);
		
		topPanel.add(closeIconLabel);
		
		
		
		
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 30, 80);
		centerPanel.add(this.myProfileLabel, c);		// My Profile
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 5, 80);
		c.fill = GridBagConstraints.HORIZONTAL;
		centerPanel.add(this.tfUsername, c);			// Username
		Gbm.newGridLine(c);
		centerPanel.add(this.tfLastname, c);			// LastName
		Gbm.newGridLine(c);
		centerPanel.add(this.tfEmail, c);			// Email
		Gbm.newGridLine(c);
		centerPanel.add(this.adminIDLabel,c);
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
	

	
		private void btnSavedaction(ActionEvent evt) {                                               
 
        JOptionPane.showMessageDialog(this, "Data saved");
		}
	
	
	
	
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		Person Person1 = new Person("Adam", "Smith");
		CenteredPage cp = new MyProfilePage(Person1);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}

}
