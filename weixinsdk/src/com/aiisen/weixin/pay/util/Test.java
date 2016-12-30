package com.aiisen.weixin.pay.util;

import java.io.IOException;
import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// String xml = FileUtils.readFileToString(new File("e:\\xml.txt"));

		// System.out.println(xml);

		// Map<String, String> map = MapUtil.xmlToMap(xml);
		// Map<String, String> map = MapUtil.xmlToMap(xml);
		BigDecimal allmoney = new BigDecimal("5.10");
		System.out.println(PayUtil.yuanToFee(allmoney.toString()));
		// System.out.println(map.get("sign"));

	}
}
