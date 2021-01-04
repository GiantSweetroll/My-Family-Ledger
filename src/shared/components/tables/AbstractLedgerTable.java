package shared.components.tables;

import java.util.List;

import models.DatabaseItem;

public abstract class AbstractLedgerTable extends SimpleTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1854537926969311310L;
	//Fields
	protected List<? extends DatabaseItem> data;
	
	//Constructor
	public AbstractLedgerTable(List<? extends DatabaseItem> data, String[][] tableData, String[] headers)
	{
		super();
		this.data = data;
	}
	public AbstractLedgerTable()
	{
		super();
		this.data = null;
	}
	
	//Public Methods
	/**
	 * Update the data and display it in the table. Make sure to call the parent version of the overloaded method.
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
		super.updateData(tableData, headers);
		if (data != null)
		{
			this.data = data;
		}
	}
}
