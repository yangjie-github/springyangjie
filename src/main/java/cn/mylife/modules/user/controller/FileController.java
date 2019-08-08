package cn.mylife.modules.user.controller;

import cn.mylife.commenservice.FileService;
import cn.mylife.commenservice.MsgJava;
import cn.mylife.commenservice.solrjutils.SolrJClientService;
import cn.mylife.modules.user.entity.SysFile;
import cn.mylife.modules.user.serviceimpl.DictionaryServiceImpl;
import cn.mylife.modules.user.serviceimpl.SysFileServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangjie
 * 2019/1/12 12:48
 */
@RequestMapping(value = "file")
@Controller
public class FileController {

    @Autowired
    private SysFileServiceImpl fileService;
    @Autowired
    private DictionaryServiceImpl dictionaryService;
    @Autowired
    private SolrJClientService solrJClientService;

    @RequestMapping(value = "list")
    public String findList(Model model,
                           @RequestParam(required = false) String content,
                           @RequestParam(required = false) Integer startPage) {

        model.addAttribute("fileTypes", dictionaryService.findList());

        if (StringUtils.isNotBlank(content)) {
            model.addAttribute("content", content);
        }

        if (Objects.nonNull(startPage)) {
            model.addAttribute("startPage", startPage);
        } else {
            model.addAttribute("startPage", 1);
        }

        try {
            Map<String, Map<String, List<String>>> search = solrJClientService.search(content, startPage);
            model.addAttribute("fileList", search);
            Long total = solrJClientService.getTotal(content, startPage);
            long page = total/16;
            long i = total%16;
            if (i > 0) {
                page = page + 1;
            }
            model.addAttribute("total", page);

        } catch (SolrServerException e) {

        }

        return "modules/file/fileList";
    }

    @RequestMapping(value = "add")
    @ResponseBody
    public MsgJava saveOrUpdate(MultipartFile files, SysFile myFile) {
        try {
            SysFile file = fileService.save(files, myFile);
            solrJClientService.addDocument(new File(file.getPath()), file);
            return MsgJava.success();
        } catch (Exception e) {
            return MsgJava.fail();
        }
    }

    @RequestMapping(value = "findNameById")
    @ResponseBody
    public SysFile findNameById(String id) {
        return fileService.findById(id);
    }

    @RequestMapping(value = "readFile")
    public void readFile(String id, HttpServletResponse response) throws IOException {

        SysFile byId = fileService.findById(id);

        final String path = byId.getPath();

        final String fileName = path.substring(path.lastIndexOf("/") + 1);

        FileService.readFile(response, fileName, path);
    }
}
