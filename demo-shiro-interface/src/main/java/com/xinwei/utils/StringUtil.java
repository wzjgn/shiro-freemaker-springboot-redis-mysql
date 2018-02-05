package com.xinwei.utils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class StringUtil {
	
	/**
	 * Description: 将16进制字节数组转换为字符  每个字节第一位为0 如：08转为8
	 * @Version1.0 2012-5-25 下午06:19:53 yangll created
	 * @param bytes
	 * @return
	 */
	public static String toHexStringNum(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * Description: 将16进制字节数组转换为字符 每个字节第一位不为0 如：1A转为1A
	 * @Version1.0 2011-4-17 上午10:22:14 laojiang created
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * Description: 将字节转换成16进制字符
	 * @Version1.0 2011-4-17 上午10:22:25 laojiang created
	 * @param b
	 * @return
	 */
	public static String byteToString(byte b) {
		byte high, low;
		byte maskHigh = (byte) 0xf0;
		byte maskLow = 0x0f;

		high = (byte) ((b & maskHigh) >> 4);
		low = (byte) (b & maskLow);

		StringBuffer buf = new StringBuffer();
		buf.append(findHex(high));
		buf.append(findHex(low));

		return buf.toString();
	}

	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;

		if ((0 <= t) && (t <= 9)) {
			return (char) (t + '0');
		}

		return (char) (t - 10 + 'A');
	}
	/**
	 * Description: 将字节数组转换成字符串
	 * @Version1.0 2011-9-22 上午11:01:56 tang created
	 * @param bytes
	 * @return
	 */
	public static char[] v2BytesToChars(byte[] bytes) {
		Charset cs = Charset.forName("UTF-8");
		ByteBuffer bb = ByteBuffer.allocate(bytes.length);
		bb.put(bytes);
		bb.flip();
		CharBuffer cb = cs.decode(bb);

		return cb.array();
	}

}
