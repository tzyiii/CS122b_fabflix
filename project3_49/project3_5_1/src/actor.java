import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

public class actor {

	private String first_name;

	private String last_name;
	private String stage_name;

	private String dob;

	public actor() {

	}

	public actor(String first_name, String last_name, String dob) {
		this.first_name = first_name;

		this.last_name = last_name;
		this.dob = dob;

	}

	public String get_stage_name() {
		return stage_name;

	}

	public void set_stage_name(String stage_name) {
		this.stage_name = stage_name;
		String s[];
		if (stage_name.contains(" ")) {
			s = stage_name.split(" ");
			this.first_name = s[0];
			this.last_name = s[1];
		} else {
			this.last_name = stage_name;
			this.first_name = "";
		}
	}

	public String get_first_name() {
		return first_name;
	}

	public void set_first_name(String first_name) {
		this.first_name = first_name;
	}

	public String get_last_name() {
		return last_name;
	}

	public void set_last_name(String last_name) {
		this.last_name = last_name;
	}

	public String getdob() {
		if(this.dob ==null){
			return this.dob;
		} else if((!this.dob.contains("-"))){
			
			StringBuffer date = new StringBuffer(this.dob);
			date.insert(this.dob.length(), "-01-01");
			
			this.dob = date.toString();
			
			
			
		} 
		if(this.dob.contains("bb"))
			this.dob=this.dob.replaceAll("bb", "00");
		if(this.dob.contains("n.a.")){
			this.dob=this.dob.replaceAll("n.a.", "1800");
			}
		if(this.dob.contains("dob"))
			this.dob=this.dob.replaceAll("dob", "1800");
		if(this.dob.contains(" 1927"))
			this.dob=this.dob.replaceAll(" ", "");
		if(this.dob.contains("~"))
			this.dob=this.dob.replaceAll("~", "");
		if(this.dob.contains("x"))
			this.dob=this.dob.replaceAll("x", "0");
		if(this.dob.contains("[1]"))
			return null;
		if(this.dob.contains("[]"))
			this.dob=null;
		if(this.dob.contains("*"))
			this.dob=null;
		
		
		return this.dob;
	}

	public void setdob(String dob) {
		this.dob = dob;
	}

	public void display() {
		
		System.out.println(this.first_name + " " + this.last_name + " " + this.dob);
	}
	
public int[] todatabase(int[] count,Connection dbcon) {
		
		
		
			
		try {
			
/*			PreparedStatement pstmt2 = DB.prepareStmt(dbcon, "select * from stars where first_name = ? and last_name = ?");
			pstmt2.setString(1, this.get_first_name());
			pstmt2.setString(2, this.get_last_name());
			
			
			ResultSet rs_ =pstmt2.executeQuery();
			if(rs_ != null && rs_.next()){
				
				return;
			}*/

			PreparedStatement pstmt = DB.prepareStmt(dbcon, "INSERT INTO stars (first_name,last_name,dob) VALUES (?,?,?)");
				pstmt.setString(1, this.get_first_name());
				
			//	System.out.println(this.get_first_name());
			//	System.out.println(this.get_last_name());
				
				pstmt.setString(2, this.get_last_name());
		//		System.out.println(this.getdob());
				
				pstmt.setString(3, this.getdob());
	
				
				pstmt.executeUpdate();
	
	count[0]++;
			
		} catch(SQLException s) {
		//	System.out.println("SQLException");
			count[1]++;
		}
		
		
		
		return count;
			
		} 

}
