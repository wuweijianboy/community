package life.wwj.community.mapper;

import life.wwj.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wwj
 * @create 2021-06-30-11:57
 */
@Mapper
public interface UserMapper {
    @Insert("insert into c_user (account_id,name,token,gmt_create,gmt_modified) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insertUser(User user);
}
