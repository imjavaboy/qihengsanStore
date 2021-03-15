package cqut.guobenqi.online_xdclass.mapper;

import cqut.guobenqi.online_xdclass.model.entity.PlayRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlayRecordMapper {

    int saveRecord(PlayRecord playRecord);
}
