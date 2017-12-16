import java.io.Serializable;

public class Person implements Serializable {
	
	
	/**
	 * -----------------Class Person---------------------------------------------------------
	 * 
	 * 	Super class for CustomerType
	 * 
	 * 	Requires data members for Name, Address, and Email from each object CustomerType
	 */
	
	
	String name;
	String address;
	String email;
	
	/**
	 * Constructor Method: instantiates Object of Person class
	 * 
	 * 
	 * @param name
	 * @param address
	 * @param email
	 */
	public Person(String name, String address, String email) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	/**
	 * Constructor Method: instantiates Object of Person class with blank information
	 * 
	 * 
	 */
	public Person() {
		this.name = "";
		this.address = "";
		this.email = "";
		
	}

}
