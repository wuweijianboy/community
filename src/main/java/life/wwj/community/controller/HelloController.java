package life.wwj.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wwj
 * @create 2021-05-18-17:08
 */
@Controller
public class HelloController {
    @GetMapping("/wwj")
    public String hello(@RequestParam(value = "name",required = false,defaultValue = "aa") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
