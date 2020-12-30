package shared.components.tables;
import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class HistoryTableCellRenderer extends JLabel implements TableCellRenderer{
	
	private static final long serialVersionUID = 558341089679656434L;

	//Constructor
	public HistoryTableCellRenderer(Icon icon)
	{
		super(icon);
		this.setOpaque(true);
	}
	
	//Overridden Methods
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int col)
	{	
		this.setBackground(Color.WHITE);
		return this;
	}

}
