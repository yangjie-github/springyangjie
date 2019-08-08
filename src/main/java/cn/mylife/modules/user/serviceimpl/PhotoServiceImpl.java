package cn.mylife.modules.user.serviceimpl;

import cn.mylife.commenservice.ThumbnailService;
import cn.mylife.commenservice.UploadPhotoService;
import cn.mylife.modules.user.dao.PhotoMapper;
import cn.mylife.modules.user.entity.Photo;
import cn.mylife.modules.user.entity.PhotoExample;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.servicein.PhotoServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author yangjie
 * @date 2018/11/24 14:58
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class PhotoServiceImpl implements PhotoServiceIn {

    @Value("${thumPath}")
    private String thum_path;
    @Value("${photoPath}")
    private String photo_path;

    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private ThumbnailService thumbnailService;
    @Autowired
    private UploadPhotoService uploadPhotoService;

    @Override
    public void save(Photo photo) {
//        photoMapper.insert();
    }

    @Override
    public Photo findById(String id) {
        return photoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoMapper.selectByExample(new PhotoExample());
    }

    @Override
    public void delete(String photoId) {
        photoMapper.selectByPrimaryKey(photoId);
    }

    public void saveMulti(MultipartFile[] files, User user, String photoName) throws IOException {
        for (MultipartFile file : files) {
            String path = photo_path + System.currentTimeMillis() + file.getOriginalFilename();
            File reallyPath = new File(path);
            final String thumPath = thumbnailService.thumbnail(file, thum_path);
            String uploadPath = uploadPhotoService.upload(file, reallyPath, path);
            Photo photo = new Photo();
            photo.setCreateDate(new Date());
            photo.setDelFlag(false);
            photo.setUpdateDate(new Date());
            photo.setName(photoName);
//            photo.setPlace(uploadPath);
//            photo.setThumPlace(thumPath);
//            photo.setUser(user);
//            photoMapper.addPhoto(photo);
        }
    }
}
