package shared.components;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

import shared.GUIListener;

public class ListView extends JPanel implements GUIListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7485576765825337951L;
	//Fields
	private List<ListTile> tiles;
	
	//Constructor
	public ListView()
	{
		//Initialization
		super();
		
		//Properties
		this.setLayout(new GridLayout(0, 1, 1, 3));
		this.setOpaque(false);
	}
	
	//Public Methods
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
	
}
