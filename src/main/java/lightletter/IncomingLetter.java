package lightletter;

import util.PrimitiveReadUtil;

public class IncomingLetter extends LightLetter {

	public IncomingLetter(byte[] data) {
		super(data);
	}

	public int readInt() {
		checkBounds(Integer.BYTES);
		int i = PrimitiveReadUtil.readInt(data, marker);
		advance(Integer.BYTES);
		return i;
	}
	
	public int [] readInts() {
		int [] ints = new int[readUnsignedShort()];
		for(int i = 0; i < ints.length; i++) {
			ints[i] = readInt();
		}
		return ints;
	}
	
	public int readUnsignedShort() {
		checkBounds(Short.BYTES);
		int us = PrimitiveReadUtil.readUnsignedShort(data, marker);
		advance(Short.BYTES);
		return us;
	}
	
	public float readFloat() {
		checkBounds(Float.BYTES);
		float f = PrimitiveReadUtil.readFloat(data, marker);
		advance(Integer.BYTES);
		return f;
	}
	
	public byte [] readBytes() {
		int length = readUnsignedShort();
		checkBounds(length);
		byte [] bytes = new byte[length];
		System.arraycopy(data, marker, bytes, 0, length);
		advance(length);
		return bytes;
	}
	
	public String readString() {
		return new String(readBytes(), CHARSET);
	}
	
	public String [] readStrings() {
		String [] strings = new String[readUnsignedShort()];
		for(int i = 0; i < strings.length; i++) {
			strings[i] = readString();
		}
		return strings;
	}

	/*
	@SuppressWarnings("unchecked")
	public <T> T[] readArray(Function<byte[], T> conversion) {
		Object [] elements = new Object[readUnsignedShort()];
		System.out.println("elemenets: " + elements.length);
		for(int i = 0; i < elements.length; i++) {
			elements[i] = conversion.apply(readBytes());
		}
		return (T[]) elements;
	}
	*/
}
