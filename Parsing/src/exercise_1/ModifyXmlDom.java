package exercise_1;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ModifyXmlDom {

	public static void main(String[] args) {
		
		try {
	         File inputFile = new File("C:/Users/User/workspace/Parsing/src/exercise_1/employees.xml");
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document doc = docBuilder.parse(inputFile);
	         Node employees = doc.getFirstChild();
	         Node employee = doc.getElementsByTagName("employee").item(0);
	         

	         // loop the employee child node
	         NodeList list = employee.getChildNodes();
	         
	         for (int temp = 0; temp < list.getLength(); temp++) {
	            Node node = list.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               if ("name".equals(eElement.getNodeName())) {
	            	   NamedNodeMap attr = eElement.getAttributes();
	                   Node nodeAttr = attr.getNamedItem("id");
	                   nodeAttr.setTextContent("0093");             //меняем id 1-го работника
	                  if("John Smith".equals(eElement.getTextContent())) {
	                     eElement.setTextContent("Sara Hawkins");  // меняем имя 1-го работника
	                  }
	               }
	               if("salary".equals(eElement.getNodeName())){
	                  if("5000".equals(eElement.getTextContent()))
	                     eElement.setTextContent("6000"); //меняем зарплату 1-го работника
	               }
	            }
	         }
	         NodeList childNodes = employees.getChildNodes();
	         
	         for(int count = 0; count < childNodes.getLength(); count++) {
	            Node node = childNodes.item(count);
	            // удаляем все элементы с тегом "total_workers" из файла
	            if("total_workers".equals(node.getNodeName()))
	               employees.removeChild(node);
	         }

	         // write the content on console
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         System.out.println("-----------Modified File-----------");
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}

}
