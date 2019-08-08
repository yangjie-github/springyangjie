package cn.mylife.modules.user.controller;

import cn.mylife.modules.user.entity.User;
import cn.mylife.modules.user.serviceimpl.UserServiceImpl;
import cn.mylife.utils.email.SendEmail;
import cn.mylife.utils.quartz.MyScheduler;
import cn.mylife.utils.randomcode.RandomNumCode;
import cn.mylife.utils.shiro.MD5;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author yangjie
 * @date 2018/11/17 13:15
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String EMAIL_CODE = "emailCode";
    private static final String LOGIN_FORM = "loginForm";

    @Autowired
    private UserServiceImpl userService;

    @ModelAttribute
    public void ahead(Model model, HttpServletRequest request) {
        final User user = (User)request.getSession().getAttribute("user");
        if (Objects.nonNull(user)) {
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getLoginName());
        }
    }

    /**
     * 首页
     */
    @RequestMapping("index")
    public String skipToIndex(){
        return LOGIN_FORM;
    }

    /**
     * 登录页面
     */
    @RequestMapping(value = "loginForm")
    public String loginForm() {
        return LOGIN_FORM;
    }

    /**
     * 注册页面
     */
    @RequestMapping(value = "registerForm")
    public String registerForm() {
        return "registerForm";
    }

    /**
     * 校验用户名
     */
    @RequestMapping(value = "checkLoginNameUnique")
    @ResponseBody
    public List<User> checkLoginNameUnique(String loginName) {
        return userService.findUserByLoginName(loginName);
    }

    /**
     * 校验邮箱
     */
    @RequestMapping(value = "checkEmailUnique")
    @ResponseBody
    public List<User> checkEmailUnique(String email) {
        return userService.findUserByEmail(email);
    }

    /**
     * 保存注册用户
     */
    @RequestMapping(value = "register")
    public String register(User user,
                           HttpServletRequest request,
                           String emailPassword,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", user);
        final String emailCode = (String)request.getSession().getAttribute(EMAIL_CODE);
        if (Objects.isNull(emailCode) || !emailCode.equals(emailPassword)) {
            redirectAttributes.addFlashAttribute("error", "邮箱验证码不正确或邮箱验证码已过期");
            return "redirect:/user/registerForm";
        }
        List<User> userByNameRegister = userService.findUserByLoginName(user.getLoginName());
        List<User> userByEmail = userService.findUserByEmail(user.getEmail());
        if (!userByNameRegister.isEmpty() || !userByEmail.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "用户名或邮箱已经存在");
            return "redirect:/user/registerForm";
        }
        String pwd = MD5.md5(user);
        user.setPassWord(pwd);
        user.setDelFlag(false);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        userService.save(user);
        redirectAttributes.addFlashAttribute("message", "注册成功，请登录！");
        return "redirect:/user/loginForm";
    }

    /**
     * 发送邮箱验证码
     */
    @RequestMapping(value = "sendEmailCode")
    @ResponseBody
    public Boolean sendEmailCode(String emailTo, HttpServletRequest request) {
        String randomNumCode;
        try {
            randomNumCode = RandomNumCode.getRandomNumCode(6);
            final Object attribute = request.getSession().getAttribute(EMAIL_CODE);
            if (Objects.nonNull(attribute)) {
                request.getSession().removeAttribute(EMAIL_CODE);
            }
            request.getSession().setAttribute(EMAIL_CODE, randomNumCode);
            MyScheduler.myScheduler(request);
            SendEmail.SendEmail(emailTo, randomNumCode);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 登录认证
     */
    @RequestMapping(value = "login")
    public String login(@RequestParam("loginName") String username,
                        @RequestParam("loginPassword") String password,
                        HttpServletRequest request,
                        Model model){
        User user = userService.findOneUserByLoginName(username);
        model.addAttribute("user", user);
        if (Objects.isNull(user)) {
            model.addAttribute("error","用户名不存在");
            return LOGIN_FORM;
        }
        final Date date = new Date();
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            /*将用户名和密码封装为UsernamePasswordToken对象*/
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //前端页面有个选择的框，用户选择是否记住我
            token.setRememberMe(true);
            try {
                //执行认证操作.会执行MyRealm里面的doGetAuthenticationInfo方法
                currentUser.login(token);
                model.addAttribute("user",user);
                request.getSession().setAttribute("user", user);
            }catch (AuthenticationException ae) {
                LOGGER.error(String.format("登陆失败: %tF%n %tT%n, 失败信息: %s", date, date, ae.getMessage()));
                model.addAttribute("error","密码错误");
                return LOGIN_FORM;
            }
        }
        model.addAttribute("userName", username);
        return "index";
    }

    /**
     * 重置密码邮箱验证
     */
    @RequestMapping(value = "resetPassWordAuthenForm")
    public String resetPassWordAuthenForm() {
        return "resetPassWordAuthenForm";
    }

    /**
     * 提交重置信息
     */
    @RequestMapping(value = "resetPassWordAuthen")
    public String resetPassWordAuthen(User user,
                                      String emailPassword,
                                      HttpServletRequest request,
                                      Model model,
                                      RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("user", user);
        User u = userService.findOneUserByLoginName(user.getLoginName());
        if (!u.getEmail().equals(user.getEmail())) {
            redirectAttributes.addFlashAttribute("error", "用户名与邮箱不符");
            return "redirect:/user/resetPassWordAuthenForm";
        }
        final Object attribute = request.getSession().getAttribute(EMAIL_CODE);
        if (!emailPassword.equals((String)attribute)) {
            redirectAttributes.addFlashAttribute("error", "邮箱验证码不正确或邮箱验证码已过期");
            return "redirect:/user/resetPassWordAuthenForm";
        }
        model.addAttribute("user", u);
        return "setPassWordForm";
    }

    /**
     * 提交重置信息
     */
    @RequestMapping(value = "setPassWord")
    public String setPassWord(User user) {
        User oldUser = userService.findUserById(user.getId());
        oldUser.setPassWord(user.getPassWord());
        user.setPassWord(MD5.md5(oldUser));
        userService.update(user);
        return "redirect:/user/loginForm";
    }

}
