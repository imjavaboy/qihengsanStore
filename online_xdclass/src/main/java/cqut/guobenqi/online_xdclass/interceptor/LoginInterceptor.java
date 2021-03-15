package cqut.guobenqi.online_xdclass.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import cqut.guobenqi.online_xdclass.utils.JWTUtils;
import cqut.guobenqi.online_xdclass.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    /**进入到controllerz之前的方法
     * */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try{
            String accesToken = request.getHeader("token");
            if (accesToken == null){
                accesToken = request.getParameter("token");

            }
            if (StringUtils.isNotBlank(accesToken)){
                Claims claims = JWTUtils.checkJWT(accesToken);
                if (claims == null){
                    //登陆失败
                    sendJsonMessage(response, JsonData.buildError("登陆过期，重新登陆"));
                    return false;
                }
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");
                request.setAttribute("user_id",id);
                request.setAttribute("name",name);
                return true;
            }
        }catch (Exception e){

        }
        sendJsonMessage(response, JsonData.buildError("登陆过期，重新登陆"));
        //登陆失败
        return false;


    }
    /**登陆不成功返回json数据*/
    private static void sendJsonMessage(HttpServletResponse response, Object obj) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsBytes(obj));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
