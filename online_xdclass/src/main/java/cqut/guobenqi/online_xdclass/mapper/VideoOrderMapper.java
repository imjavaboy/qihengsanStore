package cqut.guobenqi.online_xdclass.mapper;

import cqut.guobenqi.online_xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoOrderMapper {
    /**
     * 查询用户是否购买过此商品
     */
    VideoOrder findBuUserIdAndVideoIdAndState(@Param("user_id") int userId, @Param("video_id") int videoId, @Param("state") int state);
    /**
     * 下单
     * */
    int saveOrder(VideoOrder videoOrder);

    /**
     * 我的订单
     * */
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}

