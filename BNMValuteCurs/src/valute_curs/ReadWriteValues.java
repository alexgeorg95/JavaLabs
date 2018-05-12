package valute_curs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteValues {
		private static ArrayList<ValuteCurs> valCurs = new ArrayList<>();
		private static ArrayList<String> dates = new ArrayList<>(); // коллекция, содержащая все URL с датами
		private static String url = "https://bnm.md/en/official_exchange_rates?get_xml=1&date=";  // основная часть URL

			public static void main(String[] args) {
				readCSV();
				for(String s:dates){
					parseXml(s);
				}
				    writeToXls();
			}
			// чтение CSV файла с датами
			public static void readCSV(){
				String csvFile = "C:/Users/User/workspace/BNMValuteCurs/src/valute_curs/dates.csv";

		        CSVReader reader = null;
		        try {
		            reader = new CSVReader(new FileReader(csvFile));
		            String[] line;
		            while ((line = reader.readNext()) != null) {
		                dates.add(url+line[0]);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}
			
			// парсинг XML файла с BNM-сайта для каждого значения URL
			public static void parseXml(String url){ 
				ArrayList<Valute>valArray = new ArrayList<>();
				String date =null;
				try {
			         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			         Document doc = dBuilder.parse(new URL(url).openStream());
			         doc.getDocumentElement().normalize();
			         Node n=doc.getElementsByTagName("ValCurs").item(0);
			         if (n.getNodeType() == Node.ELEMENT_NODE) {
			         Element el = (Element)n;
			         date = el.getAttribute("Date");
			         }
			         NodeList nList = doc.getElementsByTagName("Valute");
			         
			         for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			               Element eElement = (Element) nNode;
			               valArray.add(createNewValute(eElement)); //добавляем созданный объект в коллекцию объектов типа Valute 
			            }
			         }
			         valCurs.add(new ValuteCurs(valArray,date)); // заполняем коллекцию объектов ValuteCurs, где каждый объект имеет свою коллекцию типа Valute и дату
			      } catch (Exception e) {
			         e.printStackTrace();
			      }
			}
			
			//создаем объект типа Valute и инициализируем его поля значениями, полученными при парсинге XML документа
			public static Valute createNewValute(Element el){
				Valute newVal = new Valute(el.getAttribute("ID"),
						Integer.parseInt(el.getElementsByTagName("NumCode").item(0).getTextContent()),
						el.getElementsByTagName("CharCode").item(0).getTextContent(),
						Integer.parseInt(el.getElementsByTagName("Nominal").item(0).getTextContent()),
						el.getElementsByTagName("Name").item(0).getTextContent(),
						Double.parseDouble(el.getElementsByTagName("Value").item(0).getTextContent()));
				return newVal;
			}
			// запись полученных данных в XLS файл
			public static void writeToXls(){
				Workbook wb = new XSSFWorkbook();
				for(ValuteCurs obj:valCurs){
					Sheet sheet = wb.createSheet(obj.getDate()); // именуем листы датами
					Row row1 = sheet.createRow(0);
					row1.createCell(0).setCellValue("ID");
					row1.createCell(1).setCellValue("NumCode");
					row1.createCell(2).setCellValue("CharCode");
					row1.createCell(3).setCellValue("Nominal");
					row1.createCell(4).setCellValue("Name");
					row1.createCell(5).setCellValue("Value");
					int count =1;
					// каждый лист заполняем данными из соответствующей коллекции типа Valute
					for(Valute val:obj.getAllValutes()){
						Row row =sheet.createRow(count);
						row.createCell(0).setCellValue(val.getId());
						row.createCell(1).setCellValue(val.getNumCode());
						row.createCell(2).setCellValue(val.getCharCode());
						row.createCell(3).setCellValue(val.getNominal());
						row.createCell(4).setCellValue(val.getName());
						row.createCell(5).setCellValue(val.getValue());
						count++;
					}
					count = 1; // переводим счетчик в исходное положение
				}
				try{
				FileOutputStream fos = new FileOutputStream("C:/Users/User/workspace/BNMValuteCurs/src/valute_curs/workbook.xlsx");
		        wb.write(fos); // запись в файл xlsx полученных данных
		        fos.close();
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

	}

}
