package lightletter;

import util.PrimitiveWriteUtil;

public class OutgoingLetter extends LightLetter {

	public OutgoingLetter(int size) {
		super(new byte[size]);
	}

	public final void write(short s) {
		checkBounds(Short.BYTES);
		PrimitiveWriteUtil.write(s, data, marker);
		advance(Short.BYTES);
	}

	public final void writeUnsigned(short us) {
		if(us < 0) {
			throw new IllegalArgumentException("Not unsigned: " + us);
		} else {
			write(us);
		}
	}

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

	public final void write(int i) {
		checkBounds(Integer.BYTES);
		PrimitiveWriteUtil.write(i, data, marker);
		advance(Integer.BYTES);
	}
	
	public final void write(int ... ints) {
		checkElementSize(ints.length);
		checkBounds(Short.BYTES);
		writeUnsignedShort(ints.length);
		for(int i = 0; i < ints.length; i++) {
			write(ints[i]);
		}
	}
	
	public final void write(float f) {
		checkBounds(Float.BYTES);
		PrimitiveWriteUtil.write(f, data, marker);
		advance(Float.BYTES);
	}
	
	public final void write(byte [] bytes) {
		checkElementSize(bytes.length);
		checkBounds(Short.BYTES + bytes.length);
		PrimitiveWriteUtil.write((short) bytes.length, data, marker);
		advance(Short.BYTES);
		System.arraycopy(bytes, 0, data, marker, bytes.length);
		advance(bytes.length);
	}
	
	public final void write(String string) {
		write(string.getBytes(CHARSET));
	}

	public final void write(String ... strings) {
		checkElementSize(strings.length);
		writeUnsignedShort(strings.length);
		for(String string : strings) {
			write(string);
		}
	}

	public final byte [] data() {
		return data.clone();
	}
	
	public final byte [] compact() {
		byte [] compact = new byte[marker];
		System.arraycopy(data, 0, compact, 0, marker);
		return compact;
	}
}
