import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @Description: 这里用一句话描述这个类的作用
 * @author zhudongyang 
 * @date 2015-10-28 
 * @version 1.0
 */
public class Test {
	public static void main(String[] args) {
		try {
			String paramsDateStr = "2015-10-31";
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date paramsDate = dateFormat.parse(paramsDateStr);
			System.out.println(paramsDate);
			if(paramsDate.getTime() <= new Date().getTime()){
				System.out.println("ssss");
			}
			Object code = "1234";
			Integer codeInt = (Integer)code;
			System.out.println(codeInt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
