package com.ebiz.webapp.web.struts.weixin;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aiisen.weixin.CommonApi;
import com.aiisen.weixin.WeiXinMessageProcess;
import com.aiisen.weixin.handler.WeiXinMessageHandler;
import com.aiisen.weixin.message.receive.ReceiveEventMsg;
import com.aiisen.weixin.message.receive.ReceiveImageMsg;
import com.aiisen.weixin.message.receive.ReceiveLinkMsg;
import com.aiisen.weixin.message.receive.ReceiveLocationMsg;
import com.aiisen.weixin.message.receive.ReceiveTextMsg;
import com.aiisen.weixin.message.receive.ReceiveVideoMsg;
import com.aiisen.weixin.message.receive.ReceiveVoiceMsg;
import com.aiisen.weixin.message.response.ResponseMsgBuilder;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月23日 下午12:21:16
 */
public class MyWeiXinMessageHandler implements WeiXinMessageHandler {

	private static final Log log = LogFactory.getLog(WeiXinMessageProcess.class);

	private static final String LINE = "\r\n";

	private static WeiXinMessageHandler handler = new MyWeiXinMessageHandler();

	public static WeiXinMessageHandler getInstance() {
		return handler;
	}

	// 禁止new
	private MyWeiXinMessageHandler() {
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processClickEvent(com.aiisen.weixin.message.receive.
	 * ReceiveEventMsg)
	 */
	@Override
	public String processClickEvent(ReceiveEventMsg arg0) {
		// 绑定帐号事件值BINDING,
		if (StringUtils.equalsIgnoreCase("key200_bangding", arg0.getEventKey())) {
			// 返回绑定帐号Oauth2授权连接
			// String redirectUri = Keys.APP_DOMAIN + "/IndexLogin.do?method=afterLoginWeixin";
			// String content = "感谢您关注" + Keys.app_name + "!";
			String content = "感谢您关注!";
			// + LINE+ Oauth2Api.buildUserinfoOauth2Link("请点击这里绑定", redirectUri, "");
			return ResponseMsgBuilder.buildTextMsg(arg0.getFromUserName(), CommonApi.WEI_XIN_ACCOUNT, content);
		} else {
			String content = "您点击的是:" + arg0.getEventKey();
			return ResponseMsgBuilder.buildTextMsg(arg0.getFromUserName(), CommonApi.WEI_XIN_ACCOUNT, content);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processImageMsg(com.aiisen.weixin.message.receive.
	 * ReceiveImageMsg)
	 */
	@Override
	public String processImageMsg(ReceiveImageMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.aiisen.weixin.handler.WeiXinMessageHandler#processLinkMsg(com.aiisen.weixin.message.receive.ReceiveLinkMsg )
	 */
	@Override
	public String processLinkMsg(ReceiveLinkMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processLocationEvent(com.aiisen.weixin.message.receive.
	 * ReceiveEventMsg)
	 */
	@Override
	public String processLocationEvent(ReceiveEventMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processLocationMsg(com.aiisen.weixin.message.receive.
	 * ReceiveLocationMsg)
	 */
	@Override
	public String processLocationMsg(ReceiveLocationMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processScanEvent(com.aiisen.weixin.message.receive.
	 * ReceiveEventMsg)
	 */
	@Override
	public String processScanEvent(ReceiveEventMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processSubscribeEvent(com.aiisen.weixin.message.receive.
	 * ReceiveEventMsg)
	 */
	@Override
	public String processSubscribeEvent(ReceiveEventMsg arg0) {
		// String login = Keys.APP_DOMAIN + "/weixin/login.html";
		// StringBuilder link = new StringBuilder();
		// link.append("<a href=\"").append(login).append("\">");
		// link.append("绑定微信").append("</a>");
		// String content = "欢迎使用【" + Keys.app_name + "】购物，祝您购物愉快！";
		String content = "欢迎购物，祝您购物愉快！";
		// String content = "欢迎关注【" + Keys.APP_NAME + "】," + LINE + "点击" + link.toString();
		return ResponseMsgBuilder.buildTextMsg(arg0.getFromUserName(), CommonApi.WEI_XIN_ACCOUNT, content);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.aiisen.weixin.handler.WeiXinMessageHandler#processTextMsg(com.aiisen.weixin.message.receive.ReceiveTextMsg )
	 */
	@Override
	public String processTextMsg(ReceiveTextMsg arg0) {
		log.warn("arg0.getContent():" + arg0.getContent());
		log.warn("CommonApi.WEI_XIN_ACCOUNT:" + CommonApi.WEI_XIN_ACCOUNT);
		// String content = "来自【" + Keys.app_name + "】的回复:" + arg0.getContent();
		// String content = "欢迎使用【" + Keys.app_name + "】购物，祝您购物愉快！";
		String content = "欢迎购物，祝您购物愉快！";
		return ResponseMsgBuilder.buildTextMsg(arg0.getFromUserName(), CommonApi.WEI_XIN_ACCOUNT, content);
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processUnsubscribeEvent(com.aiisen.weixin.message.receive
	 * .ReceiveEventMsg)
	 */
	@Override
	public String processUnsubscribeEvent(ReceiveEventMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processVideoMsg(com.aiisen.weixin.message.receive.
	 * ReceiveVideoMsg)
	 */
	@Override
	public String processVideoMsg(ReceiveVideoMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processViewEvent(com.aiisen.weixin.message.receive.
	 * ReceiveEventMsg)
	 */
	@Override
	public String processViewEvent(ReceiveEventMsg arg0) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.aiisen.weixin.handler.WeiXinMessageHandler#processVoiceMsg(com.aiisen.weixin.message.receive.
	 * ReceiveVoiceMsg)
	 */
	@Override
	public String processVoiceMsg(ReceiveVoiceMsg arg0) {
		return null;
	}

}
