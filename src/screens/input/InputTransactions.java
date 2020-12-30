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
import models.Category;
import models.Person;
import models.Transaction;
import screens.menu.Menu;
import shared.Constants;
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

public class InputTransactions extends TriplePanelPage{
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
					labelBack,
					labelWarning;
	private JButton buttonTransfer;
	private JComboBox cbCategory;
	private HintTextField inputItem, inputPrice;
	private ListView listView;
	private List<ListTile> tiles;
	private JScrollPane scrollTf;
	
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
		this.listView.setMultipleSelection(false);
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

		List <Category> categories = Constants.DATABASE_SERVICE.getAllCategories();

		this.cbCategory = new JComboBox<Category>();
		for(int i = 0; i < categories.size(); i++) {
			if(categories.get(i).getID() == 1) {
				continue;
			}
			cbCategory.addItem(categories.get(i));
			cbCategory.setRenderer(new ComboBoxRenderer(categories.get(i)));
		}

		
		cbCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the source of the component, which is our combo box.
                JComboBox cbCategory = (JComboBox) event.getSource();
                // Print the selected items and the action command.
                Object selected = cbCategory.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
            }
		});
		
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
				Globals.activeUser = person;
				Main.changeScreen(new Menu(person, false));
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
				String item = ((HintTextField) inputItem).getData().trim();
				String price = ((HintTextField) inputPrice).getData().trim();
				Category category = (Category) cbCategory.getSelectedItem();
				long millis = System.currentTimeMillis();
				Date date = new Date(millis);
				if(!isDigit(price)){
					labelWarning.setText("Please use digits for Price");
				}
				if(item.equals("") || price.equals("")) {
					labelWarning.setText("Invalid Input Detected");
				}
				else {
					double dprice = Double.parseDouble(price);
					Constants.DATABASE_SERVICE.insert(new Transaction(category.getID(), null, person.getID(), date, date, dprice, item));
					JOptionPane.showMessageDialog(null, "Transaction Successfully Made");
					resetInputPage();
				}
				
			}
		});
		
		//Add to panels
		panelButtons.add(this.labelBack);
		panelButtons.add(this.buttonTransfer);
		panelContent.add(Box.createRigidArea(new Dimension(0, 40)));
		panelContent.add(this.cbCategory);
		panelContent.add(this.inputItem);
		panelContent.add(this.inputPrice);
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
		JPanel panelTop = new JPanel(new BorderLayout());
		
		//Properties
		this.panelReceipt.setBackground(Color.WHITE);
		this.labelReceipt.setFont(Constants.FONT_SUB_TITLE);
		this.labelReceipt.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		
		//Add to panelTop
		panelTop.add(this.labelReceipt, BorderLayout.NORTH);
		//Add to panelInput
		this.panelReceipt.add(panelTop, BorderLayout.NORTH);
	}
	
	private void updateListView() {
		List<Transaction> transactions = Constants.DATABASE_SERVICE.getAllTransactions(person.getID());
		this.tiles.clear();
		
		//Add the data
		for(int i=0; i < transactions.size(); i++) {
			TransactionTile ttile = new TransactionTile(transactions.get(i));
			this.tiles.add(ttile);
		}
		this.listView.updateData(this.tiles);
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
		this.labelWarning.setText("");
		this.updateListView();
	}
	
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