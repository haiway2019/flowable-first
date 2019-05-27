package cn.haiway;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Haiway  on 2019/5/27
 */
public class Application {
    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
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


        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String, Object> variables = new HashMap<>(10);
        variables.put("aim", aim);
        variables.put("startTime",null);
        variables.put("endTime",null);
        variables.put("location",location);
        variables.put("reason",null);

        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("approvide", variables);


//        TaskService taskService = processEngine.getTaskService();
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
//        System.out.println("You have " + tasks.size() + " tasks:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + ") " + tasks.get(i).getName());
//
//        }
//
//        System.out.println("Which task would you like to complete?");
//        int taskIndex = Integer.valueOf(scanner.nextLine());
//        Task task = tasks.get(taskIndex - 1);
//        Map<String, Object> processVariables = taskService.getVariables(task.getId());
//        System.out.println(processVariables.get("employee") + " wants " +
//                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
//
//
//        //审批
//        boolean approved = scanner.nextLine().toLowerCase().equals("y");
//        variables = new HashMap<String, Object>();
//        variables.put("approved", approved);
//        taskService.complete(task.getId(), variables);
    }
}
