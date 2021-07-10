package life.wwj.community.model;

import lombok.Data;

/**
 * @author wwj
 * @create 2021-06-30-11:54
 */
@Data
public class User {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;
}
