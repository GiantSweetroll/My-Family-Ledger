package shared;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

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
}
