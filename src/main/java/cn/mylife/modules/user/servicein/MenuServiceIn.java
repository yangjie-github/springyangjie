package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.Menu;

import java.util.List;

/**
 * @author yangjie
 * @date 2019/1/5 16:15
 */
public interface MenuServiceIn {

    /**
     * 添加
     *
     * @param menu menu
     */
    void add(Menu menu);

    /**
     * 删除
     *
     * @param id MenuId
     */
    void delete(String id);

    /**
     * 修改
     *
     * @param menu Menu
     */
    void update(Menu menu);

    /**
     * 查询
     *
     * @param id MenuId
     * @return Menu menu
     */
    Menu findById(String id);

    /**
     * 查询list
     *
     * @return List Menu menu
     */
    List<Menu> findList();
}
