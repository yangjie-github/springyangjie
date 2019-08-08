package cn.mylife.modules.user.servicein;


import cn.mylife.modules.user.entity.User;

import java.util.List;

/**
 * @author yangjie
 * @date 2018/11/17 14:41
 */
public interface UserServiceIn {

    /**
     * 通过用户名查询用户实体
     *
     * @param name 用户名
     * @return User对象
     */
    List<User> findUserByLoginName(String name);

    /**
     * 通过用户名查询单个用户
     *
     * @param name 用户名
     * @return User对象
     */
    User findOneUserByLoginName(String name);

    /**
     * 通过邮箱号查询用户实体
     *
     * @param email 邮箱
     * @return User对象
     */
    List<User> findUserByEmail(String email);

    /**
     * 保存用户
     *
     * @param user User
     */
    void save(User user);

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return User User
     */
    User findUserById(String id);

    /**
     * 更新用户密码
     *
     * @param user User
     */
    void update(User user);
}
