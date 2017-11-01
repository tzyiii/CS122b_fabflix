import java.sql.*; 
import java.io.*;
                            // Enable SQL processing
	
	public class version5
	{
	       
	       public static void ver(Connection connection) throws Exception
	       {
				   InputStreamReader isr = new InputStreamReader(System.in);
	               BufferedReader br = new BufferedReader(isr);
	               String s = null;
	               boolean bool = true;
               
				   DatabaseMetaData dbMeta = connection.getMetaData(); 
	               // Create an execute an SQL statement to select all of table"Stars" records
	               Statement show = connection.createStatement();
	               while(bool){
			               System.out.println("Please input table name:");
			               s = br.readLine();
			               if(s.equals("exit")){
			               		break;
			               }
			               if (s.contains("movies")||s.contains("stars")||s.contains("genres")||s.contains("customers")||s.contains("sales")||s.contains("creditcards"))
			               	ShowMetaData(s, show, dbMeta);
			               else System.out.println("Table name wrong! ");
			        }

	               
	       }

	       public static void ShowMetaData (String  s, Statement show, DatabaseMetaData dbMeta) throws Exception{
	       		
	       		ResultSet result = show.executeQuery("Select * from " + s);
	            ResultSet pkRSet = dbMeta.getPrimaryKeys(null, null, s);
	            ResultSet imPSet = dbMeta.getPrimaryKeys(null, null, s);

	            ResultSetMetaData metadata = result.getMetaData();
	            System.out.println("Table name is " + metadata.getTableName(1));
	            
	     	 	if( pkRSet.next() ) { 
							System.out.println("****** Comment ******");
						    System.out.println("Primary key COLUMN_NAME: "+pkRSet.getObject(4));
							System.out.println("****** ******* ******");
					}
				else  System.out.println("There is no Primary key");  
				if ( imPSet.next() ) { 
							
						
							System.out.println("IMPORTED FORENGN KEY: "+imPSet.getObject(4));
				
							
							System.out.println("****** ******* ******");
					}
				else System.out.println("There is no Foreign key");

	     		
	            System.out.println("There are " + metadata.getColumnCount() + " columns");
	            for (int i = 1; i <= metadata.getColumnCount(); i++){
	       			
	       			System.out.println("Column " +i +" is "+ metadata.getColumnLabel(i));
	                System.out.print("Type of column "+ i + " is " + metadata.getColumnTypeName(i) +"  ");

	                if (metadata.isNullable(i) == 1){
	                	System.out.print("   Nullable  ");
	            	}
	            	else System.out.print(" Not null");
	            	
	     			if (metadata.isAutoIncrement(i))
	     				System.out.print("    Auto-Increment    ");
	     			
	     			System.out.println();
	            }

	       }
	}
