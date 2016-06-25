package lightletter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrimitiveTest {

	private static final int testSize = 64;
	
	private Letter letter;
	
	@Before
	public final void setUp() {
		letter = new Letter(testSize);
	}

	@Test
	public final void testSkip() {
		letter.skip(5);
		assertEquals(letter.marker(), 5);
		letter.skip(15);
		assertEquals(letter.marker(), 20);
	}

	@Test
	public final void testSendShort() {
		short s = 3023;
		letter.write(s);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(s, content.readShort());
	}

	@Test
	public final void testSendInt() {
		int i = 50934900;
		letter.write(i);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(i, content.readInt());
	}

	@Test
	public final void testSendFloat() {
		float f = 13120.14010f;
		letter.write(f);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(f, content.readFloat(), 0.1f);
	}

	@Test
	public final void testSendLong() {
		long l = 14874729472949l;
		letter.write(l);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(l, content.readLong());
	}
	
	@Test
	public final void testSendDouble() {
		double d = 65748392.57483978;
		letter.write(d);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(d, content.readDouble(), 0.1);
	}

	@Test
	public final void testSendString() {
		String string = "Blah !\"§$&/())85684263tgufb\nhncRZTH";
		letter.write(string);
		LetterContent content = new LetterContent(letter.compact());
		assertEquals(string, content.readString());
	}
}
