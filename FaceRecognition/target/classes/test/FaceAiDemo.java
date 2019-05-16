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
// * �ٶ�AI����ʶ��demo
// *
// * @author Libin
// * @create 2017-12-20 16:35
// * @Copyright: www.sinotn.com. All rights reserved.
// **/
//public class FaceAiDemo {
//    /**
//     * �ٶ���AK
//     */
//    private static final String API_KEY = "V5lGHeIiAKGEsnKGVzUdh6DA";
//    /**
//     * �ٶ���SK
//     */
//    private static final String SECRET_KEY = "TBnZsvD3EYIo5K0E0AqI0QMLQvSjF6eQ";
//    /**
//     * ��ȡaccess_token�Ľӿڵ�ַ
//     */
//    private static final String AUTH_HOST = "https://aip.baidubce.com/oauth/2.0/token?";
//    /**
//     * ��������̽��Ľӿڵ�ַ
//     */
//    private static final String DETECT_HOST = "https://aip.baidubce.com/rest/2.0/face/v1/detect";
//
//    public static void main(String[] args){
//        testDetect();
//    }
//    /**
//     * ����̽����÷���
//     */
//    public static void testDetect(){
//        /**
//         * ��Ƭ·�����ϣ���ʽ��Ŀʱ�ɴ����ݿ��ȡ
//         */
//        List<String> filePathList = new ArrayList<String>();
//        filePathList.add("C:/Users/F/Pictures/timg.jpg");
////        filePathList.add("C:\\Users\\Libin\\Desktop\\����.jpg");
//
//        String imgFilePath = "";
//        String expressionStr = "";
//        String glassesStr = "";
//        for (String imgPathStr : filePathList){
//            JSONObject jsonObject = JSONObject.fromObject(detect(imgPathStr).optJSONArray("result").get(0));
//            /**
//             * ��ȡ���䡢��ֵ������΢Ц�̶ȡ��Ƿ���۾�
//             */
//            double age = jsonObject.optDouble("age");
//            double beauty = jsonObject.optDouble("beauty");
//            int expression = jsonObject.optInt("expression");
//            int glasses = jsonObject.optInt("glasses");
//
//            switch (expression){
//                case 0 : expressionStr = "��Ц";
//                    break;
//                case 1 :  expressionStr = "΢Ц";
//                    break;
//                case 2 :  expressionStr = "��Ц";
//                    break;
//                default: expressionStr = "�޷�ʶ��";
//            }
//
//            switch (glasses){
//                case 0 : glassesStr = "���۾�";
//                    break;
//                case 1 :  glassesStr = "��ͨ�۾�";
//                    break;
//                case 2 :  glassesStr = "ī��";
//                    break;
//                default: glassesStr = "�޷�ʶ��";
//            }
//            /**
//             * ����̨��ӡ���̽����
//             * Tips������Ĭ��Ϊdouble������Math.round()��������ȡ��
//             */
//            System.out.println("���䣺" + Math.round(age));
//            System.out.println("΢Ц�̶ȣ�" + expressionStr);
//            System.out.println("�۾���" + glassesStr);
//            System.out.println("��ֵ��֣�" + beauty);
//
//        }
//
//
//
//    }
//
//    /**
//     * ��Ҫ��ʾ���������蹤����
//     * FileUtil,Base64Util,HttpUtil,GsonUtils���
//     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
//     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
//     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
//     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
//     * ����
//     */
//    /**
//     * ����̽��
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
//     * ��ȡȨ��token
//     * @return
//     */
//    public static String getAuth(){
//        // ��ȡtoken��ַ
//        String getAccessTokenUrl = AUTH_HOST
//                // 1. grant_typeΪ�̶�����
//                + "grant_type=client_credentials"
//                // 2. ������ȡ�� API Key
//                + "&client_id=" + API_KEY
//                // 3. ������ȡ�� Secret Key
//                + "&client_secret=" + SECRET_KEY;
//        JSONObject jsonObject = null;
//        BufferedReader in = null;
//        try {
//            URL realUrl = new URL(getAccessTokenUrl);
//            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//            // ��ȡ������Ӧͷ�ֶ�
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // �������е���Ӧͷ�ֶ�
//            /*for (String key : map.keySet()) {
//                System.err.println(key + "--->" + map.get(key));
//            }*/
//            // ���� BufferedReader����������ȡURL����Ӧ
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String result = "";
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//            /**
//             * ���ؽ��ʾ��
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