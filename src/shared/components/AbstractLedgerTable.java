package shared.components;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import models.DatabaseItem;
import shared.Constants;

public abstract class AbstractLedgerTable extends JTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1854537926969311310L;
	//Fields
	protected String[][] tableData;
	protected String[] headers;
	protected List<? extends DatabaseItem> data;
	
	//Constructor
	public AbstractLedgerTable(List<? extends DatabaseItem> data, String[][] tableData, String[] headers)
	{
		super();
		this.init(data, tableData, headers);
	}
	public AbstractLedgerTable()
	{
		super();
		this.init(null, null, null);
	}
	
	//Public Methods
	/**
	 * Update the data and display it in the table. Make sure to call the protected version of the overloaded method.
	 * @param data a List of DatabaseItem objects
	 */
	public abstract void updateData(List<? extends DatabaseItem> data);
	
	//Protected Methods
	/**
	 * Converts a List of DatabaseItem objects to data that can be represented in the table.
	 * @param data a List of DatabaseItem objects
	 * @return a 2D String array
	 */
	protected abstract String[][] convertToTableRowData(List<? extends DatabaseItem> data);
	/**
	 * Method to update the data and display it in the table.
	 * @param data a List of DatabaseItem objects
	 * @param tableData a 2D String array to be displayed on the table
	 * @param headers the headers of the table
	 */
	protected void updateData(List<? extends DatabaseItem> data, String[][] tableData, String[] headers)
	{
		if (data != null)
		{
			this.data = data;
		}
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
	private void init(List<? extends DatabaseItem> data, String[][] tableData, String[] headers)
	{
		//Initialization
		this.data = data;
		this.tableData = tableData;
		this.headers = headers;
		
		//Properties
		this.setBackground(Color.WHITE);
		this.setAutoCreateRowSorter(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	}
}
