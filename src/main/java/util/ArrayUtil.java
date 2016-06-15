package util;

public class ArrayUtil {

	public static void ensureCapacity(int capacity, byte[] target, int pos) {
		if(capacity + pos > target.length) {
			throw new IndexOutOfBoundsException(
					"available: " + target.length
					+ ", required: " + capacity + pos);
		}
	}
}
