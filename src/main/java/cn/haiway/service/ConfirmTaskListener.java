package cn.haiway.service;

import cn.haiway.pojo.FAQMessage;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Haiway  on 2019/5/31
 */
@Service
public class ConfirmTaskListener implements TaskListener {

    @Autowired
    TaskService taskService;
    @Resource
    private SimpMessagingTemplate template;

    @Override
    public void notify(DelegateTask delegateTask) {
        Map<String, Object> variables = delegateTask.getVariables();
        System.out.println("task listener:"+delegateTask.getVariables());

        FAQMessage message = new FAQMessage();
        message.setMessage("<h3>请确认下面消息</h3></br>开始时间:"+variables.get("starttime") +
                "</br>结束时间:"+variables.get("endtime")+
                "</br>出发地：上海"+
                "<br>目的地："+variables.get("location")+
                "</br>出差原因："+variables.get("reason"));
        message.setTaskId(delegateTask.getId());
        message.setVariable(variables);
        message.setFaqSessionId(variables.get("faqSessionId")+"");
        template.convertAndSend("/topic/callback", message);
    }
}
