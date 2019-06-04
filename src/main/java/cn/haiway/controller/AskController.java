package cn.haiway.controller;

import cn.haiway.pojo.FAQMessage;
import cn.haiway.service.BaiduAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author Haiway  on 2019/6/4
 */
@RestController
@RequestMapping("/faq")
public class AskController {
    @Autowired
    BaiduAIService baiduAIService;

    @RequestMapping("/ai")
    public String faqMessage(FAQMessage message) throws IOException {
        System.out.println("faq:"+message);
        Map<String, Object> map = baiduAIService.faqBaidu(message.getMessage(), message.getFaqSessionId(),message.getBotId());

        return map.get("say")+"";
    }
}
