/* Copyright(c)2012 www.aiisen.com
 * 
 * Email:admin@aiisen.com
 * 
 * 
 */

package com.aiisen.weixin.message.receive;

import java.io.Serializable;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午3:17:54
 */
public class ReceiveVoiceMsg implements Serializable {

	private static final long serialVersionUID = -1198081777025625427L;

	private String msgId;

	private String msgType;

	private String toUserName;

	private String fromUserName;

	private String format;

	private String mediaId;

	private String recognition;

	private String createTime;

	public ReceiveVoiceMsg() {
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
