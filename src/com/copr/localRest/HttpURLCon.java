package com.copr.localRest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import com.copr.localRest.ResponseVo;
import com.copr.util.JsonUtil;

import static com.copr.util.ConstantHelper.USER_AGENT_BROWSER;
import static com.copr.util.OutFomart.EXCEPTION;
import static com.copr.util.PropertiesLoadUtil.writeToLogFile;
/**
 *
 * @Description: URL连接辅助类
 * @author zdy 
 * @date 2015-10-22 
 * @version 1.0
 */
public class HttpURLCon {
	/**
	 * 
	 *
	 * @Description: Get调用
	 * @param @param params[action,params,characterCoding]
	 * @param @throws Exception    设定文件 
	 * @return void    返回类型
	 * @throws
	 */
	public static ResponseVo sendGet(Map<String, Object> paramsMap){
		URL obj = null;
		HttpURLConnection con = null;
		StringBuffer response = null;
		BufferedReader in = null;
		Object result = null;
		String url = paramsMap.get("action").toString();
		String characterCoding = paramsMap.get("base64Coding").toString();
		String params = paramsMap.get("params").toString();
        String getParams = null;
        ResponseVo responseVo = new ResponseVo();
        //base64编码
        if(isBase64Coding(characterCoding)){
        	byte[] encodeBytes = Base64.encodeBase64(params.getBytes());
        	getParams = new String(encodeBytes);
        }
		try {
			obj = new URL(url+"?"+ getParams);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT_BROWSER);
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			int responseCode = con.getResponseCode();
			//2xx response success
			if(Pattern.matches("^2[0-9][0-9]", String.valueOf(responseCode))){
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} 
			result = response.toString();
		} catch (MalformedURLException e) {
			writeToLogFile(EXCEPTION,e);
		} catch (ProtocolException e) {
			writeToLogFile(EXCEPTION,e);
		} catch (IOException e) {
			writeToLogFile(EXCEPTION,e);
		} finally {
			try {
				if(null != in){
					in.close();
				}
			} catch (IOException e) {
				writeToLogFile(EXCEPTION,e);
			}
		}
		if(null != response && isBase64Coding(characterCoding)){
			byte[] decodedBytes = Base64.decodeBase64(response.toString().getBytes());
			result = new String(decodedBytes);
		}
		responseVo = JsonUtil.toBean(result, ResponseVo.class);
		return responseVo;
	}
	

	/**
	 * 
	 *
	 * @Description: Post调用
	 * @param @param params    设定文件 
	 * @return void    返回类型
	 * @throws
	 */
	public static ResponseVo sendPost(Map<String, Object> paramsMap){
		URL obj = null;
		HttpURLConnection con = null;
		StringBuffer response = null;
		BufferedReader in = null;
		String result = null;
		String url = paramsMap.get("action").toString();
		String characterCoding = paramsMap.get("base64Coding").toString();
		String params = paramsMap.get("params").toString();
        ResponseVo responseVo = new ResponseVo();
		try {
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT_BROWSER);
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			con.setDoOutput(true);
			con.setConnectTimeout(5*1000);
			DataOutputStream wr = null;
			wr = new DataOutputStream(con.getOutputStream());
			//base64编码
			if(isBase64Coding(characterCoding)){
				byte[] encodeBytes = Base64.encodeBase64(params.getBytes());
				params = new String(encodeBytes);
			}
			wr.writeBytes(params);
			wr.flush();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
			wr.close();
			int responseCode = con.getResponseCode();
			//2xx response success
			if(Pattern.matches("^2[0-9][0-9]", String.valueOf(responseCode))){
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}
			result = response.toString();
		} catch (MalformedURLException e) {
			writeToLogFile(EXCEPTION,e);
		}  catch (ProtocolException e) {
			writeToLogFile(EXCEPTION,e);
		}	catch (IOException e) {
			writeToLogFile(EXCEPTION,e);
		} finally {
			try {
				if(null != in){
					in.close();
				}
			} catch (IOException e) {
				writeToLogFile(EXCEPTION,e);
			}
		}
		if(null != response && isBase64Coding(characterCoding)){
			byte[] decodedBytes = Base64.decodeBase64(response.toString().getBytes());
			result = new String(decodedBytes);
		}
		responseVo = JsonUtil.toBean(result, ResponseVo.class);
		return responseVo;
	}
	/**
	 *
	 * @Description: 是否是base64编码
	 * @param @param params
	 * @param @return    设定文件 
	 * @return boolean    返回类型
	 * @throws 
	 */
	 
	private static boolean isBase64Coding(String str) {
		if(str.equalsIgnoreCase("Y")){
			return true;
		}
		return false;
	}
}
