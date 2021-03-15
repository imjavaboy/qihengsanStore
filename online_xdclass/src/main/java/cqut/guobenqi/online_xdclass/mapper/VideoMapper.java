package cqut.guobenqi.online_xdclass.mapper;

import cqut.guobenqi.online_xdclass.model.entity.Video;
import cqut.guobenqi.online_xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoMapper {


    //查看所有视频列表
    List<Video> videoList();

    //查询banner信息
    List<VideoBanner> listBanner();

    /**查询视频详情*/
   Video findDetailById( @Param("video_id") int videoId);

   /** 查询视频根据id*/
   Video findById(@Param("video_id") int videoId);
}
