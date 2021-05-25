package cn.kgc.service.Impl;

import cn.kgc.dao.InRoomInfoMapper;
import cn.kgc.dao.OrdersMapper;
import cn.kgc.dao.RoomsMapper;
import cn.kgc.pojo.InRoomInfo;
import cn.kgc.pojo.Orders;
import cn.kgc.pojo.Rooms;
import cn.kgc.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private InRoomInfoMapper inRoomInfoMapper;

    @Autowired
    private RoomsMapper roomsMapper;

    //订单添加的同时要完成如下操作
    //1.生成订单数据(以订单的添加为主)
    //2.入住信息是否退房的状态的修改（未退房-->已退房）
    //3.客房的状态修改（已入住-->打扫）
    //要不全部成功，要不全部失败，所以必须控制在一个业务层事物中
    //重写父类的添加的方法
    @Override
    public String save(Orders orders) throws Exception {
        //1.生成订单数据(以订单的添加为主)
        int insOrdersCount = ordersMapper.insert(orders);//此时的baseMapper对象中的泛型T为Orders
        //2.入住信息是否退房的状态的修改（未退房-->已退房）
        //2.1新建要被修改的入住信息对象
        InRoomInfo inRoomInfo = new InRoomInfo();
        //2.2往要被修改的入住信息中设置值
        inRoomInfo.setId(orders.getIriId());
        inRoomInfo.setOutRoomStatus("1");
        //2.3执行入住信息的修改
        int updInRoomInfoCount = inRoomInfoMapper.updateByPrimaryKeySelective(inRoomInfo);
        //自定义异常，对事物进行测试
        //   int i = 1/0;  //除数为0的异常，代码到此处停止下来就不会继续向下执行
        //3.客房的状态修改（已入住-->打扫） 1---->2
        //3.1根据入住信息id查询出入住信息
        InRoomInfo selInRoomInfo = inRoomInfoMapper.selectByPrimaryKey(orders.getIriId());
        //3.2新建客房对象
        Rooms rooms = new Rooms();
        //3.3往要被修改的客房信息中设置值
        rooms.setId(selInRoomInfo.getRoomId());
        rooms.setRoomStatus("2");
        int updRoomsCount = roomsMapper.updateByPrimaryKeySelective(rooms);
        if(insOrdersCount>0&&updInRoomInfoCount>0&&updRoomsCount>0){
            return "success";
        }else {
            return "fail";
        }
    }
}

