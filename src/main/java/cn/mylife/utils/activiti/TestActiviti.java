package cn.mylife.utils.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * activiti表的创建, 后面将会改为配置文件
 *
 * @author yangjie
 * 2018/8/19 21:59
 */
public class TestActiviti {

    public static void main (String[] args){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        //链接数据库配置
        engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        engineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/mylife?userUnicode=true&characterEncoding=utf8");
        engineConfiguration.setJdbcUsername("root");
        engineConfiguration.setJdbcPassword("root");

        //true ;如果表不存在，自动创建表 。 create-drop ; 先删除，在创建 。 false ;不能自动创建表，需要表存在
        engineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        //工作流的核心对象engineConfiguration创建表
        ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
    }

    //使用配置文件创建23张表
    @Test
    public void createTable() {
        ProcessEngineConfiguration engineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = engineConfigurationFromResource.buildProcessEngine();
    }
}
