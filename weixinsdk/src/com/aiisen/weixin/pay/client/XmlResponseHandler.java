package com.aiisen.weixin.pay.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aiisen.weixin.pay.util.XMLConverUtil;

public class XmlResponseHandler {
	private final static Logger log = LoggerFactory.getLogger(XmlResponseHandler.class);

	private static Map<String, ResponseHandler<?>> map = new HashMap<String, ResponseHandler<?>>();

	@SuppressWarnings("unchecked")
	public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz) {
		if (map.containsKey(clazz.getName())) {
			return (ResponseHandler<T>) map.get(clazz.getName());
		} else {
			ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
				@Override
				public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if ((status >= 200) && (status < 300)) {
						HttpEntity entity = response.getEntity();
						String str = EntityUtils.toString(entity);
						log.warn("==XmlResponseHandler=prepayReturn1:{}", str);
						String stren = new String(str.getBytes("iso-8859-1"), "utf-8");
						log.warn("==XmlResponseHandler=prepayReturn2:{}", stren);
						return XMLConverUtil.convertToObject(clazz, stren);
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}

				}
			};
			map.put(clazz.getName(), responseHandler);
			return responseHandler;
		}
	}
}
