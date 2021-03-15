package cqut.guobenqi.online_xdclass.service;

import cqut.guobenqi.online_xdclass.model.entity.User;
import cqut.guobenqi.online_xdclass.utils.JsonData;

import java.util.Map;

public interface UserService {
    int save(Map<String,String> userInfo);

    String findByPhoneAndPwd(String phone, String pwd);

    User findByUserId(Integer userId);
}
