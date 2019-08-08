package cn.mylife.tld.manage.user;

import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.serviceimpl.UserServiceImpl;
import cn.mylife.utils.application.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Objects;

/**
 * user
 *
 * @author yangjie
 * @date 2019/1/5 10:06
 */
public class UserTld {

    private static UserServiceImpl userService = (UserServiceImpl) SpringContextUtil.getBean("userServiceImpl");
    /**
     * 获取当前登录者对象
     */
    public static User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        final String userName = (String) subject.getPrincipal();
        if (Objects.isNull(userName)) {
            return new User();
        } else {
            return userService.findOneUserByLoginName(userName);
        }
    }
}
