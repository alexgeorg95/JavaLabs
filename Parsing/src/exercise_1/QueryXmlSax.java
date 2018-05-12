package exercise_1;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class QueryXmlSax {

	public static void main(String[] args) {
		
		try {
	         File inputFile = new File("C:/Users/User/workspace/Parsing/src/exercise_1/recipe.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         NewRecipeHandler userhandler = new NewRecipeHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	}

	class NewRecipeHandler extends DefaultHandler {

	   boolean bIngredient = false;
	   String unit = null;

	   @Override
	   public void startElement(String uri, 
	      String localName, String qName, Attributes attributes)
	      throws SAXException {

	      if (qName.equalsIgnoreCase("ingredient")) {
	         unit = attributes.getValue("unit");
	      }
	      if(("glass").equals(unit) && qName.equalsIgnoreCase("ingredient")) {
	         System.out.println("Start Element :" + qName);      
	      }       
	      if (qName.equalsIgnoreCase("ingredient")) {
	         bIngredient = true;
	      }
	   }

	   @Override
	   public void endElement(
	      String uri, String localName, String qName) throws SAXException {
	      
	      if (qName.equalsIgnoreCase("ingredient")) {

	         if(("glass").equals(unit) 
	            && qName.equalsIgnoreCase("ingredient"))
	            System.out.println("End Element :" + qName);
	      }
	   }


	   @Override
	   public void characters(
	      char ch[], int start, int length) throws SAXException {
		   // вывести названия ингредиентов, для которых единица измерения - это стакан
	      if (bIngredient && ("glass").equals(unit)) {
	         System.out.println("Ingredient : " + new String(ch, start, length));
	         bIngredient = false;
	      }
	}

}
