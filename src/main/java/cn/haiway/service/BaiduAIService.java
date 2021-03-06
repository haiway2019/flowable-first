package cn.haiway.service;

import cn.haiway.utils.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Haiway  on 2019/5/30
 */
@Service
public class BaiduAIService {
    private final static String URL = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";

    private String accessToken = "24.b9123c27e4deb74c8cdab425b1103e5f.2592000.1561778218.282335-16387169";

    public Map<String, Object> requestBaidu(String content,String sessionId,String botId) throws IOException {
        sessionId = sessionId == null ? "" :sessionId;
        String reqUrl = URL + "?access_token=" + accessToken;
        String json = "{\"bot_session\":\"{\\\"session_id\\\":\\\""+sessionId+"\\\"}\",\"log_id\":\"7758521sd22\"," +
                "\"request\":{\"bernard_level\":1," +
                "\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\"," +
                "\"query\":\"" + content + "\",\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"}," +
                "\"updates\":\"\",\"user_id\":\"haiway87\"}," +
                "\"bot_id\":\""+botId+"\",\"version\":\"2.0\"}";

        System.out.println("param:"+json);

        Map<String, Object> returnMap = new HashMap<>();
        String result = HttpUtil.post(reqUrl, json);
        if (StringUtils.isEmpty(result)) {

        } else {
            Map<String, Object> map = JSON.parseObject(result, new TypeReference<Map<String, Object>>() {});

            Map<String, Object> respMap = ((Map<String, Object>) ((Map<String, Object>) map.get("result")).get("response"));
            List<Map<String, Object>> actionList = (List<Map<String, Object>>) respMap.get("action_list");

            List<Map<String, Object>> slotsList = (List<Map<String, Object>>) ((Map<String, Object>) respMap.get("schema")).get("slots");

            if (actionList != null) {
                Map<String, Object> am = actionList.get(0);

                returnMap.put("type", am.get("type"));
                returnMap.put("say", am.get("say"));
            }

            if (slotsList != null) {
//                returnMap.put("slots", slotsList);
                for (Map<String, Object> mp : slotsList) {
                    String key = mp.get("name")+"";
                    key = key.substring(key.indexOf("_")+1);
                    returnMap.put(key, mp.get("normalized_word"));
                }
            }

            String session = ((Map<String, Object>) map.get("result")).get("bot_session")+"";
            Map<String, Object> sessionMap = JSON.parseObject(session, new TypeReference<Map<String, Object>>() { });

            String returnSessionId = sessionMap.get("session_id")+"";
            returnMap.put("faqSessionId",returnSessionId);
            System.out.println(returnMap);
        }
        return returnMap;
    }


    public Map<String, Object> faqBaidu(String content,String sessionId,String botId) throws IOException {
        sessionId = sessionId == null ? "" :sessionId;
        String reqUrl = URL + "?access_token=" + accessToken;
        String json = "{\"bot_session\":\"{\\\"session_id\\\":\\\""+sessionId+"\\\"}\",\"log_id\":\"7758521sd22\"," +
                "\"request\":{\"bernard_level\":1," +
                "\"client_session\":\"{\\\"client_results\\\":\\\"\\\", \\\"candidate_options\\\":[]}\"," +
                "\"query\":\"" + content + "\",\"query_info\":{\"asr_candidates\":[],\"source\":\"KEYBOARD\",\"type\":\"TEXT\"}," +
                "\"updates\":\"\",\"user_id\":\"haiway87\"}," +
                "\"bot_id\":\""+botId+"\",\"version\":\"2.0\"}";

        System.out.println("param:"+json);

        Map<String, Object> returnMap = new HashMap<>();
        String result = HttpUtil.post(reqUrl, json);
        if (StringUtils.isEmpty(result)) {

        } else {
            Map<String, Object> map = JSON.parseObject(result, new TypeReference<Map<String, Object>>() {});

            Map<String, Object> respMap = ((Map<String, Object>) ((Map<String, Object>) map.get("result")).get("response"));
            List<Map<String, Object>> actionList = (List<Map<String, Object>>) respMap.get("action_list");

            List<Map<String, Object>> slotsList = (List<Map<String, Object>>) ((Map<String, Object>) respMap.get("schema")).get("slots");

            if (actionList != null) {
                Map<String, Object> am = actionList.get(0);

                returnMap.put("type", am.get("type"));
                returnMap.put("say", am.get("say"));
            }

            if (slotsList != null) {
//                returnMap.put("slots", slotsList);
                for (Map<String, Object> mp : slotsList) {
                    String key = mp.get("name")+"";
                    key = key.substring(key.indexOf("_")+1);
                    returnMap.put(key, mp.get("normalized_word"));
                }
            }

            System.out.println(returnMap);
        }
        return returnMap;
    }
}
