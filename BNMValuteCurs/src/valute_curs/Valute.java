package valute_curs;

public class Valute {
	private String id;
	private int numCode;
	private String charCode;
	private int nominal;
	private String name;
	private double value;
	
	public Valute(String id, int numCode,String charCode,int nominal, String name,double value){
		this.id =id;
		this.numCode=numCode;
		this.charCode=charCode;
		this.nominal=nominal;
		this.name=name;
		this.value=value;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumCode() {
		return numCode;
	}
	public void setNumCode(int numCode) {
		this.numCode = numCode;
	}
	public String getCharCode() {
		return charCode;
	}
	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}
	public int getNominal() {
		return nominal;
	}
	public void setNominal(int nominal) {
		this.nominal = nominal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Valute [id=" + id + ", numCode=" + numCode + ", charCode=" + charCode + ", nominal=" + nominal
				+ ", name=" + name + ", value=" + value + "]";
	}
	
	
}
