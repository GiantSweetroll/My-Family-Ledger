package shared.components;

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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import main.Main;
import models.Person;
import shared.Constants;
import shared.Methods;

public class ResetPasswordPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037086301150852671L;
	//Fields
	private JLabel labTitle, labWarning, labClose, labCancel;
	private JTextField tfEmail, tfPass, tfPassConfirm;
	private AppButton button;
	
	//Constructor
	public ResetPasswordPanel(Person person)
	{
		//Initialization
		super(new BorderLayout());
		this.labTitle = new JLabel("Reset Password");
		this.tfEmail = new HintTextField("Email");
		this.tfPass = new HintPasswordField("New Password");
		this.tfPassConfirm = new HintPasswordField("Confirm Password");
		this.labWarning = new WarningLabel();
		this.button = new AppButton("Confirm");
		this.labClose = new JLabel(Constants.ICON_CLOSE);
		this.labCancel = new HyperlinkLabel("Cancel");
		JPanel panelTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panelCenter = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.setBackground(Color.WHITE);
		this.tfEmail.setColumns(10);
		this.button.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String email = tfEmail.getText().trim();
						String pass = new String(((HintPasswordField)tfPass).getPassword());
						String pass2 = new String(((HintPasswordField)tfPassConfirm).getPassword());
						
						//Check if all fields are filled
						if (email.equals("") || pass.equals("") || pass2.equals(""))
						{
							labWarning.setText("Please fill in all of the fields");
						}
						else if (!pass.equals(pass2))
						{
							labWarning.setText("Password does not match");
						}
						else
						{
							person.setEmail(email);
							person.setPassword(pass);
							Constants.DATABASE_SERVICE.update(person.getID(), person);
							Main.popScreen();
						}
					}
				});
		this.labCancel.setFont(Constants.FONT_SMALLER);
		this.labCancel.setHorizontalAlignment(SwingConstants.CENTER);
		this.labCancel.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						Main.popScreen();
					}
				});
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		
		///Add to panel
		//Add to panelTop
		panelTop.add(this.labClose);
		//Add to panelCenter
		Gbm.goToOrigin(c);
		c.insets = new Insets(5, 80, 25, 80);
		panelCenter.add(this.labTitle, c);				//Title
		c.fill = GridBagConstraints.BOTH;
		Gbm.newGridLine(c);
		c.insets = new Insets(5, 80, 5, 80);
		panelCenter.add(this.tfEmail, c);				//Email Text Field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfPass, c);				//Password Text Field
		Gbm.newGridLine(c);
		panelCenter.add(this.tfPassConfirm, c);			//Confirm Password Text Field
		Gbm.newGridLine(c);
		panelCenter.add(this.labWarning, c);			//Warning label
		Gbm.newGridLine(c);
		panelCenter.add(this.button, c);				//Confirm button
		Gbm.newGridLine(c);
		panelCenter.add(this.labCancel, c);				//cancel label
		//Add to main panel
		this.add(panelTop, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		ResetPasswordPanel rpp = new ResetPasswordPanel(null);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(rpp);
		
		frame.setVisible(true);
	}
}
