package cn.mylife.utils.shiro;

import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.serviceimpl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yangjie
 * 2018/6/6 20:29
 *
 * 授权流程：调用isPermission,由securityManage进行授权
 */

@Controller
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;
//    @Autowired
//    private


//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Principal principal = (Principal) getAvailablePrincipal(principals);
//        userSsoCheck(principal);
//
//        User user = getSystemService().getUserByLoginName(principal.getLoginName());
//        if (user != null) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            List<Menu> list = UserUtils.getMenuList();
//            for (Menu menu : list){
//                if (StringUtils.isNotBlank(menu.getPermission())){
//                    // 添加基于Permission的权限信息
//                    for (String permission : StringUtils.split(menu.getPermission(),",")){
//                        info.addStringPermission(permission);
//                    }
//                }
//            }
//            // 添加用户权限
//            info.addStringPermission("user");
//            // 添加用户角色信息
//            for (Role role : user.getRoleList()){
//                info.addRole(role.getEnname());
//            }
//            // 更新登录IP和时间
//            getSystemService().updateUserLoginInfo(user);
//
//            // 记录登录日志
//            LogUtils.saveLog(Servlets.getRequest(), "系统登录");
//            return info;
//        } else {
//            return null;
//        }
//    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从principalCollection中获取用户登录信息
        Object principal = principalCollection.getPrimaryPrincipal();

        //创建info实例
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        /*利用登陆的用户信息判断当前用户的角色或权限（查询数据库）*/
        User user = userService.findOneUserByLoginName((String) principal);

//        info.addRole(role.getEnname());

        if (Objects.nonNull(user) && user.getLoginName().trim().equals("yangjie")) {
            info.addStringPermission("photo:delete");
        }

        /*创建SimpleAuthorizationInfo，并设置roles属性，返回info对象*/
        return info;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
        //2. 利用 username 查询数据库得到用户的信息.(String) principal将表单输入的userName转换为自字符串,再去数据库查找到对应的对象
        User user = userService.findOneUserByLoginName((String) principal);
        String userPassWord;
        if (Objects.isNull(user)) {
            throw new UnknownAccountException("用户不存在");
        }
        userPassWord = user.getPassWord();
        //3.获取密码
        String credentials = userPassWord;
        //4.设置盐值  盐值是ByteSource类型,此处盐值是用户名
        String source = (String)principal;
        ByteSource credentialsSalt = ByteSource.Util.bytes(source);
        //5.当前 Realm 的name
        String realmName = getName();
        //6.返回值实例化
        /* principal：认证的试实体信息，可以是username,可以是实体对象
         * credentials：数据库中获取的密码
         * credentialsSalt：盐值
         * realmName：当前对象的name,调用父类的getName()方法即可。
         */
        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }

    /**
     * 已改为init-method 配置 ，另外一种配置加密的方式
     */
//    public void setCredentialMatcher(){
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
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
        String saltSource = "liujia";
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        Object salt = ByteSource.Util.bytes(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    /**
     * 授权用户信息
     */
//    public static class Principal implements Serializable {
//
//        private static final long serialVersionUID = 1L;
//
//        private String id;
//        private String loginName;
//        private boolean mobileLogin;
//
//        public Principal(User user, boolean mobileLogin) {
//            this.id = user.getId();
//            this.loginName = user.getLoginName();
//            this.mobileLogin = mobileLogin;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public String getLoginName() {
//            return loginName;
//        }
//
//        public boolean isMobileLogin() {
//            return mobileLogin;
//        }
//
//        /**
//         * 获取SESSIONID
//         */
//        public String getSessionid() {
//            try{
//                return UserUtils.getSession() == null? "" :
//                        (String) UserUtils.getSession().getId();
//            }catch (Exception e) {
//                return "";
//            }
//        }
//
//        @Override
//        public String toString() {
//            return id;
//        }
//
//    }
}