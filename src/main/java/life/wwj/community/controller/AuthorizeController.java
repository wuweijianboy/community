package life.wwj.community.controller;

import life.wwj.community.dto.AccessTokenDTO;
import life.wwj.community.dto.GitHubUserDTO;
import life.wwj.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author wwj
 * @create 2021-05-20-14:40
 */
@Controller
public class AuthorizeController {
    @Autowired
    GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @GetMapping("/callback")
    public String callback(@RequestParam(value = "code")String code,
                           @RequestParam(value = "state")String state){
        System.out.println("callback is running");
        System.out.println(client_secret);
        System.out.println(client_id);
        System.out.println(redirect_uri);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String token = githubProvider.getaccessToken(accessTokenDTO);
        GitHubUserDTO user = githubProvider.getUser(token);
        System.out.println(user.getName());
        return "index";
    }
    @ResponseBody
    @GetMapping("/call")
    public String call(){
        return "call is runing";
    }
}
