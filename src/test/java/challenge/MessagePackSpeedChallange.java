package challenge;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lightletter.Letter;

public class MessagePackSpeedChallange {

	private static final int testSize = 1000;
	private static final int repeats = 2000;
	
	private Letter letter;
	private MessageBufferPacker packer;
	
	private int [] ints;
	
	@Before
	public void setup() {
		
		letter = new Letter(testSize * 10);
		packer = MessagePack.newDefaultBufferPacker();
		
		Random random = new Random();
		ints = random.ints(1000).toArray();
	}

	private final Logger log = LoggerFactory.getLogger(
			MessagePackSpeedChallange.class);
	
	@Rule
	public Stopwatch stopwatch = new Stopwatch() {
		
		@Override
		protected void succeeded(long nanos, Description description) {
			log.info("{} : {}ms", description.getMethodName(), nanos/1000);
		};
	};
	
	@Test
	public void writeIntsSeparatelyLightLetter() {
		letter.writeUnsignedShort(ints.length);
		for(int i : ints) {
			letter.write(i);
		}
	}

	@Test
	public void writeIntsAsArrayLightLetter() {
		// this is SLOWER? o.O
		letter.write(ints);
		
	}
	
	@Test
	public void writeIntsMessagePack() throws IOException {
		packer.packArrayHeader(ints.length);
		for(int i : ints) {
			packer.packInt(i);
		}
		packer.flush();
	}
}
