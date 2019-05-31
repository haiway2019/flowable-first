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
public class StartTimeTaskListener implements TaskListener {

    @Autowired
    TaskService taskService;
    @Resource
    private SimpMessagingTemplate template;

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("id:"+delegateTask.getId());

        Map<String, Object> variables = delegateTask.getVariables();

        System.out.println("task listener:"+delegateTask.getVariables());

        FAQMessage message = new FAQMessage();
        message.setMessage(variables.get("say")+"");
        message.setTaskId(delegateTask.getId());
        message.setVariable(variables);
        template.convertAndSend("/topic/callback", message);
    }
}
