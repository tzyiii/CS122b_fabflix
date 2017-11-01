import java.io.IOException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.*;
import java.sql.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;



public class SAXparser extends DefaultHandler {
	int[] count = new int[3];
	
	
	
	List<director> myDirector;
	
	ArrayList<film> myFilm = new ArrayList<film>();
	int i = 0;
	
	

    private String tempVal;

    //to maintain context
    private director tempEmp;
    private film tempEmp1;
    public SAXparser() {
    	myDirector = new ArrayList<director>();
    }

    public void runExample(Connection dbcon) {
        parseDocument();
        printData(dbcon);
    }
    
    private void parseDocument() {

        //get a factory
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {

            //get a new instance of parser
            SAXParser sp = spf.newSAXParser();

            //parse the file and also register this class for call backs
            sp.parse("mains243.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    
    private void printData(Connection dbcon) {
    	
    	System.out.println("running, please wait...");

        Iterator<director> it = myDirector.iterator();
        int[] collect = new int[3];
        while (it.hasNext()) {
        	
        	
       	collect = it.next().todatabase(count,dbcon);
     //  	System.out.println(sum);
        	
        }
        try{
            dbcon.commit();
         } catch (Exception e){
        	 
         }
        System.out.println("No of Movies '" + collect[0] + "'.");
        System.out.println(collect[1] + " bad data");
        System.out.println(collect[2] + " genres inserted");
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
        //reset
        tempVal = null;
        
        if (qName.equalsIgnoreCase("director")) {
        	
            //create a new instance of employee
            tempEmp = new director();          
        } else if(qName.equalsIgnoreCase("film")) {
        	tempEmp1 = new film();
        	
        } else if(qName.equalsIgnoreCase("films")) {
        	
        	i  = 0;
        } 
        tempVal = qName;
        
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {  
        if(tempVal!=null){  
        	
            String content = new String(ch,start,length); 
            
            if(tempVal.equalsIgnoreCase("dirname")){           	
            	tempEmp.set_dirname(content);   
            } else if (tempVal.equalsIgnoreCase("year")) {
            	tempEmp1.set_year(content);
            } else if (tempVal.equalsIgnoreCase("t")) {
            	tempEmp1.set_title(content);
            } else if (tempVal.equalsIgnoreCase("fid")) {
            	tempEmp1.set_fid(content);
            } else if (tempVal.equalsIgnoreCase("cat")) {
            	tempEmp1.set_genre(content);
            }
            
        }  
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {

    	if (qName.equalsIgnoreCase("film")) {
            //add it to the list
            myFilm.add(tempEmp1);       
            tempEmp1 = null;
        } else if (qName.equalsIgnoreCase("films")) {
            //add it to the list
        	
        	tempEmp.set_films(myFilm);
            myDirector.add(tempEmp);
            
            tempEmp = null;
            myFilm = new ArrayList<film>();
        }
        tempVal = null;

    }
    
    public static void main(String[] args) {
    	long startTime = System.currentTimeMillis();
		
		
    	Connection dbcon = DB.getConn();
    	try{
    	Statement s = dbcon.createStatement();
    	s.executeUpdate("alter table movies add fid varchar(100) after director;");
    	} catch (SQLException  e) {
    		
    	}
    	SAXparser spe = new SAXparser();
        spe.runExample(dbcon);
       
        SAXparserActor spe1 = new SAXparserActor();
        spe1.runExample(dbcon);
        
        SAXparserMovie spe3 = new SAXparserMovie();
        spe3.runExample(dbcon);
       try{
        
     } catch (Exception e){
    	 
     }
			DB.close(dbcon);
		
        long endTime = System.currentTimeMillis();
		System.out.println("Program running time:" + (endTime - startTime) + "ms");
    }

}
