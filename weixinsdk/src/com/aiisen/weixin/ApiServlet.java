/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ebiz.webapp.web.struts.weixin.MyWeiXinMessageHandler;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午12:22:35
 */
public class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 4120271321525038178L;

	private static final Log log = LogFactory.getLog(ApiServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		resp.setContentType("application/json; encoding=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		// log.warn("==signature:" + signature);
		// log.warn("==timestamp:" + timestamp);
		// log.warn("==nonce:" + nonce);
		// log.warn("==echostr:" + echostr);
		if ((signature != null) && (timestamp != null) && (nonce != null) && (echostr != null)) {

			boolean flag = CommonApi.checkSignature(signature, timestamp, nonce);

			if (flag) {
				log.warn("check weixin signature success");
				resp.getWriter().write(echostr);
			} else {
				log.warn("check weixin signature failed");
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/xml; encoding=utf-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");

		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");

		String respXML = "";

		// log.warn("==doPost signature:" + signature);
		// log.warn("==doPost timestamp:" + timestamp);
		// log.warn("==doPost nonce:" + nonce);
		try {

			if ((signature != null) && (timestamp != null) && (nonce != null)) {

				if (CommonApi.checkSignature(signature, timestamp, nonce)) {

					try {
						// Config.init();
						// 配置公众平台帐号信息
						WeiXinConfig config = new WeiXinConfig().setAccount(CommonApi.WEI_XIN_ACCOUNT)
								.setAppId(CommonApi.APP_ID).setAppsecret(CommonApi.APPSECRET).setToken(CommonApi.TOKEN);
						// 设置配置信息
						WeiXinMessageProcess.setWeixinConfig(config);
						// 设置消息处理Handler
						WeiXinMessageProcess.setMessageHandler(MyWeiXinMessageHandler.getInstance());

						respXML = WeiXinMessageProcess.processRequest(req);
					} catch (Exception e) {
						log.warn("respXML error:" + e.getMessage());

					}

					if (respXML == null) {
						respXML = "";
					}

					log.debug("respXML:" + respXML);
				} else {
					log.warn("Check signature failed! signature:" + signature + " timestamp:" + timestamp + " nonce:"
							+ nonce);
				}
			} else {
				log.warn("Unauthorized access! The IP is " + req.getRemoteAddr());
			}

			resp.getWriter().print(respXML);
			resp.getWriter().flush();
			resp.getWriter().close();
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		}
	}
}
