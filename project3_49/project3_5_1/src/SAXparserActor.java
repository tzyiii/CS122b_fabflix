import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class SAXparserActor extends DefaultHandler {
	int[] count = new int[2];
	List<actor> myActor;

    private String tempVal;

    //to maintain context
    private actor tempEmp;

    public SAXparserActor() {
    	myActor = new ArrayList<actor>();
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
            sp.parse("actors63.xml", this);

        } catch (SAXException se) {
            se.printStackTrace();
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    
    private void printData(Connection dbcon) {

        

        Iterator<actor> it = myActor.iterator();
        int[] collect = new int[2];
        while (it.hasNext()) {
        	try{
        	collect = it.next().todatabase(count,dbcon);
        	} catch(Exception e){
        		
        	}
        	
        	
        }
        System.out.println("No of Actors '" + myActor.size() + "'.");
        System.out.println(collect[1] + " bad data");
        
       
    }
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	
        //reset
        tempVal = null;
        
        if (qName.equalsIgnoreCase("actor")) {
        	
            //create a new instance of employee
            tempEmp = new actor();          
        }
        tempVal = qName;
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {  
        if(tempVal!=null){  
            String content = new String(ch,start,length); 
            
            if(tempVal.equalsIgnoreCase("stagename")){           	
            	tempEmp.set_stage_name(content);   
            }else if (tempVal.equalsIgnoreCase("dob"))  {
            	tempEmp.setdob(content);
            }
        }  
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase("actor")) {
            //add it to the list
            myActor.add(tempEmp);
            tempEmp = null;
        }
        tempVal = null;

    }
    
    

}
