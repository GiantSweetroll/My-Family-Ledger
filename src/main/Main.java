package main;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import screens.auth.SignIn;
import shared.Constants;
import shared.GUIListener;
import shared.Methods;

public class Main 
{
	//Fields
	private static JFrame frame;
	private static JPanel panel;
	private static String title;
	private static List<GUIListener> panels;
	private static List<String> titles;
	
	//Constructor
	public Main()
	{
		//Initialization
		Main.frame = new JFrame();
		Main.panels = new ArrayList<>();
		Main.titles = new ArrayList<>();
//		Globals.activeUser = new Admin(1, "Gardyan", "Akbar");
//		Main.panel = new Menu(Globals.activeUser, true);
		Main.panel = new SignIn();
		
		//Properties
		Main.frame.setSize(1280, 768);
		Main.frame.setLocationRelativeTo(null);
		Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main.frame.setTitle("OurFunds - Sign In");
		
		//Add to frame
		Main.frame.add(Main.panel);
		
		Main.frame.setVisible(true);
	}
	
	//Public methods
	/**
	 * Change the screen that is visible. Will perform direct switching.
	 * @param panel the JPanel to be displayed on the screen.
	 */
	public static void changeScreen(GUIListener panel, String title)
	{
		try
		{
			Main.frame.remove(Main.panel);
		}
		catch(NullPointerException ex) {}
		Main.panel = (JPanel)panel;
		Main.title = title;
		Main.frame.add(Main.panel);
		Main.frame.setTitle("OurFunds - " + title);
		panel.onDisplayed();
		Main.frame.revalidate();
		Main.frame.repaint();
	}
	/**
	 * Push the JPanel into the stack and display it.
	 * @param panel the JPanel to be displayed.
	 */
	public static void pushScreen(GUIListener panel, String title)
	{
		Main.panels.add((GUIListener)Main.panel);
		Main.panels.add((GUIListener)panel);
		Main.titles.add(Main.title);
		Main.titles.add(title);
		Main.changeScreen(panel, title);
		panel.onDisplayed();
	}
	/**
	 * Pop the screen to display the previous JPanel before being pushed.
	 * @return the popped JPanel
	 */
	public static JPanel popScreen()
	{
		try
		{
			JPanel popped = (JPanel)Main.panels.remove(Main.panels.size()-1);
			String poppedTitle = Main.titles.remove(Main.titles.size()-1);
			Main.changeScreen(Main.panels.get(Main.panels.size()-1), poppedTitle);
			return popped;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		new Main();
	}
}
