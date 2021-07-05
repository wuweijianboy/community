package life.wwj.community.model;

import lombok.Data;

/**
 * @author wwj
 * @create 2021-07-03-17:58
 */
@Data
public class Publish {
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

}
