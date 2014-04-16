package demo.vicshady.coreshopping.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64 {

	public static final int DECODE = 0;
	public static final int DONT_GUNZIP = 4;
	public static final int DO_BREAK_LINES = 8;
	public static final int ENCODE = 1;
	public static final int GZIP = 2;
	public static final int NO_OPTIONS = 0;
	public static final int ORDERED = 32;
	public static final int URL_SAFE = 16;


	public static String encodeBytes(byte[] paramArrayOfByte) {
		Object localObject = null;
		try {
			String str = encodeBytes(paramArrayOfByte, 0,
					paramArrayOfByte.length, 0);
			localObject = str;
		} catch (IOException localIOException) {
		}
		return (String) localObject;
	}

	public static String encodeBytes(byte[] paramArrayOfByte, int paramInt)
			throws IOException {
		return encodeBytes(paramArrayOfByte, 0, paramArrayOfByte.length,
				paramInt);
	}

	public static String encodeBytes(byte[] paramArrayOfByte, int paramInt1,
			int paramInt2) {
		Object localObject = null;
		try {
			String str = encodeBytes(paramArrayOfByte, paramInt1, paramInt2, 0);
			localObject = str;
		} catch (IOException localIOException) {
		}
		return (String) localObject;
	}

	public static String encodeBytes(byte[] paramArrayOfByte, int paramInt1,
			int paramInt2, int paramInt3) throws IOException {
		byte[] arrayOfByte = encodeBytesToBytes(paramArrayOfByte, paramInt1,
				paramInt2, paramInt3);
		try {
			String str = new String(arrayOfByte, "US-ASCII");
			return str;
		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
			String str;
			while (true)
				str = new String(arrayOfByte);
		}
	}

	public static byte[] encodeBytesToBytes(byte[] paramArrayOfByte) {
		Object localObject = null;
		try {
			byte[] arrayOfByte = encodeBytesToBytes(paramArrayOfByte, 0,
					paramArrayOfByte.length, 0);
			localObject = arrayOfByte;
			
		} catch (IOException localIOException) {
		}
		return (byte[]) localObject;
	}

	public static byte[] encodeBytesToBytes(byte[] paramArrayOfByte,
			int paramInt1, int paramInt2, int paramInt3) throws IOException {
				return paramArrayOfByte;
	}
}
