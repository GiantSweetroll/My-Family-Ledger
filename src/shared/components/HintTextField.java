package shared.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import shared.Constants;
import shared.Methods;  
  
public class HintTextField extends JTextField
{  
  /**
	 * 
	 */
	private static final long serialVersionUID = -4024179785933494052L;
	//Fields
	private boolean fieldEmpty;
	
	//Constructor
	public HintTextField(String hint)
	{
		//Initialization
		super(hint);
		this.fieldEmpty = true;
		
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
							setForeground(Color.GRAY);
							fieldEmpty = true;
						}
						else
						{
							fieldEmpty = false;
							setText(getText());
							setForeground(Color.BLACK);
						}
					}
				});
	}
	
	//Public Methods
	public String getData()
	{
		return this.fieldEmpty? "" : this.getText();
	}
	
	//Testing
	public static void main(String arggs[])
	{
		Methods.setUIFont(new FontUIResource(Constants.FONT_TYPE_GENERAL, Font.PLAIN, Constants.FONT_GENERAL_SIZE));
		
		//Initialization
		JFrame frame = new JFrame();
		HintTextField tf = new HintTextField("Hint");
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

//Code inspired from: http://javaswingcomponents.blogspot.com/2012/05/how-to-create-simple-hinttextfield-in.html
