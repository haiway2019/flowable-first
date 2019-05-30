package cn.haiway.process;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Haiway  on 2019/5/30
 */
@Service
public class DeployService {
    @Resource
    ProcessEngine processEngine;
    @Resource
    RepositoryService repositoryService;
    @Resource
    RuntimeService runtimeService;

    public void deploy(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("a.bpmn20.xml")
                .deploy();


        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());

        Scanner scanner = new Scanner(System.in);
        System.out.println("what do you want?");
        String aim = scanner.nextLine();
        System.out.println("where do you go?");
        String location = scanner.nextLine();

        Map<String, Object> variables = new HashMap<>(10);
        variables.put("aim", aim);
        variables.put("startTime",null);
        variables.put("endTime",null);
        variables.put("location",location);
        variables.put("reason",null);

         runtimeService.startProcessInstanceByKey("approvide", variables);
    }

    public void startProcess(String processName,Map<String,Object> variables){
        runtimeService.startProcessInstanceByKey(processName, variables);
    }
}
