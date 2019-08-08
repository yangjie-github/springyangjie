package cn.mylife.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * @author yangjie
 * 2018/8/31 18:54
 */
public class TestHelloWordActiviti {

    /**
     * 返回值就是流程引擎
     * 会从类路径下加载activiti.cfg.xml文件
     */
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 部署流程定义
     */
    @Test
    public void deploymentProcessDefinition() {

        //与流程定义和部署对象相关的Service
        Deployment deployment = processEngine.getRepositoryService()
                //创建一个部署对象
                .createDeployment()
                //添加部署名称
                .name("cn/mylife/activiti/helloWord")
                //从classpath的资源中加载，一次只能加载一个
                .addClasspathResource("cn/mylife/activiti/helloWord/helloWord.bpmn")
                .addClasspathResource("cn/mylife/activiti/helloWord/helloWord.png")
                //部署,返回一个部署队象
                .deploy();

        //部署id
        System.out.println(deployment.getId());
        //部署名称
        System.out.println(deployment.getName());

        //部署完成后向数据库的表中添加数据：act_re_deployment, act_re_procdef,
    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcessInstance() {

        String processInstanceByKey = "myProcess_1";

        //与正在执行的流程实例和执行对象相关的service
        ProcessInstance processInstance = processEngine.getRuntimeService()
                //使用流程定义的key启动，是helloWord.bpmn文件中的id的属性myProcess_1
                //使用key启动默认按照最新版本的流程定义启动
                //返回流程实例对象
                .startProcessInstanceByKey(processInstanceByKey);

        //流程实例Id
        System.out.println(processInstance.getId());
        //流程定义id
        System.out.println(processInstance.getProcessDefinitionId());
    }

    /**
     * 查询当前人的个人任务
     */
    @Test
    public void findMyPersonnelTask() {

        String assignee = "王五";

        //与正在执行的任务管理相关的service
        List<Task> list = processEngine.getTaskService()
                //创建任务查询对象
                .createTaskQuery()
                //指定个人任务查询， 指定办理人
                .taskAssignee(assignee)
                .list();

        for (Task lis : list) {
            //任务id
            System.out.println(lis.getId());
            //任务名称
            System.out.println(lis.getName());
            //任务的创建时间
            System.out.println(lis.getCreateTime());
            //任务的办理人
            System.out.println(lis.getAssignee());
            //流程实例id
            System.out.println(lis.getProcessInstanceId());
            //执行对象id
            System.out.println(lis.getExecutionId());
            //流程定义id
            System.out.println(lis.getProcessDefinitionId());
        }
    }

    /**
     * 完成我的任务
     */
    @Test
    public void completeMyPersonnelTask() {

        String taskId = "12502";

        //与正在执行的任务管理相关的service
        processEngine.getTaskService()
                //根据任务id完成
                .complete(taskId);
        System.out.println("任务完成");
    }
}
