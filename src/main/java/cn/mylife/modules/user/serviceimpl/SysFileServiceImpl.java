package cn.mylife.modules.user.serviceimpl;

import cn.mylife.commenservice.UploadPhotoService;
import cn.mylife.modules.user.dao.SysFileMapper;
import cn.mylife.modules.user.entity.SysFile;
import cn.mylife.modules.user.entity.SysFileExample;
import cn.mylife.modules.user.servicein.FileServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author yangjie
 * 2019/1/12 12:38
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SysFileServiceImpl implements FileServiceIn {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private SysFileMapper fileMapper;
    @Autowired
    private UploadPhotoService uploadPhotoService;

    @Override
    public void add(SysFile myFile) {
        fileMapper.insert(myFile);
    }

    @Override
    public void delete(String id) {
        fileMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(SysFile sysFile) {

    }

    @Override
    public SysFile findById(String id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysFile> findList() {
        return fileMapper.selectByExample(new SysFileExample());
    }

    public SysFile save(MultipartFile multipartFile, SysFile myFile) {

        String path = filePath + System.currentTimeMillis() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        File reallyPath = new File(path);
        String uploadPath = uploadPhotoService.upload(multipartFile, reallyPath, path);
        SysFile sysFile = new SysFile();
        sysFile.setCreateDate(new Date());
        sysFile.setUpdateDate(new Date());
        sysFile.setDelFlag(false);
        sysFile.setName(myFile.getName());
        sysFile.setPath(uploadPath);
        sysFile.setType(myFile.getType());
        sysFile.setSize((int) multipartFile.getSize());
        fileMapper.insert(sysFile);

        return sysFile;
    }
}
