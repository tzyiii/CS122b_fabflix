import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class director {

	private String dir;
	 ArrayList<film> films;
	 film[] f;
	 

	public String get_dir() {
		return dir;
	}

	public void set_dirname(String dir) {
		this.dir = dir;
	}
	
	public film[] get_films() {
		film[] f = (film[])this.films.toArray(new film[films.size()]);
		return f;
	}

	public void set_films(ArrayList<film> films) {
		this.films = films;
		this.f = (film[])this.films.toArray(new film[films.size()]);
	}
	
	public void display() {

		film[] f = (film[]) this.films.toArray(new film[films.size()]);
		for (int i = 0; i < films.size(); i++) {
			System.out.println(this.dir + " " + f[i].title);

		}

	}
	
	public int[] todatabase(int[] count,Connection dbcon) {
		
		
		
		

		PreparedStatement pstmt = DB.prepareStmt(dbcon, "INSERT INTO movies (fid,title,director,year) VALUES (?,?,?,?)");
		PreparedStatement pstmt2 = DB.prepareStmt(dbcon, "select * from genres where name = ?");
		
		int i = 0;
		try {
			
			for(i = 0 ; i< this.films.size(); i++){
				
				count[0]++;
				
				if(this.f[i].get_fid()==null)
				{
					this.f[i].set_fid("");
					
				}
				if(this.f[i].get_year()==null)
				{
					this.f[i].set_year("");
					
				}
			/*	if(this.f[i].get_genre()==null)
				{
					this.f[i].set_genre("");
					
				}*/
				if(this.get_dir()==null)
				{
					this.set_dirname("");
					
				}
	/*			PreparedStatement pstmt5 = DB.prepareStmt(dbcon, "select * from movies where fid = ?");
				pstmt5.setString(1, this.f[i].get_fid());
				
					
				ResultSet rs_ =pstmt5.executeQuery();
				if(rs_ != null && rs_.next()){
					
					continue;
				}
				*/
				if(this.f[i].get_title()==null)
					this.f[i].set_title("");
				
				pstmt.setString(1, this.f[i].get_fid());
				
				pstmt.setString(2, this.f[i].get_title());
				
				pstmt.setString(3, this.get_dir());
				
				pstmt.setString(4, this.f[i].get_year());
				
				pstmt.executeUpdate();
				
				
				
				pstmt2.setString(1, this.f[i].get_genre());
				ResultSet rs =pstmt2.executeQuery();
				
				
				if(!(rs != null && rs.next())){
					if(this.f[i].get_genre() != null) {
						count[2]++;
					PreparedStatement pstmt3 = DB.prepareStmt(dbcon, "INSERT INTO genres(name) VALUES (?)");
					pstmt3.setString(1, this.f[i].get_genre());
					
					
						pstmt3.executeUpdate();
						
					}
							
				}
				
				if(this.f[i].get_genre() != null) {
					
					PreparedStatement pstmt4 = DB.prepareStmt(dbcon, "INSERT INTO genres_in_movies(genre_id, movie_id) VALUES ((select id from genres where name = ?), (select id from movies where fid = ?))");
				pstmt4.setString(1, this.f[i].get_genre());
				pstmt4.setString(2, this.f[i].get_fid());
				pstmt4.executeUpdate();
				}
				
				
			}
			
		} catch(SQLException s) {
			
			count[1]++;
		}
		
		
		
		return count;
		}
	

}
