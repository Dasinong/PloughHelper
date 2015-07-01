package com.dasinong.ploughHelper.modelTran;

import java.util.HashMap;
import java.util.Random;


public class LaoNong {
	private static LaoNong laoNong;
	
	public static Duanzi getDuanzi(){
		if (laoNong==null){
			laoNong = new LaoNong();
			Random rnd= new Random();
			int picker = rnd.nextInt(laoNong.saying.size());
			return laoNong.saying.get(picker);
		}
		else{
			Random rnd= new Random();
			int picker = rnd.nextInt(laoNong.saying.size());
			return laoNong.saying.get(picker);
		}
	}
	
	private LaoNong(){
		saying = new HashMap<Integer,Duanzi>();
		loadContent();
	}

	
    private void loadContent(){
    	Duanzi d = new Duanzi(1,1,"每日农谚","春不种，秋无收。","");
    	saying.put(d.id, d);
    	d = new Duanzi(2,1,"每日农谚","立夏勿下雨，犁耙倒挂起。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(3,1,"每日农谚","五月端午晴，烂稻刮田膛。","");
    	saying.put(d.id, d);
    	d = new Duanzi(4,1,"每日农谚","寒露无青稻，霜降一齐老。","");
    	saying.put(d.id, d);
    	d = new Duanzi(5,1,"每日农谚","有水才有谷，无水守着哭。","");
    	saying.put(d.id, d);
    	d = new Duanzi(6,1,"每日农谚","水库是个宝，防旱又防涝。","");
    	saying.put(d.id, d);
    	d = new Duanzi(7,1,"每日农谚","稻田水多是糖浆，麦田水多是砒霜","");
    	saying.put(d.id, d);
    	d = new Duanzi(8,1,"每日农谚","人靠饭养，稻靠肥长。","");
    	saying.put(d.id, d);
    	d = new Duanzi(9,1,"每日农谚","肥田长稻，瘦田长草。","");
    	saying.put(d.id, d);
    	d = new Duanzi(10,1,"每日农谚","土肥长谷，猪肥长肉。","");
    	saying.put(d.id, d);
    	d = new Duanzi(11,1,"每日农谚","万物土里生，全靠两手勤。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(12,1,"每日农谚","只要功夫深，土里出黄金。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(13,1,"每日农谚","好种长好稻，坏种长稗草。","");
    	saying.put(d.id, d);
    	d = new Duanzi(14,1,"每日农谚","三年不选种，增产要落空。","");
    	saying.put(d.id, d);
    	d = new Duanzi(15,1,"每日农谚","好儿要好娘，种田要好秧。","");
    	saying.put(d.id, d);
    	d = new Duanzi(16,1,"每日农谚","作物不好胡搭配，乱点鸳鸯要吃亏. ","");
    	saying.put(d.id, d);
    }

	public static void main(String[] args){
		System.out.println(LaoNong.getDuanzi().content);
		System.out.println(LaoNong.getDuanzi().content);
		System.out.println(LaoNong.getDuanzi().content);
		System.out.println(LaoNong.getDuanzi().content);
		System.out.println(LaoNong.getDuanzi().content);
		System.out.println(LaoNong.getDuanzi().content);
		
	}
  
	
	
	private HashMap<Integer,Duanzi> saying = new HashMap<Integer,Duanzi>();
	
}
