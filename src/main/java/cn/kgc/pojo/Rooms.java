package cn.kgc.pojo;

import lombok.Data;

@Data
public class Rooms {
    private Integer id;

    private String roomPic;

    private String roomNum;

    private String roomStatus;

    private Integer roomTypeId;

    private Integer flag;

    private RoomType roomType;
}