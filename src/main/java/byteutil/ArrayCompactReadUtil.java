package byteutil;

public class ArrayCompactReadUtil {

	public int [] readCompactInts(byte [] data) {
		
		byte head = data[0];
		
		String [] types = new String[4];
		
		for(int i = 0; i < 4; i++) {
			
			int m =  (0b00000011 << i*2);
			int unmask = ((head & m) >> i*2);
		
			if(unmask == 01) {
				types[i] = "byte";
			} else if(unmask == 0b00000010) {
				types[i] = "short";
			} else if(unmask == 0b00000011){
				types[i] = "int";
			} else {
				types[i] = "error";
			}
		}
		return null;
	}
}
