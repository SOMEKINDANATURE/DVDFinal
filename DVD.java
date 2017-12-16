import java.io.Serializable;
public class DVD implements Serializable
{
	
	/**
	 * -----------------Class DVD---------------------------------------------------------
	 * 
	 * Class that stores the data pertaining to a DVD - Title, stars, Producer, Director, and Company.
	 */
	String Title;
	String stars[];
	String prod;
	String dir;
	String company;
	int count;
	
/**
 * Constructor Method: Instantiates Object of class CustomerType
 * 
 * Gives values to all fields
 * 
 * @param title
 * @param size
 * @param producer
 * @param director
 * @param pCompany
 * @param count
 */
	public DVD(String title, int size, String producer, String director, String pCompany, int count) {
		super();
		Title = title;
		this.stars = new String[size];
		this.prod = producer;
		this.dir = director;
		this.company = pCompany;
		this.count = count;
		
	}
    //Generic Dvd
	public DVD()
	{
		Title = "Pirates of the Caribbean";
		this.count = 5;
		this.stars = new String[3];
		for(int i = 0; i < stars.length; i++)
		{
			stars[i] = "Jack Sparrow";
		}
		this.prod = "Jerry Bruckheimer";
		this.dir = "Gore Verbinski";
		this.company = "The Walt Disney Company";
	}
	/**
	 * -----------------Setters and Getters---------------------------------------------------------
	 * 
	 */
	public String getTitle() {
		return this.Title;
	}
	
	public void setTitle(String title) {
		this.Title = title;
	}

	public String getStars() {
		
		
		return stars[0] + ", " + stars[1];
	}

	public void setStar(String star) {
		//add throw for no more space
		for(int i = 0; i < this.stars.length; i++)
			{
				if(this.stars[i] != null)
					{
						this.stars[i] = star;
						return;
					}
			}
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public int checkStock()
	{
		return this.count;
	}
	/**
	 * -----------------------------End getters and setters -------------------------------------------------
	 */
	
	/**
	 * Method rent
	 * 		decrements the count of how many dvds we have of this title
	 * Pre-Conditions: Count is instantiated
	 * Post-Conditions: Decrements the count variable by 1
	 */
	public void rent()
	{
		if(this.count != 0)
		{
			this.count--;
		}
		return;
	}
	
	/**
	 * Method Return
	 * 		Increments the count variable to reflect that a DVD has returned
	 * 
	 */
	public void Return()
	{
		this.count++;
		return;
	}
	

	/**
	 * Method available
	 * 		checks to see if there is any of this dvd in stock by checking the count variable
	 *
	 * Pre-conditions: Count intitialized
	 * Post-Conditions: Return status of count
	 *
	 * @return true if we have it in stock
	 */
	public boolean available()
	{
		if(this.count != 0)
		{
			return true;
		}
		return false;
	}
	/**
	 * Method toString()
	 * 		returns a formatted string of this DVDs information: the Title, Amount in stock, Stars in Movie, Director, Producer, Company
	 */
	
	public String toString()
	{
		String toString;
		toString = " " + this.Title + ": [ " + this.count + ", "; 
		for(int i = 0; i < stars.length; i++)
			{
				toString += this.stars[i] + ", ";
			}
		toString += this.dir + ", " + this.prod + ", " + this.company + " ]";
		return toString;
	}
}
