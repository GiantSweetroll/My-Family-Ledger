package legacy;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import models.DatabaseItem;

public abstract class IconActionEditor extends DefaultCellEditor implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675210222585668894L;
	//Fields
	protected JLabel label;
	protected Icon icon;
	protected List<? extends DatabaseItem> data;
	private int selectedIndex;
	private boolean clicked;
	
	//Constructor
	public IconActionEditor(List<? extends DatabaseItem> data, Icon icon)
	{
		//Initialization
		super(new JTextField());
		this.label = new JLabel(icon);
		this.data = data;

		
		//Properties
		this.label.addMouseListener(this);
	}
	
	//Public methods
	public void setClicked(boolean b)
	{
		this.clicked = b;
	}
	public boolean isClicked()
	{
		return this.clicked;
	}
	
	//Protected methods
	protected List<? extends DatabaseItem> getData()
	{
		return this.data;
	}
	protected void setData(List<? extends DatabaseItem> data)
	{
		this.data = data;
	}
	protected int getSelectedIndex()
	{
		return this.selectedIndex;
	}

	//Overridden Methods
	@Override
	public Component getTableCellEditorComponent(JTable table, Object obj, boolean isSelected, int row, int column) 
	{
		this.selectedIndex = table.convertRowIndexToModel(table.getSelectedRow());
		this.setClicked(true);
		
		return this.label;
	}
	
	@Override
	public Object getCellEditorValue() 
	{
		if (this.isClicked())
		{
			this.clickedAction();
		}
		this.setClicked(false);
		return this.label.getText();
	}
	
	@Override
	public boolean stopCellEditing() 
	{
		this.setClicked(false);
		return super.stopCellEditing();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		this.fireEditingStopped();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	//Abstract methods
	public abstract void clickedAction();
}
