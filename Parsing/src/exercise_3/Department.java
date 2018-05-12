package exercise_3;

public class Department {
private String department_name;
private String department_code;
	public Department(){}
	public void setDepartmentName(String department_name){
		this.department_name = department_name;
	}
	public String getDepartmentName(){
		return department_name;
	}
	public void setDepartmentCode(String department_code){
		this.department_code = department_code;
	}
	public String getDepartmentCode(){
		return department_code;
	}
	@Override
	public String toString(){
		return String.format("Department Name:%s%nDepartment Code:%s%n",department_name,department_code);
	}
}
