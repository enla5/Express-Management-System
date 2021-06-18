package com.yg.cn.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 快递员列表
 */
@Entity
@Table(name = "t_courier")
public class Tcourier {
    /**
     * 快递员编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cou_id")
    private  Integer couId;

    /**
     *快递员姓名
     */
    @Column(name = "cou_name")
    private String couName;

    /**
     * 快递员电话
     */
    @Column(name = "cou_tel")
    private String couTel;

    /**
     * 快递公司
     */
    @Column(name = "cou_company")
    private String couCompany;

    /**
     * 投递数量
     */
    @Column(name = "re_count")
    private Integer reCount;

    public Integer getCouId() {
        return couId;
    }

    public void setCouId(Integer couId) {
        this.couId = couId;
    }

    public String getCouName() {
        return couName;
    }

    public void setCouName(String couName) {
        this.couName = couName;
    }

    public String getCouTel() {
        return couTel;
    }

    public void setCouTel(String couTel) {
        this.couTel = couTel;
    }

    public String getCouCompany() {
        return couCompany;
    }

    public void setCouCompany(String couCompany) {
        this.couCompany = couCompany;
    }

    public Integer getReCount() {
        return reCount;
    }

    public void setReCount(Integer reCount) {
        this.reCount = reCount;
    }

    @Override
    public String toString() {
        return "Tcourier{" +
                "couId=" + couId +
                ", couName='" + couName + '\'' +
                ", couTel='" + couTel + '\'' +
                ", couCompany='" + couCompany + '\'' +
                ", reCount='" + reCount + '\'' +
                '}';
    }
}
