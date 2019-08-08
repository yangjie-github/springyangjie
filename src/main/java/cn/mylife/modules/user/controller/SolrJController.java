package cn.mylife.modules.user.controller;

import cn.mylife.commenservice.solrjutils.SolrJClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author yangjie
 * @date 2019/2/23 12:18
 */
@Controller
@RequestMapping("SolrJ")
public class SolrJController {

    @Autowired
    private SolrJClientService solrJClientService;

    @RequestMapping(value = "deleteById")
    public String deleteById(String id, RedirectAttributes redirectAttributes) {

        try {
            solrJClientService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "操作成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/file/list";
    }
}
