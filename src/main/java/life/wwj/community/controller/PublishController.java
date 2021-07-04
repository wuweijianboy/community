package life.wwj.community.controller;

import life.wwj.community.mapper.PublishMapper;
import life.wwj.community.mapper.UserMapper;
import life.wwj.community.model.Publish;
import life.wwj.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wwj
 * @create 2021-07-03-13:00
 */
@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishMapper publishMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "descraption") String descraption,
            @RequestParam(value = "lable") String lable,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("descraption", descraption);
        model.addAttribute("lable", lable);
        Cookie[] cookies = request.getCookies();
        User user = null;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                    }
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("找不到cookie！");
        }
        if (user == null) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        if (title.equals("")) {
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if (descraption.equals("")) {
            model.addAttribute("error", "问题不能为空！");
            return "publish";
        }
        Publish publish = new Publish();
        publish.setUser(user.getId());
        publish.setTitle(title);
        publish.setDescraption(descraption);
        publish.setLable(lable);
        publish.setGmt_create(System.currentTimeMillis());
        publish.setGmt_modified(publish.getGmt_create());
        publishMapper.addPublish(publish);
        return "redirect:/";
    }
}
