package com.dasinong.ploughHelper.modelTran;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IProverbDao;
import com.dasinong.ploughHelper.model.Proverb;
import com.dasinong.ploughHelper.util.LunarHelper;
import com.dasinong.ploughHelper.weather.GetLiveWeather;
import com.dasinong.ploughHelper.weather.LiveWeatherData;

import edu.emory.mathcs.backport.java.util.Arrays;

public class NongYan {

	private static NongYan nongYan;

	public Proverb getNongYan(Integer areaId) {
		IProverbDao proverbDao = null;

		try {
			proverbDao = (IProverbDao) ContextLoader.getCurrentWebApplicationContext().getBean("proverbDao");
		} catch (Exception e) {
			System.out.println("Error: Get Bean proverbDao Error!");
			e.printStackTrace();
		}

		Proverb proverb = null;
		// 为了让农谚应景，选择与当前农历节气，天气，月份相关的农谚显示，它们的重要性依序而定。
		// 1. 首先看今日是否是农历节气
		Calendar today = Calendar.getInstance();
		try {
			LunarHelper lh = new LunarHelper(today);
			String jieqi = lh.getJieQi();
			if (!"".equals(jieqi)) {
				proverb = proverbDao.findByLunarCalender(jieqi);
			}
		} catch (Exception e) {
			System.out.println("Error: Get LunarHelper Error in NongYan!");
			e.printStackTrace();
		}

		try {
			// 2. 再看今日天气：多雨，多云，大风
			if (proverb == null) {
				GetLiveWeather glw = new GetLiveWeather(areaId.toString());
				LiveWeatherData lwd = glw.getLiveWeather();
				// LiveWeatherData 有l1, l2, .. , l7 共7个字段，l5
				// （String）为天气现象编码，l3为当前风力（int）
				// 气象编码表参见 WISWeatherAPI <Lite> 第五节 5.1 天气现象编码表
				// 和雨相关的编码罗列如下，例如 03 表示 阵雨，04 表示 雷阵雨，。。。
				String[] rainyCodeArray = { "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "21", "22",
						"23", "24", "25" };
				List<String> rainyCodeList = Arrays.asList(rainyCodeArray);
				if (rainyCodeList.contains(lwd.l5))
					proverb = proverbDao.findByWeather("有雨");

				// 01 表示 多云
				if (lwd.l5.equals("01"))
					proverb = proverbDao.findByWeather("多云");

				// l3为风力，0表示微风，1表示3-4级，2表示4-5级，以此类推到9表示11-12级，其中风力大于3级，即为大风天气
				if (lwd.l3 >= 5)
					proverb = proverbDao.findByWeather("大风");
			}
		} catch (Exception e) {
			System.out.println("Error: GetLiveWeather error in NongYan!");
			e.printStackTrace();
		}
		// 3. 如果既不是农历节气，也不是特殊天气，那么看当前月份，选择与当前月份关联的农谚显示
		if (proverb == null) {
			Integer month = today.get(Calendar.MONTH) + 1;
			proverb = proverbDao.findByMonth(month.toString());
		}

		// 4. 如果均不是，那么随机取一个
		if (proverb == null) {
			proverb = proverbDao.findByAccident();
		}

		return proverb;
	}

	public static NongYan allNongYan() {
		if (nongYan == null)
			nongYan = new NongYan();
		return nongYan;
	}

	private NongYan() {
	}

}
