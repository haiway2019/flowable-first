package cn.haiway.pojo;

import lombok.Data;

import java.util.Map;

/**
 * @author Haiway  on 2019/5/30
 */
@Data
public class FAQMessage {
    private String name;
    private String message;
    private String taskId;
    private String faqSessionId;
    private Map<String,Object> variable;
}
