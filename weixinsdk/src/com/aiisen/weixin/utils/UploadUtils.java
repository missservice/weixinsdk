package com.aiisen.weixin.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aiisen.weixin.ApiUrlConstants;
import com.aiisen.weixin.CommonApi;
import com.aiisen.weixin.httpclient.HttpClientUtils;
import com.aiisen.weixin.message.NewsArticles;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebiz.webapp.web.struts.weixin.Config;

public class UploadUtils {

	public static JSONObject uploadNews(List<NewsArticles> articles) {

		JSONArray array = new JSONArray();

		for (NewsArticles article : articles) {
			JSONObject thr = new JSONObject();
			thr.put("thumb_media_id", article.getMedia_id());
			thr.put("title", article.getTitle());
			thr.put("author", article.getAuthor());
			thr.put("content_source_url", article.getContent_source_url());
			thr.put("content", article.getContent());
			thr.put("digest", article.getDigest());
			thr.put("show_cover_pic", article.getShow_cover_pic());

			array.add(thr);
		}

		JSONObject req = new JSONObject();
		req.put("articles", array);

		String url = ApiUrlConstants.UPLOAD_NEWS + "?access_token=" + CommonApi.getAccessToken();
		// System.out.println("req.toString():" + req.toString());
		JSONObject resp = HttpClientUtils.postJsonDataForJsonResult(url, req.toString());
		// System.out.println("resp.toString():" + resp.toString());

		return resp;
	}

	public static String uploadFileImage(File file) {
		return uploadFile("image", file);
	}

	public static String uploadFileVoice(File file) {
		return uploadFile("voice", file);
	}

	public static String uploadFileVideo(File file) {
		return uploadFile("video", file);
	}

	public static String uploadFileThumb(File file) {
		return uploadFile("thumb", file);
	}

	/**
	 * 上传多媒体文件
	 * 
	 * @param type 文件类型
	 * @type type:图片（image）: 1M，支持JPG格式
	 * @type type:语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 * @type type:视频（video）：10MB，支持MP4格式
	 * @type type:缩略图（thumb）：64KB，支持JPG格式
	 * @param file 文件对象
	 * @return
	 */
	public static String uploadFile(String type, File file) {
		String url = String.format("%s?access_token=%s&type=%s", ApiUrlConstants.UPLOAD_IMAGE_URL,
				CommonApi.getAccessToken(), type);
		String responseContent = HttpClientUtils.postWeiXinMedia(url, file);
		// System.out.println("responseContent:" + responseContent);
		JSONObject json = JSONObject.parseObject(responseContent);
		String result = "";
		if (json.get("errcode") == null) {
			// {"errcode":40004,"errmsg":"invalid media type"}
			// 上传成功 {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
			// {"type":"thumb","thumb_media_id":"V1m3VMoQAQkco5lQfZ2RPbno5yPMfHYXMg6ljYogBsrh4fnb6u_epuUzQO0CmN1D","created_at":1413962490}
			// type等于thumb时的返回结果和其他类型不一样
			if ("thumb".equals(type)) {
				// weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
				result = json.getString("thumb_media_id");
			} else {
				// weixinMedia.setMediaId(jsonObject.getString("media_id"));
				// weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
				result = json.getString("media_id");
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		Config.init();
		File file = new File("f:" + File.separator + "b.png");
		String m_id = UploadUtils.uploadFileImage(file);
		// String media_id = UploadUtils.uploadFileThumb(file);
		// String m_id4 = "mj00iiG9AwTwzvV1q2hw85MWdcwYPRdlSDE89qjABEdErcuOuE7RME0nbZX7BVUM";
		System.out.println("media_id:" + m_id);

		NewsArticles article = new NewsArticles();
		// article.setMedia_id(m_id);
		// article.setTitle("测试群发2014-10-23-03");
		// article.setAuthor("全民营销");
		// article.setContent_source_url("http://www.baidu.com");
		// article.setContent("<p>测试群发2014-10-23-03内容：</p><p><img src='http://www.qqya.com/userimg/5707/130311234433.png'></p>");
		// article.setDigest("测试群发2014-10-23-03简介");
		// article.setShow_cover_pic("0");
		List<NewsArticles> articles = new ArrayList<NewsArticles>();
		// articles.add(article);

		article = new NewsArticles();
		article.setMedia_id(m_id);
		article.setTitle("测试群发2014-10-23-04");
		article.setAuthor("全民营销");
		article.setContent_source_url("http://www.baidu.com");
		article.setContent("<p>测试群发2014-10-23-04内容：</p><p><img src='http://www.qqya.com/userimg/5707/130311235308.png'></p>");
		article.setDigest("测试群发2014-10-23-04简介");
		article.setShow_cover_pic("1");
		articles.add(article);

		JSONObject code = UploadUtils.uploadNews(articles);

		System.out.println("code:" + code.toString());
		// {"type":"news","media_id":"PNNBGMERd9hcUpF0ibFCERX9ClXqOTuWDPk7r6ATwqOmHn1IskeAe6YDOufpqhT8","created_at":1414035012}
		// code:{"type":"news","media_id":"8pY0oMAVHK_SJkqfgOl5rjRRgC8CCP9KfOhfj1SP8IOxPlNif0a90_AP4sMuElrG","created_at":1414035499}

		// sk5apEqHLp_BFYyDTx8A5dIX4LTpkm6gMMIvxc6lbgKPVgN1Yrf8FGDlYYuvGQOS
		// String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);// 获取token在微信接口之一中的方法获取token
		// if (accessToken != null)// token成功获取
		// {
		// System.out.println(accessToken);
		// File file = new File("f:" + File.separator + "2000.JPG"); // 获取本地文件
		// String id = uploadImage(UPLOAD_IMAGE_URL, accessToken, "image", file);// 上传文件
		// if (id != null) {
		// System.out.println(id);
		// }
		// }
	}

}
