package exercise_2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ParseJson {

	public static void main(String[] args) {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("C:/Users/User/workspace/Parsing/src/exercise_2/employee.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            

            // loop array
            JSONArray employee = (JSONArray) jsonObject.get("employee");
            Iterator<JSONObject> iterator = employee.iterator();
            JSONObject jsonObject1 = (JSONObject) obj;
            while (iterator.hasNext()) {
            	jsonObject1 = iterator.next();
            	String name = (String) jsonObject1.get("name");
                System.out.println(name);
                
                String id = (String) jsonObject1.get("id");
                System.out.println(id);
                
                String department_name = (String) jsonObject1.get("department_name");
                System.out.println(department_name);
                
                String department_code = (String) jsonObject1.get("department_code");
                System.out.println(department_code);
                
                double salary = (Double) jsonObject1.get("salary");
                System.out.println(salary);
               // System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

	}

}
