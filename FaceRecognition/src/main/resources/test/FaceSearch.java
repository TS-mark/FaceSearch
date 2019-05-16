
import java.util.HashMap;
import java.util.Map;
//import com.baidu.ai.aip.utils.HttpUtil;
//import com.baidu.ai.aip.utils.GsonUtils;
/**
* 人脸搜索
*/
public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String search() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	 String Filepath="C:/Users/F/Pictures/timg.jpg";
             String image = ImageTest.GetImageStrFromPath(Filepath);//image的base64代码
            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("image", "027d8308a2ec665acb1bdf63e513bcb9");
//            map.put("liveness_control", "NORMAL");
//            map.put("group_id_list", "group_repeat,group_233");
//            map.put("image_type", "FACE_TOKEN");
//            map.put("quality_control", "LOW");
//            map.put(image,"027d8308a2ec665acb1bdf63e513bcb9");
            map.put("image", image);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "TS_Mark");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = GetToken.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceSearch.search();
    }
}