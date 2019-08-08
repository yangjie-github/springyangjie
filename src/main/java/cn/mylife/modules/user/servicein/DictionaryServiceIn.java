package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.Dictionary;

import java.util.List;

/**
 * @author yangjie
 * 2019/1/29 20:18
 */
public interface DictionaryServiceIn {


    /**
     * 添加
     *
     * @param dictionary Dictionary
     */
    void add(Dictionary dictionary);

    /**
     * 删除
     *
     * @param id DictionaryId
     */
    void delete(String id);

    /**
     * 修改
     *
     * @param dictionary Dictionary
     */
    void update(Dictionary dictionary);

    /**
     * 查询
     *
     * @param id DictionaryId
     * @return Dictionary dictionary
     */
    Dictionary findById(String id);

    /**
     * 查询list
     *
     * @return List Dictionary dictionary
     */
    List<Dictionary> findList();

    /**
     * 通过类型查找
     *
     * @return Dictionary dictionary
     */
    List<Dictionary> findListByType();
}
