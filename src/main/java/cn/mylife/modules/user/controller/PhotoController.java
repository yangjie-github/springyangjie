package cn.mylife.modules.user.controller;

import cn.mylife.commenservice.FileService;
import cn.mylife.commenservice.MsgJava;
import cn.mylife.modules.user.entity.Photo;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.serviceimpl.PhotoServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

/**
 * @author yangjie
 * @date 2018/11/24 15:10
 */
@Controller
@RequestMapping(value = "photo")
public class PhotoController {

    @Autowired
    private PhotoServiceImpl photoService;
    @Value("${roleSearchImg}")
    private String solrImg;

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
        model.addAttribute("allPhotos", photoService.findAll());
        return "modules/photo/photoList";
    }

    @RequestMapping(value = "save")
    @ResponseBody
    public MsgJava save(MultipartFile[] files, HttpServletRequest request, String photoName) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            photoService.saveMulti(files, user, photoName);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }

    @RequestMapping(value = "readImg")
    public void readThumImg(String imgId, HttpServletResponse response) throws IOException {
        Photo photoById = photoService.findById(imgId);
//        String place = photoById.getThumPlace();
        //设置响应头和客户端保存的文件名
//        FileService.readFile(response, photoById.getName(), place);
    }

    @RequestMapping(value = "readOriginImg")
    public void readOriginImg(String imgId, HttpServletResponse response) throws IOException {
        Photo photoById = photoService.findById(imgId);
//        String place = photoById.getPlace();
        //设置响应头和客户端保存的文件名
//        FileService.readFile(response, photoById.getName(), place);
    }

    @RequiresPermissions(value = "photo:delete")
    @RequestMapping(value = "delete")
    @ResponseBody
    public MsgJava delete(Photo photo) {
        try {
            photoService.delete(photo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }

    @RequestMapping(value = "readBackGroundImg")
    public void readBackGroundImg(HttpServletResponse response) throws IOException {
        FileService.readFile(response, "", solrImg);
    }

}
