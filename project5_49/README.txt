1.
AWS Original IP:35.164.71.37
AWS apache(instance 1) IP:54.187.100.65

2.
Single address:
http://35.164.71.37:8080/fabflix_webapp

Scaled address:
http://54.187.100.65/fabflix_webapp
3.Usage of this script:

In fabflix-webapp/WriteOut.java, when you do a search query, the TS and TJ time will be updated in “log.txt”. And you can run parse-time/parse.java to get the average time.

4.Dashboard insert and sales record:

http://54.187.100.65/fabflix_webapp/_dashboard
When you visit from apache you will go into master or slave randomly.
If you go to slave and insert, the JDBC will remote access master’s database due to the remote access privilege setting of master:

GRANT ALL PRIVILEGES ON *.* TO 'root’@‘slave’s address' IDENTIFIED BY 'password' WITH GRANT OPTION;

So no matter which tomcat(master or slave) you visit from apache, you will finally insert the information to the master’s database.







