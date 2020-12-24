package shared.components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

public class HintNumberField extends JFormattedTextField
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 347150186613714628L;
	//Fields
	private boolean fieldEmpty;
	
	//Constructor
	public HintNumberField(String hint)
	{
		//Initialization
		super(NumberFormat.getInstance());
		this.fieldEmpty = true;
		
		//Properties
		this.setForeground(Color.GRAY);
		this.setText(hint);
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
}
