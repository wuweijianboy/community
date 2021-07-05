package life.wwj.community.dto;

import lombok.Data;

/**
 * @author wwj
 * @create 2021-05-20-16:50
 */
@Data
public class GitHubUserDTO {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;
}
