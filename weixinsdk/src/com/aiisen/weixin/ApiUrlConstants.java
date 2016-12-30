/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午4:09:44
 */
public final class ApiUrlConstants {

	public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

	public static final String CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

	public static final String CUSTOM_SEND_GROUP = "https://api.weixin.qq.com/cgi-bin/message/mass/send";

	public static final String TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send";// 模版消息

	public static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";

	public static final String GROUPS_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create";

	public static final String GROUPS_GET = "https://api.weixin.qq.com/cgi-bin/groups/get";

	public static final String GROUPS_GET_ID = "https://api.weixin.qq.com/cgi-bin/groups/getid";

	public static final String GROUPS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/update";

	public static final String MEMBERS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/members/update";

	public static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info";

	public static final String USER_GET = "https://api.weixin.qq.com/cgi-bin/user/get";

	public static final String MEDIA_UPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload";

	public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";

	public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";

	public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete";

	public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";

	public static final String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

	public static final String OAUTH2_LINK = "https://open.weixin.qq.com/connect/oauth2/authorize";

	public static final String OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public static final String OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	// http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html
	public static final String OAUTH2_USERINFO = "https://api.weixin.qq.com/sns/userinfo";

	// http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
	// https://api.weixin.qq.com/cgi-bin/user/info?access_token=OezXcEiiBSKSxW0eoylIeD_aeO0R1SQZFLdoCXz3iOA72N9VYKgCv&openid=oX5SRs2TL3-oH5NJznhjjNYEdVbI&lang=zh_CN
	public static final String OAUTH2_USERINFO_UNIONID = "https://api.weixin.qq.com/cgi-bin/user/info";

	public static final String UPLOAD_IMAGE_URL = "http://file.api.weixin.qq.com/cgi-bin/media/upload";// 上传多媒体文件

	public static final String JS_API_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";// js

}
