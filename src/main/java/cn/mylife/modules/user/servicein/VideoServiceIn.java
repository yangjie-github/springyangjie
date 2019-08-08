package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.Video;

import java.util.List;

/**
 * @author yangjie
 * 2018/12/9 10:00
 */
public interface VideoServiceIn {

    /**
     * add
     *
     * @param video Video
     */
    void addVideo(Video video);

    /**
     * update
     *
     * @param video Video
     */
    void updateVideo(Video video);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteVideo(String id);

    /**
     * 查询所有
     *
     * @return list Video
     */
    List<Video> findList();

    /**
     * 查询一个
     *
     * @param id id
     * @return Video video
     */
    Video findById(String id);
}
