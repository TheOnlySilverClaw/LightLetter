package lightletter;

import byteutil.PrimitiveWriteUtil;

public class Letter extends LightLetter {

	public Letter(int size) {
		super(new byte[size]);
	}

	/**
	 * Convenience method that checks whether shorts are really unsigned.
	 * This assures they can be read safely by calling
	 * {@link LetterContent#readUnsignedShort()}
	 * @param us
	 */
	public final void writeUnsigned(short us) {
		if(us < 0) {
			throw new IllegalArgumentException("Not unsigned: " + us);
		} else {
			write(us);
		}
	}

	/**
	 * Convenience method for writing small,
	 * unsigned integers in a more compact way.
	 * Automatically checks whether they are in positive short range.
	 * 
	 * @param us an integer between from 0 to {@link Short#MAX_VALUE}
	 */
	public final void writeUnsignedShort(int us) {
		if(us > Short.MAX_VALUE) {
			throw new IllegalArgumentException(
					"Out of range for short: " + us
					+ "(allowed: " + Short.MAX_VALUE + ")"
			);
		} else {
			writeUnsigned((short) us);
		}
	}

	public final void write(byte b) {
		checkBounds(Byte.BYTES);
		data[marker++] = b;
	}

	public final void write(byte [] bytes) {
		checkElementSize(bytes.length);
		checkBounds(Short.BYTES + bytes.length);
		PrimitiveWriteUtil.write((short) bytes.length, data, marker);
		advance(Short.BYTES);
		System.arraycopy(bytes, 0, data, marker, bytes.length);
		advance(bytes.length);
	}

	public final void write(short s) {
		checkBounds(Short.BYTES);
		PrimitiveWriteUtil.write(s, data, marker);
		advance(Short.BYTES);
	}

	public final void write(short... shorts) {
		checkBounds(Short.BYTES + shorts.length * Short.BYTES);
		writeUnsignedShort(shorts.length);
		for(short s : shorts) {
			PrimitiveWriteUtil.write(s, data, marker);
			advance(Short.BYTES);
		}
	}

	public final void write(int i) {
		checkBounds(Integer.BYTES);
		PrimitiveWriteUtil.write(i, data, marker);
		advance(Integer.BYTES);
	}

	public final void write(int ... ints) {
		checkBounds(Short.BYTES + ints.length * Integer.BYTES);
		writeUnsignedShort(ints.length);
		for(int i : ints) {
			PrimitiveWriteUtil.write(i, data, marker);
			advance(Integer.BYTES);
		}
	}
	
	public final void write(float f) {
		checkBounds(Float.BYTES);
		PrimitiveWriteUtil.write(f, data, marker);
		advance(Float.BYTES);
	}
	
	public final void write(float ... floats) {
		checkBounds(Short.BYTES + floats.length * Float.BYTES);
		writeUnsignedShort(floats.length);
		for(float f : floats) {
			PrimitiveWriteUtil.write(f, data, marker);
			advance(Float.BYTES);
		}
	}
	
	public final void write(long l) {
		checkBounds(Long.BYTES);
		PrimitiveWriteUtil.write(l, data, marker);
		advance(Long.BYTES);
	}
	
	public final void write(long ... longs) {
		checkBounds(Short.BYTES + longs.length * Long.BYTES);
		writeUnsignedShort(longs.length);
		for(long l : longs) {
			PrimitiveWriteUtil.write(l, data, marker);
			advance(Long.BYTES);
		}
	}
	
	public final void write(double d) {
		checkBounds(Double.BYTES);
		PrimitiveWriteUtil.write(d, data, marker);
		advance(Double.BYTES);
	}

	public final void write(double ... doubles) {
		checkBounds(Short.BYTES + doubles.length * Double.BYTES);
		writeUnsignedShort(doubles.length);
		for(double d : doubles) {
			PrimitiveWriteUtil.write(d, data, marker);
			advance(Double.BYTES);
		}
	}
	public final void write(String string) {
		write(string.getBytes(CHARSET));
	}

	public final void write(String ... strings) {
		writeUnsignedShort(strings.length);
		for(String string : strings) {
			write(string);
		}
	}

	public final byte [] data() {
		return data.clone();
	}
	
	/**
	 * Get data written to this letter.
	 * 
	 * @return An array containing all the data written to this Letter,
	 * while cutting of unused bytes.
	 */
	public final byte [] compact() {
		byte [] compact = new byte[marker];
		System.arraycopy(data, 0, compact, 0, marker);
		return compact;
	}
}
