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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsService {
	public static final String ACCOUNT_NAME = "dldasi00";
	public static final String PASSWORD = "MF3o9AFn";
	public static final int maxLength = 120;
	private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

	public static synchronized String weatherAlert(String content) {
		if (Env.getEnv().weatherAlert) {
			String postUrl = "http://cf.51welink.com/submitdata/Service.asmx/g_Submit";
			String number = "";
			String postData = "";
			try {
				if (content.trim().endsWith("Issue loading:"))
					content = content.substring(0, content.length() - 14);
				try {
					content = content + " " + InetAddress.getLocalHost().getHostAddress().toString() + "【今日农事】";
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
					content = content + "【今日农事】";
				}
				if (content.contains("failed") || content.contains("Issue loading:")) {
					number = "15311733826";
					postData = "sname=" + ACCOUNT_NAME + "&spwd=" + PASSWORD + "&scorpid=&sprdid=1012818&sdst=" + number
							+ "&smsg=" + URLEncoder.encode(content, "UTF-8");
					SMS(postData, postUrl);
				}
				number = "13162881998";
				postData = "sname=" + ACCOUNT_NAME + "&spwd=" + PASSWORD + "&scorpid=&sprdid=1012818&sdst=" + number
						+ "&smsg=" + URLEncoder.encode(content, "UTF-8");
				SMS(postData, postUrl);
				number = "13137736397";
				postData = "sname=" + ACCOUNT_NAME + "&spwd=" + PASSWORD + "&scorpid=&sprdid=1012818&sdst=" + number
						+ "&smsg=" + URLEncoder.encode(content, "UTF-8");
				SMS(postData, postUrl);
				return "OK";
			} catch (UnsupportedEncodingException e) {
				logger.error("Unsupported encoding", e);
				return "Exceptione";
			}
		}
		return "Sms weather alert disabled.";
	}

	public static String convertNumbers(ArrayList<String> numbers) {
		String numbersString = "";
		if (numbers.size() > 0) {
			for (int i = 0; i < numbers.size() - 1; i++) {
				numbersString += numbers.get(i).trim() + ",";
			}
			numbersString += numbers.get(numbers.size() - 1).trim();
		}

		return numbersString;
	}

	public static String SMS(String postData, String postUrl) {
		try {
			// 发送POST请求
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

			// 获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.error("connect failed");
				return "";
			}
			// 获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			logger.error("SMS IO error", e);
		}
		return "";
	}
}
