package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * @author Haiway  on 2019/5/27
 */
@Slf4j
public class InitService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        Map<String,Object> v = delegateExecution.getVariables();

        v.put("startTime",v.get("user_time"));
        v.put("location",v.get("user_location"));
        log.info("初始化数据:"+v);

        delegateExecution.setVariables(v);
    }
}
