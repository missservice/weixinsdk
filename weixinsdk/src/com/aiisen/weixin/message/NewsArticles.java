/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.message;

/**
 * <p>
 * 新闻消息实体 Articles
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 上午10:40:24
 */
public class NewsArticles {

	private String media_id;

	private String title;

	private String author;

	private String description;

	private String content;

	private String digest;

	private String show_cover_pic;

	private String content_source_url;

	private String picUrl;

	private String url;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public NewsArticles() {
	}

	/**
	 * @param title 标题
	 * @param description 描述
	 */
	public NewsArticles(String title, String description) {
		this.title = title;
		this.description = description;
	}

	/**
	 * @param title 标题
	 * @param description 描述
	 * @param url 点击后跳转的链接
	 */
	public NewsArticles(String title, String description, String url) {
		this(title, description);
		this.url = url;
	}

	/**
	 * 标题,可选.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题,可选.
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 描述,可选.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述,可选.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80,可选.
	 * 
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80,可选.
	 * 
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 点击后跳转的链接,可选.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 点击后跳转的链接,可选.
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
}
