package com.copr.util;

import static com.copr.util.OutFomart.EXCEPTION;
import static com.copr.util.PropertiesLoadUtil.writeToLogFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @Description: Excel辅助类
 * @author zdy 
 * @date 2015-10-22 
 * @version 1.0
 */
public class ExcelHelperUitl {
	@SuppressWarnings({"unchecked","rawtypes"})
	public static void  jsonToExcel(Object ObjList){
		List<Map<String, Object>> list =(List<Map<String, Object>>) ObjList;
		String excelPath = PropertiesLoadUtil.ReaderFile().get("excelPath").toString();
		//创建xlsx格式的表格
		Workbook wb = new XSSFWorkbook();
		Sheet st = wb.createSheet("导出数据");
		if(null != list && !list.isEmpty()){
			Map<String,Object> headerMap = list.get(0);
			int headerCount = 0;
			Row rowHeader = st.createRow(0);
			for(Object key:headerMap.keySet()){
				Cell cell = rowHeader.createCell(headerCount);
				cell.setCellValue(key.toString());
				headerCount ++;
			}
			headerCount --;
			int j = 1;
			for(Map<String,Object> map:list){
				Row rowBody = st.createRow(j);
				for(Map.Entry entry : map.entrySet()){
					for(int i = 0; i <= headerCount; i++){
						if(rowHeader.getCell(i).getStringCellValue().equals(entry.getKey())){
							Cell cell = rowBody.createCell(i);
							cell.setCellValue(entry.getValue().toString());
						}
					}
				}
				j ++;
			}
			try {
				File file = new File(excelPath);
				if (!file.exists() && !file.isDirectory()) {
					file.createNewFile();
				}
				file.createNewFile();
				FileOutputStream fout = new FileOutputStream(file);
				wb.write(fout);
				fout.close();
			} catch (Exception e) {
				writeToLogFile(EXCEPTION,e);
			}
		}
	}
	public static List<Integer>  excelToJson(){
		try {
			String excelPath = PropertiesLoadUtil.ReaderFile().get("excelPath").toString();
			InputStream inp = new FileInputStream(excelPath);
			Workbook wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			List<Integer> list = new ArrayList<Integer>();
			int rowStart = sheet.getFirstRowNum();
		    int rowEnd = sheet.getLastRowNum();
			 for (int rowNum = rowStart; rowNum < rowEnd+1; rowNum++) {
				Row row = sheet.getRow(rowNum);
//				Map<String, Object> map = new HashMap<String, Object>();
//				List<Object> objs = new LinkedList<Object>();
//				objs.add(row.getCell(2).toString());
//				map.put("productId", row.getCell(1));
				DecimalFormat df = new DecimalFormat("0");  
				int productId = Integer.parseInt(df.format(row.getCell(0).getNumericCellValue()));
//				map.put("departureDates", objs);
//				map.put("isClose", 1);
				list.add(productId);
			 }
			return list;
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
}
