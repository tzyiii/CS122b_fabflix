
import java.io.*;
import java.sql.*; // Enable SQL processing

public class Menutest {

	public static void main(String[] arg) throws Exception {

		
		
		
		Connection connection = password();
		

		/*
		 * // Incorporate mySQL driver
		 * Class.forName("com.mysql.jdbc.Driver").newInstance();
		 * 
		 * // Connect to the test database Connection connection =
		 * DriverManager.getConnection(
		 * "jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false",username,
		 * "password");
		 */

		if(connection != null)
		
		choose(connection);

	}

	static void choose(Connection connection) throws Exception {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = null;
		try {

			boolean i = true;
			while (i) {
				try {

					s = br.readLine();

					if (0 < Integer.parseInt(s) && Integer.parseInt(s) <= 8) {

						int k = Integer.parseInt(s);
						switch (k) {

						case 1:
							version1.ver(connection);
							disp();
							continue;
						case 2:
							version2.ver(connection);
							disp();
							continue;
						case 3:
							version3.ver(connection);
							disp();
							continue;
						case 4:
							version4.ver(connection);
							disp();
							continue;
						case 5:
							version5.ver(connection);
							disp();
							continue;
						case 6:
							version6.ver(connection);
							disp();
							continue;
						case 7:
							password();
							continue;
						case 8:
							System.out.println("Bye!");
							break;

						default:
							System.out.println("error");
							version3.ver(connection);

							disp();
							continue;
						}
						break;
					} else
						System.out.println("You must select from number 1~8");

				} catch (NumberFormatException n) {
					System.out.println("Wrong format!\nYou must select from number 1~8");

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static Connection password() throws Exception {

		Connection connection = null;
		String username, password;
		

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		try {

			boolean bool = true;
			while (bool) {

				// Incorporate mySQL driver
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				} catch (ClassNotFoundException c) {
					System.out.println("JDBCdrive not imported");
					break;
				}
				System.out.print("username:");
				username = br.readLine();
				if(username.equals("exit")){
					
				
					System.out.println("Bye!");
					break;
				}
				System.out.print("password:");
				password = br.readLine();
				if(password.equals("exit")){
					System.out.print("Bye!");
					break;
				}
				// Connect to the test database
				try {
					connection = DriverManager
							.getConnection("jdbc:mysql:///moviedb?autoReconnect=true&useSSL=false", username, password);
					
				} catch (SQLException sql) {
					System.out.println("Wrong username or password");
					continue;
				}
				
				break;

				/*
				 * if(s.equals("password")){ bool = false;
				 * System.out.println("Welcome"); disp(); } else
				 * System.out.println("Wrong password");
				 */

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		if(connection!=null){
		System.out.println("welcome!");
		disp();}
		return connection;

	}

	static void disp() {

		Menu[] mymenu;
		mymenu = new Menu[8];
		mymenu[0] = new Menu("Search movies from a star");
		mymenu[1] = new Menu("Insert a star");
		mymenu[2] = new Menu("Insert a customer");
		mymenu[3] = new Menu("Delete a customer");
		mymenu[4] = new Menu("Metadata of database");
		mymenu[5] = new Menu("Enter a SQL command");
		mymenu[6] = new Menu("Exit to login");
		mymenu[7] = new Menu("Exit the program");

		for (int i = 0; i < 8; i++) {
			System.out.print(i + 1 + ".");
			mymenu[i].display();
		}
		System.out.println("select a number\n");

	}

}

class Menu {

	String name;

	Menu(String name) {
		this.name = name;
	}

	void display() {

		System.out.println(this.name);

	}
}
