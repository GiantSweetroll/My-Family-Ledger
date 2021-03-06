package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import models.Person;
import shared.Constants;
import shared.GUIListener;
import shared.Methods;
import shared.components.AppButton;

public abstract class HistoryPanel extends JPanel implements GUIListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2967299059046868489L;
	//Fields
	private JPanel panelTop, 
						panelFilters,
						panelTable,
						panelButtons,
						panelFilterComp;
	private AppButton butResetFilter,
						butBack,
						butRefresh;
	private JLabel labelFilter;
	protected AccountPanel panelAcc;
	private Person person;
	private JTable table;
	private JScrollPane scrollTable;
	
	//Constructor
	public HistoryPanel(Person person)
	{
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelTop();
		this.initPanelFilter();
		this.initPanelButtons();
		this.initPanelTable();
		JPanel panelContent = new JPanel(new BorderLayout());
		JPanel panelLeft = new JPanel(new BorderLayout(10, 0));
		JPanel panelRight = new JPanel(new BorderLayout(10, 0));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		//Properties
		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.GRAY);
		this.panelAcc.setAccount(person);
		this.butResetFilter.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						resetFilters();
					}
				});
		this.butBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				backButtonPressed();
			}
		});
		this.butRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				refreshButtonPressed();
			}
		});
		panelContent.setOpaque(false);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		
		///Add to panel
		//Add to panelLeft
		panelLeft.add(this.panelFilters, BorderLayout.CENTER);
		panelLeft.add(p1, BorderLayout.WEST);
		//Add to panelContent
		panelContent.add(this.panelTable, BorderLayout.CENTER);
		panelContent.add(this.panelButtons, BorderLayout.SOUTH);
		//Add to panelRight
		panelRight.add(panelContent, BorderLayout.CENTER);
		panelRight.add(p2, BorderLayout.EAST);
		//Add to panel
		this.add(this.panelTop, BorderLayout.NORTH);
		this.add(panelLeft, BorderLayout.WEST);
		this.add(panelRight, BorderLayout.CENTER);
	}
	
	//Public Methods
	/**
	 * Set the Filter components
	 * @param panel A JPanel that holds the filter components.
	 */
	public void setFilterPanel(JPanel panel)
	{
		this.panelFilters.remove(this.panelFilterComp);
		this.panelFilterComp = panel;
		this.panelFilters.add(panel);
	}
	/**
	 * Set the table to be viewed in the table panel. 
	 * This method calls the revalidate() and repaint() methods.
	 * @param table A JTable object
	 */
	public void setTable(JTable table)
	{
		this.scrollTable.setViewportView(table);
		this.revalidate();
		this.repaint();
	}
	/**
	 * Get the table object instance.
	 * @return A JTable object.
	 */
	public JTable getTable()
	{
		return this.table;
	}
	/**
	 * Set the action when the "Reset Filter" button is clicked.
	 * @param a an ActionListener object
	 */
	public void setWhenResetFilterClicked(ActionListener a)
	{
		this.setAction(this.butResetFilter, a);
	}
	/**
	 * Set the action when the "Back" button is clicked.
	 * @param a an ActionListener object.
	 */
	public void setWhenBackButtonClicked(ActionListener a)
	{
		this.setAction(this.butBack, a);
	}
	/**
	 * Set the action when the "Refresh" button is clicked.
	 * @param a an ActionListener object.
	 */
	public void setWhenRefreshButtonClicked(ActionListener a)
	{
		this.setAction(this.butRefresh, a);
	}
	
	//Abstract Methods
	/**
	 * Method used to reset the filter components.
	 */
	public abstract void resetFilters();
	/**
	 * Operation to be done when the "Back" button is pressed.
	 */
	public abstract void backButtonPressed();
	/**
	 * Operation to be done when the "Refresh" button is pressed.
	 */
	public abstract void refreshButtonPressed();
	
	//private methods
	private void setAction(JButton button, ActionListener a)
	{
		//Remove previous action listeners
		Methods.removeActionListeners(button);
		
		//Add new action listener
		button.addActionListener(a);
	}
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		//Properties
		this.panelTop.setLayout(new BorderLayout(10, 0));
		this.panelTop.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		
		//Add to panel
		this.panelTop.add(p1, BorderLayout.NORTH);
		this.panelTop.add(p2, BorderLayout.WEST);
		this.panelTop.add(this.panelAcc, BorderLayout.CENTER);
		this.panelTop.add(p3, BorderLayout.EAST);
	}
	private void initPanelFilter()
	{
		//Initialization
		this.panelFilters = new RoundedPanel(false);
		this.labelFilter = new JLabel("Filters");
		this.butResetFilter = new AppButton("Reset Filters");
		this.panelFilterComp = new JPanel();
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		JPanel panelButton = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p2 = new JPanel();
		
		//Properties
		this.panelFilters.setBackground(Color.WHITE);
		this.panelFilters.setLayout(new BorderLayout(10, 0));
		this.panelFilterComp.setOpaque(false);
		this.labelFilter.setFont(Constants.FONT_SUB_TITLE);
		this.labelFilter.setHorizontalAlignment(SwingConstants.CENTER);
		panelButton.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		
		///Add to panel
		//Add to p1
		p1.add(this.butResetFilter);
		//Add to panelButton
		panelButton.add(sep, BorderLayout.NORTH);
		panelButton.add(p1, BorderLayout.CENTER);
		//Add to panelFilters
		this.panelFilters.add(this.labelFilter, BorderLayout.NORTH);
		this.panelFilters.add(panelButton, BorderLayout.SOUTH);
	}
	private void initPanelButtons()
	{
		//Initialization
		this.panelButtons = new RoundedPanel(false);
		this.butBack = new AppButton("Back");
		this.butRefresh = new AppButton("Refresh");
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//Properties
		this.panelButtons.setBackground(Color.WHITE);
		this.panelButtons.setLayout(new BorderLayout());
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		
		///Add to panel
		//add to panelLeft
		panelLeft.add(this.butBack);
		//Add to panelRight
		panelRight.add(this.butRefresh);
		//Add to panelButtons
		this.panelButtons.add(panelLeft, BorderLayout.WEST);
		this.panelButtons.add(panelRight, BorderLayout.EAST);
	}
	private void initPanelTable()
	{
		//Initialization
		this.panelTable = new RoundedPanel(false);
		this.scrollTable = new JScrollPane();
		
		//Properties
		this.panelTable.setLayout(new BorderLayout());
		this.panelTable.setBackground(Color.WHITE);
		this.scrollTable.setBorder(null);
		this.scrollTable.getViewport().setBorder(null);
		this.scrollTable.getViewport().setOpaque(false);
		this.scrollTable.setOpaque(false);
		
		//Add to panel
		this.panelTable.add(this.scrollTable);
	}

	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		HistoryPanel hp = new HistoryPanel(new Person("Person", "Hai"))
				{
					/**
					 * 
					 */
					private static final long serialVersionUID = -2614895861225591903L;

					@Override
					public void resetFilters()
					{
						System.out.println("Reset filters...");
					}

					@Override
					public void backButtonPressed() 
					{
						System.out.println("Back button pressed....");
						
					}

					@Override
					public void refreshButtonPressed() 
					{
						System.out.println("Refresh button pressed...");
						
					}

					@Override
					public void resetDefaults() {}

					@Override
					public void onDisplayed() {}
				};
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(hp);
		
		frame.setVisible(true);
	}
}
