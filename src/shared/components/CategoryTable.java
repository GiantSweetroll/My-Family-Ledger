package shared.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableCellRenderer;

import giantsweetroll.gui.swing.ScrollPaneManager;
import models.Category;
import shared.Constants;
import shared.Methods;

public class CategoryTable extends JTable implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4951447617549379299L;
	//Fields
	private String[][] tableData;
	private List<Category> categories;
	
	//Constructor
	public CategoryTable(String[][] data, String[] headers, List<Category> categories)
	{
		super(data, headers);
		this.init(data, categories);
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
			System.out.println("Pressed category ID: " + categories.get(selectedIndex).getID());
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
	
	//Private Methods
	private void init(String[][] data, List<Category> categories)
	{
		//Initialization
		this.tableData = data;
		this.categories = categories;
		
		//Properties
		this.setBackground(Color.WHITE);
		this.setAutoCreateRowSorter(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.getTableHeader().setBorder(BorderFactory.createRaisedBevelBorder());
		this.addMouseListener(this);
		//Apply label renderer
		this.getColumnModel().getColumn(this.tableData[0].length-1).setCellRenderer(new IconCellRenderer(Constants.ICON_DELETE));
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		String[][] data = {{"230190", "Apple", ""}, 
							{"34000", "Window", ""},
							{"34002", "Bambi", ""}};
		String[] headers = {"ID", "Category", ""};
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(230190, 1, "desc1", "Apple"));
		categories.add(new Category(34000, 1, "desc2", "Window"));
		categories.add(new Category(34002, 1, "desc3", "Bambi"));
		CategoryTable table = new CategoryTable(data, headers, categories);
		JLabel label = new JLabel(Constants.ICON_DELETE);
		JScrollPane scroll = ScrollPaneManager.generateDefaultScrollPane(table);
		
		//Properties
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
