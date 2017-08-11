package com.tuniu.localRest;

import static com.tuniu.util.OutFomart.EXCEPTION;
import static com.tuniu.util.PropertiesLoadUtil.writeToLogFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tuniu.util.ExcelHelperUitl;
import com.tuniu.util.JsonUtil;
import com.tuniu.util.PropertiesLoadUtil;

/**
 *
 * @Description: 程序入口
 * @author zhudongyang 
 * @date 2015-10-22 
 * @version 1.0
 */
public class LocalMain {
	public static void main(String[] args) {
		try {
//			writeToExecl();
			readToExecute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void writeToExecl(){
		Map<String, Object> paramsMap = PropertiesLoadUtil.ReaderFile();
		ResponseVo responseVo =  new ResponseVo();
		if("get".equals(paramsMap.get("method"))){
			responseVo = HttpURLCon.sendGet(paramsMap);
		} else if("post".equals(paramsMap.get("method"))){
			responseVo = HttpURLCon.sendPost(paramsMap);
		} else {
			writeToLogFile(EXCEPTION,"无法识别请求方式");
		}
		if(null != responseVo && responseVo.getData() != null){
			ExcelHelperUitl.jsonToExcel(responseVo.getData());
		}
	}
	public static void readToExecute() throws InterruptedException{
		Map<String, Object> paramsMap = PropertiesLoadUtil.ReaderFile();
		List<Integer> list = ExcelHelperUitl.excelToJson();
		List<Integer> listNum = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++)
        {
			listNum.add(list.get(i));
            // 分30条处理
            if (listNum.size() % 500 == 0)
            {	
                Map<String, Object> objMap = new HashMap<String, Object>();
    			paramsMap.put("params", JsonUtil.toString(objMap));
    			paramsMap.put("action", paramsMap.get("action"));
    			paramsMap.put("method", paramsMap.get("method"));
    			ResponseVo responseVo =  new ResponseVo();;
    			if("get".equals(paramsMap.get("method"))){
    				responseVo = HttpURLCon.sendGet(paramsMap);
    			} else if("post".equals(paramsMap.get("method"))){
    				responseVo = HttpURLCon.sendPost(paramsMap);
    			}
    			listNum = new ArrayList<Integer>();
    			Thread.sleep(5000);
            }
        }
//		 if (listNum != null && listNum.size() > 0)
//         {
            Map<String, Object> objMap = new HashMap<String, Object>();
 			objMap.put("id", 210032145);
 			objMap.put("delFlag", 0);
 			paramsMap.put("params", JsonUtil.toString(objMap));
 			paramsMap.put("action", paramsMap.get("action"));
 			paramsMap.put("method", paramsMap.get("method"));
 			ResponseVo responseVo =  new ResponseVo();;
 			if("get".equals(paramsMap.get("method"))){
 				responseVo = HttpURLCon.sendGet(paramsMap);
 			} else if("post".equals(paramsMap.get("method"))){
 				responseVo = HttpURLCon.sendPost(paramsMap);
 			}
//         }
//		for(Object obj :list){
//			paramsMap.remove("params");
//			paramsMap.remove("action");
//			paramsMap.remove("method");
//			Map<String, Object> objMap = new HashMap<String, Object>();
//			objMap.put("prodIds", list);
//			objMap.put("noLeader", -1);
//			List<Object> objList = new ArrayList<Object>();
//			objList.add(obj);
//			objMap.put("openOrCloseList", JsonUtil.toString(objList));
//			paramsMap.put("params", JsonUtil.toString(objMap));
//			paramsMap.put("action", paramsMap.get("action"));
//			paramsMap.put("method", paramsMap.get("method"));
//			ResponseVo responseVo =  new ResponseVo();;
//			if("get".equals(paramsMap.get("method"))){
//				responseVo = HttpURLCon.sendGet(paramsMap);
//			} else if("post".equals(paramsMap.get("method"))){
//				responseVo = HttpURLCon.sendPost(paramsMap);
//			}
//			writeToLogFile(INFO,obj.toString()+"  success:"+responseVo.isSuccess());
//		}
	}
}
