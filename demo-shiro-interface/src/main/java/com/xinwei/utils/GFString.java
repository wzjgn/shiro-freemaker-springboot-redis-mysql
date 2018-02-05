package com.xinwei.utils;

public class GFString {
	
	/**
	 * 在str每个字符之间添加cha
	 * @param str 准备拆分的字符串
	 * @param cha 添加的字符
	 * @return
	 */
	public static String supplement(String str,char cha){
		StringBuilder sb = new StringBuilder(str);
		for(int i=0;i<str.length();i++){
			sb.insert(i*2, cha);
		}
		return sb.toString();
	}

	/**
	 * Description: 把相临的两个字符对换，字符串长度为奇数时最后加“F”
	 * @Version1.0 2011-4-17 上午10:20:33 laojiang created
	 * @param src 对换后的字符串
	 * @return
	 */
	public static String interChange(String src) {
		String result = null;
		if (src != null) {
			if (src.length() % 2 != 0) {
				src += "F";
			}
			src += "0";
			result = "";
			for (int i = 0; i < src.length() - 2; i += 2) {
				result += src.substring(i + 1, i + 2);
				result += src.substring(i, i + 1);
			}
		}
		return result;
	}

	/**
	 * Description: 
	 * @Version1.0 2011-4-17 上午10:20:41 laojiang created
	 * @param gbString
	 * @return
	 */
	public static String gb2unicode(String gbString) {
		String result = "";
		// GB2Uni nn = new GB2Uni();
		char[] c;
		int value;
		if (gbString == null) {
			return null;
		}
		// if (gbString.getBytes().length == gbString.length())
		// return gbString;
		String temp = null;
		c = new char[gbString.length()];
		StringBuffer sb = new StringBuffer(gbString);
		sb.getChars(0, sb.length(), c, 0);
		for (int i = 0; i < c.length; i++) {
			value = c[i];
			// System.out.println("[" + i + "]:" +value );
			// System.out.println("hex:"+Integer.toHexString(value));
			temp = Integer.toHexString(value);
			result += fill(temp, 4);
			//-------------------------------------------------------------------------
		}
		return result.toUpperCase();
	}

	public static String fill(String temp, int totalbit) {
		String t = temp;
		while (t.length() < totalbit) {
			t = "0" + t;
		}
		return t;

	}

	/**
	 * Description:  对7-BIT编码进行解码
	 * @Version1.0 2011-4-17 上午10:20:52 laojiang created
	 * @param src 十六进制的字符串，且为偶数个
	 * @return 源字符串
	 */
	public static String decode7bit(String src) {
		String result = null;
		int[] b;
		String temp = null;
		byte srcAscii;
		byte left = 0;

		if (src != null && src.length() % 2 == 0) {
			result = "";
			b = new int[src.length() / 2];
			temp = src + "0";
			for (int i = 0, j = 0, k = 0; i < temp.length() - 2; i += 2, j++) {
				b[j] = Integer.parseInt(temp.substring(i, i + 2), 16);

				k = j % 7;
				srcAscii = (byte) (((b[j] << k) & 0x7F) | left);
				result += (char) srcAscii;
				left = (byte) (b[j] >>> (7 - k));
				if (k == 6) {
					result += (char) left;
					left = 0;
				}
				if (j == src.length() / 2) {
					result += (char) left;
				}
			}
		}
		return result;
	}

	public static String decode8bit(String src) {
		String temp = src;
		String result = "";
		for (int i = 0; i < src.length() - 2; i += 2) {
			result += (char) Integer.parseInt(temp.substring(i, i + 2), 16);
		}
		return result;
	}

	/**
	 * Description: 把UNICODE编码的字符串转化成汉字编码的字符串
	 * @Version1.0 2011-4-17 上午10:21:17 laojiang created
	 * @param hexString 转化后的字符串
	 * @return
	 */
	public static String unicode2gb(String hexString) {
		StringBuffer sb = new StringBuffer();
		if (hexString == null) {
			return null;
		}
		for (int i = 0; i + 4 <= hexString.length(); i = i + 4) {
			try {
				int j = Integer.parseInt(hexString.substring(i, i + 4), 16);
				sb.append((char) j);
			} catch (NumberFormatException e) {
				return hexString;
			}
		}
		return sb.toString();
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

	public static String byte2hex(byte b) {
		String hs = "";
		String stmp = "";

		{
			stmp = (java.lang.Integer.toHexString(b & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			// if (n<b.length-1)  hs=hs+":";
		}
		return hs.toUpperCase();
	}

	/**
	 * Description: 将16进制字符串转换成字节码
	 * @Version1.0 2011-4-17 上午10:21:40 laojiang created
	 * @param hex
	 * @return
	 */
	public static byte[] hex2byte(String hex) {
		byte[] bts = new byte[hex.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bts;
	}

	// TODO:temp
	public static String encode7bit(String src) {
		String result = null;
		String hex = null;
		byte value;
		if (src != null && src.length() == src.getBytes().length && src.length() == 1) {
			result = GFString.byte2hex(src.getBytes());
		} else if (src != null && src.length() == src.getBytes().length) {
			result = "";
			byte left = 0;
			byte[] b = src.getBytes();
			for (int i = 0, j = 0; i < b.length; i++) {
				j = i & 7;
				if (j == 0) {
					left = b[i];
				} else {
					value = (byte) ((b[i] << (8 - j)) | left);
					left = (byte) (b[i] >> j);
					hex = GFString.byte2hex(value);
					result += hex;
					if (i == b.length - 1) {
						result += GFString.byte2hex(left);
					}
				}
			}
			result = result.toUpperCase();
		}
		return result;
	}

	/**
	 * Description: 获得字符串的BCD码
	 * @Version1.0 2011-4-17 上午10:21:54 laojiang created
	 * @param asc
	 * @return
	 */
	public static byte[] str2Bcd(String asc) {
		int len = asc.length();
		int mod = len % 2;

		if (mod != 0) {
			asc = "0" + asc;
			len = asc.length();
		}

		byte abt[] = new byte[len];
		if (len >= 2) {
			len = len / 2;
		}

		byte bbt[] = new byte[len];
		abt = asc.getBytes();
		int j, k;

		for (int p = 0; p < asc.length() / 2; p++) {
			if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
				j = abt[2 * p] - '0';
			} else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
				j = abt[2 * p] - 'a' + 0x0a;
			} else {
				j = abt[2 * p] - 'A' + 0x0a;
			}

			if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
				k = abt[2 * p + 1] - '0';
			} else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
				k = abt[2 * p + 1] - 'a' + 0x0a;
			} else {
				k = abt[2 * p + 1] - 'A' + 0x0a;
			}

			int a = (j << 4) + k;
			byte b = (byte) a;
			bbt[p] = b;
		}
		return bbt;
	}
}
