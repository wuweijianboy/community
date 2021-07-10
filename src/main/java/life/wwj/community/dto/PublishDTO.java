package life.wwj.community.dto;

import life.wwj.community.model.User;
import lombok.Data;

/**
 * @author wwj
 * @create 2021-07-05-22:17
 */
@Data
public class PublishDTO {
    private Integer id;
    private String title;
    private String descraption;
    private String lable;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer user;
    private Integer like_count;
    private Integer comment_count;
    private Integer view_count;
    private User users;
}
