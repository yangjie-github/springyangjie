package cn.mylife.modules.user.controller;

import cn.mylife.commenservice.FileService;
import cn.mylife.commenservice.MsgJava;
import cn.mylife.modules.user.entity.Music;
import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.serviceimpl.MusicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2018/12/4 17:13
 */
@Controller
@RequestMapping(value = "music")
public class MusicController {

    @Autowired
    private MusicServiceImpl musicService;

    @ModelAttribute
    public void ahead(Model model, HttpServletRequest request) {
        final User user = (User)request.getSession().getAttribute("user");
        if (Objects.nonNull(user)) {
            model.addAttribute("user", user);
            model.addAttribute("username", user.getLoginName());
        }
    }

    @RequestMapping(value = "list")
    public String findList(Model model) {
        model.addAttribute("allMusic", musicService.findList());
        return "modules/music/musicList";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public MsgJava add(MultipartFile[] files, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            musicService.saveMulti(files, user);
        } catch (Exception e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }

    @RequestMapping(value = "readMusic")
    public void readMusic(String id, HttpServletResponse response) throws IOException {
        Music music = musicService.findById(id);
        String place = music.getPlace();
        //设置响应头和客户端保存的文件名
        FileService.readFile(response, music.getName(), place);
    }

    @RequestMapping(value = "delete")
    @ResponseBody
    public MsgJava delete(Music music) {
        try {
            musicService.delete(music.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return MsgJava.fail();
        }
        return MsgJava.success();
    }
}
