import java.sql.*;                              // Enable SQL processing
import java.io.*;
public class version3
{
    public static void ver(Connection connection) throws Exception
    {
        
        boolean bool = true;
        
        while (bool){
            System.out.println("Please input the customer's name");
            String s = PreparedInput.readkeyboard();
            if (s.equals("exit"))
                break;
            
            String insertString = "insert into customers (first_name,last_name,cc_id,address,email,password) values(?,?,(select id from creditcards where first_name = ? and last_name = ? and id= ?),?,?,?)";
            PreparedStatement insertcustomer = connection.prepareStatement(insertString);
            
            //String exam = "select * FROM creditcards where (first_name = ? or last_name = ?) and id not in (select cc_id FROM customers where first_name = ? or last_name = ?);";
            // PreparedStatement examinput = connection.prepareStatement(exam);
            
            String exam_ = "select * FROM creditcards where (first_name = ? and last_name = ? and id = ?) and id not in (select cc_id FROM customers where first_name = ? and last_name = ?);";
            PreparedStatement examinput_ = connection.prepareStatement(exam_);
            
            String a[];
            if (s.contains(" ")){
                a = s.split(" ");
            }
            else {
                a = new String [2];
                a[0] = " ";
                a[1] = s;
            }
            System.out.print("cc_id:");
            String st4 = PreparedInput.readkeyboard();
            
            examinput_.setString(1, a[0]);
            examinput_.setString(2, a[1]);
            try{
                
            examinput_.setInt(3, Integer.parseInt(st4));
            } catch (NumberFormatException n)
            {
                System.out.println("Format Error");
                continue;
            }
            
            examinput_.setString(4, a[0]);
            examinput_.setString(5, a[1]);
            ResultSet result = examinput_.executeQuery();
            if(result!=null&&result.next()){
  
                insertcustomer.setString(1, a[0]);
                insertcustomer.setString(2, a[1]);
                insertcustomer.setString(3, a[0]);
                insertcustomer.setString(4, a[1]);
                
                
                insertcustomer.setInt(5, Integer.parseInt(st4));
                System.out.print("address:");
                String st1 = PreparedInput.readkeyboard();
                insertcustomer.setString(6, st1);
                System.out.print("emall:");
                String st2 = PreparedInput.readkeyboard();
                insertcustomer.setString(7, st2);
                System.out.print("password:");
                String st3 = PreparedInput.readkeyboard();
                insertcustomer.setString(8, st3);
                
                insertcustomer.executeUpdate();
                
                System.out.println("info updated");
            }
            
            else
                System.out.println("deny");
        }
    }
}


class PreparedInput {
    
    static String readkeyboard(){
        String s = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            s = br.readLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    
}



