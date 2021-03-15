package cqut.guobenqi.online_xdclass.service;

import cqut.guobenqi.online_xdclass.model.entity.Video;
import cqut.guobenqi.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    //查看所有视频列表
    List<Video> videoList();

    List<VideoBanner> listBanner();

    Video findDetailById(Integer videoId);
}
