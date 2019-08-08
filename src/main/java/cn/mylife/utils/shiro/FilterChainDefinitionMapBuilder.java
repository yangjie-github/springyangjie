package cn.mylife.utils.shiro;

import java.util.LinkedHashMap;

/**
 * @author yangjie
 * 2018/6/17 16:53
 *
 * 权限配置引入的map实例
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> builderFilterChainDefinitionMap(){

        LinkedHashMap<String, String>  map = new LinkedHashMap<>();
        map.put("/user/loginOut","logout");
        map.put("/index.jsp","anon");
//        map.put("/activiti/create","anon");
        /*
         * 首页、 登录页、 注册页面、 登录名验证、 邮箱验证
         */
        map.put("/user/index","anon");
        map.put("/user/loginForm","anon");
        map.put("/user/login","anon");
        map.put("/user/registerForm","anon");
        map.put("/user/checkLoginNameUnique","anon");
        map.put("/user/checkEmailUnique","anon");
        map.put("/user/register","anon");
        map.put("/user/sendEmailCode","anon");
        map.put("/user/resetPassWordAuthenForm","anon");
        map.put("/user/resetPassWordAuthen","anon");
        map.put("/user/setPassWord","anon");
        map.put("/photo/save","anon");
        map.put("/photo/readImg","anon");
        map.put("/photo/readOriginImg","anon");
        map.put("/photo/list","anon");
        map.put("/music/list","anon");
        map.put("/music/readMusic","anon");
        map.put("/video/list","anon");
        map.put("/video/readVideoImg","anon");
        map.put("/video/readVideo","anon");

        map.put("/User/register","anon");
        map.put("/user/userNameIsExist","anon");
        //必须是认证之后，并有user权限才可以访问
//        map.put("/User.jsp","authc,roles[User]");
//        map.put("/admin.jsp","authc,roles[admin]");
//        //配置permission
//        map.put("/items/queryItems","perms[item:query]");
//        //只要是通过记住我或者认证登录都可以访问
//        map.put("/list.jsp","User");
        map.put("/static/**", "anon");
        map.put("/views/static/**", "anon");
        map.put("/**","authc");
        return map;
    }

}
