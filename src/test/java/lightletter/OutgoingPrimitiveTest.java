package lightletter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OutgoingPrimitiveTest {

	private static final int Test_SIZE = 64;
	
	private OutgoingLetter letter;
	
	@Before
	public final void setUp() {
		letter = new OutgoingLetter(Test_SIZE);
	}

	@Test
	public final void testSkip() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWriteShort() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWriteInt() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWriteFloat() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWriteByteArray() {
		fail("Not yet implemented");
	}

	@Test
	public final void testWriteString() {
		letter.write("The Fox jumped other the rabbit.");
	}

	@Test
	public final void testGetData() {
		letter.write(new byte[] { 1, 7, -3, 4 });
		byte [] expected = new byte[Test_SIZE];
		expected[1] = 4;
		expected[2] = 1;
		expected[3] = 7;
		expected[4] = -3;
		expected[5] = 4;
		assertArrayEquals(expected, letter.data());
	}

}
