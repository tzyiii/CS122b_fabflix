import java.sql.*;                              // Enable SQL processing
import java.io.*;
public class version1
{
    
    public static void ver(Connection connection) throws Exception
    {
        
        //Inputstream which determines the exact menu
        InputStreamReader isr =
        new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try{
            
            System.out.println("Please select a star's id or name:");
            
            boolean bool = true;
            while(bool){
                s = br.readLine();
                if (s.equals("exit"))
                    break;
                else if (isNumeric(s)){
                    String selectString = "select * FROM movies where id in (SELECT movie_id from stars_in_movies WHERE star_id = ?)";
                    PreparedStatement selectStars = connection.prepareStatement(selectString);
                    int id = Integer.parseInt(s);
                    selectStars.setInt(1, id);
                    ResultSet result = selectStars.executeQuery();
                    if(result!=null&&result.next()){
                    	result = selectStars.executeQuery();
                    	printResult(result);
                    }
                    else
                    	System.out.println("Not found");
                    
                }
                else if (s.contains(" ")){
                    String selectString = "SELECT  * FROM movies WHERE id IN (SELECT movie_id FROM stars_in_movies WHERE star_id IN (SELECT id FROM stars WHERE first_name = ? AND last_name = ?))";
                    PreparedStatement selectStars = connection.prepareStatement(selectString);
                    String [] a = s.split(" ");
                    selectStars.setString(1, a[0]);
                    selectStars.setString(2, a[1]);
                    ResultSet result = selectStars.executeQuery();
                    if(result!=null&&result.next()){
                    	result = selectStars.executeQuery();
                    	printResult(result);
                    }
                    else
                    	System.out.println("Not found");
                }
                else {
                    String selectString = "SELECT * FROM movies WHERE id IN (SELECT movie_id FROM stars_in_movies WHERE star_id IN (SELECT id FROM stars WHERE first_name = ? or last_name = ?))";
                    PreparedStatement selectStars = connection.prepareStatement(selectString);
                    selectStars.setString(1, s);
                    selectStars.setString(2, s);
                    ResultSet result = selectStars.executeQuery();
                    if(result!=null&&result.next()){
                    	result = selectStars.executeQuery();
                    	printResult(result);
                    }
                    else
                    	System.out.println("Not found");
                    
                }
                System.out.println("Please select a star's id or name:");
                
            }
            
        } catch (IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
    private static void printResult(ResultSet result) throws Exception{
        
        while (result.next()){
            System.out.println("Id = " + result.getInt(1));
            System.out.println("Title = " + result.getString(2));
            System.out.println("year = " + result.getInt(3));
            System.out.println("director = " + result.getString(4));
            System.out.println("banner_url = " + result.getString(5));
            System.out.println("trailer = " + result.getString(6));
            System.out.println();
            
        }
        
    }
    
}




