package com.dasinong.ploughHelper.util;

import java.util.ArrayList;
import java.util.List;


//This parser supports serialize and deserialize of a long type list up to 10 element.
public class ListofID {

	private List<Long> contentl;
	private String contents;
	public ListofID(List<Long> contentl){
		this.contentl=contentl;
		StringBuilder contentS = new StringBuilder(100);
		if (contentl!=null && contentl.size()>0){
			for(Long i : contentl){
				contentS.append(i);
				contentS.append(",");
			}
			contentS.deleteCharAt(contentS.length()-1);
		}
		this.contents = contentS.toString();
	}
	
	
	public ListofID (String contents){
		this.contents=contents;
		List<Long> ls = new ArrayList<Long>();
		if (contents!=null && !contents.equals("")){
			String[] units = contents.split(",");
			for (int i=0;i<units.length;i++){
				try{
					Long unit = Long.parseLong(units[i]);
					ls.add(unit);
				}
				catch(Exception e){
					System.out.println("Error Code 10: unit value for content not long");
				}
			}
		}
		this.contentl =ls;
	}
	
	
	public List<Long> getAsList(){
		return this.contentl;
	}
	public String getAsString(){
		return this.contents;
	}
	
	@Override
	public String toString(){
		return this.contents;
	}
	
	public static void main(String[] args){
        //Simple test here. Take care of null if business logic invovled.
		List<Long> contentl = new ArrayList<Long>();
		ListofID ls1 = new ListofID(contentl);
		System.out.println(ls1.getAsList());
		System.out.println(ls1.getAsString());
		System.out.println(ls1);
		
		contentl.add(12L);
		contentl.add(20L);
		contentl.add(11L);
		contentl.add(19L);
		
		ListofID ls2 = new ListofID(contentl);
		System.out.println(ls2.getAsList());
		System.out.println(ls2.getAsString());
		System.out.println(ls2);
		
		ListofID ls3 = new ListofID("");
		System.out.println(ls3.getAsList());
		System.out.println(ls3.getAsString());
		System.out.println(ls3);
		
		ListofID ls4 = new ListofID("1");
		System.out.println(ls4.getAsList());
		System.out.println(ls4.getAsString());
		System.out.println(ls4);
		
		ListofID ls5 = new ListofID("1,2");
		System.out.println(ls5.getAsList());
		System.out.println(ls5.getAsString());
		System.out.println(ls5);
	}
}


