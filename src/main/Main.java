package main;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import screens.auth.SignIn;
import shared.Constants;
import shared.Methods;

public class Main 
{
	//Fields
	private static JFrame frame;
	private static JPanel panel;
	
	//Constructor
	public Main()
	{
		//Initialization
		Main.frame = new JFrame();
		Main.panel = new SignIn();
		
		//Properties
		Main.frame.setSize(800, 600);
		Main.frame.setLocationRelativeTo(null);
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		Main.frame.add(Main.panel);
		
		Main.frame.setVisible(true);
	}
	
	//Public methods
	public static void changeScreen(JPanel panel)
	{
		Main.frame.remove(Main.panel);
		Main.panel = panel;
		Main.frame.add(Main.panel);
		Main.frame.revalidate();
		Main.frame.repaint();
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		new Main();
	}
}
