package exercise_3;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeserializationClass {

	public static void main(String[] args) {
		SerializationDeserializationClass obj = new SerializationDeserializationClass();
		obj.serialize();
		obj.deserialize();
		
	}

	private void serialize() {
		ObjectMapper mapper = new ObjectMapper();

		Employee employee = createNewEmployee();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("C:/Users/User/workspace/Parsing/src/exercise_3/employee.json"), employee);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(employee);
			System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void deserialize(){
		ObjectMapper mapper = new ObjectMapper();

		try {

			// Convert JSON string from file to Object
			Employee value = mapper.readValue(new File("C:/Users/User/workspace/Parsing/src/exercise_3/employee.json"), Employee.class);
			System.out.println(value);
			value.printPhoneNumbers();
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Employee createNewEmployee(){
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
		return employee;
		
	}
}
