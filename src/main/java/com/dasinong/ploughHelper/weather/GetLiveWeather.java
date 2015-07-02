package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class GetLiveWeather {
	
	private static final String url = "http://api.fuwu.weather.com.cn/wis_forcastdata/data/getData_YoLoo.php";
	//private static final String app_id = "05ac98ab74edb72f01eb";
	private static final String app_id = "05ac98ab74edb72f01eb";
	private static final String short_app_id = "05ac98";
	private static final String key = "YOLOO_webapi_data_3";
	private String date;
	private String areaId;
	private long lastAccessTime = 0;
	
	public GetLiveWeather(){
		
	}
	public GetLiveWeather(String areaId){
		this.areaId=areaId;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		date = sdf.format(new Date());
	}

	
	public LiveWeatherData getLiveWeather(){
        String result = "";
        BufferedReader in = null;
        long currentTime = System.currentTimeMillis();
        
        //如果上次关于这个地方的天气请求距离这次不到20分钟，那么直接返回缓存的天气数据
        if (this._liveweatherdata.containsKey(this.areaId) && (currentTime - this._liveweatherdata.get(this.areaId).timeStamp.getTime())<20*60*1000 )
        	return this._liveweatherdata.get(this.areaId);
        
		 try {
            	URL realUrl = new URL(this.getRealURL());	            
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
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
	                    connection.getInputStream()));
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
		 
		 if (result.equals("key error")){
			 System.out.println("areaID : "+this.areaId);
			 if (_liveweatherdata.containsKey(this.areaId)){
				 return _liveweatherdata.get(this.areaId);
			 } else {
				 LiveWeatherData initialWeatherData = new LiveWeatherData(this.areaId, 0, 0, 0, 0, "0", "0", "00:00");
				 _liveweatherdata.put(this.areaId, initialWeatherData);
				 return initialWeatherData;
			 }
		 } else {
			 LiveWeatherData initialWeatherData = new LiveWeatherData(this.areaId, 0, 0, 0, 0, "0", "0", "00:00");
			 try {
				initialWeatherData.parseHTTPResult(this.areaId, result);
				 _liveweatherdata.put(this.areaId, initialWeatherData);
			 } catch (Exception e) {
				 System.out.println("Error happend when processing live weather data!");
				 e.printStackTrace();
			 }
			 return initialWeatherData;
		 }
	}
	
	
	
	public String getRealURL() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException{
		String result;
		String urlkey = this.url+"?areaid="+this.areaId+"&type=observe&date="+date+"&appid="+this.app_id;
		System.out.println(urlkey);
		byte[] skey = this.key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(skey,"HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(urlkey.getBytes());
		byte[] encodeBytes = Base64.encodeBase64(rawHmac);
		
		String finalkey = new String(encodeBytes,"utf-8");
		System.out.println(finalkey);
		result = this.url+"?areaid="+this.areaId+"&type=observe&date="+date+"&appid="+this.short_app_id+"&key="+finalkey;
		System.out.println(result);
		return result;
	}
	
	private static HashMap<String,LiveWeatherData> _liveweatherdata = new HashMap<String,LiveWeatherData>();
		
	public static void main (String args[]){	
		GetLiveWeather gh = new GetLiveWeather("101010100");
		gh.getLiveWeather();		
	}
	public void setAreaId(String areaId) {
		this.areaId=areaId;
	}

}
