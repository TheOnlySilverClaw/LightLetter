package demo.lightletter;

import java.util.Arrays;

import lightletter.IncomingLetter;
import lightletter.OutgoingLetter;

public class Demo {

	public static void main(String[] args) {
		
		//new DataInputStream(in).readUnsignedShort();
		OutgoingLetter letter = new OutgoingLetter(1000);
		letter.write(4.5f);
		letter.write("Hello");
		letter.write(2);
		letter.write("Just some glorious, slightly longer test string.");
		
		letter.write(4, 3, 4, 6, 8);
		
		System.out.println(Arrays.toString(letter.data()));
		System.out.println(letter.data().length);
		System.out.println(Arrays.toString(letter.compact()));
		System.out.println(letter.compact().length);

		IncomingLetter in = new IncomingLetter(letter.compact());
		
		System.out.println(in.readFloat());
		System.out.println(in.readString());
		System.out.println(in.readInt());
		System.out.println(in.readString());
		System.out.println(in.available());
		System.out.println(in.marker());
		System.out.println(Arrays.toString(in.readInts()));
	}

}
