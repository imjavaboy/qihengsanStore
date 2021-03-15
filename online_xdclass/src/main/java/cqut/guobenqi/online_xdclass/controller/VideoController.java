package cqut.guobenqi.online_xdclass.controller;


import cqut.guobenqi.online_xdclass.model.entity.Video;
import cqut.guobenqi.online_xdclass.model.entity.VideoBanner;
import cqut.guobenqi.online_xdclass.service.VideoService;
import cqut.guobenqi.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    //轮播图列表
    @GetMapping("/list_banner")
    public JsonData indexBanner(){

        List<VideoBanner> bannerList  = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }


    /**视频列表*/
    @RequestMapping("/list")
    public JsonData videoList(){
        List<Video> videoList = videoService.videoList();
        return JsonData.buildSuccess(videoList);
    }

    /** 根据id查询视频信息*/
    @GetMapping("/find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true)int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }



}
