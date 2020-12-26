package shared.components;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import models.Person;
import shared.Constants;
import shared.GUIListener;
import shared.Methods;

public class ListView extends JPanel implements GUIListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7485576765825337951L;
	//Fields
	private List<ListTile> tiles;
	private boolean allowMultipleHighlight;
	
	//Constructor
	public ListView()
	{
		//Initialization
		super();
		this.tiles = new ArrayList<ListTile>();
		
		//Properties
		this.setLayout(new GridLayout(0, 1, 1, 3));
		this.setOpaque(false);
	}
	
	//Public Methods
	/**
	 * Update the tiles to be displayed.
	 * @param tiles a List containing ListTile objects.
	 */
	public void updateData(List<ListTile> tiles)
	{
		//Clear data
		for (ListTile tile : this.tiles)
		{
			try
			{
				this.remove(tile);
			}
			catch(NullPointerException ex) {}
		}
		this.tiles.clear();
		
		//Add data
		this.tiles.addAll(tiles);
		for (ListTile tile : this.tiles)
		{
			this.add(tile);
		}
		
		this.revalidate();
		this.repaint();
	}
	/**
	 * Get a list of selected tiles.
	 * @return a List of ListTile objects.
	 */
	public List<ListTile> getSelectedTiles()
	{
		List<ListTile> list = new ArrayList<ListTile>();
		
		for (ListTile tile : this.tiles)
		{
			if (tile.isSelected())
			{
				list.add(tile);
			}
		}
		
		return list;
	}
	/**
	 * Set wether to allow multiple selection or not.
	 * @param b
	 */
	public void setMultipleSelection(boolean b)
	{
		this.allowMultipleHighlight = b;
	}
	
	//Overridden Methods
	@Override
	public void resetDefaults() 
	{
		//Remove all tile selection.
		for (ListTile tile : this.tiles)
		{
			if (tile.isSelected())
			{
				tile.setSelected(false);
			}
		}
	}
	
	//Testing
	public static void main(String args[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		//Initialization
		JFrame frame = new JFrame();
		ListView lv = new ListView();
		List<ListTile> tiles = new ArrayList<ListTile>();
		tiles.add(new SimpleUserTile(new Person("Jackson", "Pierce", "jp@gmail.com", "")));
		tiles.add(new SimpleUserTile(new Person("Adam", "Driver", "ad@gmail.com", "")));
		tiles.add(new SimpleUserTile(new Person("Stu", "Ford", "sf@gmail.com", "")));
		
		//Properties
		lv.updateData(tiles);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(lv);
		
		frame.setVisible(true);
	}
}
