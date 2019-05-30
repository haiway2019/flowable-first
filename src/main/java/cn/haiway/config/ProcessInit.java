package cn.haiway.config;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Haiway  on 2019/5/30
 */
@Configuration
public class ProcessInit {
    @Resource
    RepositoryService repositoryService;
    @Resource
    RuntimeService runtimeService;

    @PostConstruct
    public void deployApproach(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("a.bpmn20.xml")
                .deploy();

        repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
    }
}
