import java.sql.*; // Enable SQL processing
import java.io.*;



public class version2 {
	public static void ver(Connection connection) throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		String s1 = null;
		String s2 = null;
		boolean bool = true;
		String insertString = "insert into stars (first_name,last_name,dob,photo_url) values(?,?,?,?)";
		PreparedStatement insertStars = connection.prepareStatement(insertString);

		/*String insertString2 = "insert into stars (first_name,last_name,photo_url) values(?,?,?)";
		PreparedStatement insertStars2 = connection.prepareStatement(insertString2);

		/*String insertString3 = "insert into stars (first_name,last_name,dob) values(?,?,?)";
		PreparedStatement insertStars3 = connection.prepareStatement(insertString3);

		String insertString4 = "insert into stars (first_name,last_name) values(?,?)";
		PreparedStatement insertStars4 = connection.prepareStatement(insertString4);
*/

		while (bool) {
			System.out.println("Please input the star's name");
			System.out.print("name:");
			s = br.readLine();
			if (s.equals("exit"))
				break;
			String a[];
			if (s.contains(" "))
				a = s.split(" ");
			else {
				a = new String[2];
				a[0] = "";
				a[1] = s;

			}
			insertStars.setString(1, a[0]);
			insertStars.setString(2, a[1]);
			System.out.print("date of birth:");
			s1 = br.readLine();
			if (s1.equals("exit"))
				break;
			else if (!s1.contains("-")){
				System.out.println("Wrong DOB Format");	
				insertStars.setNull(3, Types.DATE)	;							
			}
		 
		 		else {insertStars.setString(3, s1);
		 	}
			System.out.print("photo_url:");
			s2 = br.readLine();
			if (s2.equals("exit"))
				break;
					
					
			insertStars.setString(4, s2);
			insertStars.executeUpdate();
			/*if (s1 == null & s2 == null) {
				insertStars4.setString(1, a[0]);
				insertStars4.setString(2, a[1]);
				insertStars4.executeUpdate();
			} else {
				if (s1 != null & s2 == null) {

					insertStars3.setString(1, a[0]);
					insertStars3.setString(2, a[1]);
					insertStars3.setString(3, s2);
					insertStars3.executeUpdate();

				} else {
					if (s1 == null & s2 != null) {

						insertStars2.setString(1, a[0]);
						insertStars2.setString(2, a[1]);
						insertStars2.setString(3, s1);
						insertStars2.executeUpdate();

					} else {
						insertStars.setString(1, a[0]);
						insertStars.setString(2, a[1]);
						insertStars.setString(3, s1);
						insertStars.setString(4, s2);
						insertStars.executeUpdate();
					}
				}
			}*/

		}

	}
	
}
