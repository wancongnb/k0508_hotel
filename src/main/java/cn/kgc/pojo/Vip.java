package cn.kgc.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class Vip {
    private Integer id;

    private String vipNum;

    private String customerName;

    private Float vipRate;

    private String idcard;

    private String phone;

    private Date createDate;

    private String gender;


}