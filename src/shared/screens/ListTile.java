package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListTile extends RoundedPanel
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
	
	//Constructor
	public ListTile()
	{
		//Initialization
		super(false);
		this.initPanelTop();
		this.initPanelCenter();
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
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
	
	//Private Methods
	private void initPanelTop()
	{
		//Initialization
		this.panelTop = new JPanel(new BorderLayout());
		this.labTopLeft = new JLabel();
		this.labTopRight = new JLabel();
		JPanel panelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//Properties
		this.panelTop.setOpaque(false);
		panelLeft.setOpaque(false);
		panelRight.setOpaque(false);
		
		///Add to panel
		//Add to panelLeft
		panelLeft.add(this.labTopLeft);
		//Add to panelRight
		panelRight.add(this.labTopRight);
		//Add to panelTop
		this.panelTop.add(this.labTopLeft, BorderLayout.WEST);
		this.panelTop.add(this.labTopRight, BorderLayout.EAST);
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
		
		//Add to panel
		this.panelCenter.add(this.labTitle);
		this.panelCenter.add(this.labSubtitle);
	}
}
