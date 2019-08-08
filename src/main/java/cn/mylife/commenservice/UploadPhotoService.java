package cn.mylife.commenservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author yangjie
 * 2018/11/25 14:04
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UploadPhotoService {

    /**
     * @param realPath File对象
     * @param path 文件存放的位置
     *
     * @return String 文件存放的位置
     */
    public String upload(MultipartFile file, File realPath, String path) {

        //生成缩略图并保存到realPath
        try {
            file.transferTo(realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
