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
					panelWrapper,
					panelTopParent;
	
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
		this.panelTopParent.remove(this.panelTop);
		
		this.panelTop = panel;
		
		this.panelTopParent.add(this.panelTop, BorderLayout.CENTER);
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
		this.panelTopParent = new JPanel(new BorderLayout(10, 0));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		
		//Properties
		this.setLayout(new BorderLayout(10, 3));
		this.setBackground(outOfFocus);
		this.panelWrapper.setOpaque(false);
		this.panelTopParent.setOpaque(false);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);
		p6.setOpaque(false);
		
		///Add to panel
		//Add to Wrapper
		this.panelWrapper.add(this.panelLeft);
		this.panelWrapper.add(this.panelCenter);
		this.panelWrapper.add(this.panelRight);
		//Add to panelTopParent
		this.panelTopParent.add(p1, BorderLayout.NORTH);
		this.panelTopParent.add(p2, BorderLayout.WEST);
		this.panelTopParent.add(this.panelTop, BorderLayout.CENTER);
		this.panelTopParent.add(p3, BorderLayout.EAST);
		//Add to main panel
		this.add(this.panelTopParent, BorderLayout.NORTH);
		this.add(this.panelWrapper, BorderLayout.CENTER);
		this.add(p4, BorderLayout.WEST);
		this.add(p5, BorderLayout.EAST);
		this.add(p6, BorderLayout.SOUTH);
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
