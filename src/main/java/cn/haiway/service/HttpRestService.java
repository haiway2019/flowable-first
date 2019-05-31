package cn.haiway.service;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.List;
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

        if(v.get("starttime")==null){
            v.put("starttime",null);
        }
        if(v.get("endtime")==null){
            v.put("endtime",null);
        }
        if(v.get("location")==null){
            v.put("location",null);
        }
        if(v.get("reason")==null){
            v.put("reason",null);
        }

        delegateExecution.setVariables(v);
    }
}
