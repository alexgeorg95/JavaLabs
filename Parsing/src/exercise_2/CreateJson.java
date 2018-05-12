package exercise_2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class CreateJson {

	public static void main(String[] args) {
		
		JSONObject obj = new JSONObject();
		
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "John Smith");
		obj1.put("id","0081");
		obj1.put("department_name","development department");
		obj1.put("department_code","D01");
		obj1.put("salary", new Double(5000.00));
		
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "James Green");
		obj2.put("id","0075");
		obj2.put("department_name","testing department");
		obj2.put("department_code","T02");
		obj2.put("salary", new Double(5000.00));
		
		JSONObject obj3 = new JSONObject();
		obj3.put("name", "Katherine Blue");
		obj3.put("id","0051");
		obj3.put("department_name","management department");
		obj3.put("department_code","M03");
		obj3.put("salary", new Double(6000.00));
		
		JSONArray list = new JSONArray();
		list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        obj.put("employee",list);
        
        try (FileWriter file = new FileWriter("C:/Users/User/workspace/Parsing/src/exercise_2/employee.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);
	}

}
