package exercise_3;

public class Address {
private String streetAddress;
private String city;
private int postalCode;
	public Address(){}
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	public String getStreetAddress(){
		return streetAddress;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return city;
	}
	public void setPostalCode(int postalCode){
		this.postalCode = postalCode;
	}
	public int getPostalCode(){
		return postalCode;
	}
	@Override
	public String toString(){
		return String.format("Street Address:%s%nCity:%s%nPostal Code:%d%n",streetAddress,city,postalCode);
	}
}
