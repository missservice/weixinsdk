/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

import com.aiisen.weixin.exception.WeiXinException;
import com.aiisen.weixin.handler.WeiXinMessageHandler;
import com.aiisen.weixin.message.receive.ReceiveEventMsg;
import com.aiisen.weixin.message.receive.ReceiveImageMsg;
import com.aiisen.weixin.message.receive.ReceiveLinkMsg;
import com.aiisen.weixin.message.receive.ReceiveLocationMsg;
import com.aiisen.weixin.message.receive.ReceiveTextMsg;
import com.aiisen.weixin.message.receive.ReceiveVideoMsg;
import com.aiisen.weixin.message.receive.ReceiveVoiceMsg;
import com.aiisen.weixin.type.EventType;
import com.aiisen.weixin.type.MsgType;
import com.aiisen.weixin.utils.XmlUtils;
import com.aiisen.weixin.xml.XmlObject;

/**
 * <p>
 * 微信服务器回调处理
 * </p>
 * <p>
 * 请实现WeiXinMessageHandler接口,用于处理和响应消息
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午9:54:02
 */
public class WeiXinMessageProcess {

	private static final Log log = LogFactory.getLog(WeiXinMessageProcess.class);

	private static WeiXinMessageHandler messageHandler;

	private WeiXinMessageProcess() {
	}

	public static void setWeixinConfig(WeiXinConfig config) {
		CommonApi.WEI_XIN_ACCOUNT = config.getAccount();
		CommonApi.APP_ID = config.getAppId();
		CommonApi.APPSECRET = config.getAppsecret();
		CommonApi.TOKEN = config.getToken();
		CommonApi.ACCESS_TOKEN_URL = ApiUrlConstants.ACCESS_TOKEN + "?grant_type=client_credential&appid="
				+ CommonApi.APP_ID + "&secret=" + CommonApi.APPSECRET;
	}

	public static void setMessageHandler(WeiXinMessageHandler messageHandler) {
		WeiXinMessageProcess.messageHandler = messageHandler;
	}

	/**
	 * 回调消息处理入口
	 * 
	 * @param request
	 * @param messageHandler
	 * @return
	 * @throws IOException
	 * @throws WeiXinException
	 */
	public static String processRequest(HttpServletRequest request) throws IOException, WeiXinException {

		Document doc = XmlUtils.readXml(request.getInputStream());

		String respXML = "";

		if (doc != null) {
			XmlObject req = XmlObject.fromDocument(doc);

			String msgTypeStr = req.getString("MsgType");

			log.debug("Receive msgType:" + msgTypeStr);

			if (msgTypeStr != null) {

				MsgType msgType = MsgType.getMsgTypeByName(msgTypeStr);

				if (msgType == null)
					return respXML;

				switch (msgType) {
				case text:
					respXML = messageHandler.processTextMsg(req.buildWeiXinBean(ReceiveTextMsg.class));
					break;

				case image:
					respXML = messageHandler.processImageMsg(req.buildWeiXinBean(ReceiveImageMsg.class));
					break;

				case voice:
					respXML = messageHandler.processVoiceMsg(req.buildWeiXinBean(ReceiveVoiceMsg.class));
					break;

				case event:
					respXML = processEventMsg(req.buildWeiXinBean(ReceiveEventMsg.class));
					break;

				case video:
					respXML = messageHandler.processVideoMsg(req.buildWeiXinBean(ReceiveVideoMsg.class));
					break;

				case location:
					respXML = messageHandler.processLocationMsg(req.buildWeiXinBean(ReceiveLocationMsg.class));
					break;

				case link:
					respXML = messageHandler.processLinkMsg(req.buildWeiXinBean(ReceiveLinkMsg.class));
					break;

				default:
					break;
				}
			}
		}

		return respXML;
	}

	/**
	 * 事件处理入口
	 * 
	 * @param ReceiveEventMsg
	 * @return
	 */
	private static String processEventMsg(ReceiveEventMsg eventMsg) {

		String respXML = "";

		if (eventMsg != null) {
			log.debug("Receive eventType:" + eventMsg.getEvent());

			EventType eventType = EventType.getEventTypeByName(eventMsg.getEvent());

			if (eventType == null)
				return respXML;

			switch (eventType) {
			case subscribe:
				respXML = messageHandler.processSubscribeEvent(eventMsg);
				break;
			case unsubscribe:
				respXML = messageHandler.processUnsubscribeEvent(eventMsg);
				break;
			case CLICK:
				respXML = messageHandler.processClickEvent(eventMsg);
				break;
			case VIEW:
				respXML = messageHandler.processViewEvent(eventMsg);
				break;
			case SCAN:
				respXML = messageHandler.processScanEvent(eventMsg);
				break;
			case LOCATION:
				respXML = messageHandler.processLocationEvent(eventMsg);
				break;
			default:
				break;
			}
		}

		return respXML;
	}

}
