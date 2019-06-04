package cn.haiway.controller;

import cn.haiway.pojo.FAQMessage;
import cn.haiway.process.DeployService;
import cn.haiway.service.BaiduAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Haiway  on 2019/4/16
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    BaiduAIService baiduAIService;
    @Autowired
    DeployService deployService;

    private String botId = "58089";

    @RequestMapping("/ask")
    private void ask(@RequestParam(value = "content") String content) throws IOException {
        System.out.println("content:"+content);

        deployService.startProcess("approvide",baiduAIService.requestBaidu(content,"",botId));
    }

    @MessageMapping("/faq")
    public void faqMessage(FAQMessage message) throws IOException {
        System.out.println("faq:"+message);
        if("再见".equals(message.getMessage())||"bye".equals(message.getMessage())){
            message.setFaqSessionId("");
        }else{
            deployService.startProcess("aph",baiduAIService.requestBaidu(message.getMessage(),message.getFaqSessionId(),botId));
        }
    }


    @RequestMapping("/list")
    private List<Map<String,Object>> getPersons(@RequestParam(value = "name", defaultValue = "haiway") String name){
        System.out.println("request");

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> mp = new HashMap<>();
        mp.put("dissname","諸葛亮");
        mp.put("dissid","zhugeliang");
        mp.put("phone","18312345678");
        mp.put("imgurl","http://static.bbs.9wee.com/attachment/forum/201306/07/210751qbp4p4c5yzhhbpym.jpg");
        mp.put("location","蜀国");
        mp.put("album","http://src.zhigame.com/news/20130123/2013012310413268.jpg");
        mp.put("source","通过搜索手机号添加");
        mp.put("summary","点击发送消息");
        mp.put("unread","");
        mp.put("time","08:25");
        list.add(mp);
        System.out.println(list);
        return list;
    }

}
