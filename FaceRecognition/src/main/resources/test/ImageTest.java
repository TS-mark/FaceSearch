
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
public class ImageTest {
    /**
     * @Title: GetImageStrFromUrl
     * @Description: ��һ������ͼƬת����Base64�ַ���
     * @param imgURL ������Դλ��
     * @return Base64�ַ���
     */
    public static String GetImageStrFromUrl(String imgURL) {
        byte[] data = null;
        try {
            // ����URL
            URL url = new URL(imgURL);
            //����ʹ��
//            URL url=new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557825109570&di=4d2f8056e7e87deeac54caac6e7a6d01&imgtype=0&src=http%3A%2F%2Fpic1.16pic.com%2F00%2F10%2F68%2F16pic_1068360_b.jpg");
            // ��������
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����
//        Encoder encoder = new Encoder();
        Encoder encoder=Base64.getEncoder();
        // ����Base64��������ֽ������ַ���
        String encodedText=encoder.encodeToString(data);
        return encodedText;
    }
 
    /**
     * @Title: GetImageStrFromPath
     * @Description: (��һ�ű���ͼƬת����Base64�ַ���)
     * @param imgPath
     * @return
     */
    public static String GetImageStrFromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // ��ȡͼƬ�ֽ�����
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����
//        Encoder encoder = new BASE64Encoder();
        Encoder encoder=Base64.getEncoder();
        // ����Base64��������ֽ������ַ���
//        return encoder.encode(data);
        String result=encoder.encodeToString(data);
        return result;
    }
 
    /**
     * @Title: GenerateImage
     * @Description: base64�ַ���ת����ͼƬ
     * @param imgStr
     * @param imgFilePath  ͼƬ�ļ������硰E:/tmp.jpg��
     * @return
     */
    public static boolean saveImage(String imgStr,String imgFilePath) {
        if (imgStr == null) // ͼ������Ϊ��
            return false;
//        BASE64Decoder decoder = new BASE64Decoder();
        Decoder decoder=Base64.getDecoder();
        try {
            // Base64����
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// �����쳣����
                    b[i] += 256;
                }
            }
            // ����jpegͼƬ
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
//    public static void main(String[] args) {
//		ImageTest imageTest=new ImageTest();
//		System.out.println(imageTest.GetImageStrFromUrl(null));
//		System.out.println("done");
//		
//	}
}