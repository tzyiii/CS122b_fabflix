import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class SAXparserMovie extends DefaultHandler {
	
	List<Movie> myMovie;
	
	ArrayList<String> myFilm = new ArrayList<String>();
	
	
	

    private String tempVal;

    //to maintain context
    private Movie tempEmp;
    private String tempEmp1;
    public SAXparserMovie() {
    	myMovie = new ArrayList<Movie>();
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
            sp.parse("casts124.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    
    private void printData(Connection dbcon) {
    	int count = 0;
    	
       System.out.println("Still running, please wait...");

        Iterator<Movie> it = myMovie.iterator();
        while (it.hasNext()) {
        	count =it.next().todatabase(count,dbcon);
        }
        
        System.out.println("No of stars_in_movies '" + count + "'.");
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
        //reset
        tempVal = null;
        
        if (qName.equalsIgnoreCase("filmc")) {
        	
            //create a new instance of employee
            tempEmp = new Movie();      
            
        } else if(qName.equalsIgnoreCase("a")) {
        	tempEmp1 = new String();
        	
        } 
        tempVal = qName;
        
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {  
        if(tempVal!=null&&tempEmp!=null){ 
        	
            String content = new String(ch,start,length); 
            
            if(tempVal.equalsIgnoreCase("f")){           	
            	tempEmp.setfid(content);   
            }else if(tempVal.equalsIgnoreCase("a")){           	
            	tempEmp1=content;   
            }
            
        }  
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if(tempEmp!=null){
    	if (qName.equalsIgnoreCase("m")) {
            //add it to the list
            myFilm.add(tempEmp1);       
            tempEmp1 = null;
        } else if (qName.equalsIgnoreCase("filmc")) {
            //add it to the list
        	
        	tempEmp.setActor(myFilm);
        	
            myMovie.add(tempEmp);
            
            tempEmp = null;
            myFilm = new ArrayList<String>();
        }
    	}
        tempVal = null;

    }
    
    

}

