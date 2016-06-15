package lightletter;

import java.nio.charset.Charset;

public abstract class LightLetter {

	public static final Charset CHARSET = Charset.forName("utf-8");

	protected final byte data [];
	protected int marker;

	public LightLetter(byte [] data) {
		this.data = data.clone();
		marker = 0;
	}

	protected final void checkBounds(int bytes) {
		if(bytes > available()) {
			throw new IndexOutOfBoundsException("Mising: " + (available() - bytes));
		}
	}

	protected final void checkElementSize(int size) {
		if(size > Short.MAX_VALUE)
			throw new IllegalArgumentException(
					"Element with size " + size + " is too big!");
	}

	protected final void advance(int bytes) {
		marker += bytes;
	}

	public void skip(int bytes) {
		checkBounds(bytes);
		advance(bytes);
	}

	public int size() {
		return data.length;
	}
	
	public int marker() {
		return marker;
	}

	public int available() {
		return data.length - marker;
	}
}
