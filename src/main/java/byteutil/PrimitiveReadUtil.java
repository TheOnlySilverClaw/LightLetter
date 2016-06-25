package byteutil;

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
				+ ((source[pos+2] & 0xFF) <<  8)
				+ ((source[pos+3] & 0xFF) <<  0);
	}
	
	public static float readFloat(byte[] source, int pos) {
		return Float.intBitsToFloat(readInt(source, pos));
	}

	public static long readLong(byte[] source, int pos) {
		return	((long) (source[pos]) << 56)
				+ ((long) (source[pos+1] & 255) << 48)
				+ ((long) (source[pos+2] & 255) << 40)
				+ ((long) (source[pos+3] & 255) << 32)
				+ ((long) (source[pos+4] & 255) << 24)
				+ ((source[pos+5] & 255) << 16)
				+ ((source[pos+6] & 255) <<  8)
				+ ((source[pos+7] & 255) <<  0);
	}
	
	public static double readDouble(byte[] source, int pos) {
		return Double.longBitsToDouble(readLong(source, pos));
	}

}
