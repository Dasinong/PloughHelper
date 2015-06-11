package com.dasinong.ploughHelper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SmsService {
	public static void test() throws UnsupportedEncodingException {
		
		String content = "近期天气良好，请抓紧打药。【大司农】";
		ArrayList<String> numbers = new ArrayList<String>();
		numbers.add("18602195820"); //小张
		numbers.add("18910423016"); //娘娘
		numbers.add("18663377860");
		numbers.add("13792016926");
		String numbersString = URLEncoder.encode(convertNumbers(numbers),"UTF-8");
		System.out.println(numbersString);
		System.out.println(groupSMS(content, numbers));
	}
	
	public static String triggeredSMS(String content, String number){
		String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
		try {
			String postData = "sname=dl-wangss&spwd=wangss12&scorpid=&sprdid=1012818&sdst="+number+"&smsg="+URLEncoder.encode(content,"UTF-8");
			String response = SMS(postData, postUrl);
			return response;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String groupSMS(String content, ArrayList<String> numbers) {
		String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
		try {
			String numbersString = URLEncoder.encode(convertNumbers(numbers),"UTF-8");
			String postData = "sname=dlwangss1&spwd=wangss123456&scorpid=&sprdid=1012808&sdst="+numbersString+"&smsg="+URLEncoder.encode(content,"UTF-8");
			String response = SMS(postData, postUrl);
			return response;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String convertNumbers(ArrayList<String> numbers){
		String numbersString = "";
		if (numbers.size()>0) {
			for (int i = 0; i < numbers.size()-1; i++) {
				numbersString += numbers.get(i).trim()+",";
			}
			numbersString += numbers.get(numbers.size()-1).trim();
		}
		
		return numbersString;
	}
	
	public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
}
