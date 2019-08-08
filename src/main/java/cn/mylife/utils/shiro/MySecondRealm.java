package cn.mylife.utils.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Set;

/**
 * 第二个realm,不同的数据库加密算法是不同的，比如mysql用MD5,oracle用SHA1
 *
 * @author yangjie
 * 2018/6/6 20:29
 */

@Controller
public class MySecondRealm extends AuthorizingRealm {

//    @Autowired
//    private UserMapper userMapper;

    private String passWord;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //暂未设置获取权限方法
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
        //2. 利用 username 查询数据库得到用户的信息.(String) principal将表单输入的userName转换为自字符串,再去数据库查找到对应的对象
//        User User = userMapper.findByName((String) principal);
//        if(User != null){
//            pass = User.getPass();
//        }else{
//            throw new UnknownAccountException("用户不存在");
//        }
        //获取密码
//        String credentials = passWord;
        /*3.设置盐值  盐值是ByteSource类型*/
        String source = (String)principal;
        Object credentials = "";
        if("admin".equals(source)){
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        }
        if("User".equals(source)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(source);

        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        /* principal：认证的试实体信息，可以是username,可以是实体对象
         * credentials：数据库中获取的密码
         * credentialsSalt：盐值
         * realmName：当前对象的name,调用父类的getName()方法即可。
         */
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }

    /**
     * init-method 配置 ，另外一种配置加密的方式
     */
//    public void setCredentialMatcher(){
//        HashedCredentialsMatcher  credentialsMatcher = new HashedCredentialsMatcher();
//        /*MD5算法加密*/
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        /*1024次循环加密*/
//        credentialsMatcher.setHashIterations(1024);
//        setCredentialsMatcher(credentialsMatcher);
//    }

    /**
     * 用来测试的算出密码password盐值加密后的结果
     */
    public static void main(String[] args) {
        String saltSource = "User";
        String hashAlgorithmName = "SHA1";
        String credentials = "123456";
        Object salt = ByteSource.Util.bytes(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

}
