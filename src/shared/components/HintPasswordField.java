package shared.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;

public class HintPasswordField extends JPasswordField
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9006829736596726028L;
	//Fields
	private char originalEchoChar;

	//Constructor
	public HintPasswordField(String hint)
	{
		//Initialization
		super(hint);
		this.originalEchoChar = this.getEchoChar();
		this.setEchoChar((char)0);
		
		///Properties
		this.setForeground(Color.GRAY);
		this.addFocusListener(new FocusAdapter()
				{
					@Override
					public void focusGained(FocusEvent e)
					{
						if (getText().equals(hint))
						{
							setText("");
							setEchoChar(originalEchoChar);
							setForeground(Color.BLACK);
						}
						else
						{
							setText(getText());
						}
					}
					
					@Override
					public void focusLost(FocusEvent e)
					{
						if (getText().equals(hint) || getText().length() == 0)
						{
							setText(hint);
							setEchoChar((char)0);
							setForeground(Color.GRAY);
						}
						else
						{
							setText(getText());
							setForeground(Color.BLACK);
						}
					}
				});
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		HintPasswordField tf = new HintPasswordField("Hint");
		JTextField tfNormal = new JTextField("Helloo");
		JPanel panel = new JPanel();
		
		//Properties
		tf.setColumns(10);
		panel.add(tfNormal);
		panel.add(tf);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(panel);
		
		frame.setVisible(true);
	}
}
