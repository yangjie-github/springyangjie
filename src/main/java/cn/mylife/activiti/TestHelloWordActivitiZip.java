package cn.mylife.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * @author yangjie
 * 2018/8/31 18:54
 */
public class TestHelloWordActivitiZip {

    /**
     * 返回值就是流程引擎
     * 会从类路径下加载activiti.cfg.xml文件
     */
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 压缩文件部署流程定义
     */
    @Test
    public void deploymentProcessDefinitionZip() {

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("cn/mylife/activiti/helloWord/helloword.zip");

        ZipInputStream zipInputStream = new ZipInputStream(in);

        //与流程定义和部署对象相关的Service
        Deployment deployment = processEngine.getRepositoryService()
                //创建一个部署对象
                .createDeployment()
                //添加部署名称
                .name("helloWordZip")
                //指定zip格式的文件完成部署
                .addZipInputStream(zipInputStream)
                .deploy();

        //部署id
        System.out.println(deployment.getId());
        //部署名称
        System.out.println(deployment.getName());
    }


    /**
     * 查询流程定义
     */
    @Test
    public void findProcessDefinition() {
        List<ProcessDefinition> list = processEngine.getRepositoryService()
                //创建流程定义查询
                .createProcessDefinitionQuery()
                //指定查询条件
                //使用部署对象id
                //.deploymentId(deploymentId);
                //使用流程定义id
                //.processDefinitionId(processDefinitionId)
                //使用流程定义的key查询
                //.processDefinitionKey(processDefinitionKey)
                //流程定义名称模糊查询
                //.processDefinitionNameLike(processDefinitionNameLike)

                //排序
                //版本的升序排序
                .orderByProcessDefinitionVersion().asc()

                //返回集合
                .list();
                //返回单个对象
                //.singleResult()
                //数量
                //.count()
                //分页查询
                //.listPage(firstResult, maxResult);

        for (ProcessDefinition li : list) {
            System.out.println(li.getId());
            System.out.println(li.getName());
            System.out.println(li.getKey());
            System.out.println(li.getVersion());
        }
    }

    /**
     * 删除流程定义
     */
    @Test
    public void deleteProcessDefinition() {

        String deleteDeployment = "1";
        processEngine.getRepositoryService()
                //使用部署id完成删除， 不带级联删除，只能删除没有启动的流程
                //.deleteDeployment(deleteDeployment);

                //带级联删除，可以删除启动的流程
                .deleteDeployment(deleteDeployment, true);
        System.out.println("完成删除");
    }

    /**
     * 查看流程图
     */
    @Test
    public void viewPic() throws IOException {
        //将资源数据库表的文件生成到某一个文件夹下
        //部署id
        String deploymentId = "2501";
        //获取图片资源名称
        String resourceName = "";
        List<String> deploymentResourceNames = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);
        for (String name : deploymentResourceNames) {
            if (name.contains(".png")) {
                resourceName = name;
            }
        }

        //获取图片输入流
        InputStream resourceAsStream = processEngine.getRepositoryService()
                .getResourceAsStream(deploymentId, resourceName);

        //将图片保存
        File file = new File("D:/" + resourceName);
        //经输入流的图片写入
        FileUtils.copyInputStreamToFile(resourceAsStream, file);
    }
}
