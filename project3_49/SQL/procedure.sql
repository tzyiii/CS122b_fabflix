use moviedb;
 DELIMITER $$

 CREATE PROCEDURE add_movie (IN movie VARCHAR(100), year INT, director VARCHAR(100),banner_url VARCHAR(200),trailer_url VARCHAR(200), starf VARCHAR(50) ,starl VARCHAR(50) , dob date, photo_url VARCHAR(200), genre VARCHAR(32))
     BEGIN
     set @y = 0;
     set @query = 'select id into @y from movies where title = ? and year = ? and director = ?';   
     prepare myStat from @query;
     set @title = movie;
     set @year = year;
     set @director = director;
     EXECUTE myStat USING @title,@year,@director;
     IF(@y = 0) THEN
     insert into movies (title,year,director,banner_url,trailer_url) values(movie,year,director,banner_url,trailer_url);
     EXECUTE myStat USING @title,@year,@director;
     
     END IF;
     



     
  
    set @x = 0 ;
  	set @query = 'select id into @x from stars where first_name = ? and last_name = ? and dob = ?';
	prepare myStat from @query;
	set @first_name = starf;
	set @last_name = starl;
	set @dob = dob;
	EXECUTE myStat USING @first_name, @last_name,@dob;	
	IF(@x=0) THEN
		insert into stars (first_name,last_name,dob,photo_url) values(starf,starl,dob,photo_url);
		EXECUTE myStat USING @first_name, @last_name,@dob;
		
	END IF;
	
	set @z = 0 ;
  	set @query = 'select id into @z from genres where name = ?';

	prepare myStat from @query;
	set @genre = genre;
	EXECUTE myStat USING @genre;	
	IF(@z=0) THEN
		insert into genres (name) values(genre);
		EXECUTE myStat USING @genre;
		
	END IF;

	
	set @a = 0;
	set @query = 'select star_id into @a from stars_in_movies where star_id = ? and movie_id = ? ';
	prepare myStat from @query;
	EXECUTE myStat USING @x, @y;
	IF( @a= 0) THEN
	insert into stars_in_movies (star_id, movie_id) values (@x, @y);
	END IF;
    
	set @b = 0;
	set @query = 'select genre_id into @b from genres_in_movies where genre_id = ? and movie_id = ? ';
	prepare myStat from @query;
	EXECUTE myStat USING @z, @y;
	IF(@b = 0) THEN
	insert into genres_in_movies (genre_id, movie_id) values (@z,@y);
	END IF;
    END
     $$
DELIMITER ;


  	/**/
	
	