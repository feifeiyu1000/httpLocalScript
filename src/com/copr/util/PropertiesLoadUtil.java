package com.copr.util;

import static com.copr.util.OutFomart.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import static com.copr.util.ConstantHelper.*;
/**
 *
 * @Description: 读取初始化参数
 * @author zdy 
 * @date 2015-10-23 
 * @version 1.0
 */
public class PropertiesLoadUtil {
	
	
	private static String logPath = null;
	
	private static String excelPath = null;

	private static String base64Coding = null;
	
	private static String method = null;
	
	public static Map<String, Object> ReaderFile(){
		File readerFile = new File("D:\\config.properties");
		if(!readerFile.exists() && !readerFile.isDirectory()){
			writeToLogFile(ERROR,"config文件不存在");
		}
		Map<String, Object> paramsMap = null;
		try {
			paramsMap = new HashMap<String, Object>();
			FileInputStream inputStream = new FileInputStream(readerFile);
			Properties prop = new Properties();
			prop.load(inputStream);
			logPath = prop.getProperty("logPath");
			excelPath = prop.getProperty("excelPath");
			base64Coding = prop.getProperty("base64Coding");
			method = prop.getProperty("method");
			paramsMap.put("action", prop.getProperty("action"));
			paramsMap.put("params", prop.getProperty("params"));
//			paramsMap.put("productIds", prop.getProperty("productIds"));
//			paramsMap.put("params2", prop.getProperty("params2"));
//			paramsMap.put("params3", prop.getProperty("params3"));
//			paramsMap.put("action2", prop.getProperty("action2"));
			paramsMap.put("excelPath", excelPath == null?EXCEL_PATH_DEFAULT:excelPath);
			paramsMap.put("base64Coding", base64Coding == null?BASE64_CODING_DEFAULT:base64Coding);
			paramsMap.put("method", method == null?HTTP_METHOD:method);
//			paramsMap.put("method2", prop.getProperty("method2"));
		} catch (FileNotFoundException e) {
			writeToLogFile(EXCEPTION,e.toString());
		} catch (IOException e) {
			writeToLogFile(EXCEPTION,e.toString());
		}
		return paramsMap;
	}
	
	
	/**
	 * 
	 *
	 * @Description: log文件中追加内容
	 * @param @param str    设定文件 
	 * @return void    返回类型
	 * @throws
	 */
	public static void writeToLogFile(OutFomart fomart, Object str){
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logPath == null?LOG_PATH_DEFAULT.replace("$", getCurrentDate()):logPath, true)));
		    out.println(getCurrentTime()+SPLIT_SPACE+fomart+SPLIT_COLON+str);
		    out.close();
		} catch (FileNotFoundException e) {
			System.out.println("日志文件创建失败！");
		} catch (IOException e) {
			System.out.println("IO读写异常");
		}
	}
	/**
	 * 
	 *
	 * @Description: 获取当前日期
	 * @param @return    设定文件 
	 * @return String    返回类型
	 * @throws
	 */
	private static String getCurrentDate(){
		Calendar now = Calendar.getInstance();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
		return currentDate;
	}
	/**
	 * 
	 *
	 * @Description: 获取当前时间
	 * @param @return    设定文件 
	 * @return String    返回类型
	 * @throws
	 */
	private static String getCurrentTime(){
		Calendar now = Calendar.getInstance();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now.getTime());
		return currentDate;
	}
}
