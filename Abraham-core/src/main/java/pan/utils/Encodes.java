/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package pan.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 封装各种格式的编码解码工具类.
 * 
 * 1.Commons-Codec的 hex/base64 编码
 * 2.自制的base62 编码
 * 3.Commons-Lang的xml/html escape
 * 4.JDK提供的URLEncoder
 * 
 * @author calvin
 */
public class Encodes {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * The format of string for display is like "0F:AB:...:7F", multiply lines, and 16 bytes each line.
	 * @param hexStringWithoutFormat
	 * @return
	 */
	public static String hexString2Display(String hexStringWithoutFormat){
		char[] hexCharArray = hexStringWithoutFormat.toCharArray();
		StringBuffer hexString2Dispay = new StringBuffer();
		int idx = 0;
		for (int i = 1; i <= hexCharArray.length / 2; i++){
			hexString2Dispay.append(hexCharArray, idx, 2);
			idx += 2;
			if (i % 16 == 0){
				hexString2Dispay.append("\n");
			}else if (idx < hexCharArray.length){
				hexString2Dispay.append(':');
			}
		}

		return hexString2Dispay.toString();
	}

	/**
	 * Hex解码.
	 * @throws DecoderException 
	 */
	public static byte[] decodeHex(String input) throws DecoderException {
		return Hex.decodeHex(input.toCharArray());		
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[(input[i] & 0xFF) % BASE62.length];
		}
		return new String(chars);
	}

	/**
	 * Html 转码.
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml4(html);
	}

	/**
	 * Html 解码.
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml4(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlEncode(String part) throws UnsupportedEncodingException {
		return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlDecode(String part) throws UnsupportedEncodingException {
		return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
	}
}