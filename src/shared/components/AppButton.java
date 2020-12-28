package shared.components;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;

import shared.Constants;

public class AppButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6436957085176698580L;

	//Constructor
	public AppButton()
	{
		super();
		this.init();
	}
	public AppButton(String text)
	{
		super(text);
		this.init();
	}
	public AppButton(String text, Icon icon)
	{
		super(text, icon);
		this.init();
	}
	
	//Private Methods
	private void init()
	{
		if (!this.isOpaque())		//In mac, isOpaque is false
		{
			this.setOpaque(true);
			this.setBorderPainted(false);
		}
		
		this.setBackground(Constants.COLOR_BUTTON_BASE);
		this.setForeground(Color.WHITE);
	}
}
