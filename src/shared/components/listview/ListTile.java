package shared.components.listview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;
import shared.screens.RoundedPanel;

public class ListTile extends RoundedPanel implements MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9030748067206404980L;
	
	//Fields
	private JLabel labTitle,
					labSubtitle,
					labTopRight,
					labTopLeft;
	private JPanel panelTop, panelCenter;
	private boolean selected;
	private ListView listView;
	
	//Constructor
	public ListTile()
	{
		//Initialization
		super(false);
		this.selected = false;
		this.initPanelTop();
		this.initPanelCenter();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.addMouseListener(this);
		
		//Add to panel
		this.add(this.panelTop, BorderLayout.NORTH);
		this.add(this.panelCenter, BorderLayout.CENTER);
	}
	
	//Public Methods
	/**
	 * Set the title text.
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.labTitle.setText(title);
	}
	/**
	 * Set the subtitle text
	 * @param sub
	 */
	public void setSubtitle(String sub)
	{
		this.labSubtitle.setText(sub);
	}
	/**
	 * Set the text in the top right section
	 * @param text
	 */
	public void setTopRightText(String text)
	{
		this.labTopRight.setText(text);
	}
	/**
	 * Set the text in the top left section
	 * @param text
	 */
	public void setTopLeftText(String text)
	{
		this.labTopLeft.setText(text);
	}
	/**
	 * Check whether the tile is selected.
	 * @return Returns true if the tile is selected.
	 */
	public boolean isSelected()
	{
		return this.selected;
	}
	/**
	 * Set whether the tile is to be selected or not.
	 * @param b
	 */
	public void setSelected(boolean b)
	{
		this.selected = b;
		this.setBackground(this.selected? Constants.COLOR_SELECTED : Color.WHITE);
	}
	
	//Protected methods
	/**
	 * Sets the parent list view that will contain this tile.
	 * @param lv a ListView object
	 * @param index the location index in the list view
	 */
	protected void setListViewParent(ListView lv)
	{
		this.listView = lv;
	}
	
	//Private Methods
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel(new BorderLayout());
		this.labTopLeft = new JLabel();
		this.labTopRight = new JLabel();
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		
		//Properties
		this.panelTop.setOpaque(false);
		this.labTopRight.setFont(Constants.FONT_SMALLER);
		this.labTopLeft.setFont(Constants.FONT_SMALLER);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		
		///Add to panel
		//Add to panelLeft
		panelLeft.add(this.labTopLeft);
		//Add to panelRight
		panelRight.add(this.labTopRight);
		//Add to panelTop
		this.panelTop.add(panelLeft, BorderLayout.WEST);
		this.panelTop.add(panelRight, BorderLayout.EAST);
	}
	private void initPanelCenter()
	{
		//Initialization
		this.panelCenter = new JPanel();
		this.labTitle = new JLabel();
		this.labSubtitle = new JLabel();
		
		//Properties
		this.panelCenter.setLayout(new BoxLayout(this.panelCenter, BoxLayout.Y_AXIS));
		this.panelCenter.setOpaque(false);
		this.labTitle.setFont(Constants.FONT_GENERAL_BOLD);
		this.labTitle.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		this.labSubtitle.setFont(Constants.FONT_SMALLER);
		this.labSubtitle.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));
		
		//Add to panel
		this.panelCenter.add(this.labTitle);
		this.panelCenter.add(this.labSubtitle);
	}
	
	//Overridden Methods
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		this.setSelected(!this.selected);
		if (this.listView != null)
		{
			this.listView.notifySelection(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		ListTile t1 = new ListTile();
		ListTile t2 = new ListTile();
		
		//Properties
		t1.setTitle("Venti");
		t1.setSubtitle("Heppy birthday");
		t1.setTopRightText("Rp. 250000");
		t1.setTopLeftText("23/11/2000");
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(t1);
		frame.add(t2);
		
		frame.setVisible(true);
	}
}
