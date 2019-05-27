package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Haiway  on 2019/5/27
 */
@Slf4j
public class StartTimeService  implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        Map<String,Object> v = delegateExecution.getVariables();

        Scanner scanner = new Scanner(System.in);
        System.out.println("什么时候去?");
        String startTime = scanner.nextLine();

        v.put("startTime",startTime);

        log.info("什么时候去补全:"+v);
        delegateExecution.setVariables(v);
    }
}
