package exercise_3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Writer;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
public class SerializationDeserializationXmlClass {

	public static void main(String[] args) {
		Address address = new Address();
		address.setCity("st.Petersburg");
		address.setStreetAddress("Pushkin Street,28,apartment 356");
		address.setPostalCode(1101);
		Department department = new Department();
		department.setDepartmentName("Management");
		department.setDepartmentCode("M01");
		String []phoneNum = {"222-333-444","555-666-777","888-999-000"};
		Employee employee = new Employee();
		employee.setId("758");
		employee.setAddress(address);
		employee.setDepartment(department);
		employee.setSalary(5000);
		employee.setPhoneNumber(phoneNum);
		
		//serialization into XML
		XStream xs = new XStream(new DomDriver());
		try {
            File file = new File("C:/Users/User/workspace/Parsing/src/exercise_3/employee.xml");
            PrintWriter pw = new PrintWriter(file);
            xs.toXML(employee,pw);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
		//deserialization from XML
		Employee value = new Employee();
           try {
			xs.fromXML(new FileReader("C:/Users/User/workspace/Parsing/src/exercise_3/employee.xml"), value);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           System.out.println(value);
           value.printPhoneNumbers();
        
	}

}
