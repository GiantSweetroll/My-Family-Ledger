package shared.components;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.ScrollPaneManager;
import models.Category;
import models.DatabaseItem;
import shared.Constants;
import shared.Methods;
import shared.components.tables.AbstractLedgerTable;

public class CategoryTable extends AbstractLedgerTable implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4951447617549379299L;
	//Fields
	//Constants
	private static final String[] HEADERS = {"ID", "Name", ""};
	
	//Constructors
	public CategoryTable(List<Category> categories)
	{
		super();
		this.init(categories);
	}
	public CategoryTable()
	{
		super();
		this.init(new ArrayList<Category>());
	}
	
	//Public methods
	/**
	 * Updates the table with new data. It will create a new TableModel to display the updated data.
	 * @param categories
	 */
	@Override
	public void updateData(List<? extends DatabaseItem> categories)
	{
		this.updateData(categories, this.convertToTableRowData((List<Category>) categories), HEADERS);
		
		//Apply icon renderer
		if (this.tableData.length > 0)
		{
			this.getColumnModel().getColumn(this.tableData[0].length-1).setCellRenderer(Constants.RENDERER_DELETE);
		}
	}
	
	//Overridden Methods
	/**
	 * Only allow edit on the second column
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{	
		if (column == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		int row = this.rowAtPoint(e.getPoint());
		int col = this.columnAtPoint(e.getPoint());
		
		if (col == this.tableData[0].length-1)		//If last column is pressed
		{
			//TODO: Delete category operation
			int selectedIndex = this.convertRowIndexToModel(row);
			System.out.println("Pressed category ID: " + this.data.get(selectedIndex).getID());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	@Override
	protected String[][] convertToTableRowData(List<? extends DatabaseItem> data) 
	{
		String[][] arr = new String[data.size()][3];
		
		for (int i=0; i<data.size(); i++)
		{
			Category cat = (Category)data.get(i);
			arr[i][0] = Integer.toString(cat.getID());
			arr[i][1] = cat.getName();
			arr[i][2] = "";				//Make it empty because not needed (only to make columns the same as header)
		}
		
		return arr;
	}
	
	//Private Methods
	private void init(List<Category> categories)
	{
		//Initialization
		this.updateData(categories);
		
		//Properties
//		this.getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
		this.setTableHeader(null);		//Remove table headers
		this.addMouseListener(this);
		this.setRowHeight(50);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(230190, 1, "desc1", "Apple"));
		categories.add(new Category(34000, 1, "desc2", "Window"));
		categories.add(new Category(34002, 1, "desc3", "Bambi"));
		CategoryTable table = new CategoryTable();
		JLabel label = new JLabel(Constants.ICON_DELETE);
		JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(table);
		
		//Properties
		table.updateData(categories);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Add to frame
		frame.add(scroll);
		frame.add(label);
		
		frame.setVisible(true);
	}
}
