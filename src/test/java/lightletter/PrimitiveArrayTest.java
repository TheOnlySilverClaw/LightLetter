package lightletter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrimitiveArrayTest {

	private static final int testSize = 1024;
	
	private Letter letter;
	
	@Before
	public void setUp() {
		letter = new Letter(testSize);
	}

	@Test
	public final void testSendBytes() {
		byte [] bytes = new byte [] { 3, 8, 1, 3 -6 -122, 127};
		letter.write(bytes);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(bytes, content.readBytes());
	}

	@Test
	public void testSendShorts() {
		short [] shorts = new short[] { Short.MAX_VALUE, Short.MIN_VALUE, -4, 4, 0, -0, 1};
		letter.write(shorts);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(shorts, content.readShorts());
	}

	@Test
	public void testSendInts() {
		int [] ints = new int [] { Integer.MAX_VALUE, Integer.MIN_VALUE, 4, -4, 0, -0, 1};
		letter.write(ints);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(ints, content.readInts());
	}
	
	@Test
	public void testSendFloats() {
		float [] floats  = new float[] { Float.MAX_VALUE, Float.MIN_VALUE,
				-45.23f, +833.45f, -344.324525f, 0f, 1f, 48388f, 2003.05f };
		letter.write(floats);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(floats, content.readFloats(), 0.1f);
	}
	
	@Test
	public void testSendLongs() {
		long [] longs = new long [] { Long.MAX_VALUE, Long.MIN_VALUE, 4l, -4l,
				0l, 2345678900l, 98765678987654l, -532671829l, 1l };
		letter.write(longs);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(longs, content.readLongs());
	}
	
	@Test
	public void testSendDoubles() {
		double [] doubles = new double[] { Double.MAX_VALUE, Double.MIN_VALUE,
				0, 0.00000000000001, -0.0000000000001, 100, 100.000005,
				-0.000000000000000000005, 999.9999999 };
		letter.write(doubles);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(doubles, content.readDoubles(), 0.1);
	}
	
	@Test
	public void testSendStrings() {
		String [] strings = new String[] {
			"Duck", "Goose", "Dog", "Cat", "519270",
			"\\”ŧ\t\n", "€ŧ@łµħµħđŋðđð¢", "...,;-:?!§$%&/()[]"	
		};
		letter.write(strings);
		LetterContent content = new LetterContent(letter.compact());
		assertArrayEquals(strings, content.readStrings());
	}
}
