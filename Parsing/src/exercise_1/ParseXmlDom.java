package exercise_1;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class ParseXmlDom {

	public static void main(String[] args) {
		try {
	         File inputFile = new File("C:/Users/User/workspace/Parsing/src/exercise_1/employees.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("employee");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("Name : " 
	                  + eElement
	                  .getElementsByTagName("name")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Employee ID : " 
	 	                  + ((Element) (eElement.getElementsByTagName("name").item(0))).getAttribute("id"));
	               System.out.println("Department Name : " 
	                  + eElement
	                  .getElementsByTagName("department_name")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Department code : " 
		 	                  + ((Element) (eElement.getElementsByTagName("department_name").item(0))).getAttribute("code"));
	               System.out.println("Salary : " 
	                  + eElement
	                  .getElementsByTagName("salary")
	                  .item(0)
	                  .getTextContent());
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	}

}
