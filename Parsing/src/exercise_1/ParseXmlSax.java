package exercise_1;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseXmlSax {

	public static void main(String[] args) {
		try {
	         File inputFile = new File("C:/Users/User/workspace/Parsing/src/exercise_1/recipe.xml");
	         SAXParserFactory factory = SAXParserFactory.newInstance();
	         SAXParser saxParser = factory.newSAXParser();
	         RecipeHandler userhandler = new RecipeHandler();
	         saxParser.parse(inputFile, userhandler);     
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }   
	}

	class RecipeHandler extends DefaultHandler {
		//triggers
	   boolean bIngredient = false;
	   boolean bStep = false;

	   @Override
	   public void startElement(
	      String uri, String localName, String qName, Attributes attributes)
	      throws SAXException {
	      //значения атрибутов элементов
	      if (qName.equalsIgnoreCase("recipe")) {
	         String name = attributes.getValue("name");
	         System.out.println("Name : " + name);
	         String cookTime = attributes.getValue("cooktime");
	         System.out.println("Cook Time : " + cookTime);
	      } else if (qName.equalsIgnoreCase("ingredient")) {
	         bIngredient = true;
	         String amount = attributes.getValue("amount");
	         System.out.println("Amount : " + amount);
	         String unit = attributes.getValue("unit");
	         System.out.println("Unit : " + unit);
	      }
	      else if (qName.equalsIgnoreCase("step")) {
	         bStep = true;
	      }
	   }

	   @Override
	   public void endElement(String uri, 
	      String localName, String qName) throws SAXException {
	      
	      if (qName.equalsIgnoreCase("composition")) {
	         System.out.println("End Element :" + qName);
	      } else if(qName.equalsIgnoreCase("instructions")){
	    	  System.out.println("End Element :" + qName);
	      }
	   }

	   @Override
	   public void characters(char ch[], int start, int length) throws SAXException {
		  //текстовые значения внутри тегов элементов
	      if (bIngredient) {
	         System.out.println("Ingredient : " + new String(ch, start, length));
	         bIngredient = false;
	      }
	      else if (bStep) {
		         System.out.println("Step: " + new String(ch, start, length));
		         bStep = false;
		      }

	}

}
