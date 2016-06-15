package util;

public class PrimitiveReadUtil {

	public static short readShort(byte [] source, int pos) {
		return (short) (((source[pos] & 0xFF) << 8)
				+ ((source[pos+1] & 0xFF) << 0));
	}

	public static int readUnsignedShort(byte [] source, int pos) {
		return ((source[pos] & 0xFF) << 8)
				+ ((source[pos+1] & 0xFF) << 0);
	}

	public static int readInt(byte [] source, int pos) {
		return	((source[pos] & 0xFF) << 24)
				+ ((source[pos+1] & 0xFF) << 16)
				+ ((source[pos+2] & 0xFF) << 8)
				+ ((source[pos+3] & 0xFF) << 0);
	}
	
	public static float readFloat(byte[] source, int pos) {
		return Float.intBitsToFloat(readInt(source, pos));
	}
}
