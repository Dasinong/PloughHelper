package com.dasinong.ploughHelper.modelTran;

import java.util.HashMap;
import java.util.Random;

public class NongYan {
	private static NongYan nongYan;
	
	public LaoNong getNongYan(Integer id){
		return this.saying.get(id);
	}
	
	public LaoNong getRndNongYan(){
		Random rnd = new Random();
		int id = rnd.nextInt(this.saying.size());
		return this.saying.get(id);
	}
	
	public static NongYan allNongYan(){
		if (nongYan==null)
			nongYan = new NongYan();
		return nongYan;
	}
	
	private NongYan(){
		saying = new HashMap<Integer,LaoNong>();
		loadContent();
	}
	
    private void loadContent(){
    	LaoNong d = new LaoNong(1,3,"picName","每日农谚","春不种，秋无收。","");
    	saying.put(d.id, d);
    	d = new LaoNong(2,3,"picName","每日农谚","立夏勿下雨，犁耙倒挂起。 ","");
    	saying.put(d.id, d);
    	d = new LaoNong(3,3,"picName","每日农谚","五月端午晴，烂稻刮田膛。","");
    	saying.put(d.id, d);
    	d = new LaoNong(4,3,"picName","每日农谚","寒露无青稻，霜降一齐老。","");
    	saying.put(d.id, d);
    	d = new LaoNong(5,3,"picName","每日农谚","有水才有谷，无水守着哭。","");
    	saying.put(d.id, d);
    	d = new LaoNong(6,3,"picName","每日农谚","水库是个宝，防旱又防涝。","");
    	saying.put(d.id, d);
    	d = new LaoNong(7,3,"picName","每日农谚","稻田水多是糖浆，麦田水多是砒霜","");
    	saying.put(d.id, d);
    	d = new LaoNong(8,3,"picName","每日农谚","人靠饭养，稻靠肥长。","");
    	saying.put(d.id, d);
    	d = new LaoNong(9,3,"picName","每日农谚","肥田长稻，瘦田长草。","");
    	saying.put(d.id, d);
    	d = new LaoNong(10,3,"picName","每日农谚","土肥长谷，猪肥长肉。","");
    	saying.put(d.id, d);
    	d = new LaoNong(11,3,"picName","每日农谚","万物土里生，全靠两手勤。 ","");
    	saying.put(d.id, d);
    	d = new LaoNong(12,3,"picName","每日农谚","只要功夫深，土里出黄金。 ","");
    	saying.put(d.id, d);
    	d = new LaoNong(13,3,"picName","每日农谚","好种长好稻，坏种长稗草。","");
    	saying.put(d.id, d);
    	d = new LaoNong(14,3,"picName","每日农谚","三年不选种，增产要落空。","");
    	saying.put(d.id, d);
    	d = new LaoNong(15,3,"picName","每日农谚","好儿要好娘，种田要好秧。","");
    	saying.put(d.id, d);
    	d = new LaoNong(16,3,"picName","每日农谚","作物不好胡搭配，乱点鸳鸯要吃亏. ","");
    	saying.put(d.id, d);
    }

	public static void main(String[] args){
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		System.out.println(NongYan.allNongYan().getRndNongYan().content);
		
	}
  
	
	
	private HashMap<Integer,LaoNong> saying = new HashMap<Integer,LaoNong>();
	
}
