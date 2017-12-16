import java.io.Serializable;

public class CheckedOut implements Serializable
{
	
	/**
	 * -----------------------Class CheckedOut--------------------------------------------
	 * 
	 * Stores references to Customer account that has checkedout DVD's and stores those in a list of DVD's
	 * 
	 * Customer's can only check out up to 5 DVD's
	 */
	CustomerType customer;
	DVD[] dvd = new DVD[5];
	int cnt = 0;
	
	
	/**
	 * Constructor Method:
	 * 			Instantiates Object of class CheckedOut
	 * 
	 *  Pre-conditions: Positional lists DVDlist and Cuslist are instantiated and contain members
	 *  
	 *  Post-Conditions: List of rented DVD's is created for customer account and first DVD rented is added to list
	 * 
	 * @param cus: CustomerType object representing account of customer wishing to checkout DVD
	 * @param d: DVD object representing DVD being rented to customer
	 */
	public void CheckedOut(CustomerType cus, DVD d)
	{
		customer = cus;
		if (cnt < 5)
		{
			dvd[cnt] = d;
			cnt++;
		}
		else
		{
			System.out.println("You cant have more than 5 movies checked out at a single time");
			
		}
	}
	
	/**
	 *  Method: findandRemoveMovie
	 *  		Removes movie from list of rented movies on customer's list
	 *  
	 *  Pre-conditions: Customer has rented a DVD
	 *  
	 *  Post-conditions: position in array is dereferenced and DVD in list is setup for Garbo collector
	 * @param title
	 */
	public void findandRemoveMovie(String title)
	{
		for(int i = this.cnt - 1; i >= 0; i-- )
		{	
			if(this.dvd[i].Title.equals(title))
			{
				this.dvd[i].Return();
				this.dvd[i] = null;
				this.cnt--;
			}
			
		}
	}
	
	/**
	 * -------------------Setters and Getters----------------------------------------------
	 *
	 */
	public int getCnt()
	{
		return this.cnt;
	}
	
	public int getId()
	{
		return this.customer.acct_number;
	}
	public String getName()
	{
		return this.customer.name;
	}
	/**
	 * ---------------------------end of Setters&Getters-------------------------------------
	 */
	
	/**
	 *  Method: addDVD
	 *  		Rents an additional DVD to customer who has an outstanding rental
	 *  
	 *  Pre-conditions: Guest has rented 1 to 4 DVD's previously
	 *  
	 *  Post-conditions: adds DVD to list of rented for customer's account
	 * @param d
	 */
	public void addDVD(DVD d)
	{
		if (this.cnt < 5)
		{
			this.dvd[this.cnt] = d;
			this.cnt++;
		}
		else
		{
			System.out.println("You cant have more than 5 movies checked out at a single time");
			
		}
	}
	
	/**
	 *  Method: printChecked
	 *  		Prints customer account with list of currently rented DVD's
	 *  
	 *  Pre-conditions: Customer exists, has outstanding DVD rentals
	 *  
	 *  Post-conditions: Prints list to console
	 */
	public String toString()
	{
	
		String s = "";
		System.out.println("Name: " + getName() + "\n" +"Account Number " + customer.acct_number + " \n" + "DVD's");
		for(int i = 0; i < this.cnt; i++)
		{	
			if(this.dvd[i] == null) continue;
			s += this.dvd[i].Title + "\n ";
			
		}
		s += "\n";
		return s;
	}

	

}