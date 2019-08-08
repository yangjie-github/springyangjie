package cn.mylife.commenservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yangjie
 * @date 2018/12/6 13:19
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class FileService {

    public static void readFile(HttpServletResponse response, String name, String place) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + name);
        try (InputStream is = new FileInputStream(place); OutputStream os = response.getOutputStream()) {
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("文件下载失败");
        }
    }
}
