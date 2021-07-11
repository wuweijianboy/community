package life.wwj.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.wwj.community.dto.PublishDTO;
import life.wwj.community.mapper.UserMapper;
import life.wwj.community.model.User;
import life.wwj.community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wwj
 * @create 2021-05-18-17:08
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PublishService publishService;
    List<PublishDTO> lists = null;
    private int size = 3;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(required = true, defaultValue = "1", name = "page") Integer page
    ) {

        Cookie[] cookies = request.getCookies();
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                    }
                    break;
                }
            }
            PageHelper.startPage(page, size);
            lists = publishService.list();
            PageInfo<PublishDTO> pageInfo = new PageInfo<>(lists);
            System.out.println(pageInfo);
            pageInfo.setPages(publishService.listCount(size));
            pageInfo.setNextPage(page + 1);
            pageInfo.setPrePage(page - 1);
            pageInfo.setPageNum(page);
            System.out.println("下一页页码：" + (page + 1));
            model.addAttribute("lists", lists);
            model.addAttribute("pageInfo", pageInfo);
        } catch (NullPointerException e) {
            return "index";
        }
        return "index";
    }

}