
 
import org.apache.http.client.methods.CloseableHttpResponse;
 
import java.util.HashMap;
import java.util.Map;
 
/**
 * Created by Mark on 2019/5/14.
 */
public class FaceAPITest {
 
    public static void main(String[] args) {
        //  getToKenTest() ;
        faceDetecttest();
    }
 
    //获取token
    public static void getToKenTest(){
 
        //使用其测试百度云API---获取token
        //url: http://console.bce.baidu.com/ai
 
        String APPID ="16234524"; //管理中心获得
 
        //百度人脸识别应用apikey
        String API_KEY = "V5lGHeIiAKGEsnKGVzUdh6DA"; //管理中心获得
 
        //百度人脸识别应用sercetkey
        String SERCET_KEY = "TBnZsvD3EYIo5K0E0AqI0QMLQvSjF6eQ"; //管理中心获得
 
        //百度人脸识别token 有效期一个月
        String TOKEN = null;
 
 
        String access_token_url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials"
                +"&client_id="+API_KEY +"&client_secret="+SERCET_KEY;
 
        CloseableHttpResponse response =  HttpClientUtils.doHttpsGet(access_token_url,null);
 
        System.out.println(HttpClientUtils.toString(response));
 
 
        //得到token = 24.1d786b9cdbdd8ac7cf55d56c7f38372b.2592000.1509244497.282335-10201425
 
 
 
    }
 
    //使用token调用API
    public static void faceDetecttest(){
 
        String token = "24.35ddf090be29cc81f74e83e0f1322615.2592000.1560481003.282335-16234524";
 
//        String Filepath = "E:/test.jpg";
        String Filepath="C:/Users/F/Pictures/timg.jpg";
        String image = ImageTest.GetImageStrFromPath(Filepath);
//        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect?access_token="+token;
        String url = "https://aip.baidubce.com/rest/2.0/face/v1/detect?access_token="+token;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
 
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("image", image);
        bodys.put("face_fields", "age,beauty,expression,gender,glasses,race,qualities");
 
        try {
            CloseableHttpResponse response =  HttpClientUtils.doHttpsPost(url,headers,bodys);
            System.out.println(HttpClientUtils.toString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
 
 
 
    }
 
}