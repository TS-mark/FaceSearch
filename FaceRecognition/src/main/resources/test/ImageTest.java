
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
     * @Description: 将一张网络图片转化成Base64字符串
     * @param imgURL 网络资源位置
     * @return Base64字符串
     */
    public static String GetImageStrFromUrl(String imgURL) {
        byte[] data = null;
        try {
            // 创建URL
            URL url = new URL(imgURL);
            //测试使用
//            URL url=new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557825109570&di=4d2f8056e7e87deeac54caac6e7a6d01&imgtype=0&src=http%3A%2F%2Fpic1.16pic.com%2F00%2F10%2F68%2F16pic_1068360_b.jpg");
            // 创建链接
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
        // 对字节数组Base64编码
//        Encoder encoder = new Encoder();
        Encoder encoder=Base64.getEncoder();
        // 返回Base64编码过的字节数组字符串
        String encodedText=encoder.encodeToString(data);
        return encodedText;
    }
 
    /**
     * @Title: GetImageStrFromPath
     * @Description: (将一张本地图片转化成Base64字符串)
     * @param imgPath
     * @return
     */
    public static String GetImageStrFromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
//        Encoder encoder = new BASE64Encoder();
        Encoder encoder=Base64.getEncoder();
        // 返回Base64编码过的字节数组字符串
//        return encoder.encode(data);
        String result=encoder.encodeToString(data);
        return result;
    }
 
    /**
     * @Title: GenerateImage
     * @Description: base64字符串转化成图片
     * @param imgStr
     * @param imgFilePath  图片文件名，如“E:/tmp.jpg”
     * @return
     */
    public static boolean saveImage(String imgStr,String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
//        BASE64Decoder decoder = new BASE64Decoder();
        Decoder decoder=Base64.getDecoder();
        try {
            // Base64解码
            byte[] b = decoder.decode(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成jpeg图片
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