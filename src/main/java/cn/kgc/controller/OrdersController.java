package cn.kgc.controller;

import cn.kgc.pojo.Orders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("orders")
public class OrdersController extends BaseController<Orders> {
}
