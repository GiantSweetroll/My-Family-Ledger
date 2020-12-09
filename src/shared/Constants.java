package shared;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import giantsweetroll.numbers.GNumbers;

public class Constants
{
	//Colors
	public static final Color COLOR_OUT_OF_FOCUS = new Color(229, 233, 242);
	public static final Color COLOR_HYPERLINK = new Color(0, 166, 255);
	public static final Color COLOR_BUTTON_BASE = new Color(236, 64, 122);
	public static final Color COLOR_TEXT_GRAY = new Color(129, 144, 165);
	public static final Color COLOR_BUTTON_YELLOW = new Color(246, 196, 68);
	public static final Color COLOR_SELECTED = new Color(119, 211, 83);
	
	//Insets
	public static final Insets INSETS_GENERAL = new Insets(5, 5, 5, 5);
	
	//Fonts
	public static final String FONT_TYPE_GENERAL = "lato";
	public static final int FONT_GENERAL_SIZE = 20;
	public static final Font FONT_GENERAL = new Font(FONT_TYPE_GENERAL, Font.PLAIN, FONT_GENERAL_SIZE);
	public static final Font FONT_GENERAL_BOLD = new Font(FONT_TYPE_GENERAL, Font.BOLD, FONT_GENERAL_SIZE);
	public static final Font FONT_SUB_TITLE = new Font(FONT_TYPE_GENERAL, Font.BOLD, FONT_GENERAL_SIZE*2);
	public static final Font FONT_TITLE = new Font(FONT_TYPE_GENERAL, Font.BOLD, (int)GNumbers.round((float)FONT_GENERAL_SIZE*2.5f, 0));
	public static final Font FONT_SMALLER = new Font(FONT_TYPE_GENERAL, Font.PLAIN, (int)GNumbers.round((float)FONT_GENERAL_SIZE*0.80f, 0));

	//Date Format
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
}
