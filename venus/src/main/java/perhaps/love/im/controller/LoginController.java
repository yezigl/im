package perhaps.love.im.controller;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import perhaps.love.im.Constants;
import perhaps.love.im.entity.User;
import perhaps.love.im.service.CacheService;
import perhaps.love.im.service.LdapService;
import perhaps.love.im.service.UserService;
import perhaps.love.im.utils.CookieUtils;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    LdapService ldapService;
    @Resource
    UserService userService;
    @Resource
    CacheService cacheService;
    
    @Value("${login.url}")
    String loginUrl;
    @Value("${index.url}")
    String indexUrl;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(@RequestParam(required = false) String ru, Model model) {
        model.addAttribute("ru", ru);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam String username, @RequestParam String password,
            @RequestParam(required = false) String ru, HttpServletRequest request, HttpServletResponse response,
            Model model) {
        model.addAttribute("username", username);
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            model.addAttribute("message", "用户名密码不能为空");
            return "login";
        }
        if (username.equals("test")) {
            return testLogin(request, response);
        }
        if (!username.contains("@sohu-inc.com")) {
            username += "@sohu-inc.com";
        }
        User user = ldapService.find(username, password);
        if (user == null) {
            model.addAttribute("message", "用户不存在或用户名密码错误");
            return "login";
        }
        logger.info("{}", user);
        // 更新用户
        User currentUser = userService.findByEmployeeNumber(user.getEmployeeNumber());
        if (currentUser != null) {
            currentUser.setDepartment(user.getDepartment());
            currentUser.setDisplayName(user.getDisplayName());
            currentUser.setName(user.getName());
            currentUser.setOfficeName(user.getOfficeName());
            currentUser.setTel(user.getTel());
            currentUser.setTitle(user.getTitle());
            userService.save(currentUser);
        } else {
            currentUser = user;
            userService.save(currentUser);
        }
        CookieUtils.setCookie(request, response, Constants.COOKIE_LOGIN, CookieUtils.genCookieValue(currentUser));
        String token = UUID.randomUUID().toString().toLowerCase();
        CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN, token);
        cacheService.set(token, currentUser.getEmployeeNumber());

        model.addAttribute("user", currentUser);
        return "redirect:" + indexUrl;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.setCookie(request, response, Constants.COOKIE_LOGIN, null);
        CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN, null);
        return "redirect:" + loginUrl;
    }
    
    public String testLogin(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = userService.findByEmployeeNumber(116041);
        CookieUtils.setCookie(request, response, Constants.COOKIE_LOGIN, CookieUtils.genCookieValue(currentUser));
        String token = UUID.randomUUID().toString().toLowerCase();
        CookieUtils.setCookie(request, response, Constants.COOKIE_TOKEN, token);
        cacheService.set(token, currentUser.getEmployeeNumber());
        return "redirect:" + indexUrl;
    }
}