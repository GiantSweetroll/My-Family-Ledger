package shared.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import shared.Constants;

public class IconCellRenderer extends JLabel implements TableCellRenderer
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 558341089679656434L;
	//Fields
	private Icon icon;
	
	//Constructor
	public IconCellRenderer(Icon icon)
	{
		super(icon);
		this.icon = icon;
		this.setOpaque(true);
	}
	
	//Overridden Methods
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int col)
	{	
		//Table row color pattern
		if (row%2==1)
		{
			this.setBackground(Color.WHITE);
		}
		else
		{
			this.setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		}
		
		//Decide if icon should be drawn
		if (obj == null)
		{
			this.setIcon(null);
		}
		else
		{
			this.setIcon(this.icon);
		}
		
		return this;
	}

}
