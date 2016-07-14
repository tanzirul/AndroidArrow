package org.ucr.androidarrow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TransitionParser implements IParser{

	String className;
	String id;
	String title;
	String event;
	String action;
	String temp1;
	String temp2;
	
	List <String> all;
	
	@Override
	public List<String> parse(String fileToParse) throws FileNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		
		all=new ArrayList<String>();

		  try {
			  File file = new File(fileToParse);
			  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			  DocumentBuilder db = dbf.newDocumentBuilder();
			  Document doc = db.parse(file);
			  doc.getDocumentElement().normalize();
			  System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			  NodeList nodeLst = doc.getElementsByTagName("Activity");
			  System.out.println("Information of all activities");
	
			  for (int s = 0; s < nodeLst.getLength(); s++) {
	
			    Node fstNode = nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
			  
			      Element fstElmnt = (Element) fstNode;
			      String actName=fstElmnt.getAttribute("name");
			      NodeList viewNodeList = fstElmnt.getElementsByTagName("View");
			      
			      String type = null;
		    	  String id = null ;
		    	  String idName = null ;
		    	  String title = null ;
		    	  
		    	  String eventStr = null ;
	    		  String handlerStr = null ;
			      
			      for(int i=0; i< viewNodeList.getLength();i++)
			      {
			    	  Element viewElement = (Element)viewNodeList.item(i);
			    	  type = viewElement.getAttribute("type");
			    	  id = viewElement.getAttribute("id");
			    	  idName = viewElement.getAttribute("idName");
			    	  title = viewElement.getAttribute("title");
			    	  
			    	  NodeList eventList = viewElement.getElementsByTagName("EventAndHandler");
			    	  
			    	  for(int j = 0;j < eventList.getLength();j++)
			    	  {
			    		  Element eventElement = (Element)eventList.item(j);
			    		  eventStr = eventElement.getAttribute("event");
			    		  handlerStr = eventElement.getAttribute("handler");
			    		  
			    		  String tag="<<found>>";
					      String total=actName+tag+type+tag+id+tag+idName+tag+title+tag+eventStr+tag+handlerStr;
					      all.add(total);
			    	  }
			      }
			      
			      
//			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("firstname");
//			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
//			      NodeList fstNm = fstNmElmnt.getChildNodes();
//			      System.out.println("First Name : "  + ((Node) fstNm.item(0)).getNodeValue());
//			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
//			      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
//			      NodeList lstNm = lstNmElmnt.getChildNodes();
//			      System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
			    }
	
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }	
		
		
		return all;
	}
	
	//public String regexPattern
	
	

}
