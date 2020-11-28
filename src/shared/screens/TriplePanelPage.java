package shared.screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TriplePanelPage extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698417464737769149L;
	
	//Fields
	private JPanel panelTop,
					panelLeft,
					panelCenter,
					panelRight,
					panelWrapper;
	
	//Constructor
	public TriplePanelPage()
	{
		this.init(new JPanel(), new JPanel(), new JPanel(), new JPanel(), Color.GRAY);
	}
	
	//Public Methods
	/**
	 * Set the top panel. The revalidate() and repaint() method will not be called.
	 * If you have issues in displaying the new content try calling them.
	 * @param panel
	 */
	public void setPanelTop(JPanel panel)
	{
		this.remove(this.panelTop);
		
		this.panelTop = panel;
		
		this.add(this.panelTop, BorderLayout.NORTH);
	}
	
	/**
	 * Set the center panels. The revalidate() and repaint() method will not be called.
	 * If you have issues in displaying the new content try calling them.
	 * If you only want to update the content of a specific panel, set the other values to null.
	 * @param left
	 * @param center
	 * @param right
	 */
	public void setCenterPanels(JPanel left, JPanel center, JPanel right)
	{
		this.panelWrapper.remove(this.panelLeft);
		this.panelWrapper.remove(this.panelCenter);
		this.panelWrapper.remove(this.panelRight);
		if (left != null)
		{
			this.panelLeft = left;
		}
		
		if (center != null)
		{
			this.panelCenter = center;
		}
		
		if (right != null)
		{
			this.panelRight = right;
		}
		this.panelWrapper.add(this.panelLeft);
		this.panelWrapper.add(this.panelCenter);
		this.panelWrapper.add(this.panelRight);
	}
	
	//Private Methods
	private void init(JPanel panelTop, 
						JPanel panelLeft, 
						JPanel panelCenter, 
						JPanel panelRight,
						Color outOfFocus)
	{
		//Initialization
		this.panelTop = panelTop;
		this.panelCenter = panelCenter;
		this.panelLeft = panelLeft;
		this.panelRight = panelRight;
		this.panelWrapper = new JPanel(new GridLayout(0, 3, 10, 10));
		
		//Properties
		this.setLayout(new BorderLayout());
		this.setBackground(outOfFocus);
		
		///Add to panel
		//Add to Wrapper
		this.panelWrapper.add(this.panelLeft);
		this.panelWrapper.add(this.panelCenter);
		this.panelWrapper.add(this.panelRight);
		//Add to main panel
		this.add(this.panelTop, BorderLayout.NORTH);
		this.add(this.panelWrapper, BorderLayout.CENTER);
		this.add(new JPanel(), BorderLayout.WEST);
		this.add(new JPanel(), BorderLayout.EAST);
		this.add(new JPanel(), BorderLayout.SOUTH);
	}
	
	//Testing
	public static void main(String args[])
	{
		//Initialization
		JFrame frame = new JFrame();
		TriplePanelPage tpp = new TriplePanelPage();
		RoundedPanel left = new RoundedPanel(false);
		RoundedPanel center = new RoundedPanel(false);
		RoundedPanel right = new RoundedPanel(false);
		
		//Properties
		left.setBackground(Color.BLUE);
		center.setBackground(Color.GREEN);
		right.setBackground(Color.red);
		tpp.setCenterPanels(left, center, right);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(tpp);
		
		frame.setVisible(true);
	}
}
