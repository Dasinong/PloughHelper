package com.dasinong.ploughHelper.datapool;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.dasinong.ploughHelper.model.nohibernate.SystemMessage;
import com.dasinong.ploughHelper.util.Env;


public class AllSystemMessage {
	private static AllSystemMessage allSystemMessage;
	private AllSystemMessage(){
		
		Connection conn = null;
		String sql;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Env.getEnv().DBConnection);
			Statement stmt = conn.createStatement();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dataexp = sdf.format(new Date());
			sql = "select * from systemMessage where endTime>'"+dataexp+"'";
			ResultSet result = stmt.executeQuery(sql);
			while(result.next()){
				SystemMessage sm = new SystemMessage(
						result.getInt("id"),
						result.getInt("monitorLocation"),
						result.getString("locationGroup"),
						result.getString("channel"),
						result.getString("content"),
						result.getDate("startTime"),
						result.getDate("endTime")
						);
				this.addMessage(sm, true);
			}
			stmt.close();
			conn.close();
		}catch(Exception e){
			//TODO: send message
		}
	}
	
	public static AllSystemMessage getSystemMessage(){
		if (null == allSystemMessage){
			{
				allSystemMessage = new AllSystemMessage();
			}
		}
		return allSystemMessage;
	}

	//All OwnerRideInfo reference can be directly accessed through RID
	//public Hashtable<Integer,OwnerRideInfo> _availRides;  //All available rides. Represent by RID.

    public List<SystemMessage> get_Messages(int monitorLocation) {
		return _allSysMessage.get(monitorLocation);
	}

	public void addMessage(SystemMessage sm, boolean isLoadFromDb) {
		if(!isLoadFromDb){
			Connection conn = null;
			String sql;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(Env.getEnv().DBConnection);
				Statement stmt = conn.createStatement();
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				//String dataexp = sdf.format(new Date());
				sql = "insert into systemMessage (monitorLocation,locationGroup,channel,content,startTime,endTime) values ("+
							sm.getMonitorLocation()+","+
						    sm.getLocationGroup()+"," +
							sm.getChannel()+","+
						    sm.getContent()+","+
							sm.getStartTime()+","+
						    sm.getEndTime()+")";
				stmt.execute(sql);
				stmt.close();
				conn.close();
			}catch(Exception e){
				//Send message
			}
		}
		
		if (_allSysMessage.get(sm.getMonitorLocation())==null){
			List<SystemMessage> sml = new LinkedList<SystemMessage>();
			sml.add(sm);
			_allSysMessage.put(sm.getMonitorLocation(), sml);
		}else{
			_allSysMessage.get(sm.getMonitorLocation()).add(sm);
		}
	}

	private HashMap<Integer,List<SystemMessage>> _allSysMessage = new HashMap<Integer,List<SystemMessage>>(1000);
	
    public void cleanMessage(){
    	Date current = new Date();
    	for(Entry<Integer,List<SystemMessage>> entry : _allSysMessage.entrySet()){
    		for(Iterator<SystemMessage> smi = entry.getValue().iterator();(smi!=null && smi.hasNext());){
    			SystemMessage sm = smi.next();
    			if (sm.getEndTime().getTime()<current.getTime()){
    				smi.remove();
    			}
    		}
    	}
    }
    
    
    public static void main(String[] args){
		List<SystemMessage> sms =  AllSystemMessage.getSystemMessage().get_Messages(101010100);
		for(SystemMessage sm:sms){
			System.out.println(sm.getContent());
		}
		sms =  AllSystemMessage.getSystemMessage().get_Messages(101020100);
		for(SystemMessage sm:sms){
			System.out.println(sm.getContent());
		}
		AllSystemMessage.getSystemMessage().cleanMessage();
		sms =  AllSystemMessage.getSystemMessage().get_Messages(101010100);
		for(SystemMessage sm:sms){
			System.out.println(sm.getContent());
		}
		sms =  AllSystemMessage.getSystemMessage().get_Messages(101020100);
		for(SystemMessage sm:sms){
			System.out.println(sm.getContent());
		}
	}
}
