import java.awt.Cursor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
public class Driver {

	
	static LinkedPositionalList<DVD> DVDlist = new LinkedPositionalList<>( );
	static LinkedPositionalList<CustomerType> Cuslist = new LinkedPositionalList<>();
	static LinkedPositionalList<CheckedOut> COlist = new LinkedPositionalList<>();
	public static void main(String[] args) 
	{
		
		
		/**		DVD Store Group Project for CPS 255
		 * 		Authors: Alex Donahoe, Jack Kilburn
		 * 		Date: 12/15/17
		 * 
		 * 		Purpose: This program creates a data structure that helps automate the tracking for a DVD rental store.
		 * 		It keeps track of 3 lists: DVD's, Customer Accounts, and Customers with checked out items.
		 * 		The program uses a positional list data structure.
		 * 		This allows DVD stock to be organized and altered.
		 * 		Customer checkout lists exist independently and can be altered at anytime.
		 * 		This allows repeat customers to keep a profile at the store.
		 * 
		 * 		Stock, and rented items can be displayed at anytime allowing for easy access.
		 * 
		 */
		
		
		/*
		 * These methods fill in the data from master lists if needing reboot.
		 */
		//addCus();
		//addDvd();
		deserializeCus();
		deserializeDVD();
		
				
	
		System.out.println(DVDlist.iterator().next().Title);
		
		System.out.println(searchForD("Star Wars").toString());
		
		System.out.println(DVDstock());

		Rent("Pulp Fiction","Jack Rowell");
		System.out.println(DVDstock());
		System.out.println(COlist.toString());
		Return("Pulp Fiction","Jack Rowell");
		System.out.println(COlist.toString());
		
		serializeCO(COlist);
		serializeDVD(DVDlist);
		serializeCus(Cuslist);
	}
//------------------- End of Main-----------------------------------
	
	//----------------------------------------------

	/**
	 * Method: Rent
	 * Checks out DVD from list and creates checked out entry if none exists for the customer's account.
	 * 
	 * Pre-conditions: Positional lists DVDlist, Cuslist, and COlist are instantiated and the former two have entries.
	 * 
	 * Post conditions: Decrements stock count of DVD with Title == title, adds DVD to C/O list under customer of Name == name's account
	 * 					Creates Checkedout item in list if customer is checkingout first item.
	 * @param title
	 * @param name
	 */
	public static void Rent(String title, String name)
	{
		boolean f = false;
		Iterator<CheckedOut> itr = COlist.iterator();
		if(!searchForD(title).available())
		{
			System.out.println("DVD out of stock"); 
			return;
		}
		
		DVD temp = new DVD();
		temp = searchForD(title);
		searchForD(title).rent();
		while(itr.hasNext())
		{
			if(COlist.iterator().next().getName().equals(name))
			{
				COlist.iterator().next().addDVD(temp);
				f = true;
			}
		}
		if(f == false)
		{
			CheckedOut n = new CheckedOut();
			n.CheckedOut(searchForC(name), temp);
			COlist.addLast(n);
		}
		/*take temp and put into checkedout positional list
		* test for account existence in checkedout list
		* create instance node in C/O if none exists
		* 
		*
		*/
	}

	/**
	 * Method: Return
	 * Checks in rented DVD from customer's list into store's stock list. Deletes customer's checkedout list if empty
	 * 
	 * Pre-conditions: Positional lists DVDlist, Cuslist, and COlist are instantiated and Customer has checkedout a DVD of Title == title
	 * 
	 * Post conditions: Increments stock count of DVD, deletes DVD from customer's list and deletes checkedout list if empty.
	 * 	
	 * @param title
	 * @param name
	 */
	public static void Return(String title, String name)
	{
		Position<CheckedOut> cursor;
		cursor = COlist.first( );
		while(cursor != null)
		{	

			if(cursor.getElement().getName().equals(name))
			{	searchForD(title).Return();
				cursor.getElement().findandRemoveMovie(title);
				if(cursor.getElement().cnt == 0)
				{
					COlist.remove(cursor);	
					break;
		 		}
		
			}
			cursor = COlist.after(cursor); // advance to the next position (if any)	
			if(COlist.last() == cursor) break;
		}
					
	}

	
	
	/**
	 * Method: RemoveStock
	 * 			Deletes DVD item from store's stock list in event of discontinued sale.
	 * 
	 * Pre-conditions: Positional list DVDlist is instantiated and has entry of given title.
	 * 
	 * Post conditions: Removes node of element DVD from stock list.
	 * 
	 * @param title
	 */
	public void RemoveStock(String title)
	{
		Position<DVD> cursor;
		cursor = DVDlist.first( );
		
		 while (cursor != null) { 
			 if(cursor.getElement().Title.equals(title))
				 {
				 	System.out.println(cursor.getElement().Title);
				 	DVDlist.remove(cursor);
				 	return;
				 }
				
		 cursor = DVDlist.after(cursor); // advance to the next position (if any)
		 }
		 System.out.println("DVD no longer carried.");
		 return;
	}
	
	
	/**
	 * Method: printCheckedOut
	 * 			Displays the Positional list COlist, displays each customer that has rented something and then displays
	 *  each movie they currently have out
	 *  Pre-Conditions: Postitional List COlist is instantiated, each COlist item should have a customer name and an array of DVDs
	 *  
	 *  Post-Conditions: Console shows the Customer's name, Account number and the list of DVDs they have checked out
	 */
	public static void printCheckedOut()
	{	Iterator<CheckedOut> itr = COlist.iterator();
		while(itr.hasNext())
		{
			itr.next().toString();
			
		}
	}
	/**
	 * Method: searchForD
	 * 			Goes through the DVDlist searching for a DVD with the title given
	 * Pre-Conditions: Positional list DVDlist is instantiated, and has DVDs in it
	 * 
	 * Post-Conditions: if their is a DVD with the title provided it will be returned
	 * @param title
	 * @return the DVD with title
	 */
	
	public static DVD searchForD(String title)
	{
		int counter = 0;
		DVDlist.iterator();
		Iterator<DVD> itr = DVDlist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().Title.compareTo(title) == 0)
			{
				itr = DVDlist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("No DVD found.");
		return null;
	}
	

	/**
	 * Method: searchForC
	 * 			Goes through the Cuslist searching for a Customer with the name given
	 * Pre-Conditions: Positional list Cuslist is instantiated, and has Customers in it
	 * 
	 * Post-Conditions: if their is a Customer with the name provided it will be returned
	 * @param name
	 * @return the customer with matching name
	 */
	public static CustomerType searchForC(String name)
	{
		int counter = 0;
		Iterator<CustomerType> itr = Cuslist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().name.equals(name))
			{
				itr = Cuslist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("No Account found.");
		return null;
	}
	
	
	/**
	 * Method: searchForO
	 * 			Goes through the COlist searching for a Customer with the name given
	 * Pre-Conditions: Positional list COlist is instantiated, and has CheckedOut Customers in it
	 * 
	 * Post-Conditions: if their is a CheckedOut Customer with the name provided it will be returned
	 * @param name
	 * @return the CheckedOut customer with matching name
	 */
	public static CheckedOut searchForO(String name)
	{
		int counter = 0;
		Iterator<CheckedOut> itr = COlist.iterator();
		while(itr.hasNext())
		{
			if(itr.next().customer.name.compareTo(name) == 0)
			{
				itr = COlist.iterator();
				for(counter = counter; counter > 0; counter--)
				{
					itr.next();
				}
				return itr.next();
			}
			counter++;
		}
		System.out.println("Account has nothing rented.");
		return null;
	}
	
	/**
	 * Method: DVDstock
	 * 			Prints stock list of all DVD's in list and their current stock counts.
	 * 
	 * Pre-Conditions: Positional list DVDlist is instantiated, and has DVD's in it
	 * 
	 * Post-Conditions: Creates string with all DVD's and their stock listed.
	 * @return String with list
	 */
	public static String DVDstock()
	{
		String Tlist = "[ ";
		Tlist += DVDlist.first().getElement().Title + ": " + DVDlist.first().getElement().count;
		Position<DVD> cursor;
		cursor = DVDlist.first( );
		
		 while (cursor != DVDlist.last()) { 
			 Tlist +=  ", " + DVDlist.after(cursor).getElement().Title + ": " + DVDlist.after(cursor).getElement().count;
				
			 cursor = DVDlist.after(cursor); // advance to the next position (if any)
		 }
		 Tlist += " ]";
		 return Tlist;
	}
	
	
	/**
	 * Method: SerializeDVD,SerializeCO,SerializeCus
	 * 			Puts the DVDs,Customers and C/O lists of the sent LinkedPositionalList into an arraylist and then calls upon writeObject to serialize it to
	 * the file DVDlist.txt,customers.txt and COlist.txt respectively
	 * Pre-Conditions: a linked positional list of the given type must be sent, file of the given name must be availble for writing
	 * Post-Conditions: The data in the positional lists will be stored in text files for later recovery
	 * @param e
	 */
	public static void serializeDVD(LinkedPositionalList<DVD> e)
	{
	Position<DVD> cursor = e.first();
	ArrayList<DVD> dvd = new ArrayList<DVD>();
    
	while (cursor != null)
   {
		dvd.add(cursor.getElement( ));
    	cursor = e.after(cursor);
    
    }

    
       try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("DVDList.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(dvd);
             
            out.close();
            file.close();
             
            System.out.println("Object has been serialized");

        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

}
	public static void deserializeDVD()
	{    
		
	
    
    	try
	{   
        	// Reading the object from a file
        	FileInputStream file = new FileInputStream("DVDList.txt");
        	ObjectInputStream in = new ObjectInputStream(file);
         
        	// Method for deserialization of object
        	ArrayList<DVD> dvd = (ArrayList<DVD>)in.readObject();
        for(int i = 0; i < dvd.size(); i++)
        {
        	DVDlist.addFirst(dvd.get(i));
        }
        	in.close();
        	file.close();
         
        	System.out.println("Object has been deserialized ");

    	}
     
    	catch(IOException ex)
    	{
        	System.out.println("IOException is caught");
    	}
     
    	catch(ClassNotFoundException ex)
    	{
        	System.out.println("ClassNotFoundException is caught");
    	}
    }
	public static void serializeCO(LinkedPositionalList<CheckedOut> e)
	{
	Position<CheckedOut> cursor = e.first();
	ArrayList<CheckedOut> chkOut = new ArrayList<CheckedOut>();
    
	while (cursor != null)
   {
		chkOut.add(cursor.getElement());
    	cursor = e.after(cursor);
    
    }

    
       try
        {   
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream("COlist.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
             
            // Method for serialization of object
            out.writeObject(chkOut);
             
            out.close();
            file.close();
             
            System.out.println("Object has been serialized");

        }
         
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

}
	public static void deserializeCO()
	{    
    
    	try
	{   
        	// Reading the object from a file
        	FileInputStream file = new FileInputStream("COList.txt");
        	ObjectInputStream in = new ObjectInputStream(file);
         
        	// Method for deserialization of object
        	ArrayList<CheckedOut> chkOut = (ArrayList<CheckedOut>)in.readObject();
        for(int i = 0; i < chkOut.size(); i++)
        {
        	COlist.addFirst(chkOut.get(i));
        }
        	in.close();
        	file.close();
         
        	System.out.println("Object has been deserialized ");

    	}
     
    	catch(IOException ex)
    	{
        	System.out.println("IOException is caught");
    	}
     
    	catch(ClassNotFoundException ex)
    	{
        	System.out.println("ClassNotFoundException is caught");
    	}
    }
	public static void serializeCus(LinkedPositionalList<CustomerType> e)
    	     {
    	 		Position<CustomerType> cursor = e.first();
    	 		ArrayList<CustomerType> cus = new ArrayList<CustomerType>();
    	         
    	 		while (cursor != null)
    	        {
    	 			cus.add(cursor.getElement( ));
    	         	cursor = e.after(cursor);
    	         
    	        }       
    	            try
    	             {   
    	                 //Saving of object in a file
    	                 FileOutputStream file = new FileOutputStream("customers.txt");
    	                 ObjectOutputStream out = new ObjectOutputStream(file);
    	                  
    	                 // Method for serialization of object
    	                 out.writeObject(cus);
    	                  
    	                 out.close();
    	                 file.close();
    	                  
    	                 System.out.println("Object has been serialized");
    	     
    	             }
    	              
    	             catch(IOException ex)
    	             {
    	                 System.out.println("IOException is caught");
    	             }
    	     
    	     }
	public static void deserializeCus()
    	     {    
    	         
    	         try
    	         {   
    	             // Reading the object from a file
    	             FileInputStream file = new FileInputStream("customers.txt");
    	             ObjectInputStream in = new ObjectInputStream(file);
    	              
    	             // Method for deserialization of object
    	             ArrayList<CustomerType> cus = (ArrayList<CustomerType>)in.readObject();
    	             for(int i = 0; i < cus.size(); i++)
    	             {
    	             	Cuslist.addFirst(cus.get(i));
    	             }
    	             in.close();
    	             file.close();
    	              
    	             System.out.println("Object has been deserialized ");

    	         }
    	          
    	         catch(IOException ex)
    	         {
    	             System.out.println("IOException is caught");
    	         }
    	          
    	         catch(ClassNotFoundException ex)
    	         {
    	             System.out.println("ClassNotFoundException is caught");
    	         }
    	         
    	         
	}
    //Data for the DVD Positional List
	public static void addDvd()
    	 	{
    	 		DVD pulpFiction = new DVD("Pulp Fiction",2,"Harvey Weinstein"," Quentin Tarantino", "Miramax", 5);
    	 		pulpFiction.setStar("Bruce Willis");
    	 		pulpFiction.setStar("Uma Thruman");
    	 	
    	 		DVD starWars = new DVD("Star Wars",2,"Gary Kurtz","George Lucas", "20th Century Fox", 3);
    	 		starWars.setStar("Mark Hammil");
    	 		starWars.setStar("Harrison Ford");	
    	 		DVD clerks = new DVD("Clerks",2,"Scott Mosier","Kevin Smith", "Askew", 2);
    	 		clerks.setStar("Brian O'Halloran");
    	 		clerks.setStar("Jeff Anderson");
    	 		DVD paulBlart = new DVD("Paul Blart",2,"Barry Bernadi","Steve Carr", "Columbia", 4);
    	 		paulBlart.setStar("Kevin James");
    	 		paulBlart.setStar("Keit O'Donnell");

    	 		DVD piratesCarribean = new DVD();
    	 		DVDlist.addFirst(pulpFiction);
    	 		DVDlist.addLast(starWars);
    	 		DVDlist.addLast(clerks);
    	 		DVDlist.addLast(paulBlart);
    	 		DVDlist.addLast(piratesCarribean);
    	 		
    	 	}
    //Data for the Customer Positional List
	public static void addCus()
    {
   	 		CustomerType jack = new CustomerType("Jack Rowell","8778 wallaby way, Sydney", "Jack.Rowell95@gmail.com", 1998);
   	 		CustomerType Curt = new CustomerType("Curt Cherti","2032 Joel dr, Muskego", "Curt.Cherti95@gmail.com", 5085);
   	 		CustomerType Jason = new CustomerType("Jason Poole","1111 Fake st, Hollywood", "Jason.Poole95@gmail.com", 2018);
   	 		CustomerType Nate = new CustomerType("Nate Rolbiecki","1989 old st, forgotten", "Nate.Rolbiecki@gmail.com", 1372);
   	 		CustomerType Kyle = new CustomerType("Kyle Endl","2020 vision way, vision", "Kyle.Endl@gmail.com",3675);
   	 		Cuslist.addFirst(jack);
   	 		Cuslist.addLast(Curt);
   	 		Cuslist.addLast(Jason);
   	 		Cuslist.addLast(Nate);
   	 		Cuslist.addLast(Kyle);
    }
}


