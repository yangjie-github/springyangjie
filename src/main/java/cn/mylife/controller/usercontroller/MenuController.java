//package cn.mylife.controller.usercontroller;
//
//import cn.mylife.entity.YjMenu;
//import cn.mylife.entity.YjRole;
//import cn.mylife.entity.YjUser;
//import cn.mylife.service.YjRoleServiceIn;
//import cn.mylife.service.YjUserServiceIn;
//import cn.mylife.serviceimpl.YjMenuServiceImpl;
//import cn.mylife.serviceimpl.YjRoleServiceInImpl;
//import cn.mylife.serviceimpl.YjUserServiceImpl;
//import org.apache.shiro.web.session.HttpServletSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author yangjie
// * 2018/7/9 21:28
// */
//@Controller
//@RequestMapping(value = "/menuController")
//public class MenuController {
//
//    @Autowired
//    private YjUserServiceImpl yjUserService;
//    @Autowired
//    private YjRoleServiceInImpl yjRoleService;
//    @Autowired
//    private YjMenuServiceImpl yjMenuService;
//
//    @RequestMapping(value = "getMenu")
//    @ResponseBody
//    public List<YjMenu> doGetPrivilegeTree(HttpServletRequest request) {
//
//        YjUser currentLoginUser = yjUserService.findCurrentLoginUser(request);
//
//        List<YjRole> rolesByUser = yjRoleService.findRolesByUser(currentLoginUser);
//
//        List<YjMenu> allMenu = yjMenuService.findAllMenu();
//
//        Map<Integer, YjMenu> menuList = new HashMap<>();
//
//        ArrayList<YjMenu> yjMenus = new ArrayList<>();
//
//        for (YjMenu menu : allMenu) {
//            menuList.put(menu.getId(), menu);
//        }
//
//        for (YjMenu menu : allMenu) {
//            if (menu.getPid() == 0) {
//                menu.setIsParent("true");
//                yjMenus.add(menu);
//            } else {
//                YjMenu parentMenu = menuList.get(menu.getPid());
//                parentMenu.getChildren().add(menu);
//            }
//        }
//
//        return yjMenus;
//    }
//
//
//}
