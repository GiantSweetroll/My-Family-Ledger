package shared.screens;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import giantsweetroll.ImageManager;

public class LogoLabel extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4508062100092494017L;
	
	public LogoLabel()
	{
		super();		//TODO: Replace with our actual logo
		
		this.setIcon(new ImageIcon(ImageManager.scaleImage(ImageManager.getImage("/resources/logo.png"), 300, 300)));
	}
}
