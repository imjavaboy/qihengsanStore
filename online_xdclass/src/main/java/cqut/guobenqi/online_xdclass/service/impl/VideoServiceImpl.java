package cqut.guobenqi.online_xdclass.service.impl;

import cqut.guobenqi.online_xdclass.config.CacheKeyManager;
import cqut.guobenqi.online_xdclass.model.entity.Video;
import cqut.guobenqi.online_xdclass.model.entity.VideoBanner;
import cqut.guobenqi.online_xdclass.mapper.VideoMapper;
import cqut.guobenqi.online_xdclass.service.VideoService;
import cqut.guobenqi.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    @Override
    public List<Video> videoList() {

        try{
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST,()->{

                List<Video> videoList = videoMapper.videoList();
                return videoList;
            });

            if (cacheObj instanceof  List){
                List<Video> videoList = (  List<Video>)cacheObj;
                return videoList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<VideoBanner> listBanner() {

        try{
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY,()->{
               List<VideoBanner> bannerList = videoMapper.listBanner();
                System.out.println("从数据库种找");
               return bannerList;
            });
            if (cacheObj instanceof  List){
                List<VideoBanner> bannerList = (  List<VideoBanner>)cacheObj;
                return bannerList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video findDetailById(Integer videoId) {
//        return videoMapper.findDetailById(videoId);


        String videoCacheKey =  String.format(CacheKeyManager.VIDEO_DETAIL,videoId);
        try{
            Object cacheObj = baseCache.getTenMinuteCache().get(videoCacheKey,()->{

               Video video = videoMapper.findDetailById(videoId);
                return video;
            });

            if (cacheObj instanceof  Video){
               Video video = (Video)cacheObj;
                return video;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
