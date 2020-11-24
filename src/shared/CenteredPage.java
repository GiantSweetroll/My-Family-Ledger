package shared;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import giantsweetroll.gui.swing.Gbm;

public class CenteredPage extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -645825022745946657L;
	
	//Fields
	protected JPanel mainPanel;
	
	private GridBagConstraints c;
	
	//Constructors
	public CenteredPage(JPanel panel, Color outOfFocusColor)
	{
		this.init(panel, outOfFocusColor);
	}
	public CenteredPage(JPanel panel)
	{
		this.init(panel, Constants.COLOR_OUT_OF_FOCUS);
	}
	public CenteredPage()
	{
		this.init(new JPanel(), Constants.COLOR_OUT_OF_FOCUS);
	}
	
	//Public Methods
	/**
	 * Get the centered panel.
	 * @return a JPanel object
	 */
	public JPanel getCenterPanel()
	{
		return this.mainPanel;
	}
	/**
	 * Set the center panel (the panel to be in focus). This method also calls the revalidate() and repaint() methods.
	 * @param panel
	 */
	public void setCenterPanel(JPanel panel)
	{
		this.remove(this.mainPanel);
		
		this.mainPanel = panel;
		
		this.add(this.mainPanel, this.c);
		
		this.revalidate();
		this.repaint();
	}
	
	//Private Methods
	/**
	 * Method to initialize the class.
	 * @param panel - the main centered panel to be displayed.
	 * @param outOfFocusColor - the background color
	 */
	private void init(JPanel panel, Color outOfFocusColor)
	{
		//Initialization
		this.mainPanel = panel;
		this.c = new GridBagConstraints();
		
		//Properties
		this.setBackground(outOfFocusColor);
		this.setLayout(new GridBagLayout());
		
		//Add to panel
		Gbm.goToOrigin(c);
		this.add(this.mainPanel, c);
	}
	
	//Testing
	public static void main(String args[])
	{
		//Initialization
		JFrame frame = new JFrame();
		CenteredPage cp = new CenteredPage();
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(cp);
		
		frame.setVisible(true);
	}
}
