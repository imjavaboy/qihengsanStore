package cqut.guobenqi.online_xdclass.controller;

import cqut.guobenqi.online_xdclass.model.entity.VideoOrder;
import cqut.guobenqi.online_xdclass.model.request.VideoOrderRequest;
import cqut.guobenqi.online_xdclass.service.VideoOrderService;
import cqut.guobenqi.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * */
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        System.out.println(userId);
        System.out.println(videoOrderRequest.getVideoId());

        int rows = videoOrderService.save(userId,videoOrderRequest.getVideoId());
        return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess("下单成功");
    }
    /**
     * 我的订单接口
     * */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");

        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrderList);
    }
}
