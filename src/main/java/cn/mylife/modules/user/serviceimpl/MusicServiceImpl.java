package cn.mylife.modules.user.serviceimpl;

import cn.mylife.commenservice.UploadPhotoService;
import cn.mylife.modules.user.dao.MusicMapper;
import cn.mylife.modules.user.entity.Music;
import cn.mylife.modules.user.entity.MusicExample;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.servicein.MusicServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author yangjie
 * @date 2018/12/4 16:56
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class MusicServiceImpl implements MusicServiceIn {

//    private static final String PATH = "E:/yangjie/music/";
    private static final String PATH = "/yangjie/music/";

    @Autowired
    private MusicMapper musicMapper;
    @Autowired
    private UploadPhotoService uploadPhotoService;

    @Override
    public void add(Music music) {
        musicMapper.insert(music);
    }

    @Override
    public void delete(String id) {
        musicMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Music music) {

    }

    @Override
    public Music findById(String id) {
        return musicMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Music> findList() {
        return musicMapper.selectByExample(new MusicExample());
    }

    public void saveMulti(MultipartFile[] files, User user) {
        for (MultipartFile file : files) {
            String path = PATH + System.currentTimeMillis() + file.getOriginalFilename();
            File reallyPath = new File(path);
            String uploadPath = uploadPhotoService.upload(file, reallyPath, path);
            Music music = new Music();
            music.setCreateDate(new Date());
            music.setUpdateDate(new Date());
            music.setDelFlag(false);
            music.setPlace(uploadPath);
            music.setName(file.getOriginalFilename());
//            music.setUser(user);
//            musicMapper.add(music);
        }

    }
}
