package life.wwj.community.provider;

import com.alibaba.fastjson.JSON;
import life.wwj.community.dto.AccessTokenDTO;
import life.wwj.community.dto.GitHubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wwj
 * @create 2021-05-20-15:21
 */
@Component
public class GithubProvider {
    public String getaccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responsebody = response.body().string();
            String token = responsebody.split("&")[0].split("=")[1];
            System.out.println("*************token"+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("false");
        return null;
    }

    public GitHubUserDTO getUser(String token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + token)
                .header("Authorization","token "+token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println("********************user" + string);
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(string, GitHubUserDTO.class);
            return gitHubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
