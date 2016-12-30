# weixinsdk 包含功能：微信登陆，菜单管理，消息模板，微信支付（js支付和扫码支付）
1、可以直接下载weixin_sdk.jar
2、消息回调，如果需要用到自定义菜单或者消息的话	
<!-- weixin BEGIN -->
	<servlet>
		<description>微信服务器消息处理,由微信服务器回调</description>
		<servlet-name>weixinApi</servlet-name>
		<servlet-class>com.aiisen.weixin.ApiServlet</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>weixinApi</servlet-name>
		<url-pattern>/weixin/api.html</url-pattern>
	</servlet-mapping>
	<!-- weixin END -->	
3、增加pay.properties,放在src目录下，配置文件内容：
#=====微信支付============
WEI_XIN_ACCOUNT=gh_xxxx
APP_ID=wxxxxx
APPSECRET=46xxxx
TOKEN=xxxx

#微信支付
#商户号
MCH_ID=xxxx
#到商户后台设置
API_KEY=xxxxx888888888888888888888888888

#到商户后台设置 FORAPP
APP_ID_APP=wxxxxx
MCH_ID_APP=xxxx
API_KEY_APP=xxxxx888888888888888888888888888

4、使用方法：
  点击微信登陆是跳转到：
	public ActionForward weixin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");

		UserInfo ui = super.getUserInfoFromSession(request);
		String return_url = request.getParameter("return_url");// 返回URL
		if (null != ui && StringUtils.isNotBlank(return_url)) {// 如果session存在，并且return_url不为空，直接跳转到return_url
			response.sendRedirect(return_url);
			return null;
		}

		StringBuilder link = new StringBuilder();
		String scope = "snsapi_userinfo";
		String state = "";

		StringBuffer server = new StringBuffer();
		server.append(request.getHeader("host")).append(request.getContextPath());
		request.setAttribute("server_domain", server.toString());
		String redirectUri = "http://".concat(server.toString()).concat(
				"/weixin/WeixinLogin.do?method=afterLoginWeixin");

		if (StringUtils.isNotBlank(return_url)) {
			redirectUri += "&return_url=" + URLEncoder.encode(return_url, "UTF-8");
		}
		// String refererUrl = request.getHeader("Referer");
		// logger.info("==refererUrl:{}", refererUrl);
		link.append(ApiUrlConstants.OAUTH2_LINK + "?appid=" + CommonApi.APP_ID)
				.append("&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8")).append("&response_type=code")
				.append("&scope=" + scope).append("&state=" + state).append("#wechat_redirect");

		response.sendRedirect(link.toString());

		logger.warn("==weixin sendRedirect :{}", link.toString());

		return null;
	}
  
  //微信回调：
  
	public ActionForward afterLoginWeixin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");

		String code = request.getParameter("code");
		String state = request.getParameter("state");

		logger.info("code:" + code + "--state:" + state);
		if (StringUtils.isBlank(code)) {
			String msg = "没有获取到响应参数code";
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		Oauth2AccessToken token = Oauth2Api.getOauth2AccessToken(code);

		// 这个方法能获取到微信头像和昵称，但是获取不到subscribe
		Oauth2UserInfo oauth2UserInfoWithNicknameAndHeadimgurl = Oauth2Api.oauth2UserInfo(token.getAccess_token(),
				token.getOpenid(), "zh_CN");

		// logger.info("oauth2AccessToken:" + token);
		// logger.warn("==token.getAccess_token():" + token.getAccess_token());
		// logger.warn("==token.getOpenid():" + token.getOpenid());

		// 注意：如果要获取是否关注微信公众号和UnionID信息，需要用这个方法获取
		Oauth2AccessToken accessToken = Oauth2Api.getAccessToken();// 获取普通的accessToken
		// 这个方法不能获取到微信头像和昵称，能获取到subscribe
		Oauth2UserInfo oauth2UserInfo = Oauth2Api.oauth2UserInfoUnionID(accessToken.getAccess_token(),
				token.getOpenid(), "zh_CN");

		if ((null == oauth2UserInfoWithNicknameAndHeadimgurl)
				|| (null == oauth2UserInfoWithNicknameAndHeadimgurl.getOpenid())) {
			String msg = "user为空或者Openid为空";
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		// Oauth2UserInfo oauth2UserInfo = new Oauth2UserInfo();
		// oauth2UserInfo.setOpenid("openid_111111");
		// oauth2UserInfo.setNickname("aiisen");
		// oauth2UserInfo.setHeadimgurl("http://aasdfs.com/a.jpg");
		// logger.info("oauth2UserInfo:" + oauth2UserInfo);

		// TODO 在这里做绑定帐号操作
		String appid_weixin = oauth2UserInfoWithNicknameAndHeadimgurl.getOpenid();
		if (StringUtils.isBlank(appid_weixin)) {
			String msg = "appid_weixin为空";
			super.renderJavaScript(response, "alert('" + msg + "');history.back();");
			return null;
		}

		String appid_weixin_unionid = oauth2UserInfoWithNicknameAndHeadimgurl.getUnionid();
		// String subscribe = oauth2UserInfoWithNicknameAndHeadimgurl.getSubscribe();// 是否关注 0 未关注 1 已关注
		String subscribe = oauth2UserInfo.getSubscribe();// 是否关注 0 未关注 1 已关注
		// 参数说明：http://mp.weixin.qq.com/wiki/14/bb5031008f1494a59c6f71fa0f319c66.html
		String nickname = oauth2UserInfoWithNicknameAndHeadimgurl.getNickname();
		String headimgurl = oauth2UserInfoWithNicknameAndHeadimgurl.getHeadimgurl();
		logger.warn("=afterLoginWeixin=appid_weixin:{} -- appid_weixin_unionid:{}", appid_weixin, appid_weixin_unionid);
		logger.warn("=afterLoginWeixin=oauth2UserInfo.subscribe:" + subscribe);
	  //这里写自己的逻辑
    return new ActionForward("/xxxx/reg.jsp");

	}
