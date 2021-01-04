package shared.components.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import shared.Constants;

public class SimpleTable extends JTable
{

	/**
	 
	 */
	private static final long serialVersionUID = -4767031616718532955L;
	//Fields
	protected String[][] tableData;
	protected String[] headers;
	
	//Constructor
	public SimpleTable(String[][] tableData, String[] headers)
	{
		super();
		this.init(tableData, headers);
		this.setRowHeight(50);
	}
	
	public SimpleTable()
	{
		super();
		this.init(null, null);
	}
	
	//Public Methods
	/**
	 * Method to update the data and display it in the table.
	 * @param tableData a 2D String array to be displayed on the table
	 * @param headers the headers of the table
	 */
	public void updateData(String[][] tableData, String[] headers)
	{
		this.tableData = tableData;
		this.headers = headers;
		this.setModel(new DefaultTableModel(this.tableData, this.headers));
	}
	
	//Overridden Methods
	@Override
	public Component prepareRenderer(TableCellRenderer r, int row, int col)
	{
		Component c = super.prepareRenderer(r, row, col);
		
		//Table row color pattern
		if (row%2==1)
		{
			c.setBackground(Color.WHITE);
		}
		else
		{
			c.setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		}
		
		return c;
	}
	
	/*
	 * Resizes the table cells width to its preferred size or the viewport size, whichever is greater
	 */
	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return this.getPreferredSize().width < this.getParent().getWidth();
	}
	
	//Private methods
	private void init(String[][] tableData, String[] headers)
	{
		//Initialization
		this.tableData = tableData;
		this.headers = headers;
		
		//Properties
		this.updateData(tableData, headers);
		this.setBackground(Color.WHITE);
		this.setAutoCreateRowSorter(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTableHeader().setFont(Constants.FONT_GENERAL_BOLD);
		this.getTableHeader().setBackground(Constants.COLOR_TABLE_HEADER);
		this.setRowHeight(50);
	}
	
	
}
