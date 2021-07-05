package life.wwj.community.dto;

import lombok.Data;

/**
 * @author wwj
 * @create 2021-05-20-15:19
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
