package lightletter;

import byteutil.PrimitiveReadUtil;

public class LetterContent extends LightLetter {

	public LetterContent(byte[] data) {
		super(data);
	}

	public int readUnsignedShort() {
		checkBounds(Short.BYTES);
		int us = PrimitiveReadUtil.readUnsignedShort(data, marker);
		advance(Short.BYTES);
		return us;
	}

	public byte readByte() {
		checkBounds(1);
		return data[marker++];
	}

	public byte [] readBytes() {
		int length = readUnsignedShort();
		checkBounds(length);
		byte [] bytes = new byte[length];
		System.arraycopy(data, marker, bytes, 0, length);
		advance(length);
		return bytes;
	}

	public short readShort() {
		checkBounds(Short.BYTES);
		short s = PrimitiveReadUtil.readShort(data, marker);
		advance(Short.BYTES);
		return s;
	}

	public short[] readShorts() {
		short [] shorts = new short[readUnsignedShort()];
		checkBounds(shorts.length * Short.BYTES);
		for(int i = 0; i < shorts.length; i++) {
			shorts[i] = PrimitiveReadUtil.readShort(data, marker);
			advance(Short.BYTES);
		}
		return shorts;
	}

	public int readInt() {
		checkBounds(Integer.BYTES);
		int i = PrimitiveReadUtil.readInt(data, marker);
		advance(Integer.BYTES);
		return i;
	}
	
	public int [] readInts() {
		int [] ints = new int[readUnsignedShort()];
		checkBounds(ints.length * Integer.BYTES);
		for(int i = 0; i < ints.length; i++) {
			ints[i] = PrimitiveReadUtil.readInt(data, marker);
			advance(Integer.BYTES);
		}
		return ints;
	}
	
	public float readFloat() {
		checkBounds(Float.BYTES);
		float f = PrimitiveReadUtil.readFloat(data, marker);
		advance(Float.BYTES);
		return f;
	}

	public float[] readFloats() {
		float [] floats = new float[readUnsignedShort()];
		checkBounds(floats.length * Float.BYTES);
		for(int i = 0; i < floats.length; i++) {
			floats[i] = PrimitiveReadUtil.readFloat(data, marker);
			advance(Float.BYTES);
		}
		return floats;
	}

	public long readLong() {
		checkBounds(Long.BYTES);
		long l = PrimitiveReadUtil.readLong(data, marker);
		advance(Long.BYTES);
		return l;
	}

	public long[] readLongs() {
		long [] longs = new long[readUnsignedShort()];
		checkBounds(longs.length * Long.BYTES);
		for(int i = 0; i < longs.length; i++) {
			longs[i] = PrimitiveReadUtil.readLong(data, marker);
			advance(Long.BYTES);
		}
		return longs;
	}

	public double readDouble() {
		checkBounds(Double.BYTES);
		double d = PrimitiveReadUtil.readDouble(data, marker);
		advance(Double.BYTES);
		return d;
	}

	public double[] readDoubles() {
		double [] doubles = new double[readUnsignedShort()];
		checkBounds(doubles.length * Double.BYTES);
		for(int i = 0; i < doubles.length; i++) {
			doubles[i] = PrimitiveReadUtil.readDouble(data, marker);
			advance(Double.BYTES);
		}
		return doubles;
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
}
