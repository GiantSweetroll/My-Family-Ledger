package shared;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldHintListener implements DocumentListener
{
	//Fields
	private JTextField tf;
	private String hint;
	
	//Constructor
	public TextFieldHintListener(JTextField tf, String hint)
	{
		this.tf = tf;
		this.hint = hint;
	}
	
	//Private Methods
	/**
	 * Checks the text field if hint should be applied. Hint will only be applied if the text field is empty.
	 * @param e DocumentListener object
	 */
	private void applyHint(DocumentEvent e)
	{
		if (e.getDocument().getLength() > 0)
		{
			this.tf.setForeground(Color.BLACK);
		}
		else
		{
			this.tf.setForeground(Constants.COLOR_TEXT_GRAY);
//			this.tf.setText(this.hint);
			//TODO: Put hint
		}
	}
	
	//Overridden Methods
	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) 
	{
		this.applyHint(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) 
	{
		this.applyHint(e);
	}
}
