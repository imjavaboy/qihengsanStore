package cqut.guobenqi.online_xdclass.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * CREATE TABLE `chapter` (
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `video_id` int(11) DEFAULT NULL COMMENT '视频主键',
 *   `title` varchar(128) DEFAULT NULL COMMENT '章节名称',
 *   `ordered` int(11) DEFAULT NULL COMMENT '章节顺序',
 *   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=716 DEFAULT CHARSET=utf8;*/

public class Chapter {
    /* <id column="chapter_id" jdbcType="INTEGER" property="id"/>
            <result column="chapter_title" jdbcType="VARCHAR" property="title"/>
            <result column="ordered" jdbcType="INTEGER" property="ordered"/>
            <result column="chapter_create_time" jdbcType="TIMESTAMP" property="createTime"/>*/
    private Integer id;

    @JsonProperty("video_id")
    private Integer videoId;

    private String title;

    private Integer ordered;

    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @JsonProperty("episode_list")
    private List<Episode> episodeList;

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", title='" + title + '\'' +
                ", ordered=" + ordered +
                ", createTime=" + createTime +
                ", episodeList=" + episodeList +
                '}';
    }
}
