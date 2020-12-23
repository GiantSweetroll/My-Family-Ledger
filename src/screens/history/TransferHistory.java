package screens.history;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import giantsweetroll.gui.swing.Gbm;
import models.Person;
import shared.Constants;
import shared.Methods;
import shared.components.DatePicker;
import shared.components.HintTextField;
import shared.components.ListTile;
import shared.components.ListView;
import shared.components.SimpleUserTile;
import shared.screens.HistoryPanel;

public class TransferHistory extends HistoryPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5307084475379946087L;
	//Fields
	private JLabel labValue, 
					labReceiver,
					labFrom,
					labTo;
	private JComboBox<String> comboOperand;
	private JTextField tfValue;
	private JScrollPane scrollReceiver;
	private List<Person> persons;
	private List<ListTile> receiverTiles;
	private DatePicker dateFrom, dateTo;
	private ListView listView;
	
	//Constructor
	public TransferHistory(Person person)
	{
		//Initialization
		super(person);
		this.initFilters();
	}
	
	//Public methods
	/**
	 * Update and rebuild the list of selectable receivers. The revalidate() and repaint() methods will be called.
	 * @param persons
	 */
	public void updateReceivers(List<Person> persons)
	{
		//Clear data
		this.receiverTiles.clear();
		this.persons.clear();
		
		//Add data
		this.persons.addAll(persons);
		for (Person person : this.persons)
		{
			SimpleUserTile tile = new SimpleUserTile(person);
			this.receiverTiles.add(tile);
		}
		this.listView.updateData(this.receiverTiles);
		
		this.revalidate();
		this.repaint();
	}
	/**
	 * Disables all selected receivers.
	 */
	public void deselectAllReceivers()
	{
		for (ListTile tile : this.receiverTiles)
		{
			if (tile instanceof SimpleUserTile)
			{
				((SimpleUserTile)tile).setSelected(false);
			}
		}
	}
	
	//Overridden Methods
	@Override
	public void resetFilters()
	{
		this.dateFrom.resetDefaults();
		this.dateTo.resetDefaults();
		this.comboOperand.setSelectedIndex(0);
		this.deselectAllReceivers();
		this.tfValue.setText("");
	}
	@Override
	public void backButtonPressed() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshButtonPressed() 
	{
		// TODO Auto-generated method stub
		
	}
	
	//Private methods
	private void initPanelReceivers()
	{
		//Initialization
		this.listView = new ListView();
		this.persons = new ArrayList<Person>();
		this.receiverTiles = new ArrayList<ListTile>();
		this.scrollReceiver = new JScrollPane(this.listView);
		
		//Properties
		this.scrollReceiver.getViewport().setOpaque(false);
		this.scrollReceiver.setOpaque(false);
	}
	private void initFilters()
	{
		//Initialization
		this.initPanelReceivers();
		this.labValue = new JLabel("Value (Rp.)");
		this.comboOperand = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);
		this.tfValue = new HintTextField("Value");
		this.labReceiver = new JLabel("Receiver");
		this.labFrom = new JLabel("From");
		this.dateFrom = new DatePicker();
		this.labTo = new JLabel("To");
		this.dateTo = new DatePicker();
		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		JPanel panelTop = new JPanel(new GridBagLayout());
//		JPanel panelBelow = new JPanel();
		JPanel panelFilter = new JPanel(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Properties
		this.tfValue.setColumns(10);
		panelTop.setOpaque(false);
//		panelBelow.setOpaque(false);
//		panelBelow.setLayout(new BoxLayout(panelBelow, BoxLayout.Y_AXIS));
		panelFilter.setOpaque(false);
		
		///Add to panel
		//Add to panelTop
		Gbm.goToOrigin(c);
		c.insets = Constants.INSETS_GENERAL;
		c.fill = GridBagConstraints.BOTH;
		panelTop.add(this.labFrom, c);				//From label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelTop.add(this.dateFrom, c);				//From Date
		c.gridwidth = 1;
		Gbm.newGridLine(c);
		panelTop.add(this.labTo, c);				//To label
		Gbm.nextGridColumn(c);
		c.gridwidth = 2;
		panelTop.add(this.dateTo, c);				//To Date
		Gbm.newGridLine(c);
		c.gridwidth = 1;
		panelTop.add(this.labValue, c);				//Value label
		Gbm.nextGridColumn(c);
		panelTop.add(this.comboOperand, c);			//Operand combobox
		Gbm.nextGridColumn(c);
		panelTop.add(this.tfValue, c);				//Value text field
		Gbm.newGridLine(c);
		c.gridwidth = 3;
		panelTop.add(this.labReceiver, c);			//Receivers
		Gbm.newGridLine(c);
		panelTop.add(sep, c);						//Separator
		Gbm.newGridLine(c);
		panelTop.add(this.scrollReceiver, c);		//Receiver scroll pane
		//Add to panelBelow
//		panelBelow.add(this.labReceiver);
//		panelBelow.add(sep);
//		panelBelow.add(this.scrollReceiver);
//		panelBelow.add(Box.createRigidArea(new Dimension(5, 5)));
		//Add to panelFilter
		panelFilter.add(panelTop, BorderLayout.NORTH);
//		panelFilter.add(panelBelow, BorderLayout.SOUTH);
		//Display filter panel
		this.setFilterPanel(panelFilter);
	}

	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		TransferHistory th = new TransferHistory(new Person("Person", "Hai"));
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(th);
		
		frame.setVisible(true);
	}
}
