package com.dasinong.ploughHelper.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.dasinong.ploughHelper.weather.LiveWeatherData;

public class WISWeather {
	
	private static final String url = "http://api.fuwu.weather.com.cn/wis_forcastdata/data/getData_YoLoo.php";
	//private static final String app_id = "05ac98ab74edb72f01eb";
	private static final String app_id = "05ac98ab74edb72f01eb";
	private static final String short_app_id = "05ac98";
	private static final String key = "YOLOO_webapi_data_3";
	private String date;
	private String areaId;
	private String type;

	
	public WISWeather(String areaId,String type){
		this.areaId=areaId;
		this.type =type;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		date = sdf.format(new Date());
	}

	
	public String Commute(){
        String result = "";
        BufferedReader in = null;
        
    	try {
            	URL realUrl = new URL(this.getRealURL());	            
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            //connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream(),"UTF-8"));
	            String line;
	            result="";
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	            System.out.println(result);
	            if (result.equals("key error"))
	            	System.out.println("Error happened with the server when decoding url key!");
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        } finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
		 
    	return result;
		 
	}
	
	public String getRealURL() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException{
		String result;
		String urlkey = WISWeather.url+"?areaid="+this.areaId+"&type="+this.type+"&date="+date+"&appid="+this.app_id;
		System.out.println(urlkey);
		byte[] skey = WISWeather.key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(skey,"HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(urlkey.getBytes());
		byte[] encodeBytes = Base64.encodeBase64(rawHmac);
		
		String finalkey = new String(encodeBytes,"utf-8");
		System.out.println(finalkey);
		result = WISWeather.url+"?areaid="+this.areaId+"&type="+this.type+"&date="+date+"&appid="+this.short_app_id+"&key="+finalkey;
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args){
		WISWeather wisw = new WISWeather("101010100","alarm");
		wisw.Commute();
	}

}
