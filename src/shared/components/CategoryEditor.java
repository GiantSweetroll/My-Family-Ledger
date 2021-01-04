package shared.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import giantsweetroll.gui.swing.ScrollPaneManager;
import models.Admin;
import models.Category;
import shared.Constants;
import shared.Methods;
import shared.TableCellListener;
import shared.components.tables.CategoryTable;
import shared.screens.CenteredPage;


public class CategoryEditor extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2428591210532819183L;
	//Fields
	private JScrollPane scrollTable;
	private JLabel labTitle;
	private JTextField tf;
	private AppButton butAdd, butSave;
	private Admin admin;
	private CategoryTable table;
	private List<Category> categories;
	private TableCellListener tcl;
	
	//Interfaces
	private ActionListener addCatListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					addCategory();
				}
			};
			
	private ActionListener saveCatListener = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			saveChanges();
		}
	};
	
	//Constructor
	public CategoryEditor(Admin admin)
	{
		//Initialization
		super();
		this.admin = admin;
		this.labTitle = new JLabel("My Categories");
		this.table = new CategoryTable();
		this.tcl = new TableCellListener(this.table, new AbstractAction()
				{
					/**
					 * 
					 */
					private static final long serialVersionUID = -4717754746651568773L;

					public void actionPerformed(ActionEvent e)
					{
						TableCellListener tcl = (TableCellListener)e.getSource();
						int index = tcl.getTable().convertRowIndexToModel(tcl.getRow());
						
						//Check if value changes
						Category cat = categories.get(index);
						if (!cat.getName().equals((String)tcl.getNewValue()))
						{
							//Update the database
							cat.setName((String)tcl.getNewValue());
							Constants.DATABASE_SERVICE.update(cat.getID(), cat);
						}
					}
				});
		this.scrollTable = ScrollPaneManager.generateDefaultScrollPane(this.table, 10, 10);
		this.tf = new JTextField(10);
		this.butAdd = new AppButton("Add");
		this.butSave = new AppButton("Ok");
		this.categories = new ArrayList<Category>();
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints c2 = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		
		//Properties
		this.setLayout(new GridBagLayout());
//		this.setOpaque(false);
		this.setBackground(Color.WHITE);
		this.labTitle.setFont(Constants.FONT_SUB_TITLE);
		this.labTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.butAdd.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butAdd.setForeground(Color.WHITE);
		this.butAdd.addActionListener(this.addCatListener);
		this.butSave.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butSave.setForeground(Color.WHITE);
		this.butSave.addActionListener(this.saveCatListener);
		panel.setOpaque(false);
		
		///Add to panel
		//Add to sub panel
		Gbm.goToOrigin(c2);
		c2.fill = GridBagConstraints.VERTICAL;
		c2.insets = Constants.INSETS_GENERAL;
		panel.add(this.tf, c2);
		Gbm.nextGridColumn(c2);
		panel.add(this.butAdd, c2);
		//Add to main panel
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.gridwidth = 3;
		this.add(this.labTitle, c);				//Title label
		Gbm.newGridLine(c);
		this.add(this.scrollTable, c);			//Table Scroll bar
		Gbm.newGridLine(c);
		this.add(panel, c);						//Panel (contains the text field and add button)
		Gbm.newGridLine(c);
		this.add(this.butSave, c);				//Save button
		
		this.updateTable();
	}
	
	//Public methods
	/**
	 * Sets the data to be displayed in the table. The revalidate() and repaint() methods will be called upon method execution.
	 * @param categories
	 */
	public void updateTable()
	{
		List<Category> categories = Constants.DATABASE_SERVICE.getAllCategories(this.admin.getID());
		this.categories.clear();
		this.categories.addAll(categories);
		this.table.updateData(categories);
		
		this.revalidate();
		this.repaint();
	}
	/**
	 * Get the list of categories viewed in the table.
	 * @return a List<Category> object.
	 */
	public List<Category> getCategories()
	{
		return this.categories;
	}
	
	//Private methods
	private void addCategory()
	{
		Category cat = new Category(admin.getID(), this.tf.getText().trim(), "");
		Constants.DATABASE_SERVICE.insert(cat);	//insert into database
		this.updateTable();
		this.tf.setText("");		//Empty the text field
	}
	private void saveChanges()
	{
		//Close the window
		Methods.closeThisWindow(this);
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new CenteredPage();
		CategoryEditor ce = new CategoryEditor(null);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.setCenterPanel(ce);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}
}
