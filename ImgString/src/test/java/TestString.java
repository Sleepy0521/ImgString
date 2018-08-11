
public class TestString {
	static String str="abc\r\ndefg";

	public static void main(String[] args) {
		String[] strs=str.split("\r\n");
		for(int i=0;i<strs.length;i++) {
			System.out.println(strs[i]);
		}

	}

}
