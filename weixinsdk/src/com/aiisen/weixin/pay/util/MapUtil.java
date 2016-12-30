package com.aiisen.weixin.pay.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class MapUtil {

	/**
	 * Map key 排序
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, String> order(Map<String, String> map) {
		HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());

		Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});

		for (int i = 0; i < infoIds.size(); i++) {
			Map.Entry<String, String> item = infoIds.get(i);
			tempMap.put(item.getKey(), item.getValue());
		}
		return tempMap;
	}

	/**
	 * 转换对象为map
	 * 
	 * @param object
	 * @param ignore
	 * @return
	 */
	public static Map<String, String> objectToMap(Object object, String... ignore) {
		Map<String, String> tempMap = new LinkedHashMap<String, String>();
		for (Field f : object.getClass().getDeclaredFields()) {
			if (!f.isAccessible()) {
				f.setAccessible(true);
			}
			boolean ig = false;
			if ((ignore != null) && (ignore.length > 0)) {
				for (String i : ignore) {
					if (i.equals(f.getName())) {
						ig = true;
						break;
					}
				}
			}
			if (ig) {
				continue;
			} else {
				Object o = null;
				try {
					o = f.get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				tempMap.put(f.getName(), o == null ? "" : o.toString());
			}
		}
		return tempMap;
	}

	/**
	 * url 参数串连
	 * 
	 * @param map
	 * @param keyLower
	 * @param valueUrlencode
	 * @return
	 */
	public static String mapJoin(Map<String, String> map, boolean keyLower, boolean valueUrlencode) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String key : map.keySet()) {
			if ((map.get(key) != null) && !"".equals(map.get(key))) {
				try {
					String temp = (key.endsWith("_") && (key.length() > 1)) ? key.substring(0, key.length() - 1) : key;
					stringBuilder
							.append(keyLower ? temp.toLowerCase() : temp)
							.append("=")
							.append(valueUrlencode ? URLEncoder.encode(map.get(key), "utf-8").replace("+", "%20") : map
									.get(key)).append("&");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

	public static Map<String, String> xmlToMap(String xml) {
		Map<String, String> map = new HashMap<String, String>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (DocumentException e) {
			return map;
		}
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			System.out.println(e.getName() + "=" + e.getText());
			// List list = e.elements();
			// if (list.size() > 0) {
			// map.put(e.getName(), Dom2Map(e));
			// } else {
			map.put(e.getName(), e.getText());
			// }
		}
		return map;
	}
	// public static Map Dom2Map(Element e) {
	// Map map = new HashMap();
	// List list = e.elements();
	// if (list.size() > 0) {
	// for (int i = 0; i < list.size(); i++) {
	// Element iter = (Element) list.get(i);
	// List mapList = new ArrayList();
	//
	// if (iter.elements().size() > 0) {
	// Map m = Dom2Map(iter);
	// if (map.get(iter.getName()) != null) {
	// Object obj = map.get(iter.getName());
	// if (!obj.getClass().getName().equals("java.util.ArrayList")) {
	// mapList = new ArrayList();
	// mapList.add(obj);
	// mapList.add(m);
	// }
	// if (obj.getClass().getName().equals("java.util.ArrayList")) {
	// mapList = (List) obj;
	// mapList.add(m);
	// }
	// map.put(iter.getName(), mapList);
	// } else {
	// map.put(iter.getName(), m);
	// }
	// } else {
	// if (map.get(iter.getName()) != null) {
	// Object obj = map.get(iter.getName());
	// if (!obj.getClass().getName().equals("java.util.ArrayList")) {
	// mapList = new ArrayList();
	// mapList.add(obj);
	// mapList.add(iter.getText());
	// }
	// if (obj.getClass().getName().equals("java.util.ArrayList")) {
	// mapList = (List) obj;
	// mapList.add(iter.getText());
	// }
	// map.put(iter.getName(), mapList);
	// } else {
	// map.put(iter.getName(), iter.getText());
	// }
	// }
	// }
	// } else {
	// map.put(e.getName(), e.getText());
	// }
	// return map;
	// }

	/**
	 * 简单 xml 转换为 Map
	 * 
	 * @param reader
	 * @return
	 */
	// public static Map<String, String> xmlToMap(String xml) {
	// try {
	// DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	// Document document = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
	// Element element = document.getDocumentElement();
	// NodeList nodeList = element.getChildNodes();
	// Map<String, String> map = new LinkedHashMap<String, String>();
	// for (int i = 0; i < nodeList.getLength(); i++) {
	// Element e = (Element) nodeList.item(i);
	// map.put(e.getNodeName(), e.getTextContent());
	// }
	// return map;
	// } catch (DOMException e) {
	// e.printStackTrace();
	// } catch (ParserConfigurationException e) {
	// e.printStackTrace();
	// } catch (SAXException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

}
