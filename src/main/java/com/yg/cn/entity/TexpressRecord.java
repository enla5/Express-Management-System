package com.yg.cn.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 快递存储记录表
 * @author enla5
 */
@Entity
@Table(name = "t_express_record")
public class TexpressRecord {
    /**
     * 快递存取记录表的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "re_id")
    private  Integer reId;

    /**
     * 日期
     */
    @Column(name = "re_date")
    private String reDate;

    /**
     * 类型
     * 1.存入
     * 2.取出
     */
    @Column(name = "re_state")
    private String reState;

    /**
     * 快递柜位置
     */
    @Column(name = "re_position")
    private String rePosition;

    /**
     * 快递仓编号
     */
    @Column(name = "re_box_number")
    private String reBoxNumber;

    /**
     * 操作人
     */
    @Column(name = "re_people")
    private String rePeople;

    /**
     * 操作人手机号
     */
    @Column(name = "re_people_phone")
    private String rePeoplePhone;

    /**
     * 多个存取记录对应一个快递柜
     */
    @ManyToOne(targetEntity = TexpressCabinet.class)
    @JoinColumn(name = "ex_id",referencedColumnName = "ex_id")
    private TexpressCabinet texpressCabinet;

    public Integer getReId() {
        return reId;
    }

    public void setReId(Integer reId) {
        this.reId = reId;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }

    public String getReState() {
        return reState;
    }

    public void setReState(String reState) {
        this.reState = reState;
    }

    public String getRePosition() {
        return rePosition;
    }

    public void setRePosition(String rePosition) {
        this.rePosition = rePosition;
    }

    public String getReBoxNumber() {
        return reBoxNumber;
    }

    public void setReBoxNumber(String reBoxNumber) {
        this.reBoxNumber = reBoxNumber;
    }

    public String getRePeople() {
        return rePeople;
    }

    public void setRePeople(String rePeople) {
        this.rePeople = rePeople;
    }

    public String getRePeoplePhone() {
        return rePeoplePhone;
    }

    public void setRePeoplePhone(String rePeoplePhone) {
        this.rePeoplePhone = rePeoplePhone;
    }

    public TexpressCabinet getTexpressCabinet() {
        return texpressCabinet;
    }

    public void setTexpressCabinet(TexpressCabinet texpressCabinet) {
        this.texpressCabinet = texpressCabinet;
    }

    @Override
    public String toString() {
        return "TexpressRecord{" +
                "reId=" + reId +
                ", reDate=" + reDate +
                ", reState='" + reState + '\'' +
                ", rePosition='" + rePosition + '\'' +
                ", reBoxNumber='" + reBoxNumber + '\'' +
                ", rePeople='" + rePeople + '\'' +
                ", rePeoplePhone='" + rePeoplePhone + '\'' +
                ", texpressRecord=" + texpressCabinet +
                '}';
    }
}
