package cn.mylife.modules.user.controller;

import cn.mylife.commenservice.FileService;
import cn.mylife.commenservice.MsgJava;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.entity.Video;
import cn.mylife.modules.user.serviceimpl.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author yangjie
 * 2018/12/9 10:08
 */
@Controller
@RequestMapping(value = "video")
public class VideoController {

    @Autowired
    private VideoServiceImpl videoService;

    @ModelAttribute
    public void ahead(Model model, HttpServletRequest request) {
        final User user = (User)request.getSession().getAttribute("user");
        if (Objects.nonNull(user)) {
            model.addAttribute("user", user);
            model.addAttribute("username", user.getLoginName());
        }
    }

    @RequestMapping(value = "list")
    public String list(Model model) {
        model.addAttribute("videoList", videoService.findList());
        return "modules/video/videoList";
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public MsgJava uploadVideo(MultipartFile files,
                               String name,
                               HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            videoService.uploadVideo(files, name, user);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }

    @RequestMapping(value = "readVideo")
    public void readVideo(String id, HttpServletResponse response) throws IOException {
        Video video = videoService.findById(id);
//        String place = video.getPlace();
        //设置响应头和客户端保存的文件名
//        FileService.readFile(response, video.getName(), place);
    }

    @RequestMapping(value = "readVideoImg")
    public void readVideoImg(String id, HttpServletResponse response) throws IOException {
        Video byId = videoService.findById(id);
//        String place = byId.getThumPath();
        //设置响应头和客户端保存的文件名
//        FileService.readFile(response, byId.getName(), place);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public MsgJava delete(Video video) {
        try {
            videoService.deleteVideo(video.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }
}
