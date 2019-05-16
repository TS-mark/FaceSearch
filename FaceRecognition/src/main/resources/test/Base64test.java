
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Base64test{
	public static void main(String[] args) {
		Decoder decoder =	Base64.getDecoder();
		Encoder encoder =	Base64.getEncoder();
		final String textString="×Ö´®ÎÄ×Ö";
		try {
			/*±àÂë*/
			byte[] textByte=textString.getBytes("UTF-8");
			String encodedText=encoder.encodeToString(textByte);
			System.out.println(encodedText);
			/*½âÂë*/
			String text2=new String(decoder.decode(encodedText),"UTF-8");
			System.out.println(text2);
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
