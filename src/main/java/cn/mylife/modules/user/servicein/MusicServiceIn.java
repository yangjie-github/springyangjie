package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.Music;

import java.util.List;

/**
 * @author yangjie
 * @date 2018/12/4 16:56
 */
public interface MusicServiceIn {

    /**
     * 添加
     *
     * @param music Music
     */
    void add(Music music);

    /**
     * 删除
     *
     * @param id MusicId
     */
    void delete(String id);

    /**
     * 修改
     *
     * @param music Music
     */
    void update(Music music);

    /**
     * 查询
     *
     * @param id MusicId
     * @return Music Music
     */
    Music findById(String id);

    /**
     * 查询list
     *
     * @return List Music Music
     */
    List<Music> findList();

}
