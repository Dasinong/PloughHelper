package com.dasinong.ploughHelper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class SmsService {
	public static final String ACCOUNT_NAME = "dldasi00";
	public static final String PASSWORD = "MF3o9AFn";
	public static final int maxLength = 120;
	public static final ArrayList<String> weatherAdmin= new ArrayList<String>();
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
	

	@Test
	public void run(){
		String xiaoZhangCell = "18602195820";
		String securityCode = generateSecurityCode(6);
		System.out.println(securityCode);
		System.out.println(securityCodeSMS(securityCode, xiaoZhangCell));
		
		String niangniangCell = "18910423016";
		securityCode = generateSecurityCode(6);
		System.out.println(securityCode);
		System.out.println(securityCodeSMS(securityCode, niangniangCell));
	}
	
	public String generateSecurityCode(int numberOfDigits){
		String securityCode = "";
		Random generator = new Random();
		for (int i = 0; i < numberOfDigits; i++) {
			securityCode = securityCode + generator.nextInt(10);
		}
		return securityCode;
	}
	
	public static String securityCodeSMS (String securityCode, String number){
//		Random generator = new Random();
//		String securityCode = String.valueOf(generator.nextInt(10))+String.valueOf(generator.nextInt(10))
//								+String.valueOf(generator.nextInt(10))+String.valueOf(generator.nextInt(10));
		String content = "临时登录密码"+securityCode+"，请妥善保管，不要泄露给他人。登陆成功后记得及时修改密码。此临时密码将于3小时后失效。【今日农事】";
		return triggeredSMS(content, number);
	}
	
	public static String triggeredSMS(String content, String number){
		String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
		try {
			String postData = "sname="+ACCOUNT_NAME+"&spwd="+PASSWORD+"&scorpid=&sprdid=1012818&sdst="+number+"&smsg="+URLEncoder.encode(content,"UTF-8");
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
			String postData = "sname="+ACCOUNT_NAME+"&spwd="+PASSWORD+"&sprdid=1012808&sdst="+numbersString+"&smsg="+URLEncoder.encode(content,"UTF-8");
			String response = SMS(postData, postUrl);
			return response;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static synchronized String weatherAlert(String content) {
		if (Env.getEnv().weatherAlert){
			String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";		
			String number="";
			String postData = "";
			try {
				if (content.trim().endsWith("Issue loading:")) content = content.substring(0,content.length()-14);
				try {
					content = content+" "+ InetAddress.getLocalHost().getHostAddress().toString()+"【今日农事】";
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
					content = content+"【今日农事】";
				}
				if (content.contains("failed")||content.contains("Issue loading:"))
				{
					number="15311733826";
					postData = "sname="+ACCOUNT_NAME+"&spwd="+PASSWORD+"&scorpid=&sprdid=1012818&sdst="+number+"&smsg="+URLEncoder.encode(content,"UTF-8");
					SMS(postData, postUrl);
				}
				number="13162881998";
				postData = "sname="+ACCOUNT_NAME+"&spwd="+PASSWORD+"&scorpid=&sprdid=1012818&sdst="+number+"&smsg="+URLEncoder.encode(content,"UTF-8");
				SMS(postData, postUrl);
				number="13137736397";
				postData = "sname="+ACCOUNT_NAME+"&spwd="+PASSWORD+"&scorpid=&sprdid=1012818&sdst="+number+"&smsg="+URLEncoder.encode(content,"UTF-8");
				SMS(postData, postUrl);
				return "OK";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "Exceptione";
			}
		}
		return "Sms weather alert disabled.";
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
            System.out.println();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
	
	public static void main(String[] args){
		///SmsService sms = new SmsService();
		//String xiyaoCell = "13120128328";
		//String xiyaoCell = "13162881998";
		//String securityCode = sms.generateSecurityCode(6);
		//System.out.println(securityCode);
		//System.out.println(securityCodeSMS(securityCode, xiyaoCell));
		System.out.println(SmsService.weatherAlert("load 24h failed"));
	}
}
