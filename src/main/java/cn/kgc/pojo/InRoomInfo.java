package cn.kgc.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class InRoomInfo {
    private Integer id;

    private String customerName;

    private String gender;

    private String isVip;

    private String idcard;

    private String phone;

    private Float money;

    private Date createDate;

    private Integer roomId;

    private String status;

    private String outRoomStatus;

    private Rooms rooms;
}