package shared.components;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;

public class HyperlinkLabel extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1223127030146616131L;
	//Fields
	private String plainText;
	
	//Constructor
	public HyperlinkLabel(String text)
	{
		//Initialization
		super(text);
		this.plainText = text;
		
		//Properties
		this.setForeground(Constants.COLOR_HYPERLINK);
		this.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				setData("<html>" + "<u>" + getText() + "</u></html>");
			}

			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				setData(plainText);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
	}
	
	//Overridden Methods
	public void setText(String text)
	{
		super.setText(text);
		this.plainText = text;
	}
	
	//Private methods
	private void setData(String text)
	{
		super.setText(text);
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		HyperlinkLabel lbl = new HyperlinkLabel("Test");
		JPanel panel = new JPanel();
		
		//Properties
		lbl.addMouseListener(new MouseListener()
				{

					@Override
					public void mouseClicked(MouseEvent e) 
					{
						System.out.println("Hello there");
					}

					@Override
					public void mouseEntered(MouseEvent e) {}

					@Override
					public void mouseExited(MouseEvent e) {}

					@Override
					public void mousePressed(MouseEvent e) {}

					@Override
					public void mouseReleased(MouseEvent e) {}
				});
		panel.add(lbl);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(panel);
		
		frame.setVisible(true);
	}
}
