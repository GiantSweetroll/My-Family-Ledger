package shared;

import java.awt.event.ActionListener;
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
	
	/**
	 * Converts a list of category into data that can be represented in JTable
	 * @param list a List containing Category
	 * @return a 2D String array
	 */
	public static String[][] convertCategoryToTableRowData(List<Category> list)
	{
		String[][] arr = new String[list.size()][3];
		
		for (int i=0; i<list.size(); i++)
		{
			arr[i][0] = Integer.toString(list.get(i).getID());
			arr[i][1] = list.get(i).getName();
			arr[i][2] = "";		//Make it empty because not needed (only to make columns the same as header)
		}
		
		return arr;
	}
}
