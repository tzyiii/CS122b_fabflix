import java.sql.*; 
import java.io.*;
                            // Enable SQL processing
	
	public class version6
	{
	       
	       public static void ver(Connection connection) throws Exception
	       {

				   
				   InputStreamReader isr = new InputStreamReader(System.in);
	               BufferedReader br = new BufferedReader(isr);
	               String s = null;
	               boolean bool = true;	               
	               while (bool){
	               		   System.out.println("Please input SQL command:");
			               s = br.readLine();
			               if(s.contains("select"))
			               		query(connection, s);
			               	else if (s.contains("delete"))
			               		delete(connection, s);
			               	else if (s.contains("update"))
			               		update(connection, s);
			               	else if (s.contains("insert"))
			               		insert(connection,s);
			               	else if (s.equals("exit"))
			               		bool = false;
			               	else
			               		System.out.println("Invalid Command!");
	               	}
	               
	       }



	       public static void query( Connection connection, String s) throws Exception{
	       			Statement select = connection.createStatement();	                	                
	                if (s.contains("movies")){
	                	ResultSet result = select.executeQuery(s);
	                	if (s.contains("stars")){
	                		while (result.next())
				               {
				                       System.out.println("star_Id = " + result.getInt(1));
				                       System.out.println("movie_Id = " + result.getInt(2) );				                       
				                       System.out.println();
				               }
	                	}
	                	else {
	                		while (result.next())
				               {
				                       System.out.println("Id = " + result.getInt(1));
				                       System.out.println("title = " + result.getString(2) );
				                       System.out.println("year = " + result.getInt(3));
				                       System.out.println("Director = " + result.getString(4));
				                       System.out.println("banner_url = " + result.getString(5));
				                       System.out.println("trailer_url = " + result.getString(6));
				                       System.out.println();
				               }
	                	}

	                }

	                else if (s.contains("stars")){
	                		ResultSet result = select.executeQuery(s);
	                		while (result.next())
				               {
				                       System.out.println("Id = " + result.getInt(1));
				                       System.out.println("Name = " + result.getString(2) + result.getString(3));
				                       System.out.println("DOB = " + result.getString(4));
				                       System.out.println("photoURL = " + result.getString(5));
			                           System.out.println();
				                }

	       			}

	       			else if (s.contains("genres")){
	       				ResultSet result = select.executeQuery(s);
	       				if (s.contains("movies")){
	       					while (result.next())
				               {
				                       System.out.println("genre_Id = " + result.getInt(1));
				                       System.out.println("movie_Id = " + result.getInt(2) );
			                           System.out.println();
				                }
	       				}
			       		else{	
			       				while (result.next())
						               {
						                       System.out.println("Id = " + result.getInt(1));
						                       System.out.println("Name = " + result.getString(2));
						                       System.out.println();
						                }
						    }
	       			}

	       			else if ( s.contains("customers")){
	       					ResultSet result = select.executeQuery(s);
	       					while (result.next())
				               {
				                       System.out.println("Id = " + result.getInt(1));
				                       System.out.println("Name = " + result.getString(2) + result.getString(3));
				                       System.out.println("credit card = " + result.getString(4));
				                       System.out.println("address = " + result.getString(5));
				                       System.out.println("email = " + result.getString(6));
				                       System.out.println("password = " + result.getString(7));
			                           System.out.println();
				                }
	       			}
	       			else if ( s.contains("sales")) {
	       					ResultSet result = select.executeQuery(s);
	       					while (result.next())
				               {
				                       System.out.println("Id = " + result.getInt(1));
				                       System.out.println("customer_id = " + result.getInt(2) );
				                       System.out.println("movie_id = " + result.getInt(3));
				                       System.out.println("date = " + result.getString(4));
				                       System.out.println();
				                }
	       			}
	       			else if ( s.contains("creditcards")){
	       					ResultSet result = select.executeQuery(s);
	       					while (result.next())
				               {
				                       System.out.println("Id = " + result.getInt(1));
				                       System.out.println("Name = " + result.getString(2) + result.getString(3));
				                       System.out.println("expiration = " + result.getString(4));
				                       System.out.println();
				                }
	       			}

	       			else System.out.println("Invalid Command!");
	       	}




	       public static void delete( Connection connection, String s) {
	       			try{
	       				Statement delete = connection.createStatement();
	       				int retID = delete.executeUpdate(s);	       				
	  	       			if (retID > 0){
	       				System.out.println("delete succeed!" + retID + " row has been deleted;" );
	       			    }
	       				else System.out.println("delete falid!");
	       			}
	       			catch (SQLException e){
	       				System.out.println("Invalid Command!");
	       				
	       			}	
	       	}


	       public static void update( Connection connection, String s) throws Exception{
	       			try{
	       				Statement update = connection.createStatement();
	       				int retID = update.executeUpdate(s);	       				
	  	       			if (retID > 0){
	       				System.out.println("update succeed!" + retID + " row has been updated;" );
	       			    }
	       				else System.out.println("update failed!");
	       			}
	       			catch (SQLException e){
	       				System.out.println("Invalid Command!");
	       				
	       			}	 
	       	}

	       	public static void insert( Connection connection, String s) throws Exception{
	       			try{
	       				Statement insert = connection.createStatement();
	       				int retID = insert.executeUpdate(s);	       				
	  	       			if (retID > 0){
	       				System.out.println("insert succeed!" + retID + " row has been inserted;" );
	       			    }
	       				else System.out.println("insert failed!");
	       			}
	       			catch (SQLException e){
	       				System.out.println("Invalid Command!");
	       				
	       			}	 
	       	}

	       
	}
