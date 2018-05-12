package exercise_1;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class CreateXmlFile {

	public static void main(String[] args) {
		try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element
	         Element rootElement = doc.createElement("employees");
	         doc.appendChild(rootElement);

	         // employee element
	         Element employee = doc.createElement("employee");
	         rootElement.appendChild(employee);
	         //name element
	         Element name = doc.createElement("name");
	         // setting attribute to element
	         Attr attr = doc.createAttribute("id");
	         attr.setValue("0081");
	         name.setAttributeNode(attr);
	         name.appendChild(doc.createTextNode("John Smith"));
	         employee.appendChild(name);
	         
	         // department_name element
	         Element department_name = doc.createElement("department_name");
	         Attr attrType = doc.createAttribute("code");
	         attrType.setValue("D01");
	         department_name.setAttributeNode(attrType);
	         department_name.appendChild(doc.createTextNode("development department"));
	         employee.appendChild(department_name);
	     
	         //salary element
	         Element salary = doc.createElement("salary");
	         salary.appendChild(doc.createTextNode("5000"));
	         employee.appendChild(salary);
	         
	         // employee1 element
	         Element employee1 = doc.createElement("employee");
	         rootElement.appendChild(employee1);
	         //name1 element
	         Element name1 = doc.createElement("name");
	         // setting attribute to element
	         Attr attr1 = doc.createAttribute("id");
	         attr1.setValue("0075");
	         name1.setAttributeNode(attr1);
	         name1.appendChild(doc.createTextNode("James Green"));
	         employee1.appendChild(name1);
	         
	         // department_name1 element
	         Element department_name1 = doc.createElement("department_name");
	         Attr attrType1 = doc.createAttribute("code");
	         attrType1.setValue("T02");
	         department_name1.setAttributeNode(attrType1);
	         department_name1.appendChild(doc.createTextNode("testing department"));
	         employee1.appendChild(department_name1);
	         
	         //salary1 element
	         Element salary1 = doc.createElement("salary");
	         salary1.appendChild(doc.createTextNode("5000"));
	         employee1.appendChild(salary1);
	         
	         // employee2 element
	         Element employee2 = doc.createElement("employee");
	         rootElement.appendChild(employee2);
	         //name2 element
	         Element name2 = doc.createElement("name");
	         // setting attribute to element
	         Attr attr2 = doc.createAttribute("id");
	         attr2.setValue("0037");
	         name2.setAttributeNode(attr2);
	         name2.appendChild(doc.createTextNode("Katherine Blue"));
	         employee2.appendChild(name2);
	         
	         // department_name2 element
	         Element department_name2 = doc.createElement("department_name");
	         Attr attrType2 = doc.createAttribute("code");
	         attrType2.setValue("M05");
	         department_name2.setAttributeNode(attrType2);
	         department_name2.appendChild(doc.createTextNode("management department"));
	         employee2.appendChild(department_name2);
	         
	         //salary2 element
	         Element salary2 = doc.createElement("salary");
	         salary2.appendChild(doc.createTextNode("6000"));
	         employee2.appendChild(salary2);
	         
	         //total element
	         Element totalWorkers = doc.createElement("total_workers");
	         totalWorkers.appendChild(doc.createTextNode("3"));
	         rootElement.appendChild(totalWorkers);

	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("C:/Users/User/workspace/Parsing/src/exercise_1/employees.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}

}
