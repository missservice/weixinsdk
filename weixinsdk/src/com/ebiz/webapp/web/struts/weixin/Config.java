/* Copyright(c)2010-2014 WUDAOSOFT.COM
 * 
 * Email:changsoul.wu@gmail.com
 * 
 * QQ:275100589
 */

package com.ebiz.webapp.web.struts.weixin;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aiisen.weixin.WeiXinConfig;
import com.aiisen.weixin.WeiXinMessageProcess;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年9月23日 下午1:11:45
 */
public class Config {

	private static final Logger log = LoggerFactory.getLogger(Config.class);

	public static void init() {
		Properties prop = new Properties();
		try {
			prop.load(Config.class.getResource("/pay.properties").openStream());

			// 配置公众平台帐号信息
			WeiXinConfig.init(prop.getProperty("WEI_XIN_ACCOUNT"), prop.getProperty("APP_ID"),
					prop.getProperty("APPSECRET"), prop.getProperty("TOKEN"));

			// WeiXinConfig config = new WeiXinConfig().setAccount(prop.getProperty("WEI_XIN_ACCOUNT"))
			// .setAppId(prop.getProperty("APP_ID")).setAppsecret(prop.getProperty("APPSECRET"))
			// .setToken(prop.getProperty("TOKEN"));
			// // 设置配置信息
			// WeiXinMessageProcess.setWeixinConfig(config);

			// 设置消息处理Handler
			WeiXinMessageProcess.setMessageHandler(MyWeiXinMessageHandler.getInstance());

			log.info("Weixin initialize...");
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * <p>
	 * 作为第三方服务用，需要自定义配置
	 * </p>
	 * 
	 * @author Wu,Yang
	 * @date 2016年6月28日
	 */
	public static void init(WeiXinConfig config) {

		// 设置配置信息
		WeiXinMessageProcess.setWeixinConfig(config);
		// 设置消息处理Handler
		WeiXinMessageProcess.setMessageHandler(MyWeiXinMessageHandler.getInstance());

		log.info("Weixin initialize...");
	}
}
