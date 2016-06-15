package util;

import static org.junit.Assert.*;

import org.junit.Test;

public class FixedUtilTests {

	private byte [] buffer;

	@Test
	public final void testShort() {
		short sent = -9013;
		buffer = new byte[Short.BYTES];
		PrimitiveWriteUtil.write(sent, buffer, 0);
		short received = PrimitiveReadUtil.readShort(buffer, 0);
		assertEquals(sent, received);
	}

	@Test
	public final void testInt() {
		int sent = -25347239;
		buffer = new byte[Integer.BYTES];
		PrimitiveWriteUtil.write(sent, buffer, 0);
		int received = PrimitiveReadUtil.readInt(buffer, 0);
		assertEquals(sent, received);
	}

	@Test
	public final void testFloat() {
		float sent = -9348.45621f;
		buffer = new byte[Float.BYTES];
		PrimitiveWriteUtil.write(sent, buffer, 0);
		float received = PrimitiveReadUtil.readFloat(buffer, 0);
		assertEquals(sent, received, 0.1f);
	}
}
