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
public class ReasonService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        Map<String,Object> v = delegateExecution.getVariables();
        Scanner scanner = new Scanner(System.in);
        System.out.println("你要做什么呢?");
        String reason = scanner.nextLine();
        v.put("reason",reason);

        log.info("去干什么补全:"+v);

        delegateExecution.setVariables(v);
    }
}
