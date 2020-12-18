package shared.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
	private JButton butAdd, butSave;
	private Admin admin;
	private CategoryTable table;
	private List<Category> categories;
	private List<Integer> toBeSavedIndexes;
	//Interfaces
	private ActionListener addCatListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					addCategory();
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
		this.scrollTable = ScrollPaneManager.generateDefaultScrollPane(this.table, 10, 10);
		this.tf = new JTextField(10);
		this.butAdd = new JButton("Add");
		this.butSave = new JButton("Save");
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
	}
	
	//Public methods
	/**
	 * Sets the data to be displayed in the table. The revalidate() and repaint() methods will be called upon method execution.
	 * @param categories
	 */
	public void setData(List<Category> categories)
	{
		this.categories.clear();
		this.categories.addAll(categories);
		
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
		//TODO: Use SQL to generate ID
		this.categories.add(new Category(this.categories.size()+1, 1, "", this.tf.getText().trim()));		//Add to category list
		this.table.updateData(this.categories);		//Update the table model
		this.tf.setText("");		//Empty the text field
	}
	private void saveChanges()
	{
		//TODO: Save any changes
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
