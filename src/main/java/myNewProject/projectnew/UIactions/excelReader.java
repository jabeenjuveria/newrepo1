package myNewProject.projectnew.UIactions;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReader {
	
public static final Logger log=Logger.getLogger(excelReader.class.getName());
	
public static final String path="C:\\Users\\MARS\\Desktop\\excelreader.xlsx";
public static FileInputStream fis;
public static XSSFWorkbook wb;
public static XSSFSheet sheet;
public static XSSFRow row;

public static void LoadExcel() throws Exception
{
	File f=new File(path);
	fis=new FileInputStream(f);
	wb=new XSSFWorkbook(fis);
	sheet=wb.getSheet("TestData");
	fis.close();
	
}
 public static Map<String, Map<String, String>>getData() throws Exception
 {
	 Map<String, Map<String,String>>superMap= new HashMap<String,Map<String,String>>();
	 Map<String, String> myMap=new HashMap<String, String>();
	 
	 if (sheet==null)
	 {
		 LoadExcel();
	 }
  //to travel through rows
	 for(int i=1;i<sheet.getLastRowNum()+1;i++)
	 {
		 row=sheet.getRow(i);
		 String keyCell=row.getCell(0).getStringCellValue();
		 
		 int colNum=row.getLastCellNum();
		 for(int j=1;j<colNum;j++)
		 {
			String value= row.getCell(j).getStringCellValue();
		    myMap.put(keyCell,value);
		 }
		 superMap.put("master",myMap);
		 
	 }
      return superMap;
 }
	
      public static String getValue(String key) throws Exception
      {
		 Map<String,String>myVal = getData().get("master");
    	 String returnVal=myVal.get(key); 
    	  return returnVal;
    	  
      }
      
      public static void main(String[] args) throws Exception {
    	 log.info("Loading Excel.....");
    	 System.out.println(getValue("userName"));
    	System.out.println(getValue("password"));
  		
  	}
}


