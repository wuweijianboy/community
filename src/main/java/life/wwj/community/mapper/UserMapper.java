package life.wwj.community.mapper;

import life.wwj.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wwj
 * @create 2021-06-30-11:57
 */
@Mapper
public interface UserMapper {
    @Insert("insert into c_user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insertUser(User user);
    @Select("select * from c_user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from c_user where id = #{user_id}")
    User selectById(@Param("user_id") Integer user_id);
}
