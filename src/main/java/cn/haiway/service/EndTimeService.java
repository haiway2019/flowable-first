package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Haiway  on 2019/5/27
 */
@Slf4j
@Service
public class EndTimeService implements JavaDelegate {

    @Resource
    private SimpMessagingTemplate template;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        Map<String,Object> v = delegateExecution.getVariables();

        Task task = taskService.createTaskQuery().taskId("").singleResult();

        template.convertAndSend("/topic/callback", "什么时候回?");

        Scanner scanner = new Scanner(System.in);
        System.out.println("什么时候回?");
        String endTime = scanner.nextLine();
        v.put("endTime",endTime);
        log.info("什么时候回补全:"+v);

        delegateExecution.setVariables(v);
    }
}
