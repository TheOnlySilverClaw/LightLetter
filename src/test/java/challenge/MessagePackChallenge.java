package challenge;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;

import lightletter.OutgoingLetter;

public class MessagePackChallenge {

	private MessageBufferPacker packer;
	private OutgoingLetter letter;
	
	@Before
	public void setUp() {
		packer = MessagePack.newDefaultBufferPacker();
		letter = new OutgoingLetter(512);
	}

	@Test
	public final void intChallenge() throws IOException {
		
		int number = 1000;
		
		packer.packInt(number);
		packer.flush();
		
		letter.write(number);
		
		challengeSize();
	}
	
	@Test
	public final void stringChallenge() throws IOException {
		
		String string = "Everyone likes little chicken.";
		
		packer.packString(string);
		packer.flush();
		
		letter.write(string);
		
		challengeSize();
	}
	
	@Test
	public final void smallMixedChallenge() throws IOException {
		
		Random random = new Random();
		int i1 = random.nextInt();
		int i2 = random.nextInt(1000);
		int i3 = random.nextInt(10000) - 5000;
		short s = (short) random.nextInt();
		float f1 = random.nextFloat();
		float f2 = random.nextFloat();
		byte [] bytes = new byte[random.nextInt(100)];
		String string1 = new String(bytes);
		bytes = new byte[random.nextInt(20)];
		random.nextBytes(bytes);
		String string2 = new String(bytes);
		
		packer.packInt(i1);
		packer.packInt(i2);
		packer.packInt(i3);
		packer.packShort(s);
		packer.packFloat(f1);
		packer.packFloat(f2);
		packer.packString(string1);
		packer.packString(string2);
		packer.flush();
		
		letter.write(i1);
		letter.write(i2);
		letter.write(i3);
		letter.write(s);
		letter.write(f1);
		letter.write(f2);
		letter.write(string1);
		letter.write(string2);
	
		challengeSize();
	}

	@Test
	public final void streamChallenge() throws IOException {
		
		// we need some more space
		letter = new OutgoingLetter(8000);
		
		Random random = new Random();

		random.ints(1000).forEach(i -> {
			try {
				packer.packInt(i);
			} catch (IOException e) {
				e.printStackTrace();
			}
			letter.write(i);
		});
		
		random.doubles(100).forEach(d -> {
			float f = (float) d * 2;
			try {
				packer.packFloat(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			letter.write(f);
		});
		packer.flush();

		System.out.println(packer.getTotalWrittenBytes());
		System.out.println(letter.marker());

		challengeSize();
	}

	private void challengeSize() {
		assertTrue(packer.getTotalWrittenBytes() >= letter.marker());
	}
}
