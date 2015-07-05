package com.dasinong.ploughHelper.modelTran;

import java.util.HashMap;
import java.util.Random;

import com.dasinong.ploughHelper.weather.GetWeatherAlert;


public class LaoNong {
	private static LaoNong laoNong;
	
	public static Duanzi getDuanzi(String areaId){
		if (laoNong==null){
			laoNong = new LaoNong();
			
			GetWeatherAlert gwa = new GetWeatherAlert(areaId); 
			WeatherAlert wa = gwa.getWeatherAlert();
			if (wa == null){
				Random rnd= new Random();
				int picker = rnd.nextInt(laoNong.saying.size());
				return laoNong.saying.get(picker);
			} else {// 有预警数据
				String content = wa.shortDescription();
				String urlTag = wa.urlTag();
				Duanzi dz = new Duanzi(0, 2,"", "天气预警", content, urlTag);
				return  dz;
			}
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
    	Duanzi d = new Duanzi(1,3,"picName","每日农谚","春不种，秋无收。","");
    	saying.put(d.id, d);
    	d = new Duanzi(2,3,"picName","每日农谚","立夏勿下雨，犁耙倒挂起。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(3,3,"picName","每日农谚","五月端午晴，烂稻刮田膛。","");
    	saying.put(d.id, d);
    	d = new Duanzi(4,3,"picName","每日农谚","寒露无青稻，霜降一齐老。","");
    	saying.put(d.id, d);
    	d = new Duanzi(5,3,"picName","每日农谚","有水才有谷，无水守着哭。","");
    	saying.put(d.id, d);
    	d = new Duanzi(6,3,"picName","每日农谚","水库是个宝，防旱又防涝。","");
    	saying.put(d.id, d);
    	d = new Duanzi(7,3,"picName","每日农谚","稻田水多是糖浆，麦田水多是砒霜","");
    	saying.put(d.id, d);
    	d = new Duanzi(8,3,"picName","每日农谚","人靠饭养，稻靠肥长。","");
    	saying.put(d.id, d);
    	d = new Duanzi(9,3,"picName","每日农谚","肥田长稻，瘦田长草。","");
    	saying.put(d.id, d);
    	d = new Duanzi(10,3,"picName","每日农谚","土肥长谷，猪肥长肉。","");
    	saying.put(d.id, d);
    	d = new Duanzi(11,3,"picName","每日农谚","万物土里生，全靠两手勤。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(12,3,"picName","每日农谚","只要功夫深，土里出黄金。 ","");
    	saying.put(d.id, d);
    	d = new Duanzi(13,3,"picName","每日农谚","好种长好稻，坏种长稗草。","");
    	saying.put(d.id, d);
    	d = new Duanzi(14,3,"picName","每日农谚","三年不选种，增产要落空。","");
    	saying.put(d.id, d);
    	d = new Duanzi(15,3,"picName","每日农谚","好儿要好娘，种田要好秧。","");
    	saying.put(d.id, d);
    	d = new Duanzi(16,3,"picName","每日农谚","作物不好胡搭配，乱点鸳鸯要吃亏. ","");
    	saying.put(d.id, d);
    }

	public static void main(String[] args){
		String areaId = "101081102";
		System.out.println(LaoNong.getDuanzi(areaId).content);
		System.out.println(LaoNong.getDuanzi(areaId).content);
		System.out.println(LaoNong.getDuanzi(areaId).content);
		System.out.println(LaoNong.getDuanzi(areaId).content);
		System.out.println(LaoNong.getDuanzi(areaId).content);
		System.out.println(LaoNong.getDuanzi(areaId).content);
		
	}
  
	
	
	private HashMap<Integer,Duanzi> saying = new HashMap<Integer,Duanzi>();
	
}
