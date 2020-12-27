package screens.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.FontUIResource;

import main.Main;
import models.Account;
import models.Category;
import models.Person;
import models.Transaction;
import models.User;
import screens.auth.SignIn;
import shared.Constants;
import shared.Globals;
import shared.Methods;
import shared.components.ComboBoxRenderer;
import shared.components.HintTextField;
import shared.components.HyperlinkLabel;
import shared.components.listview.ListTile;
import shared.components.listview.ListView;
import shared.components.listview.SimpleUserTile;
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
					labelBack;
	private JButton buttonTransfer;
	private JComboBox cbCategory;
	private HintTextField inputItem, inputPrice;
	private ListView listView;
	private List<ListTile> tiles;
	private JScrollPane scrollReceiver;
	
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
		this.scrollReceiver = new JScrollPane(this.listView);
		JPanel panelTop = new JPanel(new BorderLayout());
		
		
		//Properties
		this.panelPrevTrans.setLayout(new BorderLayout(5, 50));
		this.panelPrevTrans.setBackground(Color.WHITE);
		this.labelPrevTrans.setFont(Constants.FONT_SUB_TITLE);
		this.labelPrevTrans.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelLastTs.setFont(Constants.FONT_SMALLER);
		this.labelLastTs.setForeground(Constants.COLOR_TEXT_GRAY);
		this.labelLastTs.setHorizontalAlignment(SwingConstants.CENTER);
		panelTop.setOpaque(false);
		this.listView.setMultipleSelection(false);
		this.scrollReceiver.getViewport().setOpaque(false);
		this.scrollReceiver.setOpaque(false);
		updateListView();
		
		//Add to panel
		//add to panelTop
		panelTop.add(this.labelPrevTrans, BorderLayout.NORTH);
		panelTop.add(this.labelLastTs, BorderLayout.CENTER);
		panelTop.add(this.listView, BorderLayout.SOUTH);
		//add to panelPrevTrans
		this.panelPrevTrans.add(panelTop, BorderLayout.NORTH);
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
		this.buttonTransfer = new JButton("Transfer");

		//make combo box

		List <Category> categories = Constants.DATABASE_SERVICE.getAllCategories();

		this.cbCategory = new JComboBox<Category>();
		for(int i = 0; i < categories.size(); i++) {
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
				Main.popScreen();
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
				double price = Double.parseDouble(((HintTextField) inputPrice).getData().trim());
				Category category = (Category) cbCategory.getSelectedItem();
				
			}
		});
		
		//Add to panels
		panelButtons.add(this.labelBack);
		panelButtons.add(this.buttonTransfer);
		panelContent.add(Box.createRigidArea(new Dimension(0, 40)));
		panelContent.add(this.cbCategory);
		panelContent.add(this.inputItem);
		panelContent.add(this.inputPrice);
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
		List<ListTile> currentTiles = new ArrayList<ListTile>();
		
		//Add the data
		for(int i=0; i < transactions.size(); i++) {
			TransactionTile ttile = new TransactionTile(transactions.get(i));
			currentTiles.add(ttile);
		}
		
		this.tiles = currentTiles;
		this.listView.updateData(this.tiles);
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