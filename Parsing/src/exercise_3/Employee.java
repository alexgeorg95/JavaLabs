package exercise_3;

public class Employee {
private String id;
private double salary;
private Address address;
private Department department;
private String[] phoneNumber;
	public Employee(){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setPhoneNumber(String[]phoneNumber){
		this.phoneNumber=phoneNumber;
	}
	
	public String[] getPhoneNumber(){
		return phoneNumber;
	}
	public void printPhoneNumbers(){
		System.out.println("Employee's phone numbers:");
		for(String number:phoneNumber){
			System.out.printf("%s%n",number);
		}
	}
	@Override
	public String toString(){
		return String.format("Employee id: %s%nEmployee Salary: %.2f%nEmployee Address: %n%s%nEmployee Department:%n%s", id,salary,address,department);
	}
	
}
