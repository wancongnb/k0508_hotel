package cn.kgc.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Orders {
    private Integer id;

    private String orderNum;

    private Double orderMoney;

    private String remark;

    private String orderStatus;

    private Integer iriId;

    private Date createDate;

    private String flag;

    private String orderOther;

    private String orderPrice;

    private InRoomInfo inRoomInfo;

    //查询的条件:时间范围
    private Date minDate;  //时间上限

    private Date maxDate;  //时间下限


}