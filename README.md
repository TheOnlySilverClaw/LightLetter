# LightLetter

## Goal

LightLetter is intended to be used as a very basic serialization library.
Basically, I tried to use MessagePack, which seems to be a great tool, but I thought
"I do not need most of the features, like inter-language or JSON-compatibility,
maybe I can do something similar, with a more convenient API and more speed?".
So, the goals of LightLetter are:
* be at east 10 times faster than comparable MessagePack methods
* try to keep data size smaller or at least equal to MessagePack
* be easy to use, with convenience methods for arrays, maps, and some other data structures

## Usage

One drawback of LightLetter is that you have to guess the maximum amount of data a letter will hold.
You also have to do the write and read operations in the same order.
Apart from that, usage is pretty  straightforward:

```java
	Letter letter = new Letter(1000);
	letter.write("Hello!");
	letter.write(1234);
	letter.write(new short [] { 1, 2, 3, 4, 5, 6, 7});
	
	byte [] data = letter.compact();
	// send data over network or file
	
	LetterContent content = new LetterContent(data);
	System.out.println(content.readString()); //Hello!
	System.out.println(content.readInt()); // 1234
	System.out.println(Arrays.toString(content.readShorts())); //[1, 2, 3, 4, 5, 6, 7]

```
