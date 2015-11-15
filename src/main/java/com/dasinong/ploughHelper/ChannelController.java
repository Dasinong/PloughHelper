package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.SmsService;

@Controller
public class ChannelController extends RequireUserLoginController {

	private static final Logger logger = LoggerFactory.getLogger(BaiKeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/refapp", produces = "application/json")
	@ResponseBody
	public Object refapp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.getLoginUser(request);
		String cellphone = request.getParameter("cellPhones");
		String normalUrl = "http://t.im/rctw";
		// String dowsUrl = "http://jinrinongshi.com/dows.html";
		String dowsUrl = "http://t.im/sh3v";
		String bsfUrl = "http://jinrinongshi.com/bsf.html";
		String content;
		if (cellphone != null && !"".equals(cellphone)) {
			String[] target = cellphone.split(",");
			if (user == null || user.getChannel() == null) {
				content = "哇朋友向你推荐“今日农事”软件！手机种田好帮手，查天气病虫草害一键搞定，马上免费下载" + normalUrl + " 回T退订";
				SmsService.sendref(content, target);
			} else {
				if (user.getChannel().equals("陶氏")) {
					content = "哇朋友向你推荐“今日农事”软件！手机种田好帮手，查天气病虫草害一键搞定，马上免费下载" + dowsUrl + " 回T推定";
					SmsService.sendref(content, target);
				} else if (user.getChannel().equals("巴士甫")) {
					content = "哇朋友向你推荐“今日农事”软件！手机种田好帮手，查天气病虫草害一键搞定，马上免费下载" + bsfUrl + " 回T退订";
					SmsService.sendref(content, target);
					SmsService.sendref(content, target);
				} else {
					content = "哇朋友向你推荐“今日农事”软件！手机种田好帮手，查天气病虫草害一键搞定，马上免费下载" + normalUrl + " 回T退订";
					SmsService.sendref(content, target);
				}
			}
		}
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("respCode", "200");
		result.put("message", "消息发送任务已部署");
		return result;
	}

}
