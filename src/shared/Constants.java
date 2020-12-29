package shared;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;

import database.DatabaseService;
import giantsweetroll.ImageManager;
import giantsweetroll.numbers.GNumbers;
import shared.components.IconCellRenderer;

public class Constants
{
	//Database
	public static final DatabaseService DATABASE_SERVICE = new DatabaseService();
	
	//Colors
	public static final Color COLOR_OUT_OF_FOCUS = new Color(229, 233, 242);
	public static final Color COLOR_HYPERLINK = new Color(0, 166, 255);
	public static final Color COLOR_BUTTON_BASE = new Color(236, 64, 122);
	public static final Color COLOR_TEXT_GRAY = new Color(129, 144, 165);
	public static final Color COLOR_BUTTON_YELLOW = new Color(246, 196, 68);
	public static final Color COLOR_SELECTED = new Color(119, 211, 83);
	public static final Color COLOR_TABLE_EVEN_ROW = new Color(192, 204, 218);
	
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
	public static final Font FONT_SMALLER_BOLD = new Font(FONT_TYPE_GENERAL, Font.BOLD, (int)GNumbers.round((float)FONT_GENERAL_SIZE*0.80f, 0));

	//Date Format
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static final DateTimeFormatter LOCAL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/YYYY");
	
	//ComboBox texts
	public static final String[] COMBO_BOX_OPERANDS = {"=", "<", ">", "<=", ">="};
	
	//Icons
	public static final ImageIcon ICON_DELETE = new ImageIcon(ImageManager.scaleImage(ImageManager.getImageIcon(Constants.class.getResource("/resources/trash.png")).getImage(), 15, 15));
	public static final ImageIcon ICON_EDIT = new ImageIcon(ImageManager.scaleImage(ImageManager.getImageIcon(Constants.class.getResource("/resources/editicon.svg.png")).getImage(), 15, 15));
	public static final ImageIcon ICON_LOGO = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/logo_trans.png")), 200, 200));
	public static final ImageIcon ICON_LOGO_SMALL = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/logo_trans.png")), 100, 100));
	public static final ImageIcon ICON_CLOSE = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/closeicon.png")), 16, 16));
	public static final ImageIcon ICON_HISTORY = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/history_icon.png")), 64, 64));
	public static final ImageIcon ICON_TRANSFER = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/transfer_icon.png")), 64, 64));
	public static final ImageIcon ICON_REPORT = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/report_icon.png")), 64, 64));
	public static final ImageIcon ICON_INPUT = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/input_icon.png")), 64, 64));
	public static final ImageIcon ICON_CATEGORIES = new ImageIcon(ImageManager.scaleImage(ImageManager.getImage(Constants.class.getResource("/resources/categories_icon.png")), 64, 64));
	
	//Renderers
	public static final IconCellRenderer RENDERER_DELETE = new IconCellRenderer(Constants.ICON_DELETE);
}
