package screens.history;

import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import models.Person;
import shared.Constants;
import shared.screens.HistoryPanel;
import shared.screens.SimpleUserTile;

public class TransferHistory extends HistoryPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5307084475379946087L;
	//Fields
	private JLabel labValue, labReceiver, labDateFrom, labDateTo;
	private JComboBox<String> comboOperand;
	private JTextField tfValue;
	private JScrollPane scrollReceiver;
	private JPanel panelReceivers;
	private List<Person> persons;
	private List<SimpleUserTile> receiverTiles;
	
	//Constructor
	public TransferHistory(Person person)
	{
		super(person);
	}
	
	//Public methods
	/**
	 * Update and rebuild the list of selectable receivers. The revalidate() and repaint() methods will be called.
	 * @param persons
	 */
	public void updateReceivers(List<Person> persons)
	{
		//Clear data
		for (SimpleUserTile tile : this.receiverTiles)
		{
			this.panelReceivers.remove(tile);
		}
		this.receiverTiles.clear();
		this.persons.clear();
		
		//Add data
		this.persons.addAll(persons);
		for (Person person : this.persons)
		{
			SimpleUserTile tile = new SimpleUserTile(person);
			this.receiverTiles.add(tile);
			this.panelReceivers.add(tile);
		}
		
		this.revalidate();
		this.repaint();
	}
	
	//Private methods
	private void initPanelReceivers()
	{
		//Initialization
		this.panelReceivers = new JPanel(new GridLayout(0, 1, 1, 3));
		this.persons = new ArrayList<Person>();
		this.receiverTiles = new ArrayList<SimpleUserTile>();
		
		//Properties
		this.panelReceivers.setOpaque(false);
	}
	private void initFilters()
	{
		//Initialization
		this.initPanelReceivers();
		this.labValue = new JLabel("Value (Rp.)");
		this.comboOperand = new JComboBox<String>(Constants.COMBO_BOX_OPERANDS);
		this.tfValue = new JTextField(10);
		this.labReceiver = new JLabel("Receiver");
		this.labDateFrom = new JLabel(Constants.DATE_FORMAT.format(LocalDateTime.now()));
		this.labDateTo = new JLabel(Constants.DATE_FORMAT.format(LocalDateTime.now()));
		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		JPanel panel = new JPanel();
		
		//Properties
		
	}
}
