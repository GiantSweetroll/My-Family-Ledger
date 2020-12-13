package shared.components;

import java.awt.FlowLayout;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import shared.Constants;
import shared.GUIListener;

public class DatePicker extends JPanel implements GUIListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1913537996244852889L;
	//Fields
	private JDatePickerImpl picker;
	private Properties properties;
	
	//Constructor
	public DatePicker() 
	{
		super(new FlowLayout(FlowLayout.CENTER));
		this.properties = new Properties();
		
		//Load properties
		try
		{
			this.properties.load(this.getClass().getResourceAsStream("/org/jdatepicker/i18n/Text.properties"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//Create picker
		this.picker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel(), 
															this.properties),
											new JFormattedTextField.AbstractFormatter() {
											
											/**
											 * 
											 */
											private static final long serialVersionUID = 7594384611652195490L;
											
											@Override
											public String valueToString(Object value) throws ParseException {
												if (value != null)
												{
													Calendar cal = (Calendar) value;
													return Constants.DATE_FORMAT.format(cal.getTime());
												}
												return "";
											}
											
											@Override
											public Object stringToValue(String text) throws ParseException {
												return Constants.DATE_FORMAT.parseObject(text);
											}
											});
		
		//Properties
		this.setOpaque(false);
		this.resetDefaults();
		this.picker.getModel().setSelected(true);
		
		//Add to panel
		this.add(this.picker);
	}
	
	//Public Methods
	/**
	 * Returns the JDatePickerImpl instance.
	 * @return a JDatePickerImpl object
	 */
	public JDatePickerImpl getPicker()
	{
		return this.picker;
	}
	/**
	 * Get the selected date
	 * @return a sql.Date object
	 */
	public Date getSelectedDate()
	{
		return (Date)this.picker.getModel().getValue();
	}
	
	//Overridden Methods
	@Override
	public void resetDefaults() 
	{
		LocalDateTime date = LocalDateTime.now();		//Current Date
		this.picker.getModel().setDate(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
	}
	
	//Testing
	public static void main(String args[])
	{
		//Initialization
		JFrame frame = new JFrame();
		DatePicker picker = new DatePicker();
		
		//Properties
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add to frame
		frame.add(picker);
		
		frame.setVisible(true);
	}
}
