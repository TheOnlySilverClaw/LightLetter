package byteutil;

public class PrimitiveWriteUtil {

	public static void write(short s, byte[] target) {
		write(s, target, 0);
	}

	public static void write(short s, byte[] target, int pos) {
		target[pos] = (byte) ((s >>>  8) & 0xFF);
		target[pos + 1] = (byte) (s & 0xFF);
	}

	public static void write(int i, byte[] target) {
		write(i, target, 0);
	}

	public static void write(int i, byte[] target, int pos) {
		target[pos] =		(byte) ((i >>> 24) & 0xFF);
		target[pos + 1] = 	(byte) ((i >>> 16) & 0xFF);
		target[pos + 2] = 	(byte) ((i >>>  8) & 0xFF);
		target[pos + 3] = 	(byte) ((i >>>  0) & 0xFF);
	}
	
	public static void write(float f, byte[] target) {
		write(f, target, 0);
	}

	public static void write(float f, byte[] target, int pos) {
		write(Float.floatToIntBits(f), target, pos);
	}
	
	public static void write(long l, byte[] target) {
		write(l, target, 0);
	}

	public static void write(long l, byte[] target, int pos) {
		target[pos] =		(byte) ((l >>> 56) & 0xFF);
		target[pos + 1] =	(byte) ((l >>> 48) & 0xFF);
		target[pos + 2] =	(byte) ((l >>> 40) & 0xFF);
		target[pos + 3] =	(byte) ((l >>> 32) & 0xFF);
		target[pos + 4] =	(byte) ((l >>> 24) & 0xFF);
		target[pos + 5] = 	(byte) ((l >>> 16) & 0xFF);
		target[pos + 6] = 	(byte) ((l >>>  8) & 0xFF);
		target[pos + 7] = 	(byte) ((l >>>  0) & 0xFF);
	}
	
	public static void write(double d, byte[] target) {
		write(d, target, 0);
	}

	public static void write(double d, byte[] target, int pos) {
		write(Double.doubleToLongBits(d), target, pos);
	}
}
