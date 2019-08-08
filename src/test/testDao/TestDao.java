//package testDao;
//
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.UUID;
//
///**
// * @author yangjie
// * 2018/6/1 19:53
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
//public class TestDao {
//
//    @Autowired
//    YjUserMapper userMapper;
//    @Autowired
//    SqlSession sqlSession;
//    @Autowired
//    YjUserRoleMapper userRoleMapper;
//
//    @Test
//    public void test1(){
//        List<YjUser> users = userMapper.findUserLikeName("i");
//        for (YjUser user : users) {
//            System.out.println(user.getUserName());
//        }
//    }
//
//    @Test
//    public void test2(){
//        YjUser yjUser = new YjUser();
//        yjUser.setUserName("2");
//        yjUser.setUserPassword("123456");
//        userMapper.addUser(yjUser);
//        System.out.println(yjUser.getId());
//    }
//
//    @Test
//    public void test3(){
//        YjUser yjUser = new YjUser();
//        yjUser.setId(6);
//        yjUser.setUserName("3");
//        yjUser.setUserPassword("3");
//        userMapper.updateUser(yjUser);
//    }
//
//    @Test
//    public void test4(){
//        userMapper.deleteUserById(5);
//    }
//
//    @Test
//    public void test5(){
//        Integer integer = userMapper.countUsers();
//        System.out.println(integer);
//    }
//
//    @Test
//    public void test6(){
//        List<YjUser> yjUsers = userMapper.testFindAllUser();
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test7(){
//        List<YjUser> yjUsers = userMapper.testFindAllUser2();
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test8(){
//        YjUser yjUser = new YjUser();
//        yjUser.setUserName("1");
//        yjUser.setUserPassword("123456");
//        List<YjUser> yjUsers = userMapper.findUserByNameAndPassword(yjUser);
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test9(){
//        Integer[] ids = new Integer[3];
//        ids[0] = 2;
//        ids[1] = 3;
//        ids[2] = 4;
//        List<YjUser> yjUsers = userMapper.selectUserByIds(ids);
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test10(){
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(3);
//        list.add(2);
//        List<YjUser> yjUsers = userMapper.selectUserByIdsList(list);
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test11(){
//        List<YjUser> yjUsers = userMapper.oneToOneSelectUsers();
//        for (YjUser user : yjUsers) {
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void test12(){
//        List<YjUserRole> yjUserRoles = userRoleMapper.selectOneToMany();
//        for (YjUserRole userRole : yjUserRoles) {
//            System.out.println(userRole);
//        }
//    }
//
////    @Test
////    public void testCRUD(){
////
////        //1.创建springIOC容器
////        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
////        //2.从容器中获取mapper
////        UserMapper contextBean = context.getBean(UserMapper.class);
////
////    }
//
//
//    /*使用springTest做单元测试
//    * 导入springTest模块 -> 导入pom文件
//    */
//
////    @Test
////    public void testCRUD2(){
//////        System.out.println(userMapper);
////        User User = new User();
////        User.setName("yangjie");
////        userMapper.insertSelective(User);
////        System.out.println(User);
////    }
//
////    @Test
////    //批量操作的测试
////    public void test1(){
////        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
////        for (int i = 0; i < 100; i++) {
////            String s = UUID.randomUUID().toString().substring(0, 10) + i;
////            mapper.insertSelective(new User(s));
////        }
////    }
//
////    @Test
////    //批量操作的测试
////    public void test3(){
////        User User = userMapper.selectByPrimaryKey(10);
////        System.out.println(User);
////    }
//}
