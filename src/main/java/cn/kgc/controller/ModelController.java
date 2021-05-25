package cn.kgc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("model")
public class ModelController {
    //跳转到项目首页
    @RequestMapping("toHome")
    public String showHomeUI(){
        return "home";
    }
    //跳转到入住信息查询页面
    @RequestMapping("toShowInRoomInfo")
    public String toShowInRoomInfo(){
        //这里必须添加文件夹路径，因为配置的路径不到这一层
        return "InRoomInfo/ShowInRoomInfo";
    }
    //去到入住信息添加页面
    @RequestMapping("/toSaveInRoomInfo")
    public String toSaveInRoomInfo(){
        return "InRoomInfo/SaveInRoomInfo";
    }

    //去到订单显示界面
    @RequestMapping("toShowOrders")
    public String toShowOrders(){
        return "Orders/ShowOrders";
    }

    //去到消费记录页面
    @RequestMapping("/toShowRoomSale")
    public String toShowRoomSale(){
        return "RoomSale/ShowRoomSale";
    }

    //去到会员查询页面
    @RequestMapping("/toShowVip")
    public String toShowVip(){
        return "Vip/ShowVip";
    }

    //去到会员添加页面
    @RequestMapping("/toSaveVip")
    public String toSaveVip(){
        return "Vip/saveVip";
    }

    //去到客房显示页面
    @RequestMapping("/toShowRooms")
    public String toShowRooms(){
        return "Rooms/ShowRooms";
    }
}
