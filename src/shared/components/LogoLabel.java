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
	//Constants
	public static final int SMALL = 0,
							BIG = 1;
	
	//Constructor
	/**
	 * Initialize a label with the app's logo.
	 * @param flag valid flags are LogoLabel.SMALL and LogoLabel.BIG
	 */
	public LogoLabel(int flag)
	{
		super(flag == BIG ? Constants.ICON_LOGO : Constants.ICON_LOGO_SMALL);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		LogoLabel logo = new LogoLabel(LogoLabel.BIG);
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(logo);
		
		frame.setVisible(true);
	}
}
