package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.delegate.Expression;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;
import java.util.Scanner;

/**
 * @author Haiway  on 2019/5/27
 */
@Slf4j
public class ReasonService implements JavaDelegate {

    private Expression question;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String q = (String)question.getValue(delegateExecution);

        Map<String,Object> v = delegateExecution.getVariables();
        Scanner scanner = new Scanner(System.in);
        System.out.println(q);
        String reason = scanner.nextLine();
        v.put("reason",reason);

        log.info("去干什么补全:"+v);

        delegateExecution.setVariables(v);
    }
}
