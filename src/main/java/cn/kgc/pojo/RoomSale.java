package cn.kgc.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class RoomSale {
    private Integer id;

    private String roomNum;

    private String customerName;

    private Date startDate;

    private Date endDate;

    private Integer days;

    private Double roomPrice;

    private Double rentPrice;

    private Double otherPrice;

    private Double salePrice;

    private Double discountPrice;

    //查询的条件:时间范围
    private Date minDate;  //时间上限

    private Date maxDate;  //时间下限


}