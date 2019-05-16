//
//import net.sf.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * 百度AI人脸识别demo
// *
// * @author Libin
// * @create 2017-12-20 16:35
// * @Copyright: www.sinotn.com. All rights reserved.
// **/
//public class FaceAiDemo {
//    /**
//     * 百度云AK
//     */
//    private static final String API_KEY = "V5lGHeIiAKGEsnKGVzUdh6DA";
//    /**
//     * 百度云SK
//     */
//    private static final String SECRET_KEY = "TBnZsvD3EYIo5K0E0AqI0QMLQvSjF6eQ";
//    /**
//     * 获取access_token的接口地址
//     */
//    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";
//    /**
//     * 进行人脸探测的接口地址
//     */
//    private static final String DETECT_HOST = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
//
//    public static void main(String[] args){
//        testDetect();
//    }
//    /**
//     * 人脸探测调用方法
//     */
//    public static void testDetect(){
//        /**
//         * 照片路径集合：正式项目时可从数据库获取
//         */
//        List<String> filePathList = new ArrayList<String>();
//        filePathList.add("C:/Users/F/Pictures/timg.jpg");
////        filePathList.add("C:\\Users\\Libin\\Desktop\\李四.jpg");
//
//        String imgFilePath = "";
//        String expressionStr = "";
//        String glassesStr = "";
//        for (String imgPathStr : filePathList){
//            JSONObject jsonObject = JSONObject.fromObject(detect(imgPathStr).optJSONArray("result").get(0));
//            /**
//             * 获取年龄、颜值分数、微笑程度、是否戴眼镜
//             */
//            double age = jsonObject.optDouble("age");
//            double beauty = jsonObject.optDouble("beauty");
//            int expression = jsonObject.optInt("expression");
//            int glasses = jsonObject.optInt("glasses");
//
//            switch (expression){
//                case 0 : expressionStr = "不笑";
//                    break;
//                case 1 :  expressionStr = "微笑";
//                    break;
//                case 2 :  expressionStr = "大笑";
//                    break;
//                default: expressionStr = "无法识别";
//            }
//
//            switch (glasses){
//                case 0 : glassesStr = "无眼镜";
//                    break;
//                case 1 :  glassesStr = "普通眼镜";
//                    break;
//                case 2 :  glassesStr = "墨镜";
//                    break;
//                default: glassesStr = "无法识别";
//            }
//            /**
//             * 控制台打印输出探测结果
//             * Tips：年龄默认为double，需用Math.round()四舍五入取整
//             */
//            System.out.println("年龄：" + Math.round(age));
//            System.out.println("微笑程度：" + expressionStr);
//            System.out.println("眼镜：" + glassesStr);
//            System.out.println("颜值打分：" + beauty);
//
//        }
//
//
//
//    }
//
//    /**
//     * 重要提示代码中所需工具类
//     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
//     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
//     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
//     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
//     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
//     * 下载
//     */
//    /**
//     * 人脸探测
//     * @return
//     */
//    public static JSONObject detect(String filePath) {
//        JSONObject jsonObject = null;
//        try {
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
//            String imgStr = Base64Util.encode(imgData);
//            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
//            String param = "max_face_num=" + 5
//                    + "&face_fields="
//                    + "age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities"
//                    + "&image=" + imgParam;
//            String accessToken = getAuth();
//            String result = HttpUtil.post(DETECT_HOST, accessToken, param);
//            jsonObject = JSONObject.fromObject(result);
//            return jsonObject;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//
//    /**
//     * 获取权限token
//     * @return
//     */
//    public static String getAuth(){
//        // 获取token地址
//        String getAccessTokenUrl = AUTH_HOST
//                // 1. grant_type为固定参数
//                + "grant_type=client_credentials"
//                // 2. 官网获取的 API Key
//                + "&client_id=" + API_KEY
//                // 3. 官网获取的 Secret Key
//                + "&client_secret=" + SECRET_KEY;
//        JSONObject jsonObject = null;
//        BufferedReader in = null;
//        try {
//            URL realUrl = new URL(getAccessTokenUrl);
//            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            /*for (String key : map.keySet()) {
//                System.err.println(key + "--->" + map.get(key));
//            }*/
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String result = "";
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//            /**
//             * 返回结果示例
//             */
//            jsonObject = JSONObject.fromObject(result);
//            String access_token = jsonObject.getString("access_token");
//            return access_token;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(in!=null){
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//}