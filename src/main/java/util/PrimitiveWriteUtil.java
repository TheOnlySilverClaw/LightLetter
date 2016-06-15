package util;

public class PrimitiveWriteUtil {

	public static void write(short s, byte[] target, int pos) {
		target[pos] = (byte) ((s >>>  8) & 0xFF);
		target[pos + 1] = (byte) (s & 0xFF);
	}

	public static void write(int i, byte[] target, int pos) {
		target[pos] =		(byte) ((i >>> 24) & 0xFF);
		target[pos + 1] = 	(byte) ((i >>> 16) & 0xFF);
		target[pos + 2] = 	(byte) ((i >>>  8) & 0xFF);
		target[pos + 3] = 	(byte) ((i >>>  0) & 0xFF);
	}
	
	public static void write(float f, byte[] target, int pos) {
		write(Float.floatToIntBits(f), target, pos);
	}
}
