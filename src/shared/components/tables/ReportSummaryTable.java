package shared.components.tables;

import java.sql.Date;
import java.util.List;

import models.User;
import shared.Constants;
import shared.Globals;
import shared.components.AmountCellRenderer;

public class ReportSummaryTable extends SimpleTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 525271395155527039L;
	//Fields
	private static final String[] HEADERS = {"Full Name", 
												"Email", 
												"Total Income (Rp.)",
												"Total Expenditure (Rp.)", 
												"Net (Rp.)"};
	
	//Constructor
	public ReportSummaryTable(String[][] data)
	{
		super(data, HEADERS);
	}
	public ReportSummaryTable()
	{
		super(null, HEADERS);
	}
	
	//Public Methods
	public void updateData(Date dateFrom, Date dateTo)
	{
		int adminID = Globals.activeUser.getID();
		List<User> users = Constants.DATABASE_SERVICE.getAllUsers(adminID);
		
		String [][] currentData = new String [users.size()][HEADERS.length];
		double totalBalance = 0;
		double totalIncome = 0;
		double totalExpenditure = 0;
		int amountRendererIndexes[] = new int[3];
		
		for (int i =0 ; i < users.size(); i++) 
		{
			User u = users.get(i);
			int j = 0;
			currentData[i][j] =  u.getFullName();
			currentData[i][++j] = u.getEmail();
			
			//Replace this with total income and total expenditure.
			totalIncome = Constants.DATABASE_SERVICE.getIncome(u.getID(), adminID);
			currentData[i][++j] =  Double.toString(totalIncome);
			amountRendererIndexes[0] = j;
			totalExpenditure = Constants.DATABASE_SERVICE.getExpenditure(u.getID());
			currentData[i][++j] = Double.toString(totalExpenditure);
			amountRendererIndexes[1] = j;
			totalBalance = Constants.DATABASE_SERVICE.getBalance(u.getAccountID());
			currentData[i][++j] = Double.toString(totalBalance);	
			amountRendererIndexes[2] = j;
		}
		
		this.updateData(currentData, HEADERS);
		
		//Apply amount renderer
		for (int i : amountRendererIndexes)
		{
			this.getColumnModel().getColumn(i).setCellRenderer(new AmountCellRenderer());
		}
	}
	
	//Overridden Methods
	@Override
	public boolean isCellEditable(int row, int column) {                
        return false;               
	};
}
