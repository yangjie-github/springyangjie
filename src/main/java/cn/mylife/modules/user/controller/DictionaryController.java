package cn.mylife.modules.user.controller;

import cn.mylife.modules.user.entity.Dictionary;
import cn.mylife.modules.user.serviceimpl.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author yangjie
 * 2019/1/29 20:20
 */
@Controller
@RequestMapping(value = "dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryServiceImpl dictionaryService;

    @RequestMapping(value = "list")
    public String findList(Dictionary dictionary, Model model) {

        List<Dictionary> listByType = dictionaryService.findListByType();

        model.addAttribute("list", listByType);

        return "modules/dictionary/list";
    }

    @RequestMapping(value = "form")
    public String form(Dictionary dictionary, RedirectAttributes redirectAttributes) {

        return "modules/dictionary/form";
    }

    @RequestMapping(value = "add")
    public String add(Dictionary dictionary, RedirectAttributes redirectAttributes) {

        try {
            dictionaryService.add(dictionary);
            redirectAttributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/dictionary/list";
    }

    @RequestMapping(value = "delete")
    public String delete(String id, RedirectAttributes redirectAttributes) {

        try {
            dictionaryService.delete(id);
            redirectAttributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/dictionary/list";
    }
}
