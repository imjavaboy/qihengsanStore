package cqut.guobenqi.online_xdclass.service.impl;

import cqut.guobenqi.online_xdclass.exception.XDException;
import cqut.guobenqi.online_xdclass.mapper.EpisodeMapper;
import cqut.guobenqi.online_xdclass.mapper.PlayRecordMapper;
import cqut.guobenqi.online_xdclass.mapper.VideoMapper;
import cqut.guobenqi.online_xdclass.mapper.VideoOrderMapper;
import cqut.guobenqi.online_xdclass.model.entity.Episode;
import cqut.guobenqi.online_xdclass.model.entity.PlayRecord;
import cqut.guobenqi.online_xdclass.model.entity.Video;
import cqut.guobenqi.online_xdclass.model.entity.VideoOrder;
import cqut.guobenqi.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;
    /**
     * 下单操作
     **/
    @Override
    @Transactional
    public int save(int userId, int videoId) {
        //判断是否购买
        VideoOrder videoOrder = videoOrderMapper.findBuUserIdAndVideoIdAndState(userId,videoId,1);

        if (videoOrder != null){
            return 0;
        }

        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOder = new VideoOrder();
        newVideoOder.setCreateTime(new Date());
        newVideoOder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOder.setState(1);
        newVideoOder.setTotalFee(video.getPrice());
        newVideoOder.setUserId(userId);
        newVideoOder.setVideoId(videoId);
        newVideoOder.setVideoImg(video.getCoverImg());
        newVideoOder.setVideoTitle(video.getTitle());

        int rows =  videoOrderMapper.saveOrder( newVideoOder);

        if (rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);

            if(episode == null){
                throw new XDException(-1,"视频没有集信息，请检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);

            playRecordMapper.saveRecord(playRecord);



        }
        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        List<VideoOrder> orderList = videoOrderMapper.listOrderByUserId(userId);

        return orderList;
    }
}
