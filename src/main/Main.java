package main;

import java.awt.Font;

import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;

public class Main 
{
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
	}
}
