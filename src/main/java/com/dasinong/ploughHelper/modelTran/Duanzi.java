package com.dasinong.ploughHelper.modelTran;

public class Duanzi {
	public int id; // 约定id = 0 的段子是天气预警， id = -1, 的段子为广告
	public int type; // type = 3 为 农谚， =1 为天气预警， =2 为广告
	public String picName; //图片名称，老农头
	public String title;
	public String content;
	public String url;
		
	public Duanzi(int id, int type, String picName, String title, String content, String url) {
		super();
		this.id = id;
		this.type = type;
		this.picName = picName;
		this.title = title;
		this.content = content;
		this.url = url;

	}
}
