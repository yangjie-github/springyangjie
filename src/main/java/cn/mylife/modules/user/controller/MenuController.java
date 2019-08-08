package cn.mylife.modules.user.controller;

import cn.mylife.modules.user.serviceimpl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author yangjie
 * @date 2019/1/5 16:26
 */
@RequestMapping(value = "menu")
@Controller
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @RequestMapping(value = "list")
    public String findList(Model model) {
        model.addAttribute("menuList", menuService.findList());
        return "modules/menu/menuList";
    }

}
