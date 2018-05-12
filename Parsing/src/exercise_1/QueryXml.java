package exercise_1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class QueryXml {

	public static void main(String[] args) {
		try {
	         File inputFile = new File("C:/Users/User/workspace/Parsing/src/exercise_1/employees.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.print("Root element: ");
	         System.out.println(doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("employee");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :");
	            System.out.print(nNode.getNodeName()+" ");
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               Node empName = eElement.getElementsByTagName("name").item(0);                  
	                  if (empName.getNodeType() == empName.ELEMENT_NODE) {
	                     Element worker = (Element) empName;
	                     System.out.print("Employee name : ");
	                     System.out.println(worker.getTextContent());
	                     System.out.print("Employee id : ");
	                     System.out.println(worker.getAttribute("id"));
	                  }
	                Node empDeptName = eElement.getElementsByTagName("department_name").item(0);                  
	                  if (empDeptName.getNodeType() == empDeptName.ELEMENT_NODE) {
	                     Element worker = (Element) empDeptName;
	                     System.out.print("Employee department name : ");
	                     System.out.println(worker.getTextContent());
	                     System.out.print("Employee department code : ");
	                     System.out.println(worker.getAttribute("code"));
	                  }
	                 Node empSalary = eElement.getElementsByTagName("salary").item(0);                  
	                  if (empSalary.getNodeType() == empSalary.ELEMENT_NODE) {
	                     Element worker = (Element) empSalary;
	                     System.out.print("Employee salary : ");
	                     System.out.println(worker.getTextContent());
	                  }
	               }
	            }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	}

}
