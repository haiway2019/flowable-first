package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Map;

/**
 * @author Haiway  on 2019/5/27
 */
@Slf4j
public class HttpRestService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        Map<String,Object> v = delegateExecution.getVariables();
        v.put("aim","出差");
        v.put("startTime",null);
        v.put("endTime",null);
        v.put("location",null);
        v.put("reason",null);


        delegateExecution.setVariables(v);
    }
}
