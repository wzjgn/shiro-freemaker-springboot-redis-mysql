package com.xinwei.utils;

import java.util.BitSet;

public class ArraysUtil {

	/**
	 * Description: 截取byte数组中的一段作为结果返回
	 * @Version1.0 2011-4-17 上午10:18:39 laojiang created
	 * @param a   byte数组
	 * @param begin_index  起始下标
	 * @param len  截取的长度
	 * @return  新的数组
	 */
	public static byte[] subset(byte[] a, int begin_index, int len) {
		if (a == null)
			return null;
		if (begin_index < 0 || len <= 0 || begin_index >= a.length || len > a.length - begin_index)
			throw new IllegalArgumentException("Wrong arguments: array length:" + a.length + ", begin index:"
					+ begin_index + ", subset length:" + len);
		byte[] b = new byte[len];
		for (int i = 0; i < len; i++) {
			b[i] = a[i + begin_index];
		}
		return b;
	}

	/**
	 * Description:  将byte数组的某一部分转化为一个整数
	 * @Version1.0 2011-4-17 上午10:19:03 laojiang created
	 * @param b  数组
	 * @param begin_index  开始下标
	 * @param len 截取长度
	 * @return
	 */
	public static int toInt(byte[] b, int begin_index, int len) {
		byte[] a = subset(b, begin_index, len);
		int n = 0;
		if (a == null || a.length == 0)
			return n;
		for (int i = 0; i < a.length; i++) {
			n += ((a[i] & 0xff) << ((a.length - i - 1) * 8));
		}
		return n;
	}

	/**
	 * Description: 将一个byte追加到另一个byte数组末尾
	 * @Version1.0 2011-4-17 上午10:19:28 laojiang created
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static byte[] concat(byte[] b1, byte b2) {
		return concat(b1, new byte[] { b2 });
	}

	/**
	 * Description: 将两个byte数组连接在一起并返回
	 * @Version1.0 2011-4-17 上午10:19:36 laojiang created
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static byte[] concat(byte[] b1, byte[] b2) {
		if (b1 == null)
			return b2;
		if (b2 == null)
			return b1;
		byte[] b = new byte[b1.length + b2.length];
		for (int i = 0; i < b1.length; i++) {
			b[i] = b1[i];
		}
		for (int j = 0; j < b2.length; j++) {
			b[b1.length + j] = b2[j];
		}
		return b;
	}
	
	 //把一个20位长的字符串转成Long类型值
	    public static Long stringToLong(String value) {
	        if (value == null || "".equals(value)) {
	            return 0L;
	        }
	        return Long.parseLong(value);
	    }

	    /**
	     * 把一个Long类型的值转成一个20位的字符串。位数不够时，前面补0
	     * @param value
	     * @return
	     */
	    public static String longToString(Long value) {
	        if (value == null) {
	            return "00000000000000000000";
	        }
	        return String.format("%020d", value);
	    }

	/**
	 * Description: 将整数转为byte数组
	 * @Version1.0 2011-4-17 上午10:20:03 laojiang created
	 * @param num
	 * @param len
	 * @return
	 */
	public static byte[] int2bytes(int num, int len) {
		byte[] b = new byte[len];
		for (int i = 0; i < len; i++) {
			b[i] = (byte) (num >>> ((len - 1 - i) * 8));
		}
		return b;
	}

	/**
	 * Description: 将long转为byte数组
	 * @Version1.0 2011-9-16 下午14:33:49 tang created
	 * @param num
	 * @param len
	 * @return
	 */
	public static byte[] longToByte(long num, int len) {
		long temp = num;
		byte[] b = new byte[len];
		for (int i = b.length - 1; i >= 0; i--) {
			b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位       
			temp = temp >> 8; // 向右移8位  
		}
		return b;
	}
	

	public static long unsigned4BytesToInt(byte[] buf, int pos) {
		int firstByte = 0;
		int secondByte = 0;
		int thirdByte = 0;
		int fourthByte = 0;
		int index = pos;
		firstByte = (0x000000FF & ((int) buf[index]));
		secondByte = (0x000000FF & ((int) buf[index + 1]));
		thirdByte = (0x000000FF & ((int) buf[index + 2]));
		fourthByte = (0x000000FF & ((int) buf[index + 3]));
		index = index + 4;
		return ((long) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;
	}

	public static long bytes2long(byte[] b) {

		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 8; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	/**
	 * Description: byte数组转换成4字节的long型
	 * @Version1.0 2011-9-16 下午14:33:49 tang created
	 * @param num
	 * @param len
	 * @return
	 */
	public static long bytes2LongHandle(byte[] bytes) {
		BitSet bs0 = Byte2BitSet(bytes[0]);
		//System.out.println(bitset2Int(bs0));
		BitSet bs1 = Byte2BitSet(bytes[1]);
		//System.out.println(bitset2Int(bs1));
		BitSet bs2 = Byte2BitSet(bytes[2]);
		//System.out.println(bitset2Int(bs2));
		BitSet bs3 = Byte2BitSet(bytes[3]);
		//System.out.println(bitset2Int(bs3));
		long result = bitset2Int(bs0) * ((long) 256) * 256 * 256 + bitset2Int(bs1) * 256 * 256 + bitset2Int(bs2) * 256
				+ bitset2Int(bs3);
		return result;
	}

	public static BitSet Byte2BitSet(byte bytes) {
		BitSet bits = new BitSet(8);
		for (int i = 0; i < 8; i++) {
			if ((bytes & (1 << i)) > 0) {
				bits.set(i);
			}
		}
		return bits;
	}

	public static int bitset2Int(BitSet bs) {
		int intValue = 0;
		for (int j = 0; j < 32; j++)
			if (bs.get(j))
				intValue |= 1 << j;
		return intValue;
	}

    /**    
     * 高字节数组到short的转换
     *  @param b byte[]
     *  @return short    
     */
    public static short hBytesToShort(byte[] b) {
    	int s = 0;
    	if (b[0] >= 0) {
    		s = s + b[0];
    	} else {
    		s = s + 256 + b[0];
    	}
    	s = s * 256;
    	if (b[1] >= 0) {
    		s = s + b[1];
    	} else {
    		s = s + 256 + b[1];
    	}
    	short result = (short) s;
    	return result;
    }

}
