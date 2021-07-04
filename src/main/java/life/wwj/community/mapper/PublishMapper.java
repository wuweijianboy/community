package life.wwj.community.mapper;

import life.wwj.community.model.Publish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wwj
 * @create 2021-07-04-10:10
 */
@Mapper
public interface PublishMapper {
    @Insert("insert into publish (title,descraption,lable,gmt_create,gmt_modified,user) values(#{title},#{descraption},#{lable},#{gmt_create},#{gmt_modified},#{user})")
    void addPublish(Publish publish);
}
