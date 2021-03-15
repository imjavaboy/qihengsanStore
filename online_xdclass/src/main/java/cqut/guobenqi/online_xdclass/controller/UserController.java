package cqut.guobenqi.online_xdclass.controller;

import cqut.guobenqi.online_xdclass.model.entity.User;
import cqut.guobenqi.online_xdclass.model.request.LoginRequest;
import cqut.guobenqi.online_xdclass.service.UserService;
import cqut.guobenqi.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pri/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**注册接口
     * */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){

        int rows = userService.save(userInfo);


        return rows == 1 ? JsonData.buildSuccess("注册成功") :JsonData.buildError("注册失败，请重试");
    }

    /**
     * 登录接口
     * */

    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
       String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());
       return token == null ? JsonData.buildError("登陆失败,请重新输入用户名和密码") : JsonData.buildSuccess(token);
    }

    /**
     * 根据用户id查询信息
     * */
    @GetMapping("find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");

        if (userId == null){
            return JsonData.buildError("查询失败");
        }
        User user = userService.findByUserId(userId);
        return JsonData.buildSuccess(user);
    }

}
