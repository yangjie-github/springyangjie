package cn.mylife.modules.user.servicein;

import cn.mylife.modules.user.entity.SysFile;

import java.util.List;

/**
 * @author yangjie
 * 2019/1/12 12:36
 */
public interface FileServiceIn {

    /**
     * 添加
     *
     * @param sysFile SysFile
     */
    void add(SysFile sysFile);

    /**
     * 删除
     *
     * @param id fileId
     */
    void delete(String id);

    /**
     * 修改
     *
     * @param sysFile sysFile
     */
    void update(SysFile sysFile);

    /**
     * 查询
     *
     * @param id fileId
     * @return SysFile file
     */
    SysFile findById(String id);

    /**
     * 查询list
     *
     * @return List SysFile file
     */
    List<SysFile> findList();
}
