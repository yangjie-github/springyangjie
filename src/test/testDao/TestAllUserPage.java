//package testDao;
//
//import com.github.pagehelper.PageInfo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
///**
// * @author yangjie
// * 2018/6/9 23:38
// */
//
////spring4在测试的时候需要servlet3.0的支持，在pom文件中修改servlet的版本
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml","classpath:springmvc/spring-mvc.xml"})
//public class TestAllUserPage {
//
//    //传入springMvc的ioc
//    @Autowired
//    WebApplicationContext context;
//
//    //虚拟的mvc请求
//    MockMvc mockMvc;
//
//    @Before
//    public void initMockMvc(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testAllUserPage() throws Exception{
//        //模拟请求，拿到返回值
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/User/allUser").param("pn", "5")).andReturn();
//        //在请求域中拿到对象
//        MockHttpServletRequest request = result.getRequest();
//        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
//        System.out.println("当前页码："+pageInfo.getPageNum());
//        System.out.println("总页码："+pageInfo.getPages());
//        System.out.println("总记录数："+pageInfo.getTotal());
//        int[] nums = pageInfo.getNavigatepageNums();
//        for ( int num : nums) {
//            System.out.println("连续显示的页码"+num);
//        }
//        //获取员工数据
//        List<User> list = pageInfo.getList();
//        for (User User : list) {
//            System.out.println(User.toString());
//        }
//    }
//}
