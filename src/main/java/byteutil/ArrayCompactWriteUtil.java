package byteutil;

import java.math.BigInteger;

public class ArrayCompactWriteUtil extends PrimitiveWriteUtil {

	private static int numsPerByte = 4;
	private static int BYTE_MASK = 0b01000000;
	private static int SHORT_MASK = 0b10000000;
	private static int INT_MASK = 0b11000000;

	public static void writeCompact(int [] ints, byte[] target, int pos) {
		
		int left = ints.length;
		int writePos = 1;
		for(int srcPos = 0; srcPos  < ints.length; srcPos += 4) {
			writeCompactQuattet(ints, target, writePos, srcPos);
			writePos+=4;
		}

	}
	
	public static int getMask(int value) {
		
		if(value <= Byte.MAX_VALUE && value >= Byte.MIN_VALUE) {
			return BYTE_MASK;
		} else if(value <= Short.MAX_VALUE && value >= Short.MIN_VALUE) {
			return SHORT_MASK;
		} else {
			return INT_MASK;
		}
	}

	public static void writeCompactQuattet(int[] ints, byte [] target, int pos, int offset) {
		
		byte head = 0;
		int writePos = pos + 1;
		
		for(int i = offset; i < offset + 4; i++) {
			int value = ints[i];
			int mask = getMask(value);
			if(mask == BYTE_MASK) {
				head = (byte) (head | (0b01000000 >> i*2));
				target[writePos++] =  (byte) value;
			} else if(mask == SHORT_MASK) {
				head = (byte) (head | (0b10000000 >> i*2));
				write((short) value, target, writePos);
				writePos+=Short.BYTES;
			} else {
				head = (byte) (head | (0b11000000 >> i*2));
				write(value, target, writePos);
				writePos=+Integer.BYTES;
			}
		}
		target[0] = head;
		//printBinary(head);
	}
	
	public static void printBinary(byte b) {
		System.out.printf("%08d", new BigInteger(
				Integer.toBinaryString(b)));
	}
}
