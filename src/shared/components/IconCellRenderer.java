package shared.components;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class IconCellRenderer extends JLabel implements TableCellRenderer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 558341089679656434L;

	//Constructor
	public IconCellRenderer(Icon icon)
	{
		super(icon);
	}
	
	//Overridden Methods
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int col)
	{
		if (obj == null)
		{
			this.setText("");
		}
		else
		{
			if (obj instanceof Icon)
			{
				this.setIcon((Icon)obj);
			}
		}
		
		return this;
	}

}
