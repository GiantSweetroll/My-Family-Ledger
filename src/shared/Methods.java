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
import models.Transaction;

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
	
	public static String[][] convertTransactionToTableRowData(List<Transaction> list)
	{
		String[][] arr = new String[list.size()][10];
		
		for (int i=0; i<list.size(); i++)
		{
			arr[i][0] = Integer.toString(list.get(i).getID());
			arr[i][1] = Integer.toString(list.get(i).getCategoryID());
			arr[i][2] = Integer.toString(list.get(i).getUserID());
			arr[i][3] = convertDateToString(list.get(i).getDateInput());
			arr[i][4] = convertDateToString(list.get(i).getDateEdit());
			arr[i][5] = Double.toString(list.get(i).getAmount());
			arr[i][6] = list.get(i).getDesc();
			arr[i][7] = list.get(i).getLinkReceipt();
			arr[i][8] = "";
			arr[i][9] = "";
		}
		
		return arr;
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
		
		return ls;
	}
}
