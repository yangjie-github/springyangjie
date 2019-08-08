package cn.mylife.modules.user.serviceimpl;

import cn.mylife.modules.user.dao.UserMapper;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.entity.UserExample;
import cn.mylife.modules.user.servicein.UserServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangjie
 * @date 2018/11/17 14:42
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserServiceIn {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserByLoginName(String name) {

        final UserExample userExample = new UserExample();

        userExample.createCriteria().andLoginNameEqualTo(name);

        return userMapper.selectByExample(userExample);
    }

    @Override
    public User findOneUserByLoginName(String name) {

        final UserExample userExample = new UserExample();

        userExample.createCriteria().andLoginNameEqualTo(name);

        final List<User> users = userMapper.selectByExample(userExample);

        if (users.isEmpty()) {
            return null;
        } else {
            return userMapper.selectByExample(userExample).get(0);
        }
    }

    @Override
    public List<User> findUserByEmail(String email) {

        final UserExample userExample = new UserExample();

        userExample.createCriteria().andEmailEqualTo(email);

        return userMapper.selectByExample(userExample);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(User user) {

    }
}
