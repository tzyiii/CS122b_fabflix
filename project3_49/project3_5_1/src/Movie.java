import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Movie {

	private String fid;

	ArrayList<String> actor;
	String[] a;

	public Movie() {

	}

	public Movie(String fid, ArrayList<String> actor) {
		this.fid = fid;

		this.actor = actor;

	}

	public String getfid() {
		return fid;
	}

	public void setfid(String fid) {
		this.fid = fid;
	}

	public String[] getActor() {
		this.a = (String[]) this.actor.toArray(new String[actor.size()]);
		return a;
	}

	public void setActor(ArrayList<String> actor) {
		this.actor = actor;
		this.a = (String[]) this.actor.toArray(new String[actor.size()]);
	}

	public void display() {

		String[] k = (String[]) this.actor.toArray(new String[actor.size()]);
		for (int i = 0; i < actor.size(); i++) {
			System.out.println(this.fid + " " + k[i]);

		}

	}

	public int todatabase(int count,Connection dbcon) {

		

		try {

			
			for (int i = 0; i < this.actor.size(); i++) {
				
				String[] s = new String[2];
				
				if (this.a[i] == null || this.fid == null) {
					
					continue;
				} else if (this.a[i].contains(" ")) {
					
					s = this.a[i].split(" ");
				} else {
					
					s = new String[2];

					s[0] = "";
					s[1] = this.a[i];
				}
		//		System.out.println(s[0]+" "+s[1]);
				PreparedStatement pstmt3 = DB.prepareStmt(dbcon,
						"select * from stars_in_movies where movie_id in (select id from movies where fid = ?) ");
				pstmt3.setString(1, this.getfid());
				ResultSet rs_ = pstmt3.executeQuery();
				
				if (rs_ != null && rs_.next()) {

					return count;
				}
				
				PreparedStatement pstmt2 = DB.prepareStmt(dbcon,
						"INSERT INTO stars_in_movies(star_id, movie_id) VALUES ((select id from stars where first_name = ? and last_name = ?), (select id from movies where fid = ?))");
				/*
				 * PreparedStatement pstmt5 = DB.prepareStmt(dbcon,
				 * "select * from movies where fid = ?"); pstmt5.setString(1,
				 * this.f[i].get_fid());
				 
				PreparedStatement pstmt2 = DB.prepareStmt(dbcon,
						"INSERT INTO stars_in_movies(star_id, movie_id) VALUES ((select id from stars where first_name = ? and last_name = ?), (select id from movies where fid = ?))");
			*/	pstmt2.setString(1, s[0]);
				pstmt2.setString(2, s[1]);
				pstmt2.setString(3, this.fid);
			//	System.out.println(this.fid);
				pstmt2.executeUpdate();
				count++;
			}

		} catch (SQLException s) {
			

		}

		
		return count;

	}

}
