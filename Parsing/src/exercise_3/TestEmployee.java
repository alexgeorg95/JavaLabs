package exercise_3;

public class TestEmployee {

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
		System.out.println(employee);
		employee.printPhoneNumbers();

	}

}
