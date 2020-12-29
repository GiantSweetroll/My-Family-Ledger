package shared;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import models.Category;

public class Methods 
{
	/**
	 * Set the Font of the UI
	 * @param f - a FontUIResource object
	 */
	public static void setUIFont(FontUIResource f)
	{
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
			{
				UIManager.put(key, f);
			}
		}
	}
	
	/**
	 * Remove all ActionListener instances in a JButton object.
	 * @param button a JButton object.
	 */
	public static void removeActionListeners(JButton button)
	{
		ActionListener[] listeners = button.getActionListeners();
		if (listeners.length > 0)
		{
			for (ActionListener al : listeners)
			{
				button.removeActionListener(al);
			}
		}
	}
	
	public static String convertDateToString(Date date) 
	{ 

			String dateString = null;
			SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
			try{
				
				dateString = sdfr.format(date);
			}catch (Exception ex ){
				System.out.println(ex);
			}
			return dateString;
	}
	
	public static List<Category> getDefaultCategories()
	{
		List<Category> ls = new ArrayList<>();
		
		Category cat = new Category(1, null, "Transfer", "");
		ls.add(cat);
		cat = new Category(2, null, "Food", "");
		ls.add(cat);
		cat = new Category(3, null, "Transportation", "");
		ls.add(cat);
		cat = new Category(4, null, "Household", "");
		ls.add(cat);
		cat = new Category(5, null, "Other", "");
		ls.add(cat);
		
		return ls;
	}
}
