package life.wwj.community.service;

import life.wwj.community.dto.PublishDTO;
import life.wwj.community.mapper.PublishMapper;
import life.wwj.community.mapper.UserMapper;
import life.wwj.community.model.Publish;
import life.wwj.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wwj
 * @create 2021-07-05-22:19
 */
@Service
public class PublishService {
    @Autowired
    private PublishMapper publishMapper;
    @Autowired
    private UserMapper userMapper;
    private List<PublishDTO> publishDTOList = new ArrayList<>();


    public List<PublishDTO> list() {
        List<Publish> publishes = publishMapper.selectAll();
        publishDTOList.clear();
        for (Publish publish : publishes) {
            PublishDTO publishDTO = new PublishDTO();
            Integer user_id = publish.getUser();
            User user = userMapper.selectById(user_id);
            publishDTO.setUsers(user);
            BeanUtils.copyProperties(publish, publishDTO);
            publishDTOList.add(publishDTO);
        }
        return publishDTOList;
    }

    public int listCount(int size) {
        int count = publishMapper.count();
        if (count % size == 0) {
            return count / size;
        } else {
            return count / size + 1;
        }

    }
}
