import java.sql.*; // Enable SQL processing
import java.io.*;

public class version4 {
	public static void ver(Connection connection) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		boolean bool = true;

		String deleteString = "delete from customers where id = ? ; ";
		String deleteRefString = "delete from sales where customer_id = ? ; ";
		PreparedStatement deleteCustomers = connection.prepareStatement(deleteString);
		PreparedStatement deleteRefCustomers = connection.prepareStatement(deleteRefString);

		while (bool) {
			System.out.println("Please input the customer's id you want to delete:");
			s = br.readLine();
			if (s.equals("exit"))
				break;

			try{
			deleteCustomers.setInt(1, Integer.parseInt(s));
			deleteRefCustomers.setInt(1, Integer.parseInt(s));
			deleteRefCustomers.executeUpdate();
			int retID = deleteCustomers.executeUpdate();
			System.out.println("retID =" + retID);
			}catch (NumberFormatException n){
            	System.out.println("Wrong format!");
                
            }

		}
	}
}
