package valute_curs;

import java.util.ArrayList;
// ������ ����� � �������� ������� �������� ������� ������ � ��������������� ����
public class ValuteCurs {
private ArrayList<Valute>allValutes;
private String date;
	public ValuteCurs(ArrayList<Valute>allValutes,String date){
	this.allValutes = allValutes;
	this.date = date;
	}
	public ArrayList<Valute> getAllValutes() {
		return allValutes;
	}
	public void setAllValutes(ArrayList<Valute> allValutes) {
		this.allValutes = allValutes;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ValuteCurs [allValutes=" + allValutes + ", date=" + date + "]";
	}
	
}
