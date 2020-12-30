package shared.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import shared.Constants;

public class AmountCellRenderer extends JLabel implements TableCellRenderer
{
	//Constructor
	/**
	 * 
	 */
	private static final long serialVersionUID = 203724898768156128L;
	
	public AmountCellRenderer()
	{
		super();
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
		
		//Decide foreground color
		boolean isNegative = false;
		String amount = (String)obj;
		
		if (amount.length() > 0)
		{
			isNegative = amount.substring(0, 1).equals("-");
		}
		
		if(isNegative)
		{
			this.setForeground(Color.RED);
			amount = amount.substring(1);
		}
		else
		{
			this.setForeground(Color.BLUE);
		}
		
		this.setText(amount);
		
		return this;
	}
}
