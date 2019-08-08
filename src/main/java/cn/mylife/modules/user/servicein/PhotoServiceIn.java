package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.Photo;

import java.util.List;

/**
 * @author yangjie
 * @date 2018/11/24 14:57
 */
public interface PhotoServiceIn {

    /**
     * 保存照片
     *
     * @param photo Photo
     */
    void save(Photo photo);

    /**
     * 查找照片
     *
     * @param id id
     * @return Photo Photo
     */
    Photo findById(String id);

    /**
     * 查询所有
     *
     * @return List<Photo> Photo
     */
    List<Photo> findAll();

    /**
     * 删除照片
     *
     * @param photoId 照片id
     */
    void delete(String photoId);
}
