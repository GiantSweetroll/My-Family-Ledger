package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.ScrollPaneManager;
import main.Main;
import models.Account;
import models.Category;
import models.Person;
import models.Transaction;
import screens.menu.Menu;
import shared.Constants;
import shared.GUIListener;
import shared.Globals;
import shared.Methods;
import shared.components.ComboBoxRenderer;
import shared.components.HintTextField;
import shared.components.HyperlinkLabel;
import shared.components.WarningLabel;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.TransactionTile;
import shared.screens.AccountPanel;
import shared.screens.RoundedPanel;
import shared.screens.TriplePanelPage;

public class InputTransactions extends TriplePanelPage implements GUIListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Fields
	private Person person;
	private AccountPanel panelAcc;
	private JPanel panelPrevTrans, 
					panelInput, 
					panelReceipt;
	private JLabel labelPrevTrans,
					labelLastTs,
					labelInput,
					labelReceipt,
					labWIP,
					labelBack,
					labelWarning;
	private JButton buttonTransfer;
	private JComboBox cbCategory;
	private HintTextField inputItem, inputPrice;
	private ListView listView;
	private List<ListTile> tiles;
	private JScrollPane scrollTf;
	private Transaction lastTransaction;
	
	public InputTransactions(Person person) 
	{
		super();
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.initPanelPrevTrans();
		this.initPanelInput();
		this.initPanelReceipt();
		
		//Properties
		this.setCenterPanels(this.panelPrevTrans, this.panelInput, this.panelReceipt);
		this.setPanelTop(this.panelAcc);
		panelAcc.setAccount(person);
	}
	
	public InputTransactions(Person person, Transaction tr) {
		super();
		//Initialization
		this.person = person;
		this.panelAcc = new AccountPanel();
		this.lastTransaction = tr;
		this.initPanelPrevTrans();
		this.initPanelInput();
		this.initPanelReceipt();
		
		//Properties
		this.setCenterPanels(this.panelPrevTrans, this.panelInput, this.panelReceipt);
		this.setPanelTop(this.panelAcc);
		panelAcc.setAccount(person);
		if (this.lastTransaction != null) 
		{
			Category cat = Constants.DATABASE_SERVICE.getCategory(this.lastTransaction.getCategoryID());
			for (int i=0; i<cbCategory.getItemCount(); i++)
			{
				Category c = (Category)cbCategory.getModel().getElementAt(i);
				if (cat.getID() == c.getID())
				{
					cbCategory.setSelectedIndex(i);
					break;
				}
			}
			this.inputItem.setText(this.lastTransaction.getDesc());
			this.inputPrice.setText(Double.toString(this.lastTransaction.getAmount() * -1d));
		}
	}
		
		
	private void initPanelPrevTrans()
	{
		//Initialization
		this.panelPrevTrans = new RoundedPanel(false);
		this.labelPrevTrans = new JLabel("Previous Transactions");
		this.labelLastTs = new JLabel("Your Last 5 Transactions");
		this.listView = new ListView();
		this.tiles = new ArrayList<ListTile>();
		this.scrollTf = ScrollPaneManager.generateDefaultScrollPane(this.listView, 10, 10);
		SpringLayout sprLayout = new SpringLayout();
		JPanel panelTop = new JPanel(new BorderLayout());
		JPanel panelCenter = new JPanel(sprLayout);
		
		
		//Properties
		this.panelPrevTrans.setLayout(new BorderLayout(5, 50));
		this.panelPrevTrans.setBackground(Color.WHITE);
		this.labelPrevTrans.setFont(Constants.FONT_SUB_TITLE);
		this.labelPrevTrans.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelLastTs.setFont(Constants.FONT_SMALLER);
		this.labelLastTs.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labelLastTs.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		panelCenter.setOpaque(false);
		this.scrollTf.getViewport().setOpaque(false);
		this.scrollTf.setOpaque(false);
		this.scrollTf.getViewport().setBorder(null);
		this.scrollTf.setBorder(null);
		updateListView();
		
		//SpringLayout Constraints
        sprLayout.putConstraint(SpringLayout.NORTH, this.scrollTf, 5, SpringLayout.NORTH, panelCenter);
		sprLayout.putConstraint(SpringLayout.SOUTH, this.scrollTf, -20, SpringLayout.SOUTH, panelCenter);
		sprLayout.putConstraint(SpringLayout.WEST, this.scrollTf, 20, SpringLayout.WEST, panelCenter);
        sprLayout.putConstraint(SpringLayout.EAST, this.scrollTf, -20, SpringLayout.EAST, panelCenter);
        sprLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.scrollTf, 0, SpringLayout.HORIZONTAL_CENTER, panelCenter);
		
		//Add to panel
		//add to panelTop
		panelTop.add(this.labelPrevTrans, BorderLayout.NORTH);
		panelTop.add(this.labelLastTs, BorderLayout.SOUTH);
		panelCenter.add(this.scrollTf);
		//add to panelPrevTrans
		this.panelPrevTrans.add(panelTop, BorderLayout.NORTH);
		this.panelPrevTrans.add(panelCenter, BorderLayout.CENTER);
	}
	
	private void initPanelInput()
	{
		//Initialization
		this.panelInput = new RoundedPanel(false);
		JPanel panelContent = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panelContent, BoxLayout.Y_AXIS);
		JPanel panelButtons = new JPanel(new GridLayout(1,2));
		this.labelInput = new JLabel("Input");
		this.labelBack = new HyperlinkLabel("Back");
		this.inputItem = new HintTextField("Item/Service Name");
		this.inputPrice = new HintTextField("Price (Rp.)");
		this.labelWarning = new WarningLabel();
		this.buttonTransfer = new JButton("Transfer");

		//make combo box
		
		List <Category> categories = Constants.DATABASE_SERVICE.getAllCategories(0);	//0 for default categories
		categories.addAll(Constants.DATABASE_SERVICE.getAllCategories(Globals.activeUser.getID()));

		this.cbCategory = new JComboBox<Category>();
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getID() == 1) {
				continue;
			}
			cbCategory.addItem(categories.get(i));
			cbCategory.setRenderer(new ComboBoxRenderer(categories.get(i)));
		}
		
//		cbCategory.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent event) {
//                // Get the source of the component, which is our combo box.
//                JComboBox cbCategory = (JComboBox) event.getSource();
//                // Print the selected items and the action command.
//                Object selected = cbCategory.getSelectedItem();
//                System.out.println("Selected Item  = " + selected);
//            }
//		});
//		
		//Properties
		this.panelInput.setLayout(new BorderLayout());
		this.panelInput.setBackground(Color.WHITE);
		panelContent.setLayout(boxlayout);
		panelContent.setOpaque(false);
		panelButtons.setOpaque(false);
		
		this.labelInput.setFont(Constants.FONT_SUB_TITLE);
		this.labelInput.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.labelBack.setFont(Constants.FONT_SMALLER);
		this.labelBack.setForeground(Constants.COLOR_HYPERLINK);
		this.labelBack.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{	
				if(lastTransaction != null) {
					Main.popScreen();
				}
				else {
					Globals.activeUser = person;
					Main.changeScreen(new Menu(person, false), Constants.FRAME_MAIN_MENU);
				}
			}
		});
		
		this.inputItem.setFont(Constants.FONT_SMALLER);
		this.inputItem.setForeground(Constants.COLOR_TEXT_GRAY);
		
		
		this.inputPrice.setFont(Constants.FONT_SMALLER);
		this.inputPrice.setForeground(Constants.COLOR_TEXT_GRAY);
		
		this.cbCategory.setFont(Constants.FONT_SMALLER);
		this.buttonTransfer.setBackground(Constants.COLOR_BUTTON_BASE);
		this.buttonTransfer.setForeground(Color.WHITE);
		this.buttonTransfer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String item = ((HintTextField) inputItem).getData().trim();
					String price = ((HintTextField) inputPrice).getData().trim();
					double dprice = Double.parseDouble(price) * -1d;
					Category category = (Category) cbCategory.getSelectedItem();
					long millis = System.currentTimeMillis();
					Date date = new Date(millis);
					Account curAcc = Constants.DATABASE_SERVICE.getAccount(person.getID());
					if(item.equals("") || price.equals("")) {
						labelWarning.setText("Invalid Input Detected");
					}
					if(!isDigit(price)){
						labelWarning.setText("Please use digits for Price");
					}
					else {
						// if not from table
						if(lastTransaction == null) 
						{
							Constants.DATABASE_SERVICE.insert(new Transaction(category.getID(), null, person.getID(), date, date, dprice, item));
							curAcc.updateBalance(dprice);
							Constants.DATABASE_SERVICE.update(curAcc.getID(), curAcc);
							JOptionPane.showMessageDialog(null, "Transaction Successfully Made");
							resetInputPage();
						}
						else {
							
							Double balanceAfterEdit = lastTransaction.getAmount() - dprice;
							Transaction tr = new Transaction(lastTransaction.getID(), category.getID(), null, person.getID(), lastTransaction.getDateInput(), date, dprice, item);
							Constants.DATABASE_SERVICE.update(lastTransaction.getID(), tr);
							curAcc.updateBalance(balanceAfterEdit * -1d);
							Constants.DATABASE_SERVICE.update(curAcc.getID(), curAcc);
							JOptionPane.showMessageDialog(null, "Transaction Sucessfully Edited");
							Main.popScreen();
						}
						
					}
				}
				catch(NumberFormatException ex){
					labelWarning.setText("Invalid input");
				}
				
				
			}
		});
		
		this.labelWarning.setAlignmentX(CENTER_ALIGNMENT);
		
		//Add to panels
		panelButtons.add(this.labelBack);
		panelButtons.add(this.buttonTransfer);
		panelContent.add(Box.createRigidArea(new Dimension(0, 40)));
		panelContent.add(this.cbCategory);
		panelContent.add(Box.createRigidArea(new Dimension(0, 5)));
		panelContent.add(this.inputItem);
		panelContent.add(Box.createRigidArea(new Dimension(0, 5)));
		panelContent.add(this.inputPrice);
		panelContent.add(Box.createRigidArea(new Dimension(0, 5)));
		panelContent.add(this.labelWarning);
		panelContent.add(Box.createRigidArea(new Dimension(0, 200)));
		panelContent.add(panelButtons);
		panelContent.add(Box.createRigidArea(new Dimension(0, 350)));
		this.panelInput.add(this.labelInput, BorderLayout.NORTH);
		this.panelInput.add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.WEST);
		this.panelInput.add(panelContent, BorderLayout.CENTER);
		this.panelInput.add(Box.createRigidArea(new Dimension(100, 0)), BorderLayout.EAST);
	}
	
	private void initPanelReceipt()
	{
		//Initialization
		this.panelReceipt = new RoundedPanel(false);
		this.labelReceipt = new JLabel("Receipt");
		this.labWIP = new JLabel("<html><i>To be released soon</i></html>");
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelReceipt.setBackground(Color.WHITE);
		this.panelReceipt.setLayout(new BorderLayout());
		this.labelReceipt.setFont(Constants.FONT_SUB_TITLE);
		this.labelReceipt.setHorizontalAlignment(SwingConstants.CENTER);
		this.labWIP.setFont(Constants.FONT_SUB_TITLE);
		this.labWIP.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labWIP.setHorizontalAlignment(SwingConstants.CENTER);
		this.labWIP.setVerticalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		//Add to panelTop
		panelTop.add(this.labelReceipt, BorderLayout.NORTH);
		//Add to panelInput
		this.panelReceipt.add(panelTop, BorderLayout.NORTH);
		this.panelReceipt.add(this.labWIP, BorderLayout.CENTER);
	}
	
	private void updateListView() {
		List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions(person.getID());
		this.tiles.clear();
		
		//Add the data
		for(int i=0; i < 5; i++) 
		{
			try
			{
				TransactionTile ttile = new TransactionTile(transactions.get(transactions.size()-1-i));
				this.tiles.add(ttile);
			}
			catch(Exception ex)
			{
				break;
			}
		}
		this.listView.updateData(this.tiles);
		this.listView.setAllowSelection(false);
	}
	
	private static boolean isDigit(String inputText) {
	    if (inputText == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(inputText);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	private void resetInputPage() {
		this.cbCategory.setSelectedIndex(0);
		this.inputItem.setText("");
		this.inputPrice.setText("");
		this.inputItem.setForeground(Color.GRAY);
		this.inputPrice.setForeground(Color.GRAY);
		this.labelWarning.setText("");
		this.panelAcc.setAccount(this.person);
		this.updateListView();
	}
	
	//Overridden Methods
	@Override
	public void resetDefaults() {}

	@Override
	public void onDisplayed() {}
	
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		Person person = new Person("Jocelyn", "Thiojaya");
		InputTransactions it = new InputTransactions(person);
		
		//Properties
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(it);
		
		frame.setVisible(true);
	}
}