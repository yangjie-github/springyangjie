package cn.mylife.modules.user.serviceimpl;

import cn.mylife.modules.user.dao.MenuMapper;
import cn.mylife.modules.user.entity.Menu;
import cn.mylife.modules.user.entity.MenuExample;
import cn.mylife.modules.user.servicein.MenuServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yangjie
 * @date 2019/1/5 16:17
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MenuServiceImpl implements MenuServiceIn {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void add(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void delete(String id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Menu menu) {

    }

    @Override
    public Menu findById(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> findList() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
