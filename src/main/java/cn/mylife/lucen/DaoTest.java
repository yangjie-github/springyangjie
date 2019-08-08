//package cn.mylifeyangjietest;
//
//import cn.mylife.entity.User;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.Test;
//
//import java.io.InputStream;
//import java.util.Date;
//
///**
// * @author yangjie
// * 2018/11/11 11:54
// */
//public class DaoTest {
//
//    @Test
//    public void insertUser() throws Exception {
//        /**
//         * 加载核心配置文件
//         */
//        String source = "mybatis/mybatis.xml";
//
//        InputStream in = Resources.getResourceAsStream(source);
//
//        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
//
//        SqlSession sqlSession = build.openSession();
//
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//        System.out.println(userMapper);
//        User user = new User();
//        user.setId("123");
//        user.setLoginName("杨杰");
//        user.setPassWord("123");
//        user.setCreateDate(new Date());
//        user.setUpdateDate(new Date());
//        user.setPhone("182");
//        user.setDelFlag(false);
//        user.setEmail("eamil");
//        System.out.println(user);
//        userMapper.addUser(user);
//        System.out.println(user);
//        sqlSession.commit();
//        sqlSession.close();
//    }
//}
