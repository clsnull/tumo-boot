package cn.tycoding.boot;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TumoBootApp.class)
public class ActivitiDemo {

    @Autowired
    ProcessEngine processEngine;

    @Test
    public void testDeployment() {

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment().name("出差申请流程").addClasspathResource("bpmn/bpmn.bpmn20.xml").addClasspathResource("bpmn/bpmn.png").deploy();

        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void testStartProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("bpmn");
        String processDefinitionId = processInstance.getProcessDefinitionId();
        System.out.println("流程定义id" + processDefinitionId);
        String id = processInstance.getId();
        System.out.println("流程实例id" + id);
        String activityId = processInstance.getActivityId();
        System.out.println("流程活动id" + activityId);
    }

    /**
     * 查询个人待执行的任务
     */
    @Test
    public void testFindPersonalTastList() {
        TaskService taskService = processEngine.getTaskService();

        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("bpmn")
                .taskAssignee("张三")
                .list();

        for (Task task : list) {
            System.out.println("流程实例id=" + task.getProcessInstanceId());
            System.out.println("任务id=" + task.getId());
            System.out.println("负责人=" + task.getAssignee());
            System.out.println("任务名称" + task.getName());
        }

    }

    @Test
    public void complateTask(){
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("8ef6066f-8c90-11ec-ade7-38f3ab8718b6");

    }
}
