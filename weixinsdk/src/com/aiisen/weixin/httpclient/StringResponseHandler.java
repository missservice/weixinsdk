/* Copyright(c)2012 www.aiisen.com
 * Email:admin@aiisen.com
 * 
 */

package com.aiisen.weixin.httpclient;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 * <p>
 * </p>
 * 
 * @author Wu,Yang
 * @date 2014年4月18日 下午9:07:23
 */
public class StringResponseHandler implements ResponseHandler<String> {

	@Override
	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			return entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
		} else {
			throw new ClientProtocolException("Unexpected response status: " + status);
		}
	}

}
