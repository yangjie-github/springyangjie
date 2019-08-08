package cn.mylife.modules.user.serviceimpl;

import cn.mylife.commenservice.ThumbnailService;
import cn.mylife.commenservice.UploadPhotoService;
import cn.mylife.modules.user.dao.VideoMapper;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.entity.Video;
import cn.mylife.modules.user.entity.VideoExample;
import cn.mylife.modules.user.servicein.VideoServiceIn;
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
 * 2018/12/9 10:01
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class VideoServiceImpl implements VideoServiceIn {

    @Value("${videoPath}")
    private String video_path;
    @Value("${thumPath}")
    private String thum_path;

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UploadPhotoService uploadPhotoService;

    @Override
    public void addVideo(Video video) {
//        videoMapper.insert(video);
    }

    @Override
    public void updateVideo(Video video) {

    }

    @Override
    public void deleteVideo(String id) {
//        videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Video> findList() {
        return videoMapper.selectByExample(new VideoExample());
    }

    @Override
    public Video findById(String id) {
        return null;
    }

    public void uploadVideo(MultipartFile file, String name, User user) throws Exception {
        //保存视频的所缩略图
        String path = thum_path + System.currentTimeMillis() + ".jpg";
        String thumPath = ThumbnailService.fetchFrame(file, path);

        String videoPath = video_path + System.currentTimeMillis() + file.getOriginalFilename();
        File reallyPath = new File(videoPath);
        String uploadPath = uploadPhotoService.upload(file, reallyPath, videoPath);

        Video video = new Video();
        video.setCreateDate(new Date());
        video.setUpdateDate(new Date());
        video.setDelFlag(false);
//        video.setPlace(uploadPath);
//        video.setThumPath(thumPath);
//        video.setName(name);
//        video.setUser(user);
//        videoMapper.addVideo(video);
    }

}
