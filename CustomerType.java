
public class CustomerType extends Person
{
	
	/**
	 * ------------------Class CustomerType------------------------------------------------
	 * 
	 * 	Class for objects representing Customers of the store and their renting accounts.
	 * 
	 * 	Used in Cuslist.
	 */
	
	
	int acct_number;
	String checkedOut[];
	
	/**
	 * Constructor Method: Instantiates Object of class CustomerType
	 * 
	 * Gives values to all fields including those imposed by Superclass
	 * 
	 * 
	 * @param name
	 * @param address
	 * @param email
	 * @param actNumber
	 */
	public CustomerType(String name, String address, String email, int actNumber) {
		super(name, address, email);
		acct_number = actNumber;
		
		this.checkedOut = new String[5];
	}

	/**
	 * Constructor Method: Instantiates Object of class CustomerType
	 * 
	 * Gives values to all fields including those imposed by Superclass
	 * 
	 */
	public CustomerType() {
		super();
		this.name = "Harvey Mcbutteface";
		this.address = "Lame Rd WI";
		this.email = "wowisuck@gmail.com";
		this.acct_number = 123444;
		this.checkedOut = new String[2];
		// TODO Auto-generated constructor stub
	}

	/**
	 * Method: getAcct_number
	 * 			Accesses and returns the account number of a customer.
	 * 
	 * @return Customer's account number
	 */
	public int getAcct_number() 
	{
		return acct_number;
	}

	/**
	 * Getter: acct_number
	 * @param n: new account number
	 */
	public void setAcct_number(int n) 
	{
		this.acct_number = n;
	}

	
	/**
	 *  Method: toString
	 *  
	 *  @return string with all pertinent customer information.
	 */
	public String toString()
	{
		return "Name: " + this.name +  ", Address:" + this.address + ", Email:" + this.email + ", Account Number: " + acct_number;
	}
	
}
