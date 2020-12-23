package shared.components;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;

public class LogoLabel extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4508062100092494017L;
	
	public LogoLabel()
	{
		super(Constants.ICON_LOGO);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		LogoLabel logo = new LogoLabel();
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(logo);
		
		frame.setVisible(true);
	}
}
