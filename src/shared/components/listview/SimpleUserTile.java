package shared.components.listview;

import models.Person;

public class SimpleUserTile extends ListTile
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7641136210349731551L;

	//Fields
	private Person person;
	
	//Constructor
	public SimpleUserTile(Person person)
	{
		//Initialization
		super();
		this.person = person;
		
		//Properties
		this.updateData();
	}
	
	//Public methods
	/**
	 * Change the displayed Person
	 * @param person a Person object. If this field is null, no changes will occur.
	 */
	public void setPerson(Person person)
	{
		if (person != null)
		{
			this.person = person;
			this.updateData();
		}
	}
	/**
	 * Return the Person object assigned.
	 * @return a Person object
	 */
	public Person getPerson()
	{
		return this.person;
	}
	
	//Private methods
	private void updateData()
	{
		if (this.person != null)
		{
			this.setTitle(person.getFullName());
			this.setSubtitle(person.getEmail());
		}
	}
}
