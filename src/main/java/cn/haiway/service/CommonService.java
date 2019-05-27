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
public class CommonService implements JavaDelegate {

    private Expression question;
    private Expression key;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String q = (String)question.getValue(delegateExecution);
        String kv = (String)key.getValue(delegateExecution);

        Map<String,Object> v = delegateExecution.getVariables();
        Scanner scanner = new Scanner(System.in);
        System.out.println(q);
        String reason = scanner.nextLine();
        v.put(kv,reason);

        log.info("变量信息："+v);
        delegateExecution.setVariables(v);
    }
}
