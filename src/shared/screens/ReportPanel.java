package shared.screens;

import java.awt.BorderLayout;  
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import shared.Methods;
import shared.components.AppButton;

public abstract class ReportPanel extends JPanel{
	
	private static final long serialVersionUID = -2967299059046868489L;
	
	//Fields
	private JPanel panelCenter, panelBottomRound,
					panelTableTop, panelTableBottom,
					panelUsers, panelUsersComp,
					panelDate, panelDateComp;
	private AppButton butBack,butRefresh,
					butResetDate;
	private JLabel dateLabel, usersLabel ;
	private AccountPanel panelAcc;
	private Person person;
	private JTable table1, table2;
	private JScrollPane scrollTableTop, scrollTableBottom;
	
	public ReportPanel(Person person) {
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelTop();
		this.initPanelBottom();
		this.initTable1();
		this.initTable2();
		this.initDatePanel();
		this.initUsers();
		
		
		JPanel panelLeft = new JPanel(new GridLayout(2,0));
		JPanel panelRight = new JPanel(new GridLayout(2,0));
		JPanel panelBottom = new JPanel(new BorderLayout(10, 0));
		JPanel panelContent = new JPanel(new BorderLayout());
		JPanel panelContentBorder = new JPanel(new BorderLayout(10,0));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		
		//Properties 
		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.GRAY);
		panelBottom.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		panelContent.setOpaque(false);
		panelContentBorder.setOpaque(false);
		this.butBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				BackButtonPressed();
			}
		});
		
		this.butRefresh.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				RefreshButtonPressed();
			}
		});
		
		this.butResetDate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ResetDateButtonPressed();
			}
			
		});
		
		//Add to panelTop
		this.panelAcc.setAccount(person);
		
		//Add to panelBottom
		panelBottom.add(panelBottomRound,BorderLayout.CENTER);
		panelBottom.add(p1,BorderLayout.EAST);
		panelBottom.add(p2,BorderLayout.SOUTH);
		panelBottom.add(p3,BorderLayout.WEST);
		
		//Add to panelLeft
		panelLeft.add(panelUsers);
		panelLeft.add(panelDate);
	
		//Add to panelRight
		panelRight.add(panelTableTop);
		panelRight.add(panelTableBottom);
		
		//Add to panelContent
		panelContent.add(panelLeft,BorderLayout.WEST);
		panelContent.add(panelRight,BorderLayout.CENTER);
		
		//PanelContentBorder
		panelContentBorder.add(panelContent,BorderLayout.CENTER);
		panelContentBorder.add(p4,BorderLayout.EAST);
		panelContentBorder.add(p5,BorderLayout.WEST);
		
		//Add to panel 
		this.add(panelCenter,BorderLayout.NORTH);
		this.add(panelBottom,BorderLayout.SOUTH);
		this.add(panelContentBorder,BorderLayout.CENTER);
		
	}
	
	//Public Method 
	
	public void setusersPanel(JPanel panel)
	{
		this.panelUsers.remove(this.panelUsersComp);
		this.panelUsersComp = panel;
		this.panelUsers.add(panel);
	}
	
	public void setDatePanel(JPanel panel)
	{
		this.panelDate.remove(this.panelUsersComp);
		this.panelDateComp = panel;
		this.panelDate.add(panel);
	}
	
	public void setTableTop(JTable table1)
	{
		this.scrollTableTop.setViewportView(table1);
		this.revalidate();
		this.repaint();
	}
	
	public void setTable2(JTable table2)
	{
		this.scrollTableBottom.setViewportView(table2);
		this.revalidate();
		this.repaint();
	}
	
	public JTable getTable1()
	{
		return this.table1;
	}
	
	public JTable getTable2()
	{
		return this.table2;
	}
	
	/**
	 * Set the action when the "Back" button is clicked.
	 * @param a an ActionListener object.
	 */
	public void setWhenBackButtonClicked(ActionListener a)
	{
		this.setAction(this.butBack, a);
	}
	
	public void setWhenRefreshButtonClicked(ActionListener a)
	{
		this.setAction(this.butRefresh, a);
	}
	
	public void setWhenRefreshDateButtonClicked(ActionListener a)
	{
		this.setAction(this.butResetDate, a);
	}
	
	
	public abstract void BackButtonPressed();
	
	public abstract void RefreshButtonPressed();
	
	public abstract void ResetDateButtonPressed();
	
	
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
		this.panelCenter = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		//Properties
		this.panelCenter.setLayout(new BorderLayout(10, 0));
		this.panelCenter.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		
		//Add to panel
		this.panelCenter.add(p1, BorderLayout.NORTH);
		this.panelCenter.add(p2, BorderLayout.WEST);
		this.panelCenter.add(this.panelAcc, BorderLayout.CENTER);
		this.panelCenter.add(p3, BorderLayout.EAST);
	}
	
	private void initPanelBottom()
	{
		this.panelBottomRound = new RoundedPanel(false);
		this.butBack = new AppButton("Back");
		this.butRefresh = new AppButton("Refresh");
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
	
		//Properties
		this.panelBottomRound.setBackground(Color.WHITE);
		this.panelBottomRound.setLayout(new BorderLayout(10,0));
		this.panelBottomRound.setOpaque(false);
		this.butBack.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butBack.setForeground(Color.WHITE);
		this.butRefresh.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butRefresh.setForeground(Color.WHITE);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
	
		///Add to panel
		//add to panelLeft
		panelLeft.add(this.butBack);
		//Add to panelRight
		panelRight.add(this.butRefresh);
		
		//Add to panelButtons
		this.panelBottomRound.add(panelLeft, BorderLayout.WEST);
		this.panelBottomRound.add(panelRight, BorderLayout.EAST);	
	}
	
	private void initTable1()
	{
		this.panelTableTop = new RoundedPanel(false);
		this.scrollTableTop = new JScrollPane();
		
		//Properties
		this.panelTableTop.setLayout(new BorderLayout());
		this.panelTableTop.setBackground(Color.WHITE);
		this.scrollTableTop.setBorder(null);
		this.scrollTableTop.getViewport().setOpaque(false);
		this.scrollTableTop.setOpaque(false);
		
		//Add to panel
		this.panelTableTop.add(this.scrollTableTop);
	}
	
	private void initTable2()
	{
		this.panelTableBottom = new RoundedPanel(false);
		this.scrollTableBottom = new JScrollPane();
		
		//Properties
		this.panelTableBottom.setLayout(new BorderLayout());
		this.panelTableBottom.setBackground(Color.WHITE);
		this.scrollTableBottom.setBorder(null);
		this.scrollTableBottom.getViewport().setOpaque(false);
		this.scrollTableBottom.setOpaque(false);
		
		//Add to panel
		this.panelTableBottom.add(this.scrollTableBottom);
	}
	
	private void initUsers() {
		this.panelUsers = new RoundedPanel(false);
		this.panelUsersComp = new JPanel();
		this.usersLabel = new JLabel("Users");
		JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
		JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
		JPanel paneltop = new JPanel(new BorderLayout());

		
		//Properties
		this.panelUsers.setBackground(Color.WHITE);
		this.panelUsersComp.setOpaque(false);
		this.panelUsers.setLayout(new BorderLayout(10, 0));
		this.usersLabel.setFont(Constants.FONT_GENERAL_BOLD);
		this.usersLabel.setHorizontalAlignment(SwingConstants.CENTER);
		paneltop.setOpaque(false);
		
		//Add to panel 
		//add to panel Top
		paneltop.add(usersLabel,BorderLayout.NORTH);
		paneltop.add(sep,BorderLayout.CENTER);

		//Add to panelUsers
		this.panelUsers.add(paneltop,BorderLayout.NORTH);
		this.panelUsers.add(sep2,BorderLayout.SOUTH);
	
		
	}
	
	public void initDatePanel() {
		this.panelDate = new RoundedPanel(false);
		this.panelDateComp = new JPanel();
		this.dateLabel = new JLabel("Date Range");
		this.butResetDate = new AppButton("Reset");
		JPanel pbutton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		//Properties
		this.panelDate.setBackground(Color.WHITE);
		this.panelDateComp.setOpaque(false);
		this.panelDate.setLayout(new BorderLayout(10, 0));
		this.dateLabel.setFont(Constants.FONT_GENERAL_BOLD);
		this.dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.butResetDate.setBackground(Constants.COLOR_BUTTON_BASE);
		this.butResetDate.setForeground(Color.WHITE);
		pbutton.setOpaque(false);
		panelDateComp.setOpaque(false);
		
		
		//Add to panel 
		//add to panel1 
		pbutton.add(butResetDate);
		
		
		//add to panelDate
		this.panelDate.add(dateLabel,BorderLayout.NORTH);
		this.panelDate.add(pbutton,BorderLayout.SOUTH);	
	}
	
	
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		Person p1 = new Person("adam","smith");
		ReportPanel rp = new ReportPanel(p1)
		{
			@Override
			public void BackButtonPressed() {
				System.out.println("Back button pressed.");
				
			}

			@Override
			public void RefreshButtonPressed() {
				System.out.println("Refresh button clicked");
				
			}

			@Override
			public void ResetDateButtonPressed() {
				System.out.println("Refresh Date Button clicked");				
			}
		};
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(rp);
		
		frame.setVisible(true);
			}

}
