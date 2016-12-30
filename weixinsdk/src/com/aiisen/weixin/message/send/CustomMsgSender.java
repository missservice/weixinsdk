/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.message.send;

import com.aiisen.weixin.ApiUrlConstants;
import com.aiisen.weixin.CommonApi;
import com.aiisen.weixin.GlobalReturnCode;
import com.aiisen.weixin.httpclient.HttpClientUtils;
import com.aiisen.weixin.message.NewsArticles;
import com.aiisen.weixin.type.MsgType;
import com.aiisen.weixin.utils.JsonUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 发送客服消息
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午2:51:10
 */
public class CustomMsgSender {

	private CustomMsgSender() {
	}

	/**
	 * 发送文本消息
	 * 
	 * @param toUser 普通用户openid
	 * @param content 文本消息内容,支持换行符
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendTextMsg(String toUser, String content) {
		String msgType = MsgType.text.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("content", content);

		req.put("text", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送图片消息
	 * 
	 * @param toUser 普通用户openid
	 * @param mediaId 发送的图片的媒体ID
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendImageMsg(String toUser, String mediaId) {
		String msgType = MsgType.image.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);

		req.put("image", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送语音消息
	 * 
	 * @param toUser
	 * @param mediaId 发送的语音的媒体ID
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendVoiceMsg(String toUser, String mediaId) {
		String msgType = MsgType.voice.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);

		req.put("voice", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送视频消息
	 * 
	 * @param toUser
	 * @param mediaId 发送的视频的媒体ID
	 * @param title 视频消息的标题,可选.
	 * @param description 视频消息的描述,可选.
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendVideoMsg(String toUser, String mediaId, String title, String description) {
		String msgType = MsgType.video.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("media_id", mediaId);
		sub.put("title", title);
		sub.put("description", description);

		req.put("video", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送音乐消息
	 * 
	 * @param toUser
	 * @param title 音乐标题,可选.
	 * @param description 音乐描述,可选.
	 * @param musicUrl 音乐链接
	 * @param hqMusicUrl 高品质音乐链接，wifi环境优先使用该链接播放音乐
	 * @param thumbMediaId 缩略图的媒体ID
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendMusicMsg(String toUser, String title, String description, String musicUrl,
			String hqMusicUrl, String thumbMediaId) {
		String msgType = MsgType.music.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("title", title);
		sub.put("description", description);
		sub.put("musicurl", musicUrl);
		sub.put("hqmusicurl", hqMusicUrl);
		sub.put("description", description);
		sub.put("thumb_media_id", thumbMediaId);

		req.put("music", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 发送图文消息
	 * 
	 * @param toUser
	 * @param articles NewsArticles 一个或多个,图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应。
	 * @return GlobalReturnCode
	 */
	public static GlobalReturnCode sendNewsMsg(String toUser, NewsArticles... articles) {
		if (articles == null) {
			return new GlobalReturnCode().systemError();
		}

		String msgType = MsgType.news.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONArray array = new JSONArray();

		for (NewsArticles article : articles) {
			JSONObject thr = new JSONObject();

			thr.put("Title", article.getTitle());
			thr.put("Description", article.getDescription());
			thr.put("Url", article.getUrl());
			thr.put("PicUrl", article.getPicUrl());

			array.add(thr);
		}

		JSONObject sub = new JSONObject();
		sub.put("articles", array);

		req.put("news", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/*************** 高级群发 ***************/
	public static GlobalReturnCode sendTextMsgGroup(String toUser, String content) {
		String msgType = MsgType.text.toString();

		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("msgtype", msgType);

		JSONObject sub = new JSONObject();
		sub.put("content", content);

		req.put("text", sub);

		String url = ApiUrlConstants.CUSTOM_SEND + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());

		return JsonUtils.buildSendResult(resp);
	}

	/**
	 * 高级接口 群发送图文消息
	 * 
	 * @param toUser
	 * @param articles NewsArticles 一个或多个,图文消息条数限制在10条以内，注意，如果图文数超过10，则将会无响应。
	 * @return GlobalReturnCode
	 */
	public static JSONObject sendNewsMsgGroup(String[] toUsers, NewsArticles article) {

		String msgType = "mpnews";

		JSONObject req = new JSONObject();
		req.put("touser", toUsers);
		req.put("msgtype", msgType);

		JSONObject thr = new JSONObject();
		thr.put("media_id", article.getMedia_id());
		thr.put("title", article.getTitle());
		thr.put("description", article.getDescription());
		thr.put("url", article.getUrl());

		req.put("mpnews", thr);
		// System.out.println("req:" + req.toString());
		String url = ApiUrlConstants.CUSTOM_SEND_GROUP + "?access_token=" + CommonApi.getAccessToken();

		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		// System.out.println("resp:" + resp.toString());
		return resp;
	}

	/**
	 * 模板消息
	 * 
	 * @param toUser
	 * @param template_id 模板消息ID
	 * @param data JSONObject http://mp.weixin.qq.com/wiki/17/304c1885ea66dbedf7dc170d84999a9d.html
	 * @return GlobalReturnCode
	 */
	public static JSONObject sendTemplateMsgGroup(String toUser, String template_id, String url_template,
			JSONObject data) {
		JSONObject req = new JSONObject();
		req.put("touser", toUser);
		req.put("topcolor", "#FF0000");
		req.put("template_id", template_id);
		req.put("url", url_template);
		req.put("data", data);
		String url = ApiUrlConstants.TEMPLATE_SEND + "?access_token=" + CommonApi.getAccessToken();
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		return resp;
	}
}
