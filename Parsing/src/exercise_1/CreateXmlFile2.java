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

public class CreateXmlFile2 {

	public static void main(String[] args) {
		try {
	         DocumentBuilderFactory dbFactory =
	         DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.newDocument();
	         
	         // root element
	         Element rootElement = doc.createElement("recipe");
	         Attr attr = doc.createAttribute("name");
	         attr.setValue("bread");
	         rootElement.setAttributeNode(attr);
	         Attr attr1 = doc.createAttribute("cooktime");
	         attr1.setValue("180 min");
	         rootElement.setAttributeNode(attr1);
	         doc.appendChild(rootElement);
	         
	         //composition element
	         Element composition = doc.createElement("composition");
	         rootElement.appendChild(composition);
	         
	         //ingredients elements
	         Element ingredient1 = doc.createElement("ingredient");
	         Attr attrType = doc.createAttribute("amount");
	         attrType.setValue("3");
	         ingredient1.setAttributeNode(attrType);
	         Attr attrType1 = doc.createAttribute("unit");
	         attrType1.setValue("glass");
	         ingredient1.setAttributeNode(attrType1);
	         ingredient1.appendChild(doc.createTextNode("Flour"));
	         composition.appendChild(ingredient1);
	         
	         Element ingredient2 = doc.createElement("ingredient");
	         Attr attrType2 = doc.createAttribute("amount");
	         attrType2.setValue("0.25");
	         ingredient2.setAttributeNode(attrType2);
	         Attr attrType3 = doc.createAttribute("unit");
	         attrType3.setValue("gramm");
	         ingredient2.setAttributeNode(attrType3);
	         ingredient2.appendChild(doc.createTextNode("Yeast"));
	         composition.appendChild(ingredient2);
	         
	         Element ingredient3 = doc.createElement("ingredient");
	         Attr attrType4 = doc.createAttribute("amount");
	         attrType4.setValue("1.5");
	         ingredient3.setAttributeNode(attrType4);
	         Attr attrType5 = doc.createAttribute("unit");
	         attrType5.setValue("glass");
	         ingredient3.setAttributeNode(attrType5);
	         ingredient3.appendChild(doc.createTextNode("Warm water"));
	         composition.appendChild(ingredient3);
	         
	         //instruction element
	         Element instructions = doc.createElement("instructions");
	         rootElement.appendChild(instructions);
	         
	         //steps
	         Element step1 = doc.createElement("step");
	         step1.appendChild(doc.createTextNode("Mix the ingredients and knead thoroughly"));
	         instructions.appendChild(step1);
	         
	         Element step2 = doc.createElement("step");
	         step2.appendChild(doc.createTextNode("Ñover with a cloth and leave for one hour in a dark room"));
	         instructions.appendChild(step2);
	         
	         Element step3 = doc.createElement("step");
	         step3.appendChild(doc.createTextNode("Knead again, put on a baking tray and put in the oven"));
	         instructions.appendChild(step3);

	         // write the content into xml file
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         DOMSource source = new DOMSource(doc);
	         StreamResult result = new StreamResult(new File("C:/Users/User/workspace/Parsing/src/exercise_1/recipe.xml"));
	         transformer.transform(source, result);
	         
	         // Output to console for testing
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(source, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	}

}
