package cn.mylife.commenservice;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author yangjie
 * 2018/11/25 12:23
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ThumbnailService {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    //图片水印位置
    private static final String WATERMARK = "E:/yangjie/photo/QQ20181126124716.png";
//    private static final String WATERMARK = "/yangjie/photo/QQ20181126124716.png";

    //视频水印位置
    private static final String VIDEOWATERMARK = "E:/yangjie/photo/20181209132310.png";
//    private static final String VIDEOWATERMARK = "/yangjie/photo/20181209132310.png";
    /**
     * @param file 原图文件
     * @param realPath 保存缩略图的文件夹
     *
     * @return 返回
     */
    public String thumbnail(MultipartFile file, String realPath) {

        //缩略图在服务器保存的位置
        String path = realPath + System.currentTimeMillis() + file.getOriginalFilename();
        //生成缩略图并保存到path
        try {
            Thumbnails.of(file.getInputStream())
                    .size(WIDTH, HEIGHT)
                    .keepAspectRatio(false)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(WATERMARK)), 0.5f)
                    .outputQuality(0.8f)
                    .toFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * @param file 视频文件
     * @param path 保存视频缩略图的位置
     */
    public static String fetchFrame(MultipartFile file, String path) throws Exception {
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(file.getInputStream());
        ff.start();
        int length = ff.getLengthInFrames();
        int i = 0;
        Frame f = null;
        while (i < length) {
            // 去掉前5帧，避免出现全黑的图片，依自己情况而定
            f = ff.grabImage();
            if ((i > 5) && (f.image != null)) {
                break;
            }
            i++;
        }
        //缩略图位置
        Thumbnails.of(FrameToBufferedImage(f))
                .size(WIDTH, HEIGHT)
                .keepAspectRatio(false)
                .watermark(Positions.CENTER, ImageIO.read(new File(VIDEOWATERMARK)), 0.5f)
                .outputQuality(0.8f)
                .toFile(path);
        ff.flush();
        ff.stop();
        return path;
    }

    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage;
        bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }


    public void testFunction() throws IOException {
        //指定大小进行缩放
        //size(宽度, 高度)
        /*
         * 若图片横比200小，高比300小，不变
         * 若图片横比200小，高比300大，高缩小到300，图片比例不变
         * 若图片横比200大，高比300小，横缩小到200，图片比例不变
         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
         */
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(200, 300)
                .toFile("c:/a380_200x300.jpg");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(2560, 2048)
                .toFile("c:/a380_2560x2048.jpg");

        //按照比例缩放
        //scale(比例)
        Thumbnails.of("images/a380_1280x1024.jpg")
                .scale(0.25f)
                .toFile("c:/a380_25%.jpg");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .scale(1.10f)
                .toFile("c:/a380_110%.jpg");

        //不按照比例缩放
        //keepAspectRatio(false) 默认是按照比例缩放的
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile("c:/a380_200x200.jpg");

        //旋转
        //rotate(角度),正数：顺时针 负数：逆时针
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .rotate(90)
                .toFile("c:/a380_rotate+90.jpg");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .rotate(-90)
                .toFile("c:/a380_rotate-90.jpg");

        //水印
        //watermark(位置，水印图，透明度)
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f)
                .toFile("c:/a380_watermark_bottom_right.jpg");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f)
                .toFile("c:/a380_watermark_center.jpg");

        //裁剪

        //sourceRegion()

        //图片中心400*400的区域
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(Positions.CENTER, 400,400)
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_center.jpg");

        //图片右下400*400的区域
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(Positions.BOTTOM_RIGHT, 400,400)
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_bootom_right.jpg");

        //指定坐标
        Thumbnails.of("images/a380_1280x1024.jpg")
                .sourceRegion(600, 500, 400, 400)
                .size(200, 200)
                .keepAspectRatio(false)
                .toFile("c:/a380_region_coord.jpg");

        //转换图像格式
        //outputFormat(图像格式)
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .outputFormat("png")
                .toFile("c:/a380_1280x1024.png");

        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .outputFormat("gif")
                .toFile("c:/a380_1280x1024.gif");

        //输出到OutputStream
        //toOutputStream(流对象)
        OutputStream os = new FileOutputStream("c:/a380_1280x1024_OutputStream.png");
        Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .toOutputStream(os);

        //输出到BufferedImage
        //asBufferedImage() 返回BufferedImage
        BufferedImage thumbnail = Thumbnails.of("images/a380_1280x1024.jpg")
                .size(1280, 1024)
                .asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File("c:/a380_1280x1024_BufferedImage.jpg"));


    }

}
