
public class film {

	 String fid;

	 String title;
	 String year;
	
	 String genre;
	 public String get_fid() {
			return fid;
		}

		public void set_fid(String fid) {
			this.fid = fid;
		}
	
		public String get_title() {
			return title;
		}

		public void set_title(String title) {
			this.title = title;
		}
		
		public String get_year() {
			return year;
		}

		public void set_year(String year) {
			this.year = year;
		}
		public String get_genre() {
			return genre;
		}

		public void set_genre(String genre) {
			this.genre = genre;
		}
		
		public void display() {
			
				System.out.println(this.fid + " "+ this.title + " "+ this.year + " "+ this.genre + " " );
			
		}
}
